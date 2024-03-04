package com.community.sys.service;

import com.community.sys.domain.EntryLog;

import java.util.List;

/**
 * logService接口
 *
 * @author rcz
 * @date 2024-02-22
 */
public interface IEntryLogService {
    /**
     * 查询log
     *
     * @param entryid log主键
     * @return log
     */
    public EntryLog selectEntryLogByEntryid(Long entryid);

    /**
     * 查询log列表
     *
     * @param entryLog log
     * @return log集合
     */
    public List<EntryLog> selectEntryLogList(EntryLog entryLog);

    public int selectEntryLogNums();

    public int selectEntryLogNumsDay();

    public int selectEntryLogNumsWeek();

    public int selectEntryLogNumsMonth();

    public List<Integer> selectEntryLogChartDay();

    public List<Integer> selectEntryLogChartWeek();

    public List<Integer> selectEntryLogChartMonth();

    public int selectEntryLogMonth();
    public int selectEntryLogLastMonth();
    public int selectEntryLogIn();
    public int selectEntryLogOut();
    public int selectEntryLogOwner();
    public int selectEntryLogTenant();
    public int selectEntryLogHouse();
    public int selectEntryLogWorker();

    /**
     * 新增log
     *
     * @param entryLog log
     * @return 结果
     */
    public int insertEntryLog(EntryLog entryLog);

    /**
     * 修改log
     *
     * @param entryLog log
     * @return 结果
     */
    public int updateEntryLog(EntryLog entryLog);

    /**
     * 批量删除log
     *
     * @param entryids 需要删除的log主键集合
     * @return 结果
     */
    public int deleteEntryLogByEntryids(String entryids);

    /**
     * 删除log信息
     *
     * @param entryid log主键
     * @return 结果
     */
    public int deleteEntryLogByEntryid(Long entryid);
}
