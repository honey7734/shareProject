package com.jsp.action.common;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

public class SubMenuAction implements Action {
	
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		String mCode = request.getParameter("mCode");
		List<MenuVO> subMenu = null;
		
		try {
			subMenu = menuService.getSubMenuList(mCode);
			
			// Ï∂úÎ†•
			ObjectMapper mapper = new ObjectMapper();
			
			// content Type Í≤∞Ï†ï
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			// ?Ç¥Î≥¥ÎÇ¥Í∏?
			out.println(mapper.writeValueAsString(subMenu));

			// out Í∞ùÏ≤¥Î•? Ï¢ÖÎ£å?ïòÍ≥? ?ôò?õê.
			out.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// Exception Ï≤òÎ¶¨......
			throw e;	
		}
			
		return url;	
			
	}

}
