package com.qingtian.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;


public class GlobalLogHandler {
	private static Logger logger = LoggerFactory.getLogger("ERROR_LOG");

	public Object logHandler(ProceedingJoinPoint pjp) throws Throwable {
		Object[] params = pjp.getArgs();
		HttpServletRequest request = null;
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object obj = params[i];
				if (obj instanceof HttpServletRequest) {
					request = (HttpServletRequest) obj;
					break;
				}
			}
		}
		String path="";
		if(request!=null){
			path = request.getPathInfo();
		}
		System.out.println("请求路径：" + path);
		System.out.println("请求方法:" + (pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()"));
		return pjp.proceed();
	}

	public Object logAfter(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("请求方法:" + (pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "()"));
		return pjp.proceed();
	}
}
