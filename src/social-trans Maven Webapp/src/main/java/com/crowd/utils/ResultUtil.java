package com.crowd.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.crowd.bean.Result;



/**
 * 处理统一响应结构的工具类
 * @author ZhengWeizhi
 * @time  2018 下午5:22:53  
 * typeName ResultUtil
 */
public class ResultUtil {
	/*@Autowired
	private Result<Object> result;*/
	public  Result<Object> success(Result<Object> result,Object object){
		result.setCode(200);
		result.setMsg("success");
		result.setData(object);
		return result;		
	}
	public  Result<Object> success(Result<Object> result){
		success(result,true);
		return result;
	}
	
	public  Result<Object> error(Result<Object> result,Object object,String msg,Integer code){
		result.setCode(code);
		result.setMsg(msg);
		result.setData(object);
		return result;
	}
}
