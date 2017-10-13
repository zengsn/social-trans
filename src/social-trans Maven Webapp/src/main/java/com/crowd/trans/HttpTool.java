package com.crowd.trans;


import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;


public class HttpTool {
	public static void main(String[] args) {
		String text = "dsadsad+ dasdsa";
		text = text.replaceAll("\\ ","%20");  
		
		System.out.println(text);
	}
	  private static Logger logger = LoggerFactory.getLogger(HttpTool.class);    //日志记录
	  
	    /**
	     * httpPost
	     * @param url  路径
	     * @param jsonParam 参数
	     * @return
	     */
	    public static JSONObject httpPost(String url,JSONObject jsonParam){
	        return httpPost(url, jsonParam, false);
	    }
	 
	    /**
	     * post请求
	     * @param url         url地址
	     * @param jsonParam     参数
	     * @param noNeedResponse    不需要返回结果
	     * @return
	     */
	    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
	        //post请求返回结果
	    	 CloseableHttpClient httpClient = HttpClients.createDefault();
	        JSONObject jsonResult = null;
	        HttpPost method = new HttpPost(url);
	        try {
	            if (null != jsonParam) {
	                //解决中文乱码问题
	                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
	                entity.setContentEncoding("UTF-8");
	                entity.setContentType("application/json");
	                System.out.println("最后：" + entity.toString());
	                method.setEntity(entity);
	            }
	            HttpResponse result = httpClient.execute(method);
	            url = URLDecoder.decode(url, "UTF-8");
	            /**请求发送成功，并得到响应**/
	            if (result.getStatusLine().getStatusCode() == 200) {
	                String str = "";
	                try {
	                    /**读取服务器返回过来的json字符串数据**/
	                    str = EntityUtils.toString(result.getEntity());
	                    if (noNeedResponse) {
	                        return null;
	                    }
	                    /**把json字符串转换成json对象**/
	                    jsonResult = JSONObject.fromObject(str);
	                } catch (Exception e) {
	                    logger.error("post请求提交失败:" + url, e);
	                }
	            }
	        } catch (IOException e) {
	            logger.error("post请求提交失败:" + url, e);
	        }
	        return jsonResult;
	    }
	 
	 
	    /**
	     * 发送get请求
	     * @param url    路径
	     * @return
	     */
	    public static JSONObject httpGet(String url){
	        //get请求返回结果
	        JSONObject jsonResult = null;
	        try {
	        	 CloseableHttpClient client = HttpClients.createDefault();
	            //发送get请求
	            HttpGet request = new HttpGet(url);
	            HttpResponse response = client.execute(request);
	 
	            /**请求发送成功，并得到响应**/
	            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	                /**读取服务器返回过来的json字符串数据**/
	                String strResult = EntityUtils.toString(response.getEntity());
	                /**把json字符串转换成json对象**/
	                jsonResult = JSONObject.fromObject(strResult);
	                url = URLDecoder.decode(url, "UTF-8");
	            } else {
	                logger.error("get请求提交失败:" + url);
	            }
	        } catch (IOException e) {
	            logger.error("get请求提交失败:" + url, e);
	        }
	        return jsonResult;
	    }
	    
	    public static String doPost(String url, Map<String, String> _params, String charset, boolean pretty) throws UnsupportedEncodingException {  
	          
	        StringBuffer response = new StringBuffer();  
	        HttpClient client = new HttpClient();  
	        PostMethod method = new PostMethod(url);  
	          
	        // 设置Http Post数据  
	        if (_params != null) {  
	            for (Map.Entry<String, String> entry : _params.entrySet()) {  
	            	System.out.println("sTRING"+String.valueOf(entry.getValue()));
	                method.setParameter(entry.getKey(), String.valueOf(entry.getValue()));  
	            }  
	        }  
	          
	        // 设置Http Post数据  方法二  
//	        if(_params != null) {  
//	            NameValuePair[] pairs = new NameValuePair[_params.size()];//纯参数了，键值对  
//	            int i = 0;  
//	            for (Map.Entry<String, Object> entry : _params.entrySet()) {  
//	                pairs[i] = new NameValuePair(entry.getKey(), String.valueOf(entry.getValue()));  
//	                i++;  
//	            }  
//	            method.addParameters(pairs);  
//	        }  
	          
	        try {  
	            client.executeMethod(method);  
	            if (method.getStatusCode() == HttpStatus.SC_OK) {  
	                // 读取为 InputStream，在网页内容数据量大时候推荐使用  
	                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),  
	                        charset));  
	                String line;  
	                while ((line = reader.readLine()) != null) {  
	                    if (pretty)  
	                        response.append(line).append(System.getProperty("line.separator"));  
	                    else  
	                        response.append(line);  
	                }  
	                reader.close();  
	            }  
	        } catch (IOException e) {  
	            System.out.println("执行HTTP Post请求" + url + "时，发生异常！");  
	            e.printStackTrace();  
	        } finally {  
	            method.releaseConnection();  
	        }  
	        System.out.println("--------------------" + response.toString());  
	        System.out.println("res"+new String(response.toString()));
	        return response.toString();  
	    }  
	    
	   }
