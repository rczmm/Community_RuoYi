package com.community.sys.mapper;

import com.community.sys.domain.PropertyFeePayment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 物业费缴纳Mapper接口
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Mapper
public interface PropertyFeePaymentMapper 
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

    @Select("SELECT avg(payment_amount)\n" +
            "FROM property_fee_payment;")
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
     * 删除物业费缴纳
     * 
     * @param paymentId 物业费缴纳主键
     * @return 结果
     */
    public int deletePropertyFeePaymentByPaymentId(Long paymentId);

    /**
     * 批量删除物业费缴纳
     * 
     * @param paymentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePropertyFeePaymentByPaymentIds(String[] paymentIds);
}
