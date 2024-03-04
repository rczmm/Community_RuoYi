package com.community.sys.mapper;

import com.community.sys.domain.ParkingSpaceInfo;

import java.util.List;

/**
 * 停车位信息Mapper接口
 * 
 * @author rcz
 * @date 2024-02-25
 */
public interface ParkingSpaceInfoMapper 
{
    /**
     * 查询停车位信息
     * 
     * @param parkingSpaceId 停车位信息主键
     * @return 停车位信息
     */
    public ParkingSpaceInfo selectParkingSpaceInfoByParkingSpaceId(Long parkingSpaceId);

    /**
     * 查询停车位信息列表
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 停车位信息集合
     */
    public List<ParkingSpaceInfo> selectParkingSpaceInfoList(ParkingSpaceInfo parkingSpaceInfo);

    /**
     * 新增停车位信息
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 结果
     */
    public int insertParkingSpaceInfo(ParkingSpaceInfo parkingSpaceInfo);

    /**
     * 修改停车位信息
     * 
     * @param parkingSpaceInfo 停车位信息
     * @return 结果
     */
    public int updateParkingSpaceInfo(ParkingSpaceInfo parkingSpaceInfo);

    /**
     * 删除停车位信息
     * 
     * @param parkingSpaceId 停车位信息主键
     * @return 结果
     */
    public int deleteParkingSpaceInfoByParkingSpaceId(Long parkingSpaceId);

    /**
     * 批量删除停车位信息
     * 
     * @param parkingSpaceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteParkingSpaceInfoByParkingSpaceIds(String[] parkingSpaceIds);
}
