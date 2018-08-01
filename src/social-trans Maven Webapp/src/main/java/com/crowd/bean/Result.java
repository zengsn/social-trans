package com.crowd.bean;


/**
 * 供小程序接口使用的统一响应结构
 * @author ZhengWeizhi
 * @time  2018 下午5:07:02  
 * typeName Result
 * code值及含义的对应关系如下：
 * 200  请求成功
 * 100  您已领取了此任务
 * 400  此任务已经被领完了
 * 300  此任务最多领取3个，请完成后再领取新的任务！
 * 700  提交的内容与机器翻译相似度太高或太低！
 * 600  没有已完成的任务
 * 800  暂时未完成任何任务
 * 500  暂时未收任务
 * 900  删除任务失败
 * 100  修改用户信息失败
 * 1200 用户发布的任务内容为空格
 */

public class Result<T> {

	private Integer code;
	private String msg;
	private T data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	
	
	

}
