package com.qingtian.apps.system.File.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qingtian on 2017/2/26.
 */
public class TaskFile implements Serializable{

    //文件行数
    private int line;
    //文本内容
    private List<String> lists;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }
}
