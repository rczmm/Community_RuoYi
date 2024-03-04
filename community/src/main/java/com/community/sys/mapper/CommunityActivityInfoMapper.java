package com.community.sys.mapper;

import com.community.sys.domain.CommunityActivityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 社区活动信息Mapper接口
 * 
 * @author rcz
 * @date 2024-02-13
 */
@Mapper
public interface CommunityActivityInfoMapper 
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

    @Select("select activity_name from community_activity_info")
    public List<String> selectCommunityActivityInfoNames();

    @Select("SELECT COUNT(1)\n" +
            "FROM community_activity_info\n" +
            "WHERE YEAR(activity_time) = YEAR(CURDATE())\n" +
            "  AND MONTH(activity_time) = MONTH(CURDATE());")
    public int selectCommunityActivityMonth();

   @Select("SELECT COUNT(1)\n" +
           "FROM community_activity_info\n" +
           "WHERE YEAR(activity_time) = YEAR(CURDATE())\n" +
           "  AND MONTH(activity_time) = MONTH(CURDATE())-1;")
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
     * 删除社区活动信息
     * 
     * @param activityId 社区活动信息主键
     * @return 结果
     */
    public int deleteCommunityActivityInfoByActivityId(Long activityId);

    /**
     * 批量删除社区活动信息
     * 
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityActivityInfoByActivityIds(String[] activityIds);
}
