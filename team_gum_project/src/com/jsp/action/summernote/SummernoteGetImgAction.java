package com.jsp.action.summernote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.controller.GetUploadPath;

public class SummernoteGetImgAction implements Action {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		// ??Όλͺ?		
		String fileName = request.getParameter("fileName");
		
		// ?€?  ???₯ κ²½λ‘λ₯? ?€? .
		String savePath = GetUploadPath.getUploadPath("summernote.img");
		
		FileDownloadResolver.sendFile(fileName, savePath, request, response);
		
		return url;
	}

}
