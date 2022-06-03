package com.jsp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResolver {
	

	public static void view(HttpServletResponse response, Object target) 
														throws Exception {

		// ì¶œë ¥
		ObjectMapper mapper = new ObjectMapper();

		// content Type ê²°ì •
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		// ?‚´ë³´ë‚´ê¸?
		out.println(mapper.writeValueAsString(target));

		// out ê°ì²´ë¥? ì¢…ë£Œ?•˜ê³? ?™˜?›.
		out.close();
	}
}
