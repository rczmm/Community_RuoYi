package com.community.sys.domain;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 停车位信息对象 parking_space_info
 * 
 * @author rcz
 * @date 2024-02-25
 */
public class ParkingSpaceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long parkingSpaceId;

    /** 编号 */
    @com.community.common.annotation.Excel(name = "编号")
    private String parkingSpaceNo;

    /** 车位类型 */
    @com.community.common.annotation.Excel(name = "车位类型")
    private String parkingSpaceType;

    /** 价格 */
    @com.community.common.annotation.Excel(name = "价格")
    private BigDecimal parkingSpacePrice;

    /** 车位状态 */
    @Excel(name = "车位状态")
    private Long parkingType;

    public void setParkingSpaceId(Long parkingSpaceId) 
    {
        this.parkingSpaceId = parkingSpaceId;
    }

    public Long getParkingSpaceId() 
    {
        return parkingSpaceId;
    }
    public void setParkingSpaceNo(String parkingSpaceNo) 
    {
        this.parkingSpaceNo = parkingSpaceNo;
    }

    public String getParkingSpaceNo() 
    {
        return parkingSpaceNo;
    }
    public void setParkingSpaceType(String parkingSpaceType) 
    {
        this.parkingSpaceType = parkingSpaceType;
    }

    public String getParkingSpaceType() 
    {
        return parkingSpaceType;
    }
    public void setParkingSpacePrice(BigDecimal parkingSpacePrice) 
    {
        this.parkingSpacePrice = parkingSpacePrice;
    }

    public BigDecimal getParkingSpacePrice() 
    {
        return parkingSpacePrice;
    }
    public void setParkingType(Long parkingType) 
    {
        this.parkingType = parkingType;
    }

    public Long getParkingType() 
    {
        return parkingType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("parkingSpaceId", getParkingSpaceId())
            .append("parkingSpaceNo", getParkingSpaceNo())
            .append("parkingSpaceType", getParkingSpaceType())
            .append("parkingSpacePrice", getParkingSpacePrice())
            .append("parkingType", getParkingType())
            .toString();
    }
}
