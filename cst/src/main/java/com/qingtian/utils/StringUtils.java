package com.qingtian.utils;

import java.io.File;

/**
 * Created by machao on 2016/12/31.
 */
public class StringUtils {

    /**
     * 字符为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符非空返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取方法名
     * @return
     */
    public static String getMethodName(){
        //获取方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName();
        return methodName;
    }

    /**
     * 日期格式转换为路径格式
     * 比如2016-12-08转换为2016\12\08\
     * @param date 日期
     * @return
     */
    public static String dateToPath(String date){
        String dateArray[] = date.split("-");
        String path = "";
        //定义分隔符，防止调用多次
        String separator = File.separator;
        for(int i=0;i<dateArray.length;i++){
            path += dateArray[i] + separator;
            System.out.println(path);
        }
        return path;
    }
}
