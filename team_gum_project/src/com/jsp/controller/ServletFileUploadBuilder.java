package com.jsp.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletFileUploadBuilder {
	
	public static ServletFileUpload build(int MEMORY_THRESHOLD, 
										  int MAX_FILE_SIZE, 
		                                  int MAX_REQUEST_SIZE) {
		
		

		// ?—…ë¡œë“œë¥? ?œ„?•œ upload ?™˜ê²½ì„¤? • ? ?š©.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ???¥?„ ?œ„?•œ threshold memory ? ?š©.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ?„?‹œ ???¥ ?œ„ì¹? ê²°ì •.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ?—…ë¡œë“œ ?ŒŒ?¼?˜ ?¬ê¸? ? ?š©.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// ?—…ë¡œë“œ request ?¬ê¸? ? ?š©.
		upload.setSizeMax(MAX_REQUEST_SIZE);

		return upload;
	}
}
