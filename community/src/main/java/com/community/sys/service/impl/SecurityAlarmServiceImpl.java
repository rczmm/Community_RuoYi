package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.domain.SecurityAlarm;
import com.community.sys.mapper.SecurityAlarmMapper;
import com.community.sys.service.ISecurityAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安全报警Service业务层处理
 * 
 * @author rcz
 * @date 2024-02-24
 */
@Service
public class SecurityAlarmServiceImpl implements ISecurityAlarmService
{
    @Autowired
    private SecurityAlarmMapper securityAlarmMapper;

    /**
     * 查询安全报警
     * 
     * @param alarmTime 安全报警主键
     * @return 安全报警
     */
    @Override
    public SecurityAlarm selectSecurityAlarmByAlarmTime(Date alarmTime)
    {
        return securityAlarmMapper.selectSecurityAlarmByAlarmTime(alarmTime);
    }

    /**
     * 查询安全报警列表
     * 
     * @param securityAlarm 安全报警
     * @return 安全报警
     */
    @Override
    public List<SecurityAlarm> selectSecurityAlarmList(SecurityAlarm securityAlarm)
    {
        return securityAlarmMapper.selectSecurityAlarmList(securityAlarm);
    }

    @Override
    public List<String> selectSecurityAlarmNames() {
        return securityAlarmMapper.selectSecurityAlarmNames();
    }

    /**
     * 新增安全报警
     * 
     * @param securityAlarm 安全报警
     * @return 结果
     */
    @Override
    public int insertSecurityAlarm(SecurityAlarm securityAlarm)
    {
        return securityAlarmMapper.insertSecurityAlarm(securityAlarm);
    }

    /**
     * 修改安全报警
     * 
     * @param securityAlarm 安全报警
     * @return 结果
     */
    @Override
    public int updateSecurityAlarm(SecurityAlarm securityAlarm)
    {
        return securityAlarmMapper.updateSecurityAlarm(securityAlarm);
    }

    /**
     * 批量删除安全报警
     * 
     * @param alarmTimes 需要删除的安全报警主键
     * @return 结果
     */
    @Override
    public int deleteSecurityAlarmByAlarmTimes(String alarmTimes)
    {
        return securityAlarmMapper.deleteSecurityAlarmByAlarmTimes(Convert.toStrArray(alarmTimes));
    }

    /**
     * 删除安全报警信息
     * 
     * @param alarmTime 安全报警主键
     * @return 结果
     */
    @Override
    public int deleteSecurityAlarmByAlarmTime(Date alarmTime)
    {
        return securityAlarmMapper.deleteSecurityAlarmByAlarmTime(alarmTime);
    }
}
