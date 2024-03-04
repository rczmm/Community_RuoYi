package com.community.sys.domain;

import java.math.BigDecimal;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 社区服务项目对象 community_service_project
 * 
 * @author rcz
 * @date 2024-01-30
 */
public class CommunityServiceProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务id */
    private Long serviceProjectId;

    /** 服务名 */
    @com.community.common.annotation.Excel(name = "服务名")
    private String serviceProjectName;

    /** 服务内容 */
    @com.community.common.annotation.Excel(name = "服务内容")
    private String serviceProjectContent;

    /** 服务费用 */
    @Excel(name = "服务费用")
    private BigDecimal serviceProjectFee;

    public void setServiceProjectId(Long serviceProjectId) 
    {
        this.serviceProjectId = serviceProjectId;
    }

    public Long getServiceProjectId() 
    {
        return serviceProjectId;
    }
    public void setServiceProjectName(String serviceProjectName) 
    {
        this.serviceProjectName = serviceProjectName;
    }

    public String getServiceProjectName() 
    {
        return serviceProjectName;
    }
    public void setServiceProjectContent(String serviceProjectContent) 
    {
        this.serviceProjectContent = serviceProjectContent;
    }

    public String getServiceProjectContent() 
    {
        return serviceProjectContent;
    }
    public void setServiceProjectFee(BigDecimal serviceProjectFee) 
    {
        this.serviceProjectFee = serviceProjectFee;
    }

    public BigDecimal getServiceProjectFee() 
    {
        return serviceProjectFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serviceProjectId", getServiceProjectId())
            .append("serviceProjectName", getServiceProjectName())
            .append("serviceProjectContent", getServiceProjectContent())
            .append("serviceProjectFee", getServiceProjectFee())
            .toString();
    }
}
