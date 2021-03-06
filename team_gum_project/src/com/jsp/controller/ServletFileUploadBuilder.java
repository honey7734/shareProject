package com.jsp.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletFileUploadBuilder {
	
	public static ServletFileUpload build(int MEMORY_THRESHOLD, 
										  int MAX_FILE_SIZE, 
		                                  int MAX_REQUEST_SIZE) {
		
		

		// ?λ‘λλ₯? ?? upload ?κ²½μ€?  ? ?©.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ???₯? ?? threshold memory ? ?©.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ?? ???₯ ?μΉ? κ²°μ .
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ?λ‘λ ??Ό? ?¬κΈ? ? ?©.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// ?λ‘λ request ?¬κΈ? ? ?©.
		upload.setSizeMax(MAX_REQUEST_SIZE);

		return upload;
	}
}
