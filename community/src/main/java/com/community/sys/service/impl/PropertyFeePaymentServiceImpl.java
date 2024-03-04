package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.service.IPropertyFeePaymentService;
import com.community.sys.domain.PropertyFeePayment;
import com.community.sys.mapper.PropertyFeePaymentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物业费缴纳Service业务层处理
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Service
public class PropertyFeePaymentServiceImpl implements IPropertyFeePaymentService
{
    @Resource
    private PropertyFeePaymentMapper propertyFeePaymentMapper;

    /**
     * 查询物业费缴纳
     * 
     * @param paymentId 物业费缴纳主键
     * @return 物业费缴纳
     */
    @Override
    public PropertyFeePayment selectPropertyFeePaymentByPaymentId(Long paymentId)
    {
        return propertyFeePaymentMapper.selectPropertyFeePaymentByPaymentId(paymentId);
    }

    /**
     * 查询物业费缴纳列表
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 物业费缴纳
     */
    @Override
    public List<PropertyFeePayment> selectPropertyFeePaymentList(PropertyFeePayment propertyFeePayment)
    {
        return propertyFeePaymentMapper.selectPropertyFeePaymentList(propertyFeePayment);
    }

    @Override
    public float selectPropertyFeePaymentAvg() {
        return propertyFeePaymentMapper.selectPropertyFeePaymentAvg();
    }

    /**
     * 新增物业费缴纳
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 结果
     */
    @Override
    public int insertPropertyFeePayment(PropertyFeePayment propertyFeePayment)
    {
        return propertyFeePaymentMapper.insertPropertyFeePayment(propertyFeePayment);
    }

    /**
     * 修改物业费缴纳
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 结果
     */
    @Override
    public int updatePropertyFeePayment(PropertyFeePayment propertyFeePayment)
    {
        return propertyFeePaymentMapper.updatePropertyFeePayment(propertyFeePayment);
    }

    /**
     * 批量删除物业费缴纳
     * 
     * @param paymentIds 需要删除的物业费缴纳主键
     * @return 结果
     */
    @Override
    public int deletePropertyFeePaymentByPaymentIds(String paymentIds)
    {
        return propertyFeePaymentMapper.deletePropertyFeePaymentByPaymentIds(Convert.toStrArray(paymentIds));
    }

    /**
     * 删除物业费缴纳信息
     * 
     * @param paymentId 物业费缴纳主键
     * @return 结果
     */
    @Override
    public int deletePropertyFeePaymentByPaymentId(Long paymentId)
    {
        return propertyFeePaymentMapper.deletePropertyFeePaymentByPaymentId(paymentId);
    }
}
