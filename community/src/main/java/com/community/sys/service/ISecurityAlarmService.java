package com.community.sys.service;

import com.community.sys.domain.SecurityAlarm;

import java.util.Date;
import java.util.List;

/**
 * 安全报警Service接口
 * 
 * @author rcz
 * @date 2024-02-24
 */
public interface ISecurityAlarmService 
{
    /**
     * 查询安全报警
     * 
     * @param alarmTime 安全报警主键
     * @return 安全报警
     */
    public SecurityAlarm selectSecurityAlarmByAlarmTime(Date alarmTime);

    /**
     * 查询安全报警列表
     * 
     * @param securityAlarm 安全报警
     * @return 安全报警集合
     */
    public List<SecurityAlarm> selectSecurityAlarmList(SecurityAlarm securityAlarm);

    public List<String> selectSecurityAlarmNames();

    /**
     * 新增安全报警
     * 
     * @param securityAlarm 安全报警
     * @return 结果
     */
    public int insertSecurityAlarm(SecurityAlarm securityAlarm);

    /**
     * 修改安全报警
     * 
     * @param securityAlarm 安全报警
     * @return 结果
     */
    public int updateSecurityAlarm(SecurityAlarm securityAlarm);

    /**
     * 批量删除安全报警
     * 
     * @param alarmTimes 需要删除的安全报警主键集合
     * @return 结果
     */
    public int deleteSecurityAlarmByAlarmTimes(String alarmTimes);

    /**
     * 删除安全报警信息
     * 
     * @param alarmTime 安全报警主键
     * @return 结果
     */
    public int deleteSecurityAlarmByAlarmTime(Date alarmTime);
}
