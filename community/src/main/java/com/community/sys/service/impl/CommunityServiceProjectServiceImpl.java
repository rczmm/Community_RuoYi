package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.domain.CommunityServiceProject;
import com.community.sys.mapper.CommunityServiceProjectMapper;
import com.community.sys.service.ICommunityServiceProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社区服务项目Service业务层处理
 * 
 * @author rcz
 * @date 2024-01-30
 */
@Service
public class CommunityServiceProjectServiceImpl implements ICommunityServiceProjectService
{
    @Autowired
    private CommunityServiceProjectMapper communityServiceProjectMapper;

    /**
     * 查询社区服务项目
     * 
     * @param serviceProjectId 社区服务项目主键
     * @return 社区服务项目
     */
    @Override
    public CommunityServiceProject selectCommunityServiceProjectByServiceProjectId(Long serviceProjectId)
    {
        return communityServiceProjectMapper.selectCommunityServiceProjectByServiceProjectId(serviceProjectId);
    }

    /**
     * 查询社区服务项目列表
     * 
     * @param communityServiceProject 社区服务项目
     * @return 社区服务项目
     */
    @Override
    public List<CommunityServiceProject> selectCommunityServiceProjectList(CommunityServiceProject communityServiceProject)
    {
        return communityServiceProjectMapper.selectCommunityServiceProjectList(communityServiceProject);
    }

    @Override
    public List<String> selectCommunityServiceProjectNames() {
        return communityServiceProjectMapper.selectCommunityServiceProjectNames();
    }

    @Override
    public int selectCommunityServiceProjectCount() {
        return communityServiceProjectMapper.selectCommunityServiceProjectCount();
    }

    /**
     * 新增社区服务项目
     * 
     * @param communityServiceProject 社区服务项目
     * @return 结果
     */
    @Override
    public int insertCommunityServiceProject(CommunityServiceProject communityServiceProject)
    {
        return communityServiceProjectMapper.insertCommunityServiceProject(communityServiceProject);
    }

    /**
     * 修改社区服务项目
     * 
     * @param communityServiceProject 社区服务项目
     * @return 结果
     */
    @Override
    public int updateCommunityServiceProject(CommunityServiceProject communityServiceProject)
    {
        return communityServiceProjectMapper.updateCommunityServiceProject(communityServiceProject);
    }

    /**
     * 批量删除社区服务项目
     * 
     * @param serviceProjectIds 需要删除的社区服务项目主键
     * @return 结果
     */
    @Override
    public int deleteCommunityServiceProjectByServiceProjectIds(String serviceProjectIds)
    {
        return communityServiceProjectMapper.deleteCommunityServiceProjectByServiceProjectIds(Convert.toStrArray(serviceProjectIds));
    }

    /**
     * 删除社区服务项目信息
     * 
     * @param serviceProjectId 社区服务项目主键
     * @return 结果
     */
    @Override
    public int deleteCommunityServiceProjectByServiceProjectId(Long serviceProjectId)
    {
        return communityServiceProjectMapper.deleteCommunityServiceProjectByServiceProjectId(serviceProjectId);
    }
}
