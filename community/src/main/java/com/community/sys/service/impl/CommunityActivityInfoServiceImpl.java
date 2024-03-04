package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.domain.CommunityActivityInfo;
import com.community.sys.mapper.CommunityActivityInfoMapper;
import com.community.sys.service.ICommunityActivityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社区活动信息Service业务层处理
 * 
 * @author rcz
 * @date 2024-02-13
 */
@Service
public class CommunityActivityInfoServiceImpl implements ICommunityActivityInfoService
{
    @Autowired
    private CommunityActivityInfoMapper communityActivityInfoMapper;

    /**
     * 查询社区活动信息
     * 
     * @param activityId 社区活动信息主键
     * @return 社区活动信息
     */
    @Override
    public CommunityActivityInfo selectCommunityActivityInfoByActivityId(Long activityId)
    {
        return communityActivityInfoMapper.selectCommunityActivityInfoByActivityId(activityId);
    }

    /**
     * 查询社区活动信息列表
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 社区活动信息
     */
    @Override
    public List<CommunityActivityInfo> selectCommunityActivityInfoList(CommunityActivityInfo communityActivityInfo)
    {
        return communityActivityInfoMapper.selectCommunityActivityInfoList(communityActivityInfo);
    }

    @Override
    public List<String> selectCommunityActivityInfoNames() {
        return communityActivityInfoMapper.selectCommunityActivityInfoNames();
    }

    @Override
    public int selectCommunityActivityMonth() {
        return communityActivityInfoMapper.selectCommunityActivityMonth();
    }

    @Override
    public int selectCommunityActivityLastMonth() {
        return communityActivityInfoMapper.selectCommunityActivityLastMonth();
    }

    /**
     * 新增社区活动信息
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 结果
     */
    @Override
    public int insertCommunityActivityInfo(CommunityActivityInfo communityActivityInfo)
    {
        return communityActivityInfoMapper.insertCommunityActivityInfo(communityActivityInfo);
    }

    /**
     * 修改社区活动信息
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 结果
     */
    @Override
    public int updateCommunityActivityInfo(CommunityActivityInfo communityActivityInfo)
    {
        return communityActivityInfoMapper.updateCommunityActivityInfo(communityActivityInfo);
    }

    /**
     * 批量删除社区活动信息
     * 
     * @param activityIds 需要删除的社区活动信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityActivityInfoByActivityIds(String activityIds)
    {
        return communityActivityInfoMapper.deleteCommunityActivityInfoByActivityIds(Convert.toStrArray(activityIds));
    }

    /**
     * 删除社区活动信息信息
     * 
     * @param activityId 社区活动信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityActivityInfoByActivityId(Long activityId)
    {
        return communityActivityInfoMapper.deleteCommunityActivityInfoByActivityId(activityId);
    }
}
