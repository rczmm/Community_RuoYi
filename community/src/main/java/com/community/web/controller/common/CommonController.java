package com.community.web.controller.common;

import com.community.common.config.CommunityConfig;
import com.community.common.config.ServerConfig;
import com.community.common.constant.Constants;
import com.community.common.core.domain.AjaxResult;
import com.community.common.utils.StringUtils;
import com.community.common.utils.file.FileUploadUtils;
import com.community.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!com.community.common.utils.file.FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(com.community.common.utils.StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = com.community.common.config.CommunityConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            com.community.common.utils.file.FileUtils.setAttachmentResponseHeader(response, realFileName);
            com.community.common.utils.file.FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                com.community.common.utils.file.FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = com.community.common.config.CommunityConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = com.community.common.utils.file.FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            com.community.common.core.domain.AjaxResult ajax = com.community.common.core.domain.AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", com.community.common.utils.file.FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return com.community.common.core.domain.AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @ResponseBody
    public com.community.common.core.domain.AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = com.community.common.config.CommunityConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(com.community.common.utils.file.FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            com.community.common.core.domain.AjaxResult ajax = com.community.common.core.domain.AjaxResult.success();
            ajax.put("urls", com.community.common.utils.StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", com.community.common.utils.StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", com.community.common.utils.StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", com.community.common.utils.StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!com.community.common.utils.file.FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(com.community.common.utils.StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = CommunityConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + com.community.common.utils.StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            com.community.common.utils.file.FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
