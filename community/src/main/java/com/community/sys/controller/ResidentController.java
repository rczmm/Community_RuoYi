package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.Resident;
import com.community.sys.service.IResidentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 居民信息Controller
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Controller
@RequestMapping("/temp/resident")
public class ResidentController extends BaseController
{
    private String prefix = "temp/resident";

    @Autowired
    private IResidentService residentService;

    @RequiresPermissions("temp:resident:view")
    @GetMapping()
    public String resident()
    {
        return prefix + "/resident";
    }

    /**
     * 查询居民信息列表
     */
    @RequiresPermissions("temp:resident:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Resident resident)
    {
        startPage();
        List<Resident> list = residentService.selectResidentList(resident);
        return getDataTable(list);
    }

    /**
     * 导出居民信息列表
     */
    @RequiresPermissions("temp:resident:export")
    @com.community.common.annotation.Log(title = "居民信息", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(Resident resident)
    {
        List<Resident> list = residentService.selectResidentList(resident);
        com.community.common.utils.poi.ExcelUtil<Resident> util = new ExcelUtil<Resident>(Resident.class);
        return util.exportExcel(list, "居民信息数据");
    }

    /**
     * 新增居民信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存居民信息
     */
    @RequiresPermissions("temp:resident:add")
    @com.community.common.annotation.Log(title = "居民信息", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(Resident resident)
    {
        return toAjax(residentService.insertResident(resident));
    }

    /**
     * 修改居民信息
     */
    @RequiresPermissions("temp:resident:edit")
    @GetMapping("/edit/{residentId}")
    public String edit(@PathVariable("residentId") Long residentId, ModelMap mmap)
    {
        Resident resident = residentService.selectResidentByResidentId(residentId);
        mmap.put("resident", resident);
        return prefix + "/edit";
    }

    /**
     * 修改保存居民信息
     */
    @RequiresPermissions("temp:resident:edit")
    @com.community.common.annotation.Log(title = "居民信息", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(Resident resident)
    {
        return toAjax(residentService.updateResident(resident));
    }

    /**
     * 删除居民信息
     */
    @RequiresPermissions("temp:resident:remove")
    @Log(title = "居民信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(residentService.deleteResidentByResidentIds(ids));
    }
}
