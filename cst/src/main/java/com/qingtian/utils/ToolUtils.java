package com.qingtian.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qingtian on 2017/2/26.
 */
public class ToolUtils {

    public static String separator = File.separator;
    /**
     * 在目标地址下按年月日生成文件夹
     * @param sourceFilePath
     * @return
     */
    public static String getPath(String sourceFilePath){
        //获取当前日期
        Date date = new Date();
        //转换格式
        String date_s = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //转换为路径格式
        date_s = StringUtils.dateToPath(date_s);
        //拼接路径
        String path = sourceFilePath + separator + date_s  ;
        return path;
    }



    public static void main(String[] args) {
        System.out.println(ToolUtils.getPath("E://a"));
    }
}
