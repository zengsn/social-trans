package com.crowd.utils;


import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crowd.trans.HttpTool;

/**
 * 向微信发起请求，获得OpenId和SessionKey
 * @author ZhengWeizhi
 * @time  2018 下午4:22:18  
 * typeName GetOpenIdFromWX
 * AppID(小程序ID) wx720555805b941f3e
   AppSecret(小程序密钥) d0a4ccd2de1e5d0657b7258567fa3ab1
 */
public class GetOpenIdFromWX {
	private static Logger logger = LoggerFactory.getLogger(HttpTool.class); // 日志记录
	public JSONObject getOpenIdAndSessionkeyFromWX(String code){
		//小程序的appSecret
		String appSecret="d0a4ccd2de1e5d0657b7258567fa3ab1";
				//appId
		String appId="wx720555805b941f3e";
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
		JSONObject jsonResult=null;
		try {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpGet request=new HttpGet(url);
			HttpResponse response=httpClient.execute(request);
		    	
			if (response.getStatusLine().getStatusCode()==200) {
				String result=EntityUtils.toString(response.getEntity());
				 jsonResult= JSONObject.fromObject(result);
			}else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		}
		return jsonResult;
	}
}
