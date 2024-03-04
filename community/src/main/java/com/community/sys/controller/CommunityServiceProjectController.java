package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.CommunityServiceProject;
import com.community.sys.service.ICommunityServiceProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社区服务项目Controller
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Controller
@RequestMapping("/temp/project")
public class CommunityServiceProjectController extends BaseController
{
    private String prefix = "temp/project";

    @Autowired
    private ICommunityServiceProjectService communityServiceProjectService;

    @RequiresPermissions("temp:project:view")
    @GetMapping()
    public String project()
    {
        return prefix + "/project";
    }

    /**
     * 查询社区服务项目列表
     */
    @RequiresPermissions("temp:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommunityServiceProject communityServiceProject)
    {
        startPage();
        List<CommunityServiceProject> list = communityServiceProjectService.selectCommunityServiceProjectList(communityServiceProject);
        return getDataTable(list);
    }

    /**
     * 导出社区服务项目列表
     */
    @RequiresPermissions("temp:project:export")
    @com.community.common.annotation.Log(title = "社区服务项目", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(CommunityServiceProject communityServiceProject)
    {
        List<CommunityServiceProject> list = communityServiceProjectService.selectCommunityServiceProjectList(communityServiceProject);
        com.community.common.utils.poi.ExcelUtil<CommunityServiceProject> util = new ExcelUtil<CommunityServiceProject>(CommunityServiceProject.class);
        return util.exportExcel(list, "社区服务项目数据");
    }

    /**
     * 新增社区服务项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存社区服务项目
     */
    @RequiresPermissions("temp:project:add")
    @com.community.common.annotation.Log(title = "社区服务项目", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(CommunityServiceProject communityServiceProject)
    {
        return toAjax(communityServiceProjectService.insertCommunityServiceProject(communityServiceProject));
    }

    /**
     * 修改社区服务项目
     */
    @RequiresPermissions("temp:project:edit")
    @GetMapping("/edit/{serviceProjectId}")
    public String edit(@PathVariable("serviceProjectId") Long serviceProjectId, ModelMap mmap)
    {
        CommunityServiceProject communityServiceProject = communityServiceProjectService.selectCommunityServiceProjectByServiceProjectId(serviceProjectId);
        mmap.put("communityServiceProject", communityServiceProject);
        return prefix + "/edit";
    }

    /**
     * 修改保存社区服务项目
     */
    @RequiresPermissions("temp:project:edit")
    @com.community.common.annotation.Log(title = "社区服务项目", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(CommunityServiceProject communityServiceProject)
    {
        return toAjax(communityServiceProjectService.updateCommunityServiceProject(communityServiceProject));
    }

    /**
     * 删除社区服务项目
     */
    @RequiresPermissions("temp:project:remove")
    @Log(title = "社区服务项目", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(communityServiceProjectService.deleteCommunityServiceProjectByServiceProjectIds(ids));
    }
}
