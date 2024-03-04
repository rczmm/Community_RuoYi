package com.community.sys.domain;

import com.community.common.annotation.Excel;
import com.community.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 居民信息对象 resident
 * 
 * @author rcz
 * @date 2024-01-30
 */
public class Resident extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 居民id */
    private Long residentId;

    /** 姓名 */
    @com.community.common.annotation.Excel(name = "姓名")
    private String name;

    /** 性别 */
    @com.community.common.annotation.Excel(name = "性别")
    private String gender;

    /** 身份证号 */
    @com.community.common.annotation.Excel(name = "身份证号")
    private String idCard;

    /** 地址 */
    @com.community.common.annotation.Excel(name = "地址")
    private String address;

    /** 联系方式 */
    @com.community.common.annotation.Excel(name = "联系方式")
    private String contact;

    /** 家庭成员信息 */
    @com.community.common.annotation.Excel(name = "家庭成员信息")
    private String familyMembers;

    /** 居民类型 */
    @Excel(name = "居民类型")
    private String type;

    public void setResidentId(Long residentId) 
    {
        this.residentId = residentId;
    }

    public Long getResidentId() 
    {
        return residentId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setFamilyMembers(String familyMembers) 
    {
        this.familyMembers = familyMembers;
    }

    public String getFamilyMembers() 
    {
        return familyMembers;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("residentId", getResidentId())
            .append("name", getName())
            .append("gender", getGender())
            .append("idCard", getIdCard())
            .append("address", getAddress())
            .append("contact", getContact())
            .append("familyMembers", getFamilyMembers())
            .append("type", getType())
            .toString();
    }
}
