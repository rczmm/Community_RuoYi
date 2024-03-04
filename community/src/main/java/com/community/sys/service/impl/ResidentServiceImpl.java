package com.community.sys.service.impl;

import java.util.List;

import com.community.common.core.text.Convert;
import com.community.sys.service.IResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.community.sys.mapper.ResidentMapper;
import com.community.sys.domain.Resident;

/**
 * 居民信息Service业务层处理
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Service
public class ResidentServiceImpl implements IResidentService
{
    @Autowired
    private ResidentMapper residentMapper;

    /**
     * 查询居民信息
     * 
     * @param residentId 居民信息主键
     * @return 居民信息
     */
    @Override
    public Resident selectResidentByResidentId(Long residentId)
    {
        return residentMapper.selectResidentByResidentId(residentId);
    }

    /**
     * 查询居民信息列表
     * 
     * @param resident 居民信息
     * @return 居民信息
     */
    @Override
    public List<Resident> selectResidentList(Resident resident)
    {
        return residentMapper.selectResidentList(resident);
    }

    /**
     * 新增居民信息
     * 
     * @param resident 居民信息
     * @return 结果
     */
    @Override
    public int insertResident(Resident resident)
    {
        return residentMapper.insertResident(resident);
    }

    /**
     * 修改居民信息
     * 
     * @param resident 居民信息
     * @return 结果
     */
    @Override
    public int updateResident(Resident resident)
    {
        return residentMapper.updateResident(resident);
    }

    /**
     * 批量删除居民信息
     * 
     * @param residentIds 需要删除的居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentByResidentIds(String residentIds)
    {
        return residentMapper.deleteResidentByResidentIds(Convert.toStrArray(residentIds));
    }

    /**
     * 删除居民信息信息
     * 
     * @param residentId 居民信息主键
     * @return 结果
     */
    @Override
    public int deleteResidentByResidentId(Long residentId)
    {
        return residentMapper.deleteResidentByResidentId(residentId);
    }
}
