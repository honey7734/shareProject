package com.jsp.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletFileUploadBuilder {
	
	public static ServletFileUpload build(int MEMORY_THRESHOLD, 
										  int MAX_FILE_SIZE, 
		                                  int MAX_REQUEST_SIZE) {
		
		

		// ?��로드�? ?��?�� upload ?��경설?�� ?��?��.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ???��?�� ?��?�� threshold memory ?��?��.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ?��?�� ???�� ?���? 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ?��로드 ?��?��?�� ?���? ?��?��.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// ?��로드 request ?���? ?��?��.
		upload.setSizeMax(MAX_REQUEST_SIZE);

		return upload;
	}
}
