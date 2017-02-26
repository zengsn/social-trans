package com.qingtian.apps.system.taskTranslate;

import com.qingtian.utils.Constant;

/**
 * Created by qingtian on 2017/2/20.
 */
public class SizeSet {


    /**
     * 根据文件大小来划分粒度
     * @param fileLength
     * @return
     */
    public static int getSizeSet(Long fileLength){
        if(fileLength> Constant.FILESIZE_0){
            return Constant.LEVEL_SIZE_1;
        }else{
            return Constant.LEVEL_SIZE_0;
        }
    }
}
