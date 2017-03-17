package com.qingtian.utils;

import com.qingtian.apps.system.menu.entity.*;
import com.qingtian.apps.system.menu.entity.Menu;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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


    public static List<Map<String,String>> tranJsonData(List<Menu> list){
        List<Map<String,String>> reList = new ArrayList<>();
        Menu menu = null;
        Map<String,String> map = null;
        for(int i=0;i<list.size();i++){
//            menu = new Menu();
//            menu.setId(list.get(i).getId());
//            menu.setMenuName(list.get(i).getMenuName());
//            menu.setParentId(list.get(i).getParentId());
            map = new HashMap<String,String>();
            map.put("id",list.get(i).getId());
            map.put("pId",list.get(i).getParentId());
            map.put("name",list.get(i).getMenuName());
            reList.add(map);
        }
        return reList;
    }


    public static void main(String[] args) {
        System.out.println(ToolUtils.getPath("E://a"));
    }
}
