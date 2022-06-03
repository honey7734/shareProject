package com.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class DispatcherServlet extends HttpServlet {

	private HandlerMapper handlerMapper;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getInitParameter("url.properties");

		try {
			if (path != null) {
				handlerMapper = new HandlerMapper(path);
			} else {
				handlerMapper = new HandlerMapper();
			}

			System.out.println("[DispatcherServlet] handlerMapper Í∞? Ï§?ÎπÑÎêò?óà?äµ?ãà?ã§.");
		} catch (Exception e) {
			System.out.println("[DispatcherServlet] handlerMapper Í∞? ?ã§?å®?ñà?äµ?ãà?ã§.");
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ?Ç¨?ö©?ûê URI Í≤?Ï∂?
		String command = request.getRequestURI(); // contextPath ?è¨?ï®.
		if (command.indexOf(request.getContextPath()) == 0) { // contextPath ?Ç≠?†ú
			command = command.substring(request.getContextPath().length());
		}
		
		//commandHandler ?ã§?ñâ (HandlerMapper ?ùòÎ¢?  action ?ï†?ãπ)
		Action action = null;
		String view = null;
		

		if (handlerMapper != null){
			action = handlerMapper.getAction(command);
			if(action!=null) { //?ò¨Î∞îÎ•∏ ?öîÏ≤?
				try {
					view = action.process(request, response);
					
					if (view == null) {
						return;
					}
					
					request.setAttribute("viewName", view);
					InternalViewResolver.view(request, response);
					
				} catch (Exception e) {	
					e.printStackTrace();					
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}			
			
		}else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}










