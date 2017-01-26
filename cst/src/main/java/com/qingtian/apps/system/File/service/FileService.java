package com.qingtian.apps.system.File.service;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.utils.StringUtils;
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

/**
 * Created by machao on 2017/1/15.
 */
@Service("fileService")
public class FileService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private static String separator = File.separator;

    public Boolean insertFile(FileInfo fileInfo, InputStream in) throws Exception {

        Calendar.getInstance();
        //获取上传基路径，从配置文件中获取
        String path = "E:" + separator + "UpAndDown";
        //按年月日来生成文件夹
        //获取当前日期
        Date date = new Date();
        String date_s = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //转换为路径格式
        date_s = StringUtils.dateToPath(date_s);
        //拼接路径
        path = path + separator + date_s + separator + fileInfo.getFileName();

        //存储数据库
        FileInfo reFileInfo = new FileInfo();
        reFileInfo.setFilePath(path);

        //存入数据库
        sqlSession.insert("File.insert", reFileInfo);

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
}
