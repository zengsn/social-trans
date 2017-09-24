package com.crowd.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ToolUtils {
	
	public static void main(String args[]) 
	{ 
	Date nowTime=new Date(); 
	System.out.println(nowTime); 
	
	System.out.println(ToolUtils.dateToString(nowTime)); 
	} 
	public static String separator = File.separator;
    /**
     * 在目标地址下按年月日生成文件夹
     * @param sourceFilePath
     * @return
     */
    
    //获取文件路径
    public static String getPath(String sourceFilePath){
        //获取当前日期
        Date date = new Date();
        //转换格式
        String date_s = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //转换为路径格式
        date_s = StringUtils.dateToPath(date_s);
        //拼接路径
        String path = sourceFilePath + separator + date_s  ;
       path = path.replace("\\","\\\\");
       path =path.substring(0, path.length()-1);
        System.out.println(path);
        return path;
    }

    //
   
    public static String dateToString(Date time){ 
        SimpleDateFormat formatter; 
        formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
        String ctime = formatter.format(time); 
        return ctime; 
    } 
   
}
