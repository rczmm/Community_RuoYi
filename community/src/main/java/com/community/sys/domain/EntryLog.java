package com.community.sys.domain;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * log对象 entry_log
 * 
 * @author rcz
 * @date 2024-02-22
 */
public class EntryLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long entryid;

    /** 出入人 */
    @com.community.common.annotation.Excel(name = "出入人")
    private String entryperson;

    /** 出入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:SS")
    @com.community.common.annotation.Excel(name = "出入时间", width = 30, dateFormat = "yyyy-MM-dd HH:MM:SS")
    private Date entrytime;

    /** 出入类型 */
    @com.community.common.annotation.Excel(name = "出入类型")
    private String entrytype;

    /** 出入人类型 */
    @com.community.common.annotation.Excel(name = "出入人类型")
    private String entrypersontype;

    /** 出入照片 */
    @Excel(name = "出入照片")
    private String entryphoto;

    public void setEntryid(Long entryid) 
    {
        this.entryid = entryid;
    }

    public Long getEntryid() 
    {
        return entryid;
    }
    public void setEntryperson(String entryperson) 
    {
        this.entryperson = entryperson;
    }

    public String getEntryperson() 
    {
        return entryperson;
    }
    public void setEntrytime(Date entrytime) 
    {
        this.entrytime = entrytime;
    }

    public Date getEntrytime() 
    {
        return entrytime;
    }
    public void setEntrytype(String entrytype) 
    {
        this.entrytype = entrytype;
    }

    public String getEntrytype() 
    {
        return entrytype;
    }
    public void setEntrypersontype(String entrypersontype) 
    {
        this.entrypersontype = entrypersontype;
    }

    public String getEntrypersontype() 
    {
        return entrypersontype;
    }
    public void setEntryphoto(String entryphoto) 
    {
        this.entryphoto = entryphoto;
    }

    public String getEntryphoto() 
    {
        return entryphoto;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("entryid", getEntryid())
            .append("entryperson", getEntryperson())
            .append("entrytime", getEntrytime())
            .append("entrytype", getEntrytype())
            .append("entrypersontype", getEntrypersontype())
            .append("entryphoto", getEntryphoto())
            .toString();
    }
}
