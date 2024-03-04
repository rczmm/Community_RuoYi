package com.community.sys.service;

import com.community.sys.domain.Resident;

import java.util.List;

/**
 * 居民信息Service接口
 * 
 * @author rcz
 * @date 2024-01-30
 */
public interface IResidentService 
{
    /**
     * 查询居民信息
     * 
     * @param residentId 居民信息主键
     * @return 居民信息
     */
    public Resident selectResidentByResidentId(Long residentId);

    /**
     * 查询居民信息列表
     * 
     * @param resident 居民信息
     * @return 居民信息集合
     */
    public List<Resident> selectResidentList(Resident resident);

    /**
     * 新增居民信息
     * 
     * @param resident 居民信息
     * @return 结果
     */
    public int insertResident(Resident resident);

    /**
     * 修改居民信息
     * 
     * @param resident 居民信息
     * @return 结果
     */
    public int updateResident(Resident resident);

    /**
     * 批量删除居民信息
     * 
     * @param residentIds 需要删除的居民信息主键集合
     * @return 结果
     */
    public int deleteResidentByResidentIds(String residentIds);

    /**
     * 删除居民信息信息
     * 
     * @param residentId 居民信息主键
     * @return 结果
     */
    public int deleteResidentByResidentId(Long residentId);
}
