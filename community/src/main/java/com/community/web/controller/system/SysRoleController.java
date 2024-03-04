package com.community.web.controller.system;

import java.util.List;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.domain.Ztree;
import com.community.common.core.domain.entity.SysRole;
import com.community.common.core.domain.entity.SysUser;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.system.domain.SysUserRole;
import com.community.system.service.ISysDeptService;
import com.community.system.service.ISysRoleService;
import com.community.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.community.framework.shiro.util.AuthorizationUtils;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController
{
    private String prefix = "system/role";

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public com.community.common.core.page.TableDataInfo list(com.community.common.core.domain.entity.SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(com.community.common.core.domain.entity.SysRole role)
    {
        List<com.community.common.core.domain.entity.SysRole> list = roleService.selectRoleList(role);
        com.community.common.utils.poi.ExcelUtil<com.community.common.core.domain.entity.SysRole> util = new ExcelUtil<com.community.common.core.domain.entity.SysRole>(com.community.common.core.domain.entity.SysRole.class);
        return util.exportExcel(list, "角色数据");
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("system:role:add")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(@Validated com.community.common.core.domain.entity.SysRole role)
    {
        if (!roleService.checkRoleNameUnique(role))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!roleService.checkRoleKeyUnique(role))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改角色
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        roleService.checkRoleDataScope(roleId);
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(@Validated com.community.common.core.domain.entity.SysRole role)
    {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        if (!roleService.checkRoleNameUnique(role))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!roleService.checkRoleKeyUnique(role))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 角色分配数据权限
     */
    @GetMapping("/authDataScope/{roleId}")
    public String authDataScope(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/dataScope";
    }

    /**
     * 保存角色分配数据权限
     */
    @RequiresPermissions("system:role:edit")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult authDataScopeSave(com.community.common.core.domain.entity.SysRole role)
    {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        role.setUpdateBy(getLoginName());
        if (roleService.authDataScope(role) > 0)
        {
            setSysUser(userService.selectUserById(getUserId()));
            return success();
        }
        return error();
    }

    @RequiresPermissions("system:role:remove")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult remove(String ids)
    {
        return toAjax(roleService.deleteRoleByIds(ids));
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public boolean checkRoleNameUnique(com.community.common.core.domain.entity.SysRole role)
    {
        return roleService.checkRoleNameUnique(role);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public boolean checkRoleKeyUnique(com.community.common.core.domain.entity.SysRole role)
    {
        return roleService.checkRoleKeyUnique(role);
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

    /**
     * 角色状态修改
     */
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult changeStatus(com.community.common.core.domain.entity.SysRole role)
    {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        return toAjax(roleService.changeStatus(role));
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public com.community.common.core.page.TableDataInfo allocatedList(com.community.common.core.domain.entity.SysUser user)
    {
        startPage();
        List<com.community.common.core.domain.entity.SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     */
    @RequiresPermissions("system:role:edit")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult cancelAuthUser(SysUserRole userRole)
    {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权
     */
    @RequiresPermissions("system:role:edit")
    @com.community.common.annotation.Log(title = "角色管理", businessType = com.community.common.enums.BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult cancelAuthUserAll(Long roleId, String userIds)
    {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{roleId}")
    public String selectUser(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(com.community.common.core.domain.entity.SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(Long roleId, String userIds)
    {
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/deptTreeData")
    @ResponseBody
    public List<com.community.common.core.domain.Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(role);
        return ztrees;
    }
}