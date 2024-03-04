package com.community.sys.domain;

import java.util.Date;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 安全报警对象 security_alarm
 * 
 * @author rcz
 * @date 2024-02-24
 */
public class SecurityAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @com.community.common.annotation.Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date alarmTime;

    /** 地点 */
    @com.community.common.annotation.Excel(name = "地点")
    private String alarmLocation;

    /** 类型 */
    @Excel(name = "类型")
    private String alarmType;

    public void setAlarmTime(Date alarmTime) 
    {
        this.alarmTime = alarmTime;
    }

    public Date getAlarmTime() 
    {
        return alarmTime;
    }
    public void setAlarmLocation(String alarmLocation) 
    {
        this.alarmLocation = alarmLocation;
    }

    public String getAlarmLocation() 
    {
        return alarmLocation;
    }
    public void setAlarmType(String alarmType) 
    {
        this.alarmType = alarmType;
    }

    public String getAlarmType() 
    {
        return alarmType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("alarmTime", getAlarmTime())
            .append("alarmLocation", getAlarmLocation())
            .append("alarmType", getAlarmType())
            .toString();
    }
}
