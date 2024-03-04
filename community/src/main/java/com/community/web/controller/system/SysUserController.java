package com.community.web.controller.system;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.domain.Ztree;
import com.community.common.core.domain.entity.SysDept;
import com.community.common.core.domain.entity.SysRole;
import com.community.common.core.domain.entity.SysUser;
import com.community.common.core.page.TableDataInfo;
import com.community.common.core.text.Convert;
import com.community.common.enums.BusinessType;
import com.community.common.utils.DateUtils;
import com.community.common.utils.ShiroUtils;
import com.community.common.utils.StringUtils;
import com.community.common.utils.poi.ExcelUtil;
import com.community.framework.shiro.service.SysPasswordService;
import com.community.framework.shiro.util.AuthorizationUtils;
import com.community.system.service.ISysDeptService;
import com.community.system.service.ISysPostService;
import com.community.system.service.ISysRoleService;
import com.community.system.service.ISysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;
    
    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(com.community.common.core.domain.entity.SysUser user)
    {
        startPage();
        List<com.community.common.core.domain.entity.SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(com.community.common.core.domain.entity.SysUser user)
    {
        List<com.community.common.core.domain.entity.SysUser> list = userService.selectUserList(user);
        com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysUser> util = new com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysUser>(com.community.common.core.domain.entity.SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysUser> util = new com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysUser>(com.community.common.core.domain.entity.SysUser.class);
        List<com.community.common.core.domain.entity.SysUser> userList = util.importExcel(file.getInputStream());
        String message = userService.importUser(userList, updateSupport, getLoginName());
        return com.community.common.core.domain.AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult importTemplate()
    {
        com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysUser> util = new ExcelUtil<com.community.common.core.domain.entity.SysUser>(com.community.common.core.domain.entity.SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(@Validated com.community.common.core.domain.entity.SysUser user)
    {
        if (!userService.checkLoginNameUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (com.community.common.utils.StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (com.community.common.utils.StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(com.community.common.utils.ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setPwdUpdateDate(DateUtils.getNowDate());
        user.setCreateBy(getLoginName());
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("system:user:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        userService.checkUserDataScope(userId);
        List<com.community.common.core.domain.entity.SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", com.community.common.core.domain.entity.SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 查询用户详细
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        userService.checkUserDataScope(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roleGroup", userService.selectUserRoleGroup(userId));
        mmap.put("postGroup", userService.selectUserPostGroup(userId));
        return prefix + "/view";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(@Validated com.community.common.core.domain.entity.SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        if (!userService.checkLoginNameUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (com.community.common.utils.StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @com.community.common.annotation.Log(title = "重置密码", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult resetPwdSave(com.community.common.core.domain.entity.SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        user.setSalt(com.community.common.utils.ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        if (userService.resetUserPwd(user) > 0)
        {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
            {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        com.community.common.core.domain.entity.SysUser user = userService.selectUserById(userId);
        // 获取用户所属的角色列表
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", com.community.common.core.domain.entity.SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:edit")
    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult insertAuthRole(Long userId, Long[] roleIds)
    {
        userService.checkUserDataScope(userId);
        userService.insertUserAuth(userId, roleIds);
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return success();
    }

    @RequiresPermissions("system:user:remove")
    @com.community.common.annotation.Log(title = "用户管理", businessType = com.community.common.enums.BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult remove(String ids)
    {
        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId()))
        {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public boolean checkLoginNameUnique(com.community.common.core.domain.entity.SysUser user)
    {
        return userService.checkLoginNameUnique(user);
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public boolean checkPhoneUnique(com.community.common.core.domain.entity.SysUser user)
    {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public boolean checkEmailUnique(com.community.common.core.domain.entity.SysUser user)
    {
        return userService.checkEmailUnique(user);
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user)
    {
        userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());
        return toAjax(userService.changeStatus(user));
    }

    /**
     * 加载部门列表树
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/deptTreeData")
    @ResponseBody
    public List<com.community.common.core.domain.Ztree> deptTreeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }

    /**
     * 选择部门树
     * 
     * @param deptId 部门ID
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/deptTree";
    }
}