package com.community.sys.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物业费缴纳对象 property_fee_payment
 * 
 * @author rcz
 * @date 2024-01-30
 */
public class PropertyFeePayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long paymentId;

    /** 订单名 */
    @com.community.common.annotation.Excel(name = "订单名")
    private String payerName;

    /** 金额 */
    @com.community.common.annotation.Excel(name = "金额")
    private BigDecimal paymentAmount;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    public void setPayerName(String payerName) 
    {
        this.payerName = payerName;
    }

    public String getPayerName() 
    {
        return payerName;
    }
    public void setPaymentAmount(BigDecimal paymentAmount) 
    {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentAmount() 
    {
        return paymentAmount;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paymentId", getPaymentId())
            .append("payerName", getPayerName())
            .append("paymentAmount", getPaymentAmount())
            .append("paymentTime", getPaymentTime())
            .toString();
    }
}
