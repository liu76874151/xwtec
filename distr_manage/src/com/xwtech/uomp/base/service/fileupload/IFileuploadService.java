package com.xwtech.uomp.base.service.fileupload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IFileuploadService {

	boolean fileupload(HttpServletRequest request,String targetURL);
	boolean filesupload(HttpServletRequest request,String targetURL);
	boolean fileuploadReName(HttpServletRequest request,String targetURL, String fileName);
	boolean filesuploadReName(HttpServletRequest request,String targetURL, String[] fileName);
	boolean filesDelete(HttpServletRequest request,String targetURL, List<String> list);
}
