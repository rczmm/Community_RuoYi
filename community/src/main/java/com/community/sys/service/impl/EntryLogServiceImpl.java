package com.community.sys.service.impl;

import com.community.common.core.text.Convert;
import com.community.sys.service.IEntryLogService;
import com.community.sys.domain.EntryLog;
import com.community.sys.mapper.EntryLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * logService业务层处理
 * 
 * @author rcz
 * @date 2024-02-22
 */
@Service
public class EntryLogServiceImpl implements IEntryLogService
{
    @Autowired
    private EntryLogMapper entryLogMapper;

    /**
     * 查询log
     * 
     * @param entryid log主键
     * @return log
     */
    @Override
    public EntryLog selectEntryLogByEntryid(Long entryid)
    {
        return entryLogMapper.selectEntryLogByEntryid(entryid);
    }

    /**
     * 查询log列表
     * 
     * @param entryLog log
     * @return log
     */
    @Override
    public List<EntryLog> selectEntryLogList(EntryLog entryLog)
    {
        return entryLogMapper.selectEntryLogList(entryLog);
    }

    @Override
    public int selectEntryLogNums() {
        return entryLogMapper.selectEntryLogNums();
    }

    @Override
    public int selectEntryLogNumsDay() {
        return entryLogMapper.selectEntryLogNumsDay();
    }

    @Override
    public List<Integer> selectEntryLogChartDay() {
        return entryLogMapper.selectEntryLogChartDay();
    }

    @Override
    public List<Integer> selectEntryLogChartWeek() {
        return entryLogMapper.selectEntryLogChartWeek();
    }

    @Override
    public List<Integer> selectEntryLogChartMonth() {
        return entryLogMapper.selectEntryLogChartMonth();
    }

    @Override
    public int selectEntryLogMonth() {
        return entryLogMapper.selectEntryLogMonth();
    }

    @Override
    public int selectEntryLogLastMonth() {
        return entryLogMapper.selectEntryLogLastMonth();
    }

    @Override
    public int selectEntryLogIn() {
        return entryLogMapper.selectEntryLogInOut(1);
    }

    @Override
    public int selectEntryLogOut() {
        return entryLogMapper.selectEntryLogInOut(2);
    }

    @Override
    public int selectEntryLogOwner() {
        return entryLogMapper.selectEntryLogType(1);
    }

    @Override
    public int selectEntryLogTenant() {
        return entryLogMapper.selectEntryLogType(2);
    }

    @Override
    public int selectEntryLogHouse() {
        return entryLogMapper.selectEntryLogType(3);
    }

    @Override
    public int selectEntryLogWorker() {
        return entryLogMapper.selectEntryLogType(4);
    }

    @Override
    public int selectEntryLogNumsWeek() {
        return entryLogMapper.selectEntryLogNumsWeek();
    }

    @Override
    public int selectEntryLogNumsMonth() {
        return entryLogMapper.selectEntryLogNumsMonth();
    }

    /**
     * 新增log
     * 
     * @param entryLog log
     * @return 结果
     */
    @Override
    public int insertEntryLog(EntryLog entryLog)
    {
        return entryLogMapper.insertEntryLog(entryLog);
    }

    /**
     * 修改log
     * 
     * @param entryLog log
     * @return 结果
     */
    @Override
    public int updateEntryLog(EntryLog entryLog)
    {
        return entryLogMapper.updateEntryLog(entryLog);
    }

    /**
     * 批量删除log
     * 
     * @param entryids 需要删除的log主键
     * @return 结果
     */
    @Override
    public int deleteEntryLogByEntryids(String entryids)
    {
        return entryLogMapper.deleteEntryLogByEntryids(Convert.toStrArray(entryids));
    }

    /**
     * 删除log信息
     * 
     * @param entryid log主键
     * @return 结果
     */
    @Override
    public int deleteEntryLogByEntryid(Long entryid)
    {
        return entryLogMapper.deleteEntryLogByEntryid(entryid);
    }
}
