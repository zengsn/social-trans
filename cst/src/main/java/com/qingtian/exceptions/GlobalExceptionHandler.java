package com.qingtian.exceptions;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.workSpace.utils.JsonUtils;


public class GlobalExceptionHandler implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger("ERROR");
	/**
	 * 
	 * 
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		try {
		     logger.error("系统异常", ex);
			 PrintWriter ow = response.getWriter();
			 StringBuffer url = new StringBuffer(); 
			 url =url.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/").append(request.getServletPath()); 
			 Map map = new HashMap();  
			 Enumeration paramNames = request.getParameterNames();  
			  while (paramNames.hasMoreElements()) {  
			    String paramName = (String) paramNames.nextElement();  
			
			    String[] paramValues = request.getParameterValues(paramName);  
			    if (paramValues.length == 1) {  
			      String paramValue = paramValues[0];  
			      if (paramValue.length() != 0) {  
			         //System.out.println("参数：" + paramName + "=" + paramValue);  
						map.put(paramName, paramValue);  
			       }  
			     }  
			   }  

			logger.error("请求路径："+	url.toString());
			logger.error("系统请求参数："+map);
			//TODO 发送邮件 或 短信
			ow.print(JsonUtils.genUpdateDataReturnJsonStr(false, "系统异常"));
			ow.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
