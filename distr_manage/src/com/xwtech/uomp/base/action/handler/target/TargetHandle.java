/**
 * Title: TargetHandler.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-7 
 * @ time 下午7:57:00
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.target;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.org.ITargetGroupInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.service.target.IImportNumberService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 * @author zhanglu
 *
 */
@Controller("H_target")
public class TargetHandle  implements IHandler {

	@Autowired
	ITargetGroupInfoService targetGroupInfoService;
	@Autowired
	IImportNumberService importNumberService;
	@Autowired
	ISequenceService sequenceService;
	@Value("${dataUpload.URL}")
	private String targetURL;
	@Value("${loadDB.URL}")
	private String dbURL;
	
	/**
	 * 导入手机号码文件
	 * 创建日期：2013-12-10下午8:57:34
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult fileUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String groupId = request.getParameter("groupId");
		try {
			String fileName = importNumberService.fileupload(request,targetURL);
			if(fileName != null){
				// 导入到存储数据库的load脚本文件
				String realPath = request.getSession().getServletContext().getRealPath(
						targetURL);
				//System.out.println(realPath);
				File sqlLoadFile = new File(realPath + File.separator + "control" + ".sql");
				// 导入到存储数据库的数据文件
				File datFile = new File(realPath + File.separator + fileName);
				boolean createFlag = importNumberService.createLoadSQLFile(sqlLoadFile,datFile,groupId);
				if(createFlag){
					String pfileName = fileName.substring(0,fileName.indexOf("."));
					File repLoadLogFile = new File(realPath + File.separator + pfileName + ".log");
					if(!repLoadLogFile.exists()){
						repLoadLogFile.createNewFile();
					}
					boolean loadFlag = importNumberService.executeLoadFileToDB(sqlLoadFile, repLoadLogFile, dbURL);
					if(loadFlag){
						Map<String, String>map = new HashMap<String, String>();
						map.put("groupId", groupId);
						int count = targetGroupInfoService.queryPhoneNumber(map);
						map.put("importCount", String.valueOf(count));
						importNumberService.updatePhoneCount(map);
					}
				}
			}
			result.setRetObj(true);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 插入
	 * 创建日期：2013-12-10下午3:38:31
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult addGroupInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		TargetGroupInfoBean targetGroupInfoBean = (TargetGroupInfoBean)BeanUtil.getBeanFromRequest(request, TargetGroupInfoBean.class);
		String groupId = sequenceService.getSequence("JSMSS_TARGET_GROUP_INFO_SEQ");
		targetGroupInfoBean.setGroupId(new Long(groupId));
		try {
			targetGroupInfoService.addGroupInfo(targetGroupInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据条件查询目标用户组
	 * 创建日期：2013-12-7下午7:58:32
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	
	public HandlerResult queryTargetGroupInfo(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		if(param.get("city")==null){
		    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
			UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
			String city=userInfoBean.getUserAreaCode();
			   city="0".equals(city)?null:city;//--省级用户全查出来
			   if(null==city)city="";
			   param.put("cityId", city);
		}else if("0".equals(param.get("city"))){
			param.remove("city");
		}
		
		try {
			Page  page = targetGroupInfoService.queryTargetByPage(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查看目标库信息
	 * 创建日期：2013-12-10下午6:12:13
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryTargetGroupInfoByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			TargetGroupInfoBean targetGroupInfoBean = targetGroupInfoService
					.queryGroupInfoById(param);
			result.setRetObj(targetGroupInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查找手机号码
	 * 创建日期：2013-12-10下午6:46:13
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryPhonNumber(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			int count = targetGroupInfoService
					.queryPhoneNumber(param);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("count", Integer.toString(count));
			result.setRetObj(resultMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 添加单个号码
	 * 创建日期：2013-12-10下午7:05:17
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult addPhoneNumber(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		TargetGroupInfoBean targetGroupInfoBean = (TargetGroupInfoBean)BeanUtil.getBeanFromRequest(request, TargetGroupInfoBean.class);
		try {
			targetGroupInfoService.addPhoneNumber(targetGroupInfoBean);
			Map<String, String>map = new HashMap<String, String>();
			map.put("groupId", targetGroupInfoBean.getGroupId()+"");
			int count = targetGroupInfoService.queryPhoneNumber(map);
			map.put("importCount", String.valueOf(count));
			importNumberService.updatePhoneCount(map);
			
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	public HandlerResult deletePhoneNumber(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String groupId=request.getParameter("groupId");
		try {
			targetGroupInfoService.deletePhoneNumber(groupId);
			Map<String, String>map = new HashMap<String, String>();
			map.put("groupId", groupId);
			int count = targetGroupInfoService.queryPhoneNumber(map);
			map.put("importCount", String.valueOf(count));
			importNumberService.updatePhoneCount(map);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 更新用户目标组状态
	 * 创建日期：2013-12-12上午11:06:02
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateState(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			targetGroupInfoService.updateState(param);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 更新用户目标组
	 * 创建日期：2013-12-12上午11:06:02
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateGroupInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		TargetGroupInfoBean targetGroupInfoBean = (TargetGroupInfoBean)BeanUtil.getBeanFromRequest(request, TargetGroupInfoBean.class);
		try {
			targetGroupInfoService.updateGroupInfo(targetGroupInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
	
	public HandlerResult importData(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		result.setRetCode(IResultCode.SYS_SUCCESS);
		result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		return result;
	}
}
