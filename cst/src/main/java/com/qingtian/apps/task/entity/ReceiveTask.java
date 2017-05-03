package com.qingtian.apps.task.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/27.
 */
public class ReceiveTask implements Serializable{

    private static final long serialVersionUID = 1L;

    private String chilefileId;


    private double taskMoney;

    private String fileCode;


    private String comment;

    private String filePath;

    public double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public String getChilefileId() {
        return chilefileId;
    }

    public void setChilefileId(String chilefileId) {
        this.chilefileId = chilefileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     */
    private String id;

    /**
     *与P_task_subbmit表的taskId关联
     */
    private String taskId;

    /**
     *任务领取人
     */
    private String receiver;

    /**
     *与P_user表的userId关联
     */
    private String receiverId;

    /**
     *是否有人领取任务,0为无人领取
     */
    private String isReceive;

    /**
     *任务提交者
     */
    private String submitter;

    /**
     *与P_user表的userId关联
     */
    private String submitterId;

    /**
     *附件id
     */
    private String fileId;


    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    /**
     * 获取属性
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 设置属性
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 获取属性 与P_task_subbmit表的taskId关联
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    /**
     * 设置属性 与P_task_subbmit表的taskId关联
     * @return taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 获取属性 任务领取人
     * @param receiver
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    /**
     * 设置属性 任务领取人
     * @return receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 获取属性 与P_user表的userId关联
     * @param receiverId
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    /**
     * 设置属性 与P_user表的userId关联
     * @return receiverId
     */
    public String getReceiverId() {
        return receiverId;
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

}
