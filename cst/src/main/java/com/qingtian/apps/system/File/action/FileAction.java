package com.qingtian.apps.system.File.action;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.service.FileService;
import com.qingtian.utils.Constant;
import com.qingtian.utils.Generator;
import com.qingtian.utils.StringUtils;
import com.qingtian.utils.ToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.workSpace.utils.JsonUtils;
import org.workSpace.utils.RandomGUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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


    private String separator = File.separator;


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
                                if ( null != fileName && !"".equals(fileName)){
                                    FileInfo fileInfo = new FileInfo();
                                    //附件关联标识号
                                    fileInfo.setFileCode(new RandomGUID().toString());
                                    //附件id
                                    fileInfo.setFileId(new RandomGUID().toString());
                                    //附件名：带后缀
                                    fileInfo.setFileName(fileName);
                                    //附件大小
                                    fileInfo.setFileSize(String.valueOf(multipartFile.getSize()));
                                    //附件类型
                                    fileInfo.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
                                    //附件地址
                                    String path = ToolUtils.getPath(Constant.SOURCE_FILE_PATH) + separator + fileInfo.getFileCode();
                                    fileInfo.setFilePath(path);
                                    //是否为分割的小文件
                                    fileInfo.setChildFile("0");
                                    Boolean isSuccess = fileService.insertFile(fileInfo,in);
                                    if(isSuccess){
                                        fileInfos.add(fileInfo);
                                    }

                                }
                            }else{
                                return JsonUtils.genUpdateDataReturnJsonStr(false,"上传的文件不能为空");
                            }

                    } catch (Exception e) {
                        return JsonUtils.genUpdateDataReturnJsonStr(false,"读取文件时操作由于异常失败"+e.getMessage());
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
                    return JsonUtils.genUpdateDataReturnJsonStr(false,"文件上传失败",null);
                }
            }else{
                return JsonUtils.genUpdateDataReturnJsonStr(false,"当前表单不是文件上传表单");
            }
        }catch (Exception e) {
            return JsonUtils.genUpdateDataReturnJsonStr(false,"操作由于异常失败");
        }
    }
}
