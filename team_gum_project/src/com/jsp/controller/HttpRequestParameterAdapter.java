package com.jsp.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestParameterAdapter {

	public static <T>T execute(HttpServletRequest request, 
								Class<T> className) throws Exception {
		//?์กด์ฑ ??ธ ๋ฐ? ์กฐ๋ฆฝ
		Method[] methods = className.getMethods();

		//?ธ?ค?ด?ค ??ฑ
		T obj = className.newInstance();

		//setter ??ธ
		for (Method method : methods) {
			if (method.getName().indexOf("set") == 0) {
				String requestParamName = method.getName().replace("set", "");
				requestParamName = (requestParamName.charAt(0) + "")
						.toLowerCase()
						+ requestParamName.substring(1);

				String[] paramValues 
						= request.getParameterValues(requestParamName);

				if (paramValues != null && paramValues.length > 0) {
					if (method.getParameterTypes()[0].isArray()) {
						method.invoke(obj, new Object[] { paramValues });
					} else {
						method.invoke(obj, paramValues[0]);
					}

				}
			}
		}

		return obj;
	}
}
