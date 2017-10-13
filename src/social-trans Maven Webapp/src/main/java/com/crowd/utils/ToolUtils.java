package com.crowd.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ToolUtils {
	
	public static void main(String args[]) 
	{ 
		String text = "dsadsad+ dasdsa!@#$%^&*()_+";
		System.out.println(ToolUtils.exchange(text)); 
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
    } /*
//    //\t 水平制表符(\u0009)
//    注：\n 回车(\u000a)
//	\t 水平制表符(\u0009)
//	\s 空格(\u0008) 
//	\r 换行(\u000d)*/
    
    public static String exchange(String str){ 
    	return str.replaceAll("\\ ","%20")
    			.replaceAll("\\+","%2B")
    			.replaceAll("\\/","%2F")
    			.replaceAll("\\?","%3F")
    			.replaceAll("\\#","%23")
    			.replaceAll("\\&","%26")
    			.replaceAll("\\=","%3D")
    			.replaceAll("\\r", "%0D")
    			.replaceAll("\\n", "%0A")
    			.replaceAll("\\t", "%09")
    			.replaceAll("\\s", "%08");
    }
   
}
