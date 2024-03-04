package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.EntryLog;
import com.community.sys.service.IEntryLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * logController
 * 
 * @author rcz
 * @date 2024-02-22
 */
@Controller
@RequestMapping("/temp/log")
public class EntryLogController extends BaseController
{
    private String prefix = "temp/log";

    @Autowired
    private IEntryLogService entryLogService;

    @RequiresPermissions("temp:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询log列表
     */
    @RequiresPermissions("temp:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EntryLog entryLog)
    {
        startPage();
        List<EntryLog> list = entryLogService.selectEntryLogList(entryLog);
        return getDataTable(list);
    }

    /**
     * 导出log列表
     */
    @RequiresPermissions("temp:log:export")
    @com.community.common.annotation.Log(title = "log", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(EntryLog entryLog)
    {
        List<EntryLog> list = entryLogService.selectEntryLogList(entryLog);
        com.community.common.utils.poi.ExcelUtil<EntryLog> util = new ExcelUtil<EntryLog>(EntryLog.class);
        return util.exportExcel(list, "log数据");
    }

    /**
     * 新增log
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存log
     */
    @RequiresPermissions("temp:log:add")
    @com.community.common.annotation.Log(title = "log", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(EntryLog entryLog)
    {
        return toAjax(entryLogService.insertEntryLog(entryLog));
    }

    /**
     * 修改log
     */
    @RequiresPermissions("temp:log:edit")
    @GetMapping("/edit/{entryid}")
    public String edit(@PathVariable("entryid") Long entryid, ModelMap mmap)
    {
        EntryLog entryLog = entryLogService.selectEntryLogByEntryid(entryid);
        mmap.put("entryLog", entryLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存log
     */
    @RequiresPermissions("temp:log:edit")
    @com.community.common.annotation.Log(title = "log", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(EntryLog entryLog)
    {
        return toAjax(entryLogService.updateEntryLog(entryLog));
    }

    /**
     * 删除log
     */
    @RequiresPermissions("temp:log:remove")
    @Log(title = "log", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(entryLogService.deleteEntryLogByEntryids(ids));
    }
}
