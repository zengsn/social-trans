package com.qingtian.utils;

/**
 * Created by machao on 2016/12/31.
 */
public class StringUtils {

    /**
     * 字符为空返回true
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
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
