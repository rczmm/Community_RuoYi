package com.community.sys.service;

import com.community.sys.domain.CommunityActivityInfo;

import java.util.List;

/**
 * 社区活动信息Service接口
 * 
 * @author rcz
 * @date 2024-02-13
 */
public interface ICommunityActivityInfoService 
{
    /**
     * 查询社区活动信息
     * 
     * @param activityId 社区活动信息主键
     * @return 社区活动信息
     */
    public CommunityActivityInfo selectCommunityActivityInfoByActivityId(Long activityId);

    /**
     * 查询社区活动信息列表
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 社区活动信息集合
     */
    public List<CommunityActivityInfo> selectCommunityActivityInfoList(CommunityActivityInfo communityActivityInfo);

    public List<String> selectCommunityActivityInfoNames();

    public int selectCommunityActivityMonth();
    public int selectCommunityActivityLastMonth();

    /**
     * 新增社区活动信息
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 结果
     */
    public int insertCommunityActivityInfo(CommunityActivityInfo communityActivityInfo);

    /**
     * 修改社区活动信息
     * 
     * @param communityActivityInfo 社区活动信息
     * @return 结果
     */
    public int updateCommunityActivityInfo(CommunityActivityInfo communityActivityInfo);

    /**
     * 批量删除社区活动信息
     * 
     * @param activityIds 需要删除的社区活动信息主键集合
     * @return 结果
     */
    public int deleteCommunityActivityInfoByActivityIds(String activityIds);

    /**
     * 删除社区活动信息信息
     * 
     * @param activityId 社区活动信息主键
     * @return 结果
     */
    public int deleteCommunityActivityInfoByActivityId(Long activityId);
}
