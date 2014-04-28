/**
 * Title: IImportNumberService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-8 
 * @ time 下午2:35:02
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.target;

import java.io.BufferedReader;
import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhanglu
 *
 */
public interface IImportNumberService {

	String fileupload(HttpServletRequest request,String targetURL);
	boolean createLoadSQLFile(File sqlLoadFile, File datFile, String groupId);
	String getLoadSQL(String dataFilePath, String groupId);
	boolean executeLoadFileToDB(File sqlLoadFile, File repLoadLogFile, String DBURL);
	int countPhoneNum(Map<String, String> map);
	void updatePhoneCount(Map<String, String> param);
	
}
