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

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.content.ContentChannelBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.pojo.pagemanage.PageInfo;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.content.IContentChannelService;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.pagemanage.IPageInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
/**
 *@ClassName:ContentHandler.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-6 time：上午09:50:08
 *@version 1.0
 */
@Controller("H_content")
public class ContentChannelHandler implements IHandler {
	@Autowired
	IContentChannelService contentChannelService;
	@Autowired
	IPageInfoService pageInfoService ;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileZQUpload.CHANURL}")
	private String targetURL;
	/**
	 * 查询专区频道
	 * @param context
	 * @return
	 */
	public HandlerResult queryAllChannels(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			Page page = contentChannelService.queryAllChannels(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	}
	/**
	 * 保存专区频道
	 * @param context
	 * @return
	 */
	public HandlerResult saveChannel(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String sortNums = RequestUtil.getString(request, "sortNum");
	    String sortNum[]=sortNums.split("\\.");
		ContentChannelBean contentChannelBean=(ContentChannelBean)BeanUtil.getBeanFromRequest(request, ContentChannelBean.class);
		String chanId=sequenceService.getSequence("T_CONTENT_CHANNEL_PKID_SEQ"); 
		contentChannelBean.setChanId(Long.valueOf(chanId));
		contentChannelBean.setChanNum(chanId);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    String creater=userInfoBean.getUserName();
	    contentChannelBean.setCreater(creater);
	    contentChannelBean.setUpdater(creater);
		
		try {
			int flag =contentChannelService.saveChannel(contentChannelBean);
			if(1==flag){
				contentChannelService.sort(sortNum, chanId);
			}
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			fileNameMap.put("chanId", contentChannelBean.getChanId()+"");
			result.setRetObj(fileNameMap);
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
	 * 查询子频道
	 * @param context
	 * @return
	 */
	public HandlerResult querySubchannel(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String parentId = RequestUtil.getString(request, "parentId");
	    try {
	    	Page page = contentChannelService.querySubchannel(parentId);
			result.setRetObj(page);
	    	result.setResMsg(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			result.setResMsg(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
		}
	/**
	 * 根据chanid查询一条记录
	 * @param context
	 * @return
	 */
	public HandlerResult queryChannelByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String chanId = RequestUtil.getString(request, "chanId");
	    try {
			ContentChannelBean  contentChannelBean=contentChannelService.queryChannelByPkid(chanId);
			result.setRetObj(contentChannelBean);
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
	 * 修改一条记录
	 * @param context
	 * @return
	 */
	public HandlerResult updateByPrimaryKey(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String sortNums = RequestUtil.getString(request, "sortNum");
	    String logo = RequestUtil.getString(request, "logo");//--新文件
	    String fileName = RequestUtil.getString(request, "fileName");//--老文件
	    String sortNum[]=null;
	    if(StringUtils.isNotBlank(sortNums)){
	    sortNum=sortNums.split("\\.");}
	   // for (int i = 0; i < sortNum.length; i++) {
			//System.out.println(sortNum[i]);
		//}
		ContentChannelBean contentChannelBean=(ContentChannelBean)BeanUtil.getBeanFromRequest(request, ContentChannelBean.class);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    String user=userInfoBean.getUserName();
	    contentChannelBean.setUpdater(user);
	    try {
			int flag=contentChannelService.updateByPrimaryKey(contentChannelBean);
			if(1==flag&&sortNum!=null){
				contentChannelService.sort(sortNum, "");
			}
			List<String> deleteFileName = new ArrayList<String>();
			Map<String, String> fileNameMap = new HashMap<String, String>();
			if (StringUtils.isNotBlank(logo)) {
				
				fileNameMap.put("logo", logo);
			}
			
			if(StringUtils.isNotBlank(fileName)){
				fileName=fileName.substring(fileName.lastIndexOf("/")+1);
				deleteFileName.add(fileName);
				fileuploadService.filesDelete(request, targetURL,
						deleteFileName);
			}
			String names = "";
			long nanoTime;
			String tempName = "";
			for (Map.Entry<String, String> entry : fileNameMap.entrySet()) {
				tempName = entry.getValue();
				nanoTime = System.nanoTime();
				names = Long.toString(nanoTime)+ tempName.substring(tempName.indexOf("."));
				fileNameMap.put(entry.getKey(), names);
				
			}

			fileNameMap.put("chanId", RequestUtil.getString(request, "chanId"));
			result.setRetObj(fileNameMap);
			  result.setRetCode(IResultCode.SYS_SUCCESS);
	          result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	          result.setRetCode(IResultCode.SYS_FAILED);
	          result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;}

	
	public HandlerResult querryChannelByChannal(HandlerRequestContext context) {
		 HandlerResult result = HandlerResult.newInstance();
		    HttpServletRequest request = context.getRequest();
		    String channelNum = RequestUtil.getString(request, "channelNum");
	        Map<String, Object> menuMap = new HashMap<String, Object>();
	        try {
	        	List<TreeNode> list =contentChannelService.querryChannelByChannal(channelNum);
	        	
	        	TreeNode titleTree = new TreeNode();
	            titleTree.setId("-1");
	            titleTree.setPid("-2");
	            titleTree.setText("频道管理");
	            Map<String, Object> map=new HashMap<String, Object>();
	            map.put("name", "CHANID");
	            map.put("content", "-1");
	            List<Map<String, Object>> userdata=new ArrayList<Map<String,Object>>();
	            userdata.add(map);
				titleTree.setUserdata(userdata);
	            TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list, titleTree);
	 	        menuMap.put("root", root);
	 	        result.setRetObj(menuMap);
	 	       result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setRetCode(IResultCode.SYS_FAILED);
			}
	       
		return result;}
	public HandlerResult querryTMPList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
		//pageInfoService
	    try {
	    	List<PageInfo> list=pageInfoService.queryPageInfoList(param);
	    	result.setRetObj(list);
	    	  result.setRetCode(IResultCode.SYS_SUCCESS);
	      } catch (Exception e) {
	    	  e.printStackTrace();
	          result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	
	/**
	 * 上传多个图片
	 * @param context
	 * @return
	 */
	public HandlerResult filesuploadReName(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		 Map<String, String> param = new HashMap<String, String>();
		 ContentChannelBean contentChannelBean=new ContentChannelBean();
		String logo=RequestUtil.getString(request, "fileName");
		String chanId = RequestUtil.getString(request, "chanId");
		contentChannelBean.setChanId(Long.valueOf(chanId));
		contentChannelBean.setLogo(targetURL+"/"+logo);
		try {
			fileuploadService.filesuploadReName(request, targetURL, new String[]{logo});
			contentChannelService.updateByPrimaryKey(contentChannelBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	
	
}
