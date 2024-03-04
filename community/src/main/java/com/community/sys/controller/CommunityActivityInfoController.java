package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.CommunityActivityInfo;
import com.community.sys.service.ICommunityActivityInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社区活动信息Controller
 * 
 * @author rcz
 * @date 2024-02-13
 */
@Controller
@RequestMapping("/temp/info")
public class CommunityActivityInfoController extends BaseController
{
    private String prefix = "temp/info";

    @Autowired
    private ICommunityActivityInfoService communityActivityInfoService;

    @RequiresPermissions("temp:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询社区活动信息列表
     */
    @RequiresPermissions("temp:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommunityActivityInfo communityActivityInfo)
    {
        startPage();
        List<CommunityActivityInfo> list = communityActivityInfoService.selectCommunityActivityInfoList(communityActivityInfo);
        return getDataTable(list);
    }

    /**
     * 导出社区活动信息列表
     */
    @RequiresPermissions("temp:info:export")
    @com.community.common.annotation.Log(title = "社区活动信息", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(CommunityActivityInfo communityActivityInfo)
    {
        List<CommunityActivityInfo> list = communityActivityInfoService.selectCommunityActivityInfoList(communityActivityInfo);
        com.community.common.utils.poi.ExcelUtil<CommunityActivityInfo> util = new ExcelUtil<CommunityActivityInfo>(CommunityActivityInfo.class);
        return util.exportExcel(list, "社区活动信息数据");
    }

    /**
     * 新增社区活动信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存社区活动信息
     */
    @RequiresPermissions("temp:info:add")
    @com.community.common.annotation.Log(title = "社区活动信息", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(CommunityActivityInfo communityActivityInfo)
    {
        return toAjax(communityActivityInfoService.insertCommunityActivityInfo(communityActivityInfo));
    }

    /**
     * 修改社区活动信息
     */
    @RequiresPermissions("temp:info:edit")
    @GetMapping("/edit/{activityId}")
    public String edit(@PathVariable("activityId") Long activityId, ModelMap mmap)
    {
        CommunityActivityInfo communityActivityInfo = communityActivityInfoService.selectCommunityActivityInfoByActivityId(activityId);
        mmap.put("communityActivityInfo", communityActivityInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存社区活动信息
     */
    @RequiresPermissions("temp:info:edit")
    @com.community.common.annotation.Log(title = "社区活动信息", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(CommunityActivityInfo communityActivityInfo)
    {
        return toAjax(communityActivityInfoService.updateCommunityActivityInfo(communityActivityInfo));
    }

    /**
     * 删除社区活动信息
     */
    @RequiresPermissions("temp:info:remove")
    @Log(title = "社区活动信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(communityActivityInfoService.deleteCommunityActivityInfoByActivityIds(ids));
    }
}
