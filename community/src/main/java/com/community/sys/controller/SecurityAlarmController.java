package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.SecurityAlarm;
import com.community.sys.service.ISecurityAlarmService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 安全报警Controller
 * 
 * @author rcz
 * @date 2024-02-24
 */
@Controller
@RequestMapping("/temp/alarm")
public class SecurityAlarmController extends BaseController
{
    private String prefix = "temp/alarm";

    @Autowired
    private ISecurityAlarmService securityAlarmService;

    @RequiresPermissions("temp:alarm:view")
    @GetMapping()
    public String alarm()
    {
        return prefix + "/alarm";
    }

    /**
     * 查询安全报警列表
     */
    @RequiresPermissions("temp:alarm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SecurityAlarm securityAlarm)
    {
        startPage();
        List<SecurityAlarm> list = securityAlarmService.selectSecurityAlarmList(securityAlarm);
        return getDataTable(list);
    }

    /**
     * 导出安全报警列表
     */
    @RequiresPermissions("temp:alarm:export")
    @com.community.common.annotation.Log(title = "安全报警", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(SecurityAlarm securityAlarm)
    {
        List<SecurityAlarm> list = securityAlarmService.selectSecurityAlarmList(securityAlarm);
        com.community.common.utils.poi.ExcelUtil<SecurityAlarm> util = new ExcelUtil<SecurityAlarm>(SecurityAlarm.class);
        return util.exportExcel(list, "安全报警数据");
    }

    /**
     * 新增安全报警
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存安全报警
     */
    @RequiresPermissions("temp:alarm:add")
    @com.community.common.annotation.Log(title = "安全报警", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(SecurityAlarm securityAlarm)
    {
        return toAjax(securityAlarmService.insertSecurityAlarm(securityAlarm));
    }

    /**
     * 修改安全报警
     */
    @RequiresPermissions("temp:alarm:edit")
    @GetMapping("/edit/{alarmTime}")
    public String edit(@PathVariable("alarmTime") Date alarmTime, ModelMap mmap)
    {
        SecurityAlarm securityAlarm = securityAlarmService.selectSecurityAlarmByAlarmTime(alarmTime);
        mmap.put("securityAlarm", securityAlarm);
        return prefix + "/edit";
    }

    /**
     * 修改保存安全报警
     */
    @RequiresPermissions("temp:alarm:edit")
    @com.community.common.annotation.Log(title = "安全报警", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(SecurityAlarm securityAlarm)
    {
        return toAjax(securityAlarmService.updateSecurityAlarm(securityAlarm));
    }

    /**
     * 删除安全报警
     */
    @RequiresPermissions("temp:alarm:remove")
    @Log(title = "安全报警", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(securityAlarmService.deleteSecurityAlarmByAlarmTimes(ids));
    }
}
