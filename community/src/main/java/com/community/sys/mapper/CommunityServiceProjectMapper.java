package com.community.sys.mapper;

import com.community.sys.domain.CommunityServiceProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 社区服务项目Mapper接口
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Mapper
public interface CommunityServiceProjectMapper 
{
    /**
     * 查询社区服务项目
     * 
     * @param serviceProjectId 社区服务项目主键
     * @return 社区服务项目
     */
    public CommunityServiceProject selectCommunityServiceProjectByServiceProjectId(Long serviceProjectId);

    /**
     * 查询社区服务项目列表
     * 
     * @param communityServiceProject 社区服务项目
     * @return 社区服务项目集合
     */
    public List<CommunityServiceProject> selectCommunityServiceProjectList(CommunityServiceProject communityServiceProject);

    @Select("select service_project_name from community_service_project")
    public List<String> selectCommunityServiceProjectNames();

    @Select("SELECT COUNT(1)\n" +
            "FROM community_service_project;")
    public int selectCommunityServiceProjectCount();



    /**
     * 新增社区服务项目
     * 
     * @param communityServiceProject 社区服务项目
     * @return 结果
     */
    public int insertCommunityServiceProject(CommunityServiceProject communityServiceProject);

    /**
     * 修改社区服务项目
     * 
     * @param communityServiceProject 社区服务项目
     * @return 结果
     */
    public int updateCommunityServiceProject(CommunityServiceProject communityServiceProject);

    /**
     * 删除社区服务项目
     * 
     * @param serviceProjectId 社区服务项目主键
     * @return 结果
     */
    public int deleteCommunityServiceProjectByServiceProjectId(Long serviceProjectId);

    /**
     * 批量删除社区服务项目
     * 
     * @param serviceProjectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityServiceProjectByServiceProjectIds(String[] serviceProjectIds);
}
