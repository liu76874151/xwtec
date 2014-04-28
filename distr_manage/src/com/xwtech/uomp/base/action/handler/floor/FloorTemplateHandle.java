/**
 * Title: FloorTemplateHandle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-6 
 * @ time 下午4:05:37
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.floor;

import java.util.HashMap;
import java.util.List;
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
import com.xwtech.uomp.base.pojo.floor.FloorTemplateBean;
import com.xwtech.uomp.base.pojo.pagemanage.PageCompInfo;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.floor.IFloorService;
import com.xwtech.uomp.base.service.floor.IFloorTemplateService;
import com.xwtech.uomp.base.service.pagemanage.IPageCompInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;

/**
 * @author zhanglu
 *
 */
@Controller("H_floorTemplate")
public class FloorTemplateHandle implements IHandler{

	@Autowired
	IFloorTemplateService floorTemplateService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFloorService floorService;
	@Autowired
	IFileuploadService fileuploadService;
	@Autowired
	IPageCompInfoService pageCompInfoService;
	@Value("${fileUpload.URL}")
	private String targetURL;
	
	/**
	 * 查询模版列表
	 * 创建日期：2013-11-7上午9:59:37
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorTemplateList(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			Page page = floorTemplateService.queryFloorTemplateList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据主键查询模版信息
	 * 创建日期：2013-11-8下午3:36:10
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorTemplateByPk(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			FloorTemplateBean floorTemplateBean = floorTemplateService.queryFloorTemplateByPk(param);
			result.setRetObj(floorTemplateBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.HANDLER_FAILED);
		}
		return result;
	}
	
	/**
	 * 更新启用状态
	 * 创建日期：2013-11-7上午10:11:33
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateUseableState(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		String state = param.get("state");
		//如果将模版置为停用状态，则判断是否有楼层使用了该模版
		if("1".equals(state))
		{
			int floorCount = floorService.queryFloorByTemplate(param);
			//如果有楼层使用，则不能停用
			if(floorCount > 0)
			{
				result.setResMsg("该模版已被楼层使用，禁止停用！");
				result.setRetCode(IResultCode.SYS_FAILED);
				result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
				return result;
			}
		}
		try {
			floorTemplateService.updateUseableState(param);
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
	 * 修改模版信息
	 * 创建日期：2013-11-8下午4:11:05
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateFloorTemplate(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		FloorTemplateBean floorTemplateBean = (FloorTemplateBean)BeanUtil.getBeanFromRequest(request, FloorTemplateBean.class);
		
		try {
			floorTemplateService.updateFloorTemplate(floorTemplateBean);
			//上传附件时定义文件名
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			fileNameMap.put("floorTemplateNum", floorTemplateBean.getTempNum());
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
	 * 添加模版
	 * 创建日期：2013-11-8下午2:19:47
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult addFloorTemplate(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		
		FloorTemplateBean floorTemplateBean = (FloorTemplateBean)BeanUtil.getBeanFromRequest(request, FloorTemplateBean.class);
		String floorTemplateNum = sequenceService.getSequence("t_floor_template_seq");
		floorTemplateBean.setTempNum(floorTemplateNum);
		
		try {
			floorTemplateService.addFloorTemplate(floorTemplateBean);
			//上传附件时定义文件名
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			fileNameMap.put("floorTemplateNum", floorTemplateNum);
			
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
	 * 删除模版
	 * 创建日期：2013-11-11上午10:42:03
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult deleteFloorTemplate(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		FloorTemplateBean floorTemplateBean = (FloorTemplateBean)BeanUtil.getBeanFromRequest(request, FloorTemplateBean.class);
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			int floorCount = floorService.queryFloorByTemplate(param);
			//如果有楼层使用，则不能停用
			if(floorCount > 0)
			{
				result.setResMsg("该模版已被楼层使用，禁止删除！");
				result.setRetCode(IResultCode.SYS_FAILED);
				result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
				return result;
			}
			floorTemplateService.deleteFloorTemplate(floorTemplateBean);
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
	 * 上传模版图片
	 * 创建日期：2013-12-18下午4:04:03
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult fileUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String fileName = request.getParameter("fileName");
		try {
			boolean flag = fileuploadService.fileuploadReName(request,
					targetURL, fileName);
			if (flag) {
				FloorTemplateBean floorTemplateBean = (FloorTemplateBean) BeanUtil
						.getBeanFromRequest(request, FloorTemplateBean.class);
				floorTemplateBean.setTempImg(targetURL + "/" + fileName);
				floorTemplateService.updateFloorTemplate(floorTemplateBean);
			}
			result.setRetObj(flag);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
     * 查询楼层组件列表
     * 创建日期：2013-11-8上午11:12:10
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:HandlerResult
     */
    public HandlerResult queryFloorCompInfoList(HandlerRequestContext context){
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);
        
        try {
			List<PageCompInfo> list = pageCompInfoService.queryPageCompInfoList(param);
			result.setRetObj(list);
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
}
