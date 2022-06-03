package com.jsp.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;


public class XSSHttpRequestParameterAdapter extends HttpRequestParameterAdapter {

	public static <T>T execute(HttpServletRequest request, Class<T> className,
									boolean xss) throws Exception{
		
		T result = null;
		
		if(xss) {
			XSSResolver.parseXSS(request);
			
			// 의존성 확인 및 조립
			Method[] methods = className.getMethods();

			// 인스턴스 생성
			result = className.newInstance();
			
			//setter 확인
			for(Method method : methods) {
				if(method.getName().indexOf("set")==0) {
					String requestParamName = method.getName().replace("set", "");
					requestParamName = (requestParamName.charAt(0)+"").toLowerCase()
							+requestParamName.substring(1);
					
					String[] paramValues
						= (String[])request.getAttribute("XSS"+requestParamName);
					
					if(paramValues != null && paramValues.length > 0) {
						if(method.getParameterTypes()[0].isArray()) {
							method.invoke(result, new Object[] {paramValues});
						}else {
							method.invoke(result, paramValues[0]);
						}
					}
				}
			}
			
		}else {
			result = execute(request, className);
		}
		
		return result;
		
		
	}
	
	
}
