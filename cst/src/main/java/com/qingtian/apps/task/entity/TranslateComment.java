package com.qingtian.apps.task.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/1.
 */
public class TranslateComment implements Serializable{

    private int commentId ;
    private String comment;
    private String fileId;
    private String commentResult;

    public String getCommentResult() {
        return commentResult;
    }

    public void setCommentResult(String commentResult) {
        this.commentResult = commentResult;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

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
