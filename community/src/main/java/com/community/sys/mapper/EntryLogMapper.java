package com.community.sys.mapper;

import com.community.sys.domain.EntryLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * logMapper接口
 *
 * @author rcz
 * @date 2024-02-22
 */
@Mapper
public interface EntryLogMapper {
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

    @Select("select count(1) from entry_log;")
    public int selectEntryLogNums();

    @Select("SELECT count(1) FROM entry_log WHERE DAY(entrytime) = DAY(CURRENT_DATE());")
    public int selectEntryLogNumsDay();

    @Select("SELECT count(1) FROM entry_log WHERE MONTH(entrytime) = MONTH(CURRENT_DATE())")
    public int selectEntryLogNumsMonth();

    @Select("SELECT count(1) FROM entry_log WHERE WEEK(entrytime) = WEEK(CURRENT_DATE())")
    public int selectEntryLogNumsWeek();

    @Select("SELECT COUNT(*) FROM entry_log WHERE DAY(entrytime) = DAY(CURRENT_DATE())\n" +
            "GROUP BY DATE_FORMAT(entrytime, '%H:00-%H:59');")
    public List<Integer> selectEntryLogChartDay();

    @Select("SELECT count(1) AS count_Log\n" +
            "FROM entry_log\n" +
            "where month(entrytime) = month(current_date())\n" +
            "GROUP BY MONTH(entrytime), WEEK(entrytime)\n" +
            "order by week(entrytime);")
    public List<Integer> selectEntryLogChartMonth();

    @Select("SELECT count(1)       AS count_Log\n" +
            "FROM entry_log\n" +
            "where WEEK(entrytime) = week(current_date())\n" +
            "GROUP BY DAY(entrytime), WEEK(entrytime)\n" +
            "order by day(entrytime)")
    public List<Integer> selectEntryLogChartWeek();

    @Select("SELECT COUNT(1)                                        as currentTime\n" +
            "FROM entry_log\n" +
            "WHERE YEAR(entrytime) = YEAR(CURDATE())\n" +
            "  AND MONTH(entrytime) = MONTH(CURDATE());")
    public int selectEntryLogMonth();

    @Select("select count(1)\n" +
            "        from entry_log\n" +
            "        where YEAR(entrytime) = YEAR(CURDATE())\n" +
            "          AND MONTH(entrytime) = MONTH(CURDATE()) - 1;")
    public int selectEntryLogLastMonth();

    @Select("SELECT count(1)\n" +
            "from entry_log\n" +
            "where entrytype = #{type};")
    public int selectEntryLogInOut(int type);


    @Select("SELECT count(1)\n" +
            "from entry_log\n" +
            "where entrypersontype = #{type};")
    public int selectEntryLogType(int type);

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
     * 删除log
     *
     * @param entryid log主键
     * @return 结果
     */
    public int deleteEntryLogByEntryid(Long entryid);

    /**
     * 批量删除log
     *
     * @param entryids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEntryLogByEntryids(String[] entryids);
}
