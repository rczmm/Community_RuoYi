package com.community.sys.service;

import com.community.sys.domain.CommunityServiceProject;

import java.util.List;

/**
 * 社区服务项目Service接口
 * 
 * @author rcz
 * @date 2024-01-30
 */
public interface ICommunityServiceProjectService 
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

    public List<String> selectCommunityServiceProjectNames();

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
     * 批量删除社区服务项目
     * 
     * @param serviceProjectIds 需要删除的社区服务项目主键集合
     * @return 结果
     */
    public int deleteCommunityServiceProjectByServiceProjectIds(String serviceProjectIds);

    /**
     * 删除社区服务项目信息
     * 
     * @param serviceProjectId 社区服务项目主键
     * @return 结果
     */
    public int deleteCommunityServiceProjectByServiceProjectId(Long serviceProjectId);
}
