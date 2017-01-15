package com.qingtian.utils;

import org.workSpace.utils.RandomGUID;

/**
 * Created by machao on 2017/1/15.
 */
public class Generator {

    /**
     * 获取GUID
     */
    public static String getGUID(){
        return new RandomGUID().toString();
    }
}
