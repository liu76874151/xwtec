package com.xwtech.uomp.base.action.handler.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.content.ContentAttachmentBean;
import com.xwtech.uomp.base.pojo.content.ContentDocBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.content.IContentAttachmentService;
import com.xwtech.uomp.base.service.content.IContentDocService;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 *@ClassName:ContentDocHandler.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-8 time：下午02:15:30
 *@version 1.0
 */
@Controller("H_contentDoc")
public class ContentDocHandler implements IHandler {
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IContentDocService contentDocService;
	@Autowired
	IContentAttachmentService contentAttachmentService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileZQUpload.URL}")
	private String targetURL;
	/**
	 * 查询频道下的内容
	 * @param context
	 * @return
	 */
	public HandlerResult queryContentDocs(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    try {
			Page page =contentDocService.queryContentDocs(param);
			result.setRetObj(page);
			result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
		}
	public HandlerResult saveContentDoc(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    List<ContentAttachmentBean> contentAttachmentBeanList =new ArrayList<ContentAttachmentBean>();
	    List<ContentAttachmentBean> contentAttachmentBeanList1 =new ArrayList<ContentAttachmentBean>();
	    String attach = RequestUtil.getString(request, "attach");
	  
	   contentAttachmentBeanList= JSONArray.parseArray(attach,ContentAttachmentBean.class);
	    String sortNums = RequestUtil.getString(request, "sortNum");
	    String sortNum[]=sortNums.split("\\.");
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    String creater=userInfoBean.getUserName();
	    String pkid=sequenceService.getSequence("T_CONTENT_DOC_PKID_SEQ");
	    ContentDocBean contentDocBean=(ContentDocBean)BeanUtil.getBeanFromRequest(request, ContentDocBean.class);
	    contentDocBean.setCreater(creater);
	    contentDocBean.setContentAttachmentBeanList(contentAttachmentBeanList);
	    contentDocBean.setDocId(pkid);
	    //重命名文件
	    String tempLogo="",interfaceDir="",attachDir="";
	    ContentDocBean contentDocBean1=new ContentDocBean();
	    tempLogo=contentDocBean.getLogo();
		if(StringUtils.isNotBlank(tempLogo)){
			tempLogo=tempLogo.substring(tempLogo.indexOf("."));
			long nanoTime = System.nanoTime(); 
			tempLogo=nanoTime+tempLogo;
		}
	    try {
	    	    List pkidList=contentDocService.saveContentDoc(contentDocBean);
	    		contentDocService.sort(sortNum, pkid);
	    		for (int i = 0; i < contentAttachmentBeanList.size(); i++) {
	    			ContentAttachmentBean AttachBean1=new ContentAttachmentBean();
	    			interfaceDir=contentAttachmentBeanList.get(i).getInterfaceDir();
	    			attachDir=contentAttachmentBeanList.get(i).getAttachDir();
	    			if(StringUtils.isNotBlank(interfaceDir)){
	    				interfaceDir=interfaceDir.substring(interfaceDir.indexOf("."));
	    				long nanoTime = System.nanoTime(); 
	    				interfaceDir=nanoTime+interfaceDir;
	        		}
	    			if(StringUtils.isNotBlank(attachDir)){
	    				attachDir=attachDir.substring(attachDir.indexOf("."));
	    				long nanoTime = System.nanoTime(); 
	    				attachDir=nanoTime+attachDir;
	        		}
	    			AttachBean1.setAttachDir(attachDir);
	    			AttachBean1.setInterfaceDir(interfaceDir);
	    			AttachBean1.setAttachId(pkidList.get(i).toString());
	    			AttachBean1.setAttachName(contentAttachmentBeanList.get(i).getAttachName());
	    			AttachBean1.setAttachId(pkidList.get(i).toString());
	    			AttachBean1.setAttachUrl(contentAttachmentBeanList.get(i).getAttachUrl());
	    			AttachBean1.setType(contentAttachmentBeanList.get(i).getType());
	    			contentAttachmentBeanList1.add(AttachBean1);
	    		}	
	    		contentDocBean1.setLogo(tempLogo);
	    		contentDocBean1.setDocId(pkid);
	    		contentDocBean1.setContentAttachmentBeanList(contentAttachmentBeanList1);
	    	
	    	result.setRetObj(contentDocBean1);
	    	result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
		
	}
	/**
	 * 查询一条记录
	 * @param context
	 * @return
	 */
	public HandlerResult queryOneContentDoc(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    try {
			ContentDocBean contentDocBean=contentDocService.queryOneContentDoc(param);
			result.setRetObj(contentDocBean);
			result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
		
	}
	/**
	 * 修改
	 * @param context
	 * @return
	 */
	public HandlerResult updateBydocId(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String sortNums = RequestUtil.getString(request, "sortNum");
	    List<ContentAttachmentBean> contentAttachmentBeanList =new ArrayList<ContentAttachmentBean>();
	    List<ContentAttachmentBean> contentAttachmentBeanList1 =new ArrayList<ContentAttachmentBean>();
	    String attach = RequestUtil.getString(request, "attach");
	   contentAttachmentBeanList= JSONArray.parseArray(attach,ContentAttachmentBean.class);
	    String sortNum[]=sortNums.split("\\.");
	    ContentDocBean contentDocBean=(ContentDocBean)BeanUtil.getBeanFromRequest(request, ContentDocBean.class);
	    contentDocBean.setContentAttachmentBeanList(contentAttachmentBeanList);
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    String user=userInfoBean.getUserName();
	    contentDocBean.setUpdater(user);
	    //重命名文件
	    List<String> deleteFileName = new ArrayList<String>();
	    Map<String,Object> retMap=new HashMap<String, Object>();
	   
	    String tempLogo="",interfaceDir="",attachDir="";
	  
	    tempLogo=contentDocBean.getLogo();
		if(StringUtils.isNotBlank(tempLogo)){
			deleteFileName.add(tempLogo.substring(tempLogo.lastIndexOf("/")+1));
			tempLogo=tempLogo.substring(tempLogo.indexOf("."));
			long nanoTime = System.nanoTime(); 
			tempLogo=nanoTime+tempLogo;}
	    
	    
	    try {
	    	contentDocBean.setLogo(tempLogo);
	    	contentDocService.updateBydocId(contentDocBean,false);
			contentDocService.sort(sortNum, "");
			//--重命名上传文件
				for (int i = 0; i < contentAttachmentBeanList.size(); i++) {
					  ContentAttachmentBean AttachBean1=new ContentAttachmentBean();
	    			interfaceDir=contentAttachmentBeanList.get(i).getInterfaceDir();
	    			attachDir=contentAttachmentBeanList.get(i).getAttachDir();
	    			if(StringUtils.isNotBlank(interfaceDir)){
	    				deleteFileName.add(interfaceDir.substring(interfaceDir.lastIndexOf("/")+1));
	    				interfaceDir=interfaceDir.substring(interfaceDir.indexOf("."));
	    				long nanoTime = System.nanoTime(); 
	    				interfaceDir=nanoTime+interfaceDir;
	        		}
	    			if(StringUtils.isNotBlank(attachDir)){
	    				deleteFileName.add(attachDir.substring(attachDir.lastIndexOf("/")+1));
	    				attachDir=attachDir.substring(attachDir.indexOf("."));
	    				long nanoTime = System.nanoTime(); 
	    				attachDir=nanoTime+attachDir;
	        		}
	    			AttachBean1.setAttachDir(attachDir);
	    			AttachBean1.setInterfaceDir(interfaceDir);
	    			AttachBean1.setAttachId(contentAttachmentBeanList.get(i).getAttachId());
	    			AttachBean1.setAttachName(contentAttachmentBeanList.get(i).getAttachName());
	    			AttachBean1.setAttachUrl(contentAttachmentBeanList.get(i).getAttachUrl());
	    			AttachBean1.setType(contentAttachmentBeanList.get(i).getType());
	    			contentAttachmentBeanList1.add(AttachBean1);
	    		}	
	    		retMap.put("logo",tempLogo);
	    		retMap.put("docId",contentDocBean.getDocId());
	    		retMap.put("isUpdate","true");
	    	    retMap.put("attach", contentAttachmentBeanList1);
	    		fileuploadService.filesDelete(request, targetURL,deleteFileName);
	    		result.setRetObj(retMap);
			result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
	    return result;}
	/**
	 * 删除
	 * @param context
	 * @return
	 */
	public HandlerResult updateStateBydocId(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    try {
	    	contentDocService.updateStateBydocId(param);
	    	result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
	    
		return result;
		}
	/**
	 * 删除
	 * @param context
	 * @return
	 */
	public HandlerResult deleteBydocId(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String pkid = RequestUtil.getString(request, "pkid");
	    try {
	    	contentDocService.deleteBydocId(pkid);
	    	result.setResMsg(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResMsg(IResultCode.SYS_FAILED);
		}
	    
		return result;}
	
	public HandlerResult filesuploadReName(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		List<ContentAttachmentBean> contentAttachmentBeanList =new ArrayList<ContentAttachmentBean>();
		String logo=request.getParameter("hiddenLogo");
		String docId=request.getParameter("hiddenDocId");
		String hiddenIsUpdate=request.getParameter("hiddenIsUpdate");
		if(StringUtils.isBlank(logo)){
			logo="";
		}
		contentAttachmentBeanList= JSONArray.parseArray(request.getParameter("hiddenAttach"),ContentAttachmentBean.class);
		String fileName=logo+",";
		for(ContentAttachmentBean contentAttachmentBean:contentAttachmentBeanList){//---注意fileName加的顺序和 multipartRequest.getFiles("files");一致(页面展示顺序)
			fileName+=contentAttachmentBean.getAttachDir()+",";
			fileName+=contentAttachmentBean.getInterfaceDir()+",";
		}
		if(fileName.length()>0){
			fileName=fileName.substring(0,fileName.length()-1);
		}
		String[] fileNames=fileName.split("\\,",-1); 
		 try {
			fileuploadService.filesuploadReName(request, targetURL, fileNames);
			ContentDocBean contentDocBean = new ContentDocBean();
			contentDocBean.setLogo(logo);
			contentDocBean.setDocId(docId);
			if(hiddenIsUpdate!=null&&"true".equals(hiddenIsUpdate)){
			contentAttachmentService.updateAttachByDocId(contentAttachmentBeanList);
			}else{
				contentDocBean.setContentAttachmentBeanList(contentAttachmentBeanList);
				contentDocService.updateBydocId(contentDocBean,true);
			}
			result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setRetCode(IResultCode.SYS_FAILED);
			}
		
		return result;
		}
}
