package com.community.web.controller.monitor;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.system.domain.SysOperLog;
import com.community.system.service.ISysOperLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{
    private String prefix = "monitor/operlog";

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/operlog";
    }

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(com.community.system.domain.SysOperLog operLog)
    {
        startPage();
        List<com.community.system.domain.SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @com.community.common.annotation.Log(title = "操作日志", businessType = com.community.common.enums.BusinessType.EXPORT)
    @RequiresPermissions("monitor:operlog:export")
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(com.community.system.domain.SysOperLog operLog)
    {
        List<com.community.system.domain.SysOperLog> list = operLogService.selectOperLogList(operLog);
        com.community.common.utils.poi.ExcelUtil<com.community.system.domain.SysOperLog> util = new ExcelUtil<com.community.system.domain.SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "操作日志");
    }

    @com.community.common.annotation.Log(title = "操作日志", businessType = com.community.common.enums.BusinessType.DELETE)
    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/remove")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult remove(String ids)
    {
        return toAjax(operLogService.deleteOperLogByIds(ids));
    }

    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long operId, ModelMap mmap)
    {
        mmap.put("operLog", operLogService.selectOperLogById(operId));
        return prefix + "/detail";
    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return success();
    }
}
