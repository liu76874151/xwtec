package com.xwtech.uomp.base.service.fileupload.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xwtech.uomp.base.service.fileupload.IFileuploadService;

/**
 * 文件上传 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-29 下午06:42:16
 */
@Service("fileuploadService")
public class FileuploadServiceImpl implements IFileuploadService {


	/**
	 * 上传单个文件
	 */
	public boolean fileupload(HttpServletRequest request,String targetURL) {

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
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath, file.getOriginalFilename()));
			return true;
		} catch (IOException e) {
			//TODO
			return false;
		}
	}

	/**
	 * 上传多个文件
	 */
	public boolean filesupload(HttpServletRequest request,String targetURL) {
		
		String realPath = request.getSession().getServletContext().getRealPath(
				targetURL);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//获得文件
		List<MultipartFile> files = multipartRequest.getFiles("files");
		File tarPath = new File(realPath);
		
		if(!tarPath.exists()){//创建目录
			tarPath.mkdirs();
		}
		try {
			for (int i = 0; i < files.size(); i++) {
				FileUtils.copyInputStreamToFile(files.get(i).getInputStream(), new File(
						realPath, files.get(i).getOriginalFilename()));
			}
			return true;
		} catch (IOException e) {
			//TODO
			return false;
		}
		
	}
	
	/**
	 * 重命名上传图片
	 * 创建日期：2013-12-9下午2:53:36
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:boolean
	 */
	public boolean fileuploadReName(HttpServletRequest request,String targetURL, String fileName) {

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
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath, fileName));
			return true;
		} catch (IOException e) {
			//TODO
			return false;
		}
	}
	/**
	 * 重命名上传多图片
	 * @param request
	 * @param targetURL
	 * @param fileName:重命名的文件名
	 * @return
	 */
	public boolean filesuploadReName(HttpServletRequest request,String targetURL, String[] fileName) {

		String realPath = request.getSession().getServletContext().getRealPath(
				targetURL);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		List<MultipartFile> files = multipartRequest.getFiles("files");
		File tarPath = new File(realPath);
		if(!tarPath.exists()){//创建目录
			tarPath.mkdirs();
		}
		try {
			int count=fileName.length;
			for (int i = 0; i < count; i++) {
				if(StringUtils.isNotBlank(fileName[i])){
				FileUtils.copyInputStreamToFile(files.get(i).getInputStream(), new File(
						realPath, fileName[i]));}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 修改图片前 先删除老图片
	 */
	public boolean filesDelete(HttpServletRequest request,String targetURL, List<String> deleteFileName) {
		String realPath = request.getSession().getServletContext().getRealPath(
				targetURL);
		File deleteFile ;
		try {
			if(deleteFileName!=null){
			for (int i = 0; i < deleteFileName.size(); i++) {
				deleteFile=new File(realPath, deleteFileName.get(i));
				if(deleteFile.exists()){
				FileUtils.deleteQuietly(deleteFile);
				}
				
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
		}
}
