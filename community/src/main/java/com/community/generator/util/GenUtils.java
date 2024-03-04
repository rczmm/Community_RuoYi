package com.community.generator.util;

import java.util.Arrays;

import com.community.common.constant.GenConstants;
import com.community.common.utils.StringUtils;
import com.community.generator.config.GenConfig;
import com.community.generator.domain.GenTable;
import com.community.generator.domain.GenTableColumn;
import org.apache.commons.lang3.RegExUtils;

/**
 * 代码生成器 工具类
 * 
 * @author ruoyi
 */
public class GenUtils
{
    /**
     * 初始化表信息
     */
    public static void initTable(com.community.generator.domain.GenTable genTable, String operName)
    {
        genTable.setClassName(convertClassName(genTable.getTableName()));
        genTable.setPackageName(com.community.generator.config.GenConfig.getPackageName());
        genTable.setModuleName(getModuleName(com.community.generator.config.GenConfig.getPackageName()));
        genTable.setBusinessName(getBusinessName(genTable.getTableName()));
        genTable.setFunctionName(replaceText(genTable.getTableComment()));
        genTable.setFunctionAuthor(com.community.generator.config.GenConfig.getAuthor());
        genTable.setCreateBy(operName);
    }

    /**
     * 初始化列属性字段
     */
    public static void initColumnField(GenTableColumn column, GenTable table)
    {
        String dataType = getDbType(column.getColumnType());
        String columnName = column.getColumnName();
        column.setTableId(table.getTableId());
        column.setCreateBy(table.getCreateBy());
        // 设置java字段名
        column.setJavaField(com.community.common.utils.StringUtils.toCamelCase(columnName));
        // 设置默认类型
        column.setJavaType(com.community.common.constant.GenConstants.TYPE_STRING);
        column.setQueryType(com.community.common.constant.GenConstants.QUERY_EQ);

        if (arraysContains(com.community.common.constant.GenConstants.COLUMNTYPE_STR, dataType) || arraysContains(com.community.common.constant.GenConstants.COLUMNTYPE_TEXT, dataType))
        {
            // 字符串长度超过500设置为文本域
            Integer columnLength = getColumnLength(column.getColumnType());
            String htmlType = columnLength >= 500 || arraysContains(com.community.common.constant.GenConstants.COLUMNTYPE_TEXT, dataType) ? com.community.common.constant.GenConstants.HTML_TEXTAREA : com.community.common.constant.GenConstants.HTML_INPUT;
            column.setHtmlType(htmlType);
        }
        else if (arraysContains(com.community.common.constant.GenConstants.COLUMNTYPE_TIME, dataType))
        {
            column.setJavaType(com.community.common.constant.GenConstants.TYPE_DATE);
            column.setHtmlType(com.community.common.constant.GenConstants.HTML_DATETIME);
        }
        else if (arraysContains(com.community.common.constant.GenConstants.COLUMNTYPE_NUMBER, dataType))
        {
            column.setHtmlType(com.community.common.constant.GenConstants.HTML_INPUT);

            // 如果是浮点型 统一用BigDecimal
            String[] str = com.community.common.utils.StringUtils.split(com.community.common.utils.StringUtils.substringBetween(column.getColumnType(), "(", ")"), ",");
            if (str != null && str.length == 2 && Integer.parseInt(str[1]) > 0)
            {
                column.setJavaType(com.community.common.constant.GenConstants.TYPE_BIGDECIMAL);
            }
            // 如果是整形
            else if (str != null && str.length == 1 && Integer.parseInt(str[0]) <= 10)
            {
                column.setJavaType(com.community.common.constant.GenConstants.TYPE_INTEGER);
            }
            // 长整形
            else
            {
                column.setJavaType(com.community.common.constant.GenConstants.TYPE_LONG);
            }
        }

        // 插入字段（默认所有字段都需要插入）
        column.setIsInsert(com.community.common.constant.GenConstants.REQUIRE);

        // 编辑字段
        if (!arraysContains(com.community.common.constant.GenConstants.COLUMNNAME_NOT_EDIT, columnName) && !column.isPk())
        {
            column.setIsEdit(com.community.common.constant.GenConstants.REQUIRE);
        }
        // 列表字段
        if (!arraysContains(com.community.common.constant.GenConstants.COLUMNNAME_NOT_LIST, columnName) && !column.isPk())
        {
            column.setIsList(com.community.common.constant.GenConstants.REQUIRE);
        }
        // 查询字段
        if (!arraysContains(com.community.common.constant.GenConstants.COLUMNNAME_NOT_QUERY, columnName) && !column.isPk())
        {
            column.setIsQuery(com.community.common.constant.GenConstants.REQUIRE);
        }

        // 查询字段类型
        if (com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "name"))
        {
            column.setQueryType(com.community.common.constant.GenConstants.QUERY_LIKE);
        }
        // 状态字段设置单选框
        if (com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "status"))
        {
            column.setHtmlType(com.community.common.constant.GenConstants.HTML_RADIO);
        }
        // 类型&性别字段设置下拉框
        else if (com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "type")
                || com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "sex"))
        {
            column.setHtmlType(com.community.common.constant.GenConstants.HTML_SELECT);
        }
        // 文件字段设置上传控件
        else if (com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "file"))
        {
            column.setHtmlType(com.community.common.constant.GenConstants.HTML_UPLOAD);
        }
        // 内容字段设置富文本控件
        else if (com.community.common.utils.StringUtils.endsWithIgnoreCase(columnName, "content"))
        {
            column.setHtmlType(GenConstants.HTML_SUMMERNOTE);
        }
    }

    /**
     * 校验数组是否包含指定值
     * 
     * @param arr 数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue)
    {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 获取模块名
     * 
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName)
    {
        int lastIndex = packageName.lastIndexOf(".");
        int nameLength = packageName.length();
        return com.community.common.utils.StringUtils.substring(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 获取业务名
     * 
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName)
    {
        int lastIndex = tableName.lastIndexOf("_");
        int nameLength = tableName.length();
        return com.community.common.utils.StringUtils.substring(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 表名转换成Java类名
     * 
     * @param tableName 表名称
     * @return 类名
     */
    public static String convertClassName(String tableName)
    {
        boolean autoRemovePre = com.community.generator.config.GenConfig.getAutoRemovePre();
        String tablePrefix = GenConfig.getTablePrefix();
        if (autoRemovePre && com.community.common.utils.StringUtils.isNotEmpty(tablePrefix))
        {
            String[] searchList = com.community.common.utils.StringUtils.split(tablePrefix, ",");
            tableName = replaceFirst(tableName, searchList);
        }
        return com.community.common.utils.StringUtils.convertToCamelCase(tableName);
    }

    /**
     * 批量替换前缀
     * 
     * @param replacementm 替换值
     * @param searchList 替换列表
     * @return
     */
    public static String replaceFirst(String replacementm, String[] searchList)
    {
        String text = replacementm;
        for (String searchString : searchList)
        {
            if (replacementm.startsWith(searchString))
            {
                text = replacementm.replaceFirst(searchString, "");
                break;
            }
        }
        return text;
    }

    /**
     * 关键字替换
     * 
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text)
    {
        return RegExUtils.replaceAll(text, "(?:表|若依)", "");
    }

    /**
     * 获取数据库类型字段
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static String getDbType(String columnType)
    {
        if (com.community.common.utils.StringUtils.indexOf(columnType, "(") > 0)
        {
            return com.community.common.utils.StringUtils.substringBefore(columnType, "(");
        }
        else
        {
            return columnType;
        }
    }

    /**
     * 获取字段长度
     * 
     * @param columnType 列类型
     * @return 截取后的列类型
     */
    public static Integer getColumnLength(String columnType)
    {
        if (com.community.common.utils.StringUtils.indexOf(columnType, "(") > 0)
        {
            String length = StringUtils.substringBetween(columnType, "(", ")");
            return Integer.valueOf(length);
        }
        else
        {
            return 0;
        }
    }
}
