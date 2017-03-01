package com.qingtian.apps.task.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/1.
 */
public class TranslateComment implements Serializable{

    private int commentId ;
    private String comment;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
