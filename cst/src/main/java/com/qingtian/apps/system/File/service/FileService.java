package com.qingtian.apps.system.File.service;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.entity.TaskFile;
import com.qingtian.apps.system.taskTranslate.SplitFile;
import com.qingtian.utils.Constant;
import com.qingtian.utils.StringUtils;
import com.qingtian.utils.ToolUtils;
import org.apache.commons.io.IOUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by machao on 2017/1/15.
 */
@Service("fileService")
public class FileService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private static String separator = File.separator;

    public Boolean insertFile(FileInfo fileInfo, InputStream in) throws Exception {

        sqlSession.insert("File.insert", fileInfo);

        String path = fileInfo.getFilePath();
        // 创建目标文件
        File file = new File(path);
        //该路径不存在，则先创建文件夹
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //创建文件
        file.createNewFile();
        //写入文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        IOUtils.copy(in, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return true;
    }

    public Boolean saveFile(FileInfo fileInfo, InputStream in) throws Exception{
//        //1:获取文件总行数
//        SplitFile splitFile = new SplitFile();
//        //1.1:获取已经经过处理的文件
//        TaskFile taskFile = splitFile.getFileCountByIn(in);
//        //行数
//        int line = taskFile.getLine();
//        //文本内容
//        List<String> list = taskFile.getLists();
//        //2:对文件进行分割（设置任务）
////        fileInfo.setFilePath(sourceFilePath);
//
        return false;
    }
}
