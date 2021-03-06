package com.jsp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadResolver {

	public static void sendFile(String fileName, String savedPath, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String filePath = savedPath + File.separator + fileName;

		// ë³´ë¼ ??¼ ?¤? .
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		// ??¼ ?¬ë§·ì¼ë¡? MIMEë¥? ê²°ì ??¤.
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		// response ?? .
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		String headerKey = "Content-Disposition";
		// ?ê¸?ê¹¨ì§ ë°©ì? : ISO-8859-1 
		String sendFileName = 
		MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$");
		String header = request.getHeader("User-Agent");
		if (header.contains("MSIE")) {
			sendFileName = URLEncoder.encode(sendFileName, "UTF-8");
		} else {
			sendFileName = new String(sendFileName.getBytes("utf-8"),
				"ISO-8859-1");
		}
		String headerValue = String.format("attachment; filename=\"%s\"", 
				sendFileName);
		response.setHeader(headerKey, headerValue);
		
		// ??¼ ?´ë³´ë´ê¸?
		OutputStream outStream = response.getOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		} finally {

			outStream.close();
			inStream.close();
		}
	}
}










