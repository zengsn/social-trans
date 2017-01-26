package com.qingtian.apps.system.File.action;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.service.FileService;
import com.qingtian.utils.Generator;
import com.qingtian.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.workSpace.utils.JsonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by machao on 2017/1/15.
 */
@RestController
@RequestMapping("FileAction")
public class FileAction {


    //获取类名
    private  String className = this.getClass().getName();

    private  Logger logger = LoggerFactory.getLogger(className+".class");

    @Resource(name="fileService")
    private FileService fileService;

    @RequestMapping(path="/upLoadFile.do",produces = "text/html;charset=UTF-8")
    public String upLoadFile(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

            //解析请求
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //附件实体类
                List<FileInfo> fileInfos = new ArrayList<>();
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    MultipartFile multipartFile = multiRequest.getFile(iter.next());
                    InputStream in = multipartFile.getInputStream();
                    try {

                            if (null != in){
                                //获取附件名
                                String fileName = multipartFile.getOriginalFilename();
                                //
                                if ( null != fileName && !"".equals(fileName)){
                                    FileInfo fileInfo = new FileInfo();
                                    fileInfo.setFileId(Generator.getGUID());
                                    fileInfo.setFileName(fileName);
                                    fileInfo.setFileSize(String.valueOf(multipartFile.getSize()));
                                    fileInfo.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
                                    Boolean isSuccess = fileService.insertFile(fileInfo,in);
                                    if(isSuccess){
                                        fileInfos.add(fileInfo);
                                    }

                                }
                            }else{
                                logger.error(className+"==========="+ StringUtils.getMethodName()+":上传的文件不能为空");
                                return JsonUtils.genUpdateDataReturnJsonStr(true,"上传的文件不能为空");
                            }

                    } catch (Exception e) {
                        logger.error(className+"==========="+ StringUtils.getMethodName()+":操作由于异常失败"+e.getStackTrace());
                        return JsonUtils.genUpdateDataReturnJsonStr(false,"读取文件时操作由于异常失败");
                    } finally {
                        if(in!=null){
                            in.close();
                        }
                    }
                }
                //检查是否有成功上传文件
                if(fileInfos.size() > 0){
                    return JsonUtils.genUpdateDataReturnJsonStr(true,"上传文件成功",fileInfos);
                }else{
                    logger.error(className+"==========="+ StringUtils.getMethodName()+":处理失败！文件上传失败");
                    return JsonUtils.genUpdateDataReturnJsonStr(true,"文件上传失败",null);
                }
            }else{
                logger.error(className+"==========="+ StringUtils.getMethodName()+":当前表单不是文件上传表单");
                return JsonUtils.genUpdateDataReturnJsonStr(true,"当前表单不是文件上传表单");
            }
        }catch (Exception e) {
            logger.error(className+"==========="+ StringUtils.getMethodName()+":操作由于异常失败"+e.getStackTrace());
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常失败");
        }
    }
}
