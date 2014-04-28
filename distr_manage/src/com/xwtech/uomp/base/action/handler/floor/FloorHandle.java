/**
 * Title: FloorHandle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-5 
 * @ time 下午4:50:20
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.floor;

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
import com.xwtech.uomp.base.pojo.floor.FloorBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDsBean;
import com.xwtech.uomp.base.pojo.floor.FloorTemplateBean;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.floor.IFloorBlockContentService;
import com.xwtech.uomp.base.service.floor.IFloorBlockDaService;
import com.xwtech.uomp.base.service.floor.IFloorBlockDsService;
import com.xwtech.uomp.base.service.floor.IFloorService;
import com.xwtech.uomp.base.service.floor.IFloorTemplateService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;

/**
 * @author zhanglu
 *
 */
@Controller("H_floor")
public class FloorHandle implements IHandler{

	@Autowired
	IFloorService floorService;
	@Autowired
	IFloorTemplateService floorTemplateService;
	@Autowired
	IFloorBlockDsService floorBlockDsService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFloorBlockDaService floorBlockDaService;
	@Autowired
	IFloorBlockContentService floorBlockContentService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileUpload.URL}")
	private String targetURL;
	 
	/**
	 * 根据条件查询楼层信息列表
	 * 创建日期：2013-11-5下午5:11:28
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorList(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			Page page = floorService.queryFloorList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据渠道查询所有可用模版
	 * 创建日期：2013-11-11上午11:21:01
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorTemplateAll(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			List<FloorTemplateBean> list = floorTemplateService.queryFloorTemplateAll(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 获取区块数据源
	 * 创建日期：2013-11-11下午2:38:26
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryBlockDsList(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		try {
			List<FloorBlockDsBean> list = floorBlockDsService.queryBlockDsList();
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据主键获取楼层
	 * 创建日期：2013-11-18下午2:20:00
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult getFloorByPKid(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			FloorBean floorBean = floorService.getFloorByPKid(param);
			result.setRetObj(floorBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据楼层ID获取区块信息
	 * 创建日期：2013-11-18下午3:03:07
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryBlockByFloorId(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String,String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			List<FloorBlockDaBean> list = floorBlockDaService.queryBlockDAContentByFloorId(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 更新展示状态
	 * 创建日期：2013-11-6上午11:08:44
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
			floorService.updateState(param);
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
	 * 添加楼层
	 * 创建日期：2013-11-13下午4:37:55
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult addFloorInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		FloorBean floorbean = (FloorBean)BeanUtil.getBeanFromRequest(request, FloorBean.class);
		//区块内容信息
		String floorBlockContentParam = request.getParameter("floorBlockContentParam");
		List<FloorBlockContentBean> blockContentList = (List<FloorBlockContentBean>)JsonHelper.json2List(floorBlockContentParam, FloorBlockContentBean.class);
		//区块信息
		String floorBlockDaParam = request.getParameter("floorBlockDaParam");
		List<FloorBlockDaBean> blockDaList = (List<FloorBlockDaBean>)JsonHelper.json2List(floorBlockDaParam, FloorBlockDaBean.class);
		
		floorbean.setFloorBlockDaList(blockDaList);
		floorbean.setFloorBlockContentList(blockContentList);
		
		String floorId = sequenceService.getSequence("T_FLOOR_DA_SEQ");
		floorbean.setFloorId(floorId);
		//新增楼层默认不展示：1
		floorbean.setState("1");
		
		try {
			String contentId=floorService.addFloorInfo(floorbean);
			String[] contentIds=contentId.split("\\,");
			Map<String, String> fileNameMap = new HashMap<String, String>();
			String tempImg="";
			String ids="";
			String fileNames="";
			for (int i = 0; i < blockContentList.size(); i++) {
				tempImg=blockContentList.get(i).getContentImg();
				if(StringUtils.isNotBlank(tempImg)){
				tempImg=tempImg.substring(tempImg.indexOf("."));
				long nanoTime = System.nanoTime(); 
				fileNames=nanoTime+tempImg+",";
				ids+=contentIds[i]+",";
				}
			}
			if(StringUtils.isNotBlank(ids)){
			ids=ids.substring(0,ids.length()-1);}
			if(StringUtils.isNotBlank(fileNames)){
			fileNames=fileNames.substring(0,fileNames.length()-1);}
			fileNameMap.put("fileNames", fileNames);
			fileNameMap.put("contentIds", ids);
			result.setRetObj(fileNameMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 修改楼层信息
	 * 创建日期：2013-11-19上午11:04:02
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateFloorInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		FloorBean floorbean = (FloorBean)BeanUtil.getBeanFromRequest(request, FloorBean.class);
		//区块内容信息
		String floorBlockContentParam = request.getParameter("floorBlockContentParam");
		List<FloorBlockContentBean> blockContentList = (List<FloorBlockContentBean>)JsonHelper.json2List(floorBlockContentParam, FloorBlockContentBean.class);
		//区块信息
		String floorBlockDaParam = request.getParameter("floorBlockDaParam");
		List<FloorBlockDaBean> blockDaList = (List<FloorBlockDaBean>)JsonHelper.json2List(floorBlockDaParam, FloorBlockDaBean.class);
		
		floorbean.setFloorBlockDaList(blockDaList);
		floorbean.setFloorBlockContentList(blockContentList);
		
		//新增楼层默认不展示：1
		floorbean.setState("1");
		
		try {
			String contentId=floorService.updateFloorInfo(floorbean);
			String[] contentIds=contentId.split("\\,");
			Map<String, String> fileNameMap = new HashMap<String, String>();
			String tempImg="";
			String ids="";
			String fileNames="";
			for (int i = 0; i < blockContentList.size(); i++) {
				tempImg=blockContentList.get(i).getContentImg();
				if(StringUtils.isNotBlank(tempImg)){
				tempImg=tempImg.substring(tempImg.indexOf("."));
				long nanoTime = System.nanoTime(); 
				fileNames+=nanoTime+tempImg+",";
				ids+=contentIds[i]+",";
				}
			}
			if(StringUtils.isNotBlank(ids)){
			ids=ids.substring(0,ids.length()-1);}
			if(StringUtils.isNotBlank(fileNames)){
			fileNames=fileNames.substring(0,fileNames.length()-1);}
			fileNameMap.put("fileNames", fileNames);
			fileNameMap.put("contentIds", ids);
			result.setRetObj(fileNameMap);
			
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
	 * 删除楼层信息
	 * 创建日期：2013-11-26下午2:40:24
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult deleteFloorInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String floorId = request.getParameter("floorId");
		try {
			floorService.deleteFloorInfo(floorId);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
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
		//区块内容信息
		String fileName = request.getParameter("fileNames");
		String contentId = request.getParameter("contentIds");
		String[] fileNames=fileName.split("\\,"); 
		String[] contentIds=contentId.split("\\,"); 
		 try {
			fileuploadService.filesuploadReName(request, targetURL, fileNames);
			floorBlockContentService.updateBlockImg(contentIds,fileNames);
				result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setRetCode(IResultCode.SYS_FAILED);
			}
			return result;
			}
	
}
