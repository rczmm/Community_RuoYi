package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.ParkingSpaceInfo;
import com.community.sys.service.IParkingSpaceInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 停车位信息Controller
 * 
 * @author rcz
 * @date 2024-02-25
 */
@Controller
@RequestMapping("/temp/park")
public class ParkingSpaceInfoController extends BaseController
{
    private String prefix = "temp/park";

    @Resource
    private IParkingSpaceInfoService parkingSpaceInfoService;

    @RequiresPermissions("temp:park:view")
    @GetMapping()
    public String park()
    {
        return prefix + "/park";
    }

    /**
     * 查询停车位信息列表
     */
    @RequiresPermissions("temp:park:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ParkingSpaceInfo parkingSpaceInfo)
    {
        startPage();
        List<ParkingSpaceInfo> list = parkingSpaceInfoService.selectParkingSpaceInfoList(parkingSpaceInfo);
        return getDataTable(list);
    }

    /**
     * 导出停车位信息列表
     */
    @RequiresPermissions("temp:park:export")
    @com.community.common.annotation.Log(title = "停车位信息", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(ParkingSpaceInfo parkingSpaceInfo)
    {
        List<ParkingSpaceInfo> list = parkingSpaceInfoService.selectParkingSpaceInfoList(parkingSpaceInfo);
        com.community.common.utils.poi.ExcelUtil<ParkingSpaceInfo> util = new ExcelUtil<ParkingSpaceInfo>(ParkingSpaceInfo.class);
        return util.exportExcel(list, "停车位信息数据");
    }

    /**
     * 新增停车位信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存停车位信息
     */
    @RequiresPermissions("temp:park:add")
    @com.community.common.annotation.Log(title = "停车位信息", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(ParkingSpaceInfo parkingSpaceInfo)
    {
        return toAjax(parkingSpaceInfoService.insertParkingSpaceInfo(parkingSpaceInfo));
    }

    /**
     * 修改停车位信息
     */
    @RequiresPermissions("temp:park:edit")
    @GetMapping("/edit/{parkingSpaceId}")
    public String edit(@PathVariable("parkingSpaceId") Long parkingSpaceId, ModelMap mmap)
    {
        ParkingSpaceInfo parkingSpaceInfo = parkingSpaceInfoService.selectParkingSpaceInfoByParkingSpaceId(parkingSpaceId);
        mmap.put("parkingSpaceInfo", parkingSpaceInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存停车位信息
     */
    @RequiresPermissions("temp:park:edit")
    @com.community.common.annotation.Log(title = "停车位信息", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(ParkingSpaceInfo parkingSpaceInfo)
    {
        return toAjax(parkingSpaceInfoService.updateParkingSpaceInfo(parkingSpaceInfo));
    }

    /**
     * 删除停车位信息
     */
    @RequiresPermissions("temp:park:remove")
    @Log(title = "停车位信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(parkingSpaceInfoService.deleteParkingSpaceInfoByParkingSpaceIds(ids));
    }
}
