package com.community.sys.domain;

import java.util.Date;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 社区活动信息对象 community_activity_info
 * 
 * @author rcz
 * @date 2024-02-13
 */
public class CommunityActivityInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long activityId;

    /** 活动名 */
    @com.community.common.annotation.Excel(name = "活动名")
    private String activityName;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @com.community.common.annotation.Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityTime;

    /** 活动地点 */
    @com.community.common.annotation.Excel(name = "活动地点")
    private String activityLocation;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String activityContent;

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }
    public void setActivityTime(Date activityTime) 
    {
        this.activityTime = activityTime;
    }

    public Date getActivityTime() 
    {
        return activityTime;
    }
    public void setActivityLocation(String activityLocation) 
    {
        this.activityLocation = activityLocation;
    }

    public String getActivityLocation() 
    {
        return activityLocation;
    }
    public void setActivityContent(String activityContent) 
    {
        this.activityContent = activityContent;
    }

    public String getActivityContent() 
    {
        return activityContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("activityName", getActivityName())
            .append("activityTime", getActivityTime())
            .append("activityLocation", getActivityLocation())
            .append("activityContent", getActivityContent())
            .toString();
    }
}
