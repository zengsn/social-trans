package com.qingtian.apps.task.entity;

import java.io.Serializable;

/**
 * Created by qingtian on 2017/1/25.
 */
public class SubbmitTask implements Serializable{

//    private String taskId;
//    private String comment;
//    private String submitter;
//    private String submitterId;
//    private String receiver;
//    private String receiverId;
//    private String fileId;
//    private String finishedTime;
//    private String isReceive;
//    private String fileCode;
//    private String filePath;
//
//
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public String getFileCode() {
//        return fileCode;
//    }
//
//    public void setFileCode(String fileCode) {
//        this.fileCode = fileCode;
//    }
//
//    public String getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(String taskId) {
//        this.taskId = taskId;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public String getSubmitter() {
//        return submitter;
//    }
//
//    public void setSubmitter(String submitter) {
//        this.submitter = submitter;
//    }
//
//    public String getSubmitterId() {
//        return submitterId;
//    }
//
//    public void setSubmitterId(String submitterId) {
//        this.submitterId = submitterId;
//    }
//
//    public String getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(String receiver) {
//        this.receiver = receiver;
//    }
//
//    public String getReceiverId() {
//        return receiverId;
//    }
//
//    public void setReceiverId(String receiverId) {
//        this.receiverId = receiverId;
//    }
//
//    public String getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(String fileId) {
//        this.fileId = fileId;
//    }
//
//    public String getFinishedTime() {
//        return finishedTime;
//    }
//
//    public void setFinishedTime(String finishedTime) {
//        this.finishedTime = finishedTime;
//    }
//
//    public String getIsReceive() {
//        return isReceive;
//    }
//
//    public void setIsReceive(String isReceive) {
//        this.isReceive = isReceive;
//    }

    private static final long serialVersionUID = 1L;

    /**
     *任务id
     */
    private String taskId;

    /**
     *任务内容
     */
    private String comment;

    /**
     *任务提交者
     */
    private String submitter;

    /**
     *与P_user表的userId关联
     */
    private String submitterId;

    /**
     *附件关联标识号
     */
    private String fileCode;

    /**
     *任务截止时间
     */
    private String finishedTime;

    /**
     *是否有人领取任务,0为无人领取
     */
    private String isReceive;

    /**
     *附件id
     */
    private String fileId;

    /**
     *任务奖金
     */
    private String taskMoney;

    /**
     *翻译文本名
     */
    private String commentName;

    /**
     *领取人数
     */
    private String receivePeopleNum;

    private String filePath;

    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取属性 任务id
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    /**
     * 设置属性 任务id
     * @return taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 获取属性 任务内容
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * 设置属性 任务内容
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 获取属性 任务提交者
     * @param submitter
     */
    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }
    /**
     * 设置属性 任务提交者
     * @return submitter
     */
    public String getSubmitter() {
        return submitter;
    }

    /**
     * 获取属性 与P_user表的userId关联
     * @param submitterId
     */
    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }
    /**
     * 设置属性 与P_user表的userId关联
     * @return submitterId
     */
    public String getSubmitterId() {
        return submitterId;
    }

    /**
     * 获取属性 附件关联标识号
     * @param fileCode
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
    /**
     * 设置属性 附件关联标识号
     * @return fileCode
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * 获取属性 任务截止时间
     * @param finishedTime
     */
    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }
    /**
     * 设置属性 任务截止时间
     * @return finishedTime
     */
    public String getFinishedTime() {
        return finishedTime;
    }

    /**
     * 获取属性 是否有人领取任务,0为无人领取
     * @param isReceive
     */
    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }
    /**
     * 设置属性 是否有人领取任务,0为无人领取
     * @return isReceive
     */
    public String getIsReceive() {
        return isReceive;
    }

    /**
     * 获取属性 附件id
     * @param fileId
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    /**
     * 设置属性 附件id
     * @return fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 获取属性 任务奖金
     * @param taskMoney
     */
    public void setTaskMoney(String taskMoney) {
        this.taskMoney = taskMoney;
    }
    /**
     * 设置属性 任务奖金
     * @return taskMoney
     */
    public String getTaskMoney() {
        return taskMoney;
    }

    /**
     * 获取属性 翻译文本名
     * @param commentName
     */
    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }
    /**
     * 设置属性 翻译文本名
     * @return commentName
     */
    public String getCommentName() {
        return commentName;
    }

    /**
     * 获取属性 领取人数
     * @param receivePeopleNum
     */
    public void setReceivePeopleNum(String receivePeopleNum) {
        this.receivePeopleNum = receivePeopleNum;
    }
    /**
     * 设置属性 领取人数
     * @return receivePeopleNum
     */
    public String getReceivePeopleNum() {
        return receivePeopleNum;
    }
}
