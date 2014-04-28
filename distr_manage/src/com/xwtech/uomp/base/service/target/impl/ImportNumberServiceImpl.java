/**
 * Title: ImportNumberImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-7 
 * @ time 下午10:19:56
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.target.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xwtech.uomp.base.dao.org.TargetGroupInfoMapper;
import com.xwtech.uomp.base.pojo.target.TargetNumberBean;
import com.xwtech.uomp.base.service.target.IImportNumberService;
import com.xwtech.uomp.base.util.StringUtil;

/**
 * @author zhanglu
 * 
 */
@Service("importNumberService")
public class ImportNumberServiceImpl implements IImportNumberService{

	@Autowired
	TargetGroupInfoMapper targetGroupInfoMapper;
	
	public String fileupload(HttpServletRequest request,String targetURL) {

		String realPath = request.getSession().getServletContext().getRealPath(
				targetURL);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		MultipartFile file = multipartRequest.getFile("file");
		File tarPath = new File(realPath);
		
		if(!tarPath.exists()){//创建目录
			tarPath.mkdirs();
		}
		try {
			String fileName = file.getOriginalFilename();
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath, file.getOriginalFilename()));
			return fileName;
		} catch (IOException e) {
			//TODO
			return null;
		}
	}
	/**
	 * 创建Load SQL文件，并写入load的SQL信息 
	 * 创建日期：2013-12-7下午11:05:49 
	 * 修改日期： 
	 * 作者：zhanglu
	 * @param sqlLoadFile LOAD文件信息
	 * @param datFile 数据文件信息
	 * @return true：创建成功 false：创建失败
	 */
	public boolean createLoadSQLFile(File sqlLoadFile, File datFile, String groupId) {
		boolean createSucc = false;
		FileOutputStream loadOS = null;
		try {
			String loadStr = getLoadSQL(datFile.getPath(), groupId);
			loadOS = new FileOutputStream(sqlLoadFile);
			loadOS.write(loadStr.getBytes());
			loadOS.flush();
			createSucc = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != loadOS) {
				try {
					loadOS.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return createSucc;
	}

	/**
	 * 生成Load语法SQL 
	 * 创建日期：2013-12-7下午11:06:04
	 *  修改日期： 
	 *  作者：zhanglu
	 * @param dataFilePath load的数据文件
	 * @return 生成的Load语法SQL
	 */
	public String getLoadSQL(String dataFilePath, String groupId) {
		StringBuffer buf = new StringBuffer();
		buf.append("load data characterset ZHS16GBK infile '" + dataFilePath
				+ "' ");
		buf.append(" into table ").append("jsmss_target_user_info");

		buf.append(" APPEND fields terminated by ',' ");
		buf.append("(");

		buf.append("GROUP_ID constant "+groupId+"").append(", ");
		buf.append("PHONE_NUMBER");
//		buf.append("row_num ");

		buf.append(")");
		return buf.toString();
	}

	/**
	 * 方法描述：执行创建好的load文件，将数据文件导入到数据库表中
	 * 创建日期：2013-12-7下午11:41:38
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	public boolean executeLoadFileToDB(File sqlLoadFile, File repLoadLogFile, String DBURL) {
		Process proc = null;
		String executeCmd = null; // 导入执行的命令
		BufferedReader br = null;
		int uploadNum = 0;
		try {
			Runtime rt = Runtime.getRuntime();

			// 判断当前系统环境为windows还是linux,讲采用不同的调用语法
			String osName = System.getProperty("os.name").toLowerCase();
			executeCmd = "sqlldr "+DBURL;
			executeCmd += " control=" + sqlLoadFile.getPath() + " log="
					+ repLoadLogFile.getPath();
			executeCmd += " rows=10000 errors=-1 silent=all readsize=10000000 bindsize=10000000 direct=true columnarrayrows=20000 multithreading=true ";
			// 执行命令
			System.out.println("执行脚本:" + executeCmd);
			proc = rt.exec(executeCmd);
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			uploadNum = resolveLoadMsg(br, osName);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 方法描述：解析load数据到数据库中时打印出的日志信息 	
	 * 创建日期：2013-12-7下午11:42:19
	 * 修改日期：
	 * 作者：zhanglu
	 * @param br 日志读取流对象
	 * @param osName 操作系统明细
	 * @return load到数据库的数据条数
	 */
	private int resolveLoadMsg(BufferedReader br, String osName)
			throws IOException {
		int uploadNum = 0;
		String count = null; // 数据装入行数
		String delCount = null; // 数据删除行数

		StringBuilder outBuf = new StringBuilder();
		outBuf.append("操作输出信息为：\r\n");
		String line = null;
		while ((line = br.readLine()) != null) {
			if (!StringUtil.isNotEmpty(line)) {
				continue;
			}

			if (osName.toLowerCase().indexOf("windows") > -1) {
				String succFlagStr = "行加载成功";
				line = line.replaceAll(" ", "");
				line = line.replaceAll("。", "");
				System.out.println(line);
				if (line.trim().endsWith(succFlagStr)) {
					count = line.substring(0, line.indexOf(succFlagStr)).trim();
					uploadNum = StringUtil.convertIntoInt(count, 0);
				}
			} else {
				String succFlagStr = "SUCCESSFULLY LOADED.";
				if (line.trim().toUpperCase().endsWith(succFlagStr)) {
					count = line.substring(0,
							line.toUpperCase().indexOf(succFlagStr) - 5).trim();
					uploadNum = StringUtil.convertIntoInt(count, 0);
				}
			}

			outBuf.append(line).append("\r\n");
		}

		return uploadNum;
	}
	
	/**
	 * 查询目标组下的号码个数
	 */
	public int countPhoneNum(Map<String,String> paramMap){
		return targetGroupInfoMapper.queryPhoneNumber(paramMap);
	}
	
	/**
	 * 更新数据条数
	 */
	public void updatePhoneCount(Map<String, String> param){
		targetGroupInfoMapper.updatePhoneCount(param);
	}

}
