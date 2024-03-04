package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.service.IParkingSpaceInfoService;
import com.community.sys.domain.ParkingSpaceInfo;
import com.community.sys.mapper.ParkingSpaceInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 停车位信息Service业务层处理
 * 
 * @author rcz
 * @date 2024-02-25
 */
@Service
public class ParkingSpaceInfoServiceImpl implements IParkingSpaceInfoService
{
    @Resource
    private ParkingSpaceInfoMapper parkingSpaceInfoMapper;

    /**
     * 查询停车位信息
     * 
     * @param parkingSpaceId 停车位信息主键
     * @return 停车位信息
     */
    @Override
    public ParkingSpaceInfo selectParkingSpaceInfoByParkingSpaceId(Long parkingSpaceId)
    {
        return parkingSpaceInfoMapper.selectParkingSpaceInfoByParkingSpaceId(parkingSpaceId);
    }

    /**
     * 查询停车位信息列表
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 停车位信息
     */
    @Override
    public List<ParkingSpaceInfo> selectParkingSpaceInfoList(ParkingSpaceInfo parkingSpaceInfo)
    {
        return parkingSpaceInfoMapper.selectParkingSpaceInfoList(parkingSpaceInfo);
    }


    /**
     * 新增停车位信息
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 结果
     */
    @Override
    public int insertParkingSpaceInfo(ParkingSpaceInfo parkingSpaceInfo)
    {
        return parkingSpaceInfoMapper.insertParkingSpaceInfo(parkingSpaceInfo);
    }

    /**
     * 修改停车位信息
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 结果
     */
    @Override
    public int updateParkingSpaceInfo(ParkingSpaceInfo parkingSpaceInfo)
    {
        return parkingSpaceInfoMapper.updateParkingSpaceInfo(parkingSpaceInfo);
    }

    /**
     * 批量删除停车位信息
     * 
     * @param parkingSpaceIds 需要删除的停车位信息主键
     * @return 结果
     */
    @Override
    public int deleteParkingSpaceInfoByParkingSpaceIds(String parkingSpaceIds)
    {
        return parkingSpaceInfoMapper.deleteParkingSpaceInfoByParkingSpaceIds(Convert.toStrArray(parkingSpaceIds));
    }

    /**
     * 删除停车位信息信息
     * 
     * @param parkingSpaceId 停车位信息主键
     * @return 结果
     */
    @Override
    public int deleteParkingSpaceInfoByParkingSpaceId(Long parkingSpaceId)
    {
        return parkingSpaceInfoMapper.deleteParkingSpaceInfoByParkingSpaceId(parkingSpaceId);
    }
}
