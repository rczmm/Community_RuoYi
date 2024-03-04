package com.community.sys.service;

import com.community.sys.domain.PropertyFeePayment;

import java.util.List;

/**
 * 物业费缴纳Service接口
 * 
 * @author rcz
 * @date 2024-01-30
 */
public interface IPropertyFeePaymentService 
{
    /**
     * 查询物业费缴纳
     * 
     * @param paymentId 物业费缴纳主键
     * @return 物业费缴纳
     */
    public PropertyFeePayment selectPropertyFeePaymentByPaymentId(Long paymentId);

    /**
     * 查询物业费缴纳列表
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 物业费缴纳集合
     */
    public List<PropertyFeePayment> selectPropertyFeePaymentList(PropertyFeePayment propertyFeePayment);

    public float selectPropertyFeePaymentAvg();

    /**
     * 新增物业费缴纳
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 结果
     */
    public int insertPropertyFeePayment(PropertyFeePayment propertyFeePayment);

    /**
     * 修改物业费缴纳
     * 
     * @param propertyFeePayment 物业费缴纳
     * @return 结果
     */
    public int updatePropertyFeePayment(PropertyFeePayment propertyFeePayment);

    /**
     * 批量删除物业费缴纳
     * 
     * @param paymentIds 需要删除的物业费缴纳主键集合
     * @return 结果
     */
    public int deletePropertyFeePaymentByPaymentIds(String paymentIds);

    /**
     * 删除物业费缴纳信息
     * 
     * @param paymentId 物业费缴纳主键
     * @return 结果
     */
    public int deletePropertyFeePaymentByPaymentId(Long paymentId);
}
