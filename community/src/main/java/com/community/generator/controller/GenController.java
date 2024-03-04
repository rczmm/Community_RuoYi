package com.community.generator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.community.common.annotation.Log;
import com.community.common.core.controller.BaseController;
import com.community.common.core.domain.AjaxResult;
import com.community.common.core.domain.CxSelect;
import com.community.common.core.page.TableDataInfo;
import com.community.common.core.text.Convert;
import com.community.common.enums.BusinessType;
import com.community.common.utils.StringUtils;
import com.community.common.utils.security.PermissionUtils;
import com.community.common.utils.sql.SqlUtil;
import com.community.generator.service.IGenTableColumnService;
import com.community.generator.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.alibaba.fastjson.JSON;
import com.community.generator.domain.GenTable;
import com.community.generator.domain.GenTableColumn;

/**
 * 代码生成 操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseController
{
    private String prefix = "tool/gen";

    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    @RequiresPermissions("tool:gen:view")
    @GetMapping()
    public String gen()
    {
        return prefix + "/gen";
    }

    /**
     * 查询代码生成列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/list")
    @ResponseBody
    public com.community.common.core.page.TableDataInfo genList(GenTable genTable)
    {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据库列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/db/list")
    @ResponseBody
    public com.community.common.core.page.TableDataInfo dataList(GenTable genTable)
    {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    /**
     * 查询数据表字段列表
     */
    @RequiresPermissions("tool:gen:list")
    @PostMapping("/column/list")
    @ResponseBody
    public com.community.common.core.page.TableDataInfo columnList(GenTableColumn genTableColumn)
    {
        com.community.common.core.page.TableDataInfo dataInfo = new TableDataInfo();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(genTableColumn);
        dataInfo.setRows(list);
        dataInfo.setTotal(list.size());
        return dataInfo;
    }

    /**
     * 导入表结构
     */
    @RequiresPermissions("tool:gen:list")
    @GetMapping("/importTable")
    public String importTable()
    {
        return prefix + "/importTable";
    }

    /**
     * 创建表结构
     */
    @GetMapping("/createTable")
    public String createTable()
    {
        return prefix + "/createTable";
    }

    /**
     * 导入表结构（保存）
     */
    @RequiresPermissions("tool:gen:list")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.IMPORT)
    @PostMapping("/importTable")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult importTableSave(String tables)
    {
        String[] tableNames = com.community.common.core.text.Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        String operName = com.community.common.core.text.Convert.toStr(com.community.common.utils.security.PermissionUtils.getPrincipalProperty("loginName"));
        genTableService.importGenTable(tableList, operName);
        return com.community.common.core.domain.AjaxResult.success();
    }

    /**
     * 修改代码生成业务
     */
    @RequiresPermissions("tool:gen:edit")
    @GetMapping("/edit/{tableId}")
    public String edit(@PathVariable("tableId") Long tableId, ModelMap mmap)
    {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> genTables = genTableService.selectGenTableAll();
        List<com.community.common.core.domain.CxSelect> cxSelect = new ArrayList<com.community.common.core.domain.CxSelect>();
        for (GenTable genTable : genTables)
        {
            if (!StringUtils.equals(table.getTableName(), genTable.getTableName()))
            {
                com.community.common.core.domain.CxSelect cxTable = new com.community.common.core.domain.CxSelect(genTable.getTableName(), genTable.getTableName() + '：' + genTable.getTableComment());
                List<com.community.common.core.domain.CxSelect> cxColumns = new ArrayList<com.community.common.core.domain.CxSelect>();
                for (GenTableColumn tableColumn : genTable.getColumns())
                {
                    cxColumns.add(new CxSelect(tableColumn.getColumnName(), tableColumn.getColumnName() + '：' + tableColumn.getColumnComment()));
                }
                cxTable.setS(cxColumns);
                cxSelect.add(cxTable);
            }
        }
        mmap.put("table", table);
        mmap.put("data", JSON.toJSON(cxSelect));
        return prefix + "/edit";
    }

    /**
     * 修改保存代码生成业务
     */
    @RequiresPermissions("tool:gen:edit")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult editSave(@Validated GenTable genTable)
    {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return com.community.common.core.domain.AjaxResult.success();
    }

    @RequiresPermissions("tool:gen:remove")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult remove(String ids)
    {
        genTableService.deleteGenTableByIds(ids);
        return com.community.common.core.domain.AjaxResult.success();
    }

    @RequiresRoles("admin")
    @com.community.common.annotation.Log(title = "创建表", businessType = com.community.common.enums.BusinessType.OTHER)
    @PostMapping("/createTable")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult create(String sql)
    {
        try
        {
            SqlUtil.filterKeyword(sql);
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
            List<String> tableNames = new ArrayList<>();
            for (SQLStatement sqlStatement : sqlStatements)
            {
                if (sqlStatement instanceof MySqlCreateTableStatement)
                {
                    MySqlCreateTableStatement createTableStatement = (MySqlCreateTableStatement) sqlStatement;
                    if (genTableService.createTable(createTableStatement.toString()))
                    {
                        String tableName = createTableStatement.getTableName().replaceAll("`", "");
                        tableNames.add(tableName);
                    }
                }
            }
            List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames.toArray(new String[tableNames.size()]));
            String operName = com.community.common.core.text.Convert.toStr(PermissionUtils.getPrincipalProperty("loginName"));
            genTableService.importGenTable(tableList, operName);
            return com.community.common.core.domain.AjaxResult.success();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return com.community.common.core.domain.AjaxResult.error("创建表结构异常");
        }
    }

    /**
     * 预览代码
     */
    @RequiresPermissions("tool:gen:preview")
    @GetMapping("/preview/{tableId}")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException
    {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return com.community.common.core.domain.AjaxResult.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @RequiresPermissions("tool:gen:code")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException
    {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @RequiresPermissions("tool:gen:code")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult genCode(@PathVariable("tableName") String tableName)
    {
        genTableService.generatorCode(tableName);
        return com.community.common.core.domain.AjaxResult.success();
    }

    /**
     * 同步数据库
     */
    @RequiresPermissions("tool:gen:edit")
    @com.community.common.annotation.Log(title = "代码生成", businessType = com.community.common.enums.BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult synchDb(@PathVariable("tableName") String tableName)
    {
        genTableService.synchDb(tableName);
        return AjaxResult.success();
    }

    /**
     * 批量生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException
    {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}