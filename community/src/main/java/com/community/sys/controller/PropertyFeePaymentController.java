package com.community.sys.controller;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.page.TableDataInfo;
import com.community.common.enums.BusinessType;
import com.community.common.utils.poi.ExcelUtil;
import com.community.sys.domain.PropertyFeePayment;
import com.community.sys.service.IPropertyFeePaymentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物业费缴纳Controller
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Controller
@RequestMapping("/temp/payment")
public class PropertyFeePaymentController extends BaseController
{
    private String prefix = "temp/payment";

    @Autowired
    private IPropertyFeePaymentService propertyFeePaymentService;

    @RequiresPermissions("temp:payment:view")
    @GetMapping()
    public String payment()
    {
        return prefix + "/payment";
    }

    /**
     * 查询物业费缴纳列表
     */
    @RequiresPermissions("temp:payment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PropertyFeePayment propertyFeePayment)
    {
        startPage();
        List<PropertyFeePayment> list = propertyFeePaymentService.selectPropertyFeePaymentList(propertyFeePayment);
        return getDataTable(list);
    }

    /**
     * 导出物业费缴纳列表
     */
    @RequiresPermissions("temp:payment:export")
    @com.community.common.annotation.Log(title = "物业费缴纳", businessType = com.community.common.enums.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult export(PropertyFeePayment propertyFeePayment)
    {
        List<PropertyFeePayment> list = propertyFeePaymentService.selectPropertyFeePaymentList(propertyFeePayment);
        com.community.common.utils.poi.ExcelUtil<PropertyFeePayment> util = new ExcelUtil<PropertyFeePayment>(PropertyFeePayment.class);
        return util.exportExcel(list, "物业费缴纳数据");
    }

    /**
     * 新增物业费缴纳
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物业费缴纳
     */
    @RequiresPermissions("temp:payment:add")
    @com.community.common.annotation.Log(title = "物业费缴纳", businessType = com.community.common.enums.BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult addSave(PropertyFeePayment propertyFeePayment)
    {
        return toAjax(propertyFeePaymentService.insertPropertyFeePayment(propertyFeePayment));
    }

    /**
     * 修改物业费缴纳
     */
    @RequiresPermissions("temp:payment:edit")
    @GetMapping("/edit/{paymentId}")
    public String edit(@PathVariable("paymentId") Long paymentId, ModelMap mmap)
    {
        PropertyFeePayment propertyFeePayment = propertyFeePaymentService.selectPropertyFeePaymentByPaymentId(paymentId);
        mmap.put("propertyFeePayment", propertyFeePayment);
        return prefix + "/edit";
    }

    /**
     * 修改保存物业费缴纳
     */
    @RequiresPermissions("temp:payment:edit")
    @com.community.common.annotation.Log(title = "物业费缴纳", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(PropertyFeePayment propertyFeePayment)
    {
        return toAjax(propertyFeePaymentService.updatePropertyFeePayment(propertyFeePayment));
    }

    /**
     * 删除物业费缴纳
     */
    @RequiresPermissions("temp:payment:remove")
    @Log(title = "物业费缴纳", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(propertyFeePaymentService.deletePropertyFeePaymentByPaymentIds(ids));
    }
}
