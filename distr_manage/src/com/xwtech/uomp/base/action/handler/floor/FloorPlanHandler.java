/**
 * Title: FloorPlanHandler.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-21 
 * @ time 下午5:10:23
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.floor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockFaBean;
import com.xwtech.uomp.base.pojo.floor.FloorFaBean;
import com.xwtech.uomp.base.pojo.floor.FloorPlanBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockFaService;
import com.xwtech.uomp.base.service.floor.IFloorFaService;
import com.xwtech.uomp.base.service.floor.IFloorPlanService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 * @author zhanglu
 * 
 */
@Controller("H_floorPlan")
public class FloorPlanHandler implements IHandler {

	@Autowired
	IFloorPlanService floorPlanService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFloorFaService floorFaService;
	@Autowired
	IFloorBlockFaService floorBlockFaService;

	/**
	 * 查询某楼层可用的楼层方案信息
	 * 创建日期：2013-11-27下午2:50:58
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryUseableFloorFa(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String floorId = request.getParameter("floorId");
		
		try {
			FloorFaBean floorFaBean = floorFaService.queryFloorFaByFloorId(floorId);
			result.setRetObj(floorFaBean);
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
	 * 发布方案
	 * 创建日期：2013-11-29下午4:12:16
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult generateProgram(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();

		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String[] floorids = request.getParameterValues("floorIdArray[]");
		String channelNum = request.getParameter("channelNum");

		Map<String, Object> floorMap = new HashMap<String, Object>();
		floorMap.put("floorids", floorids);
		/**
		 * 楼层方案主表
		 */
		FloorPlanBean floorPlanBean = new FloorPlanBean();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String planId = sequenceService.getSequence("T_FLOOR_PLAN_SEQ");
		floorPlanBean.setPlanId(planId);
		floorPlanBean.setPlanNum("F_" + planId);
		floorPlanBean.setChannelNum(channelNum);
		floorPlanBean.setCreater(userInfoBean.getLoginName());
		floorPlanBean.setCreateTime(now);
		floorPlanBean.setArrayMap(floorMap);
		// 可用
		floorPlanBean.setState("0");

		try {
			floorPlanService.batchAddFloorPlan(floorPlanBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建楼层方案
	 * 创建日期：2013-11-26下午3:42:22
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	@Transactional
	public HandlerResult addFloorPlan(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();

		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();

		FloorFaBean floorFaBean = (FloorFaBean) BeanUtil.getBeanFromRequest(
				request, FloorFaBean.class);
		
		String updateFlag = request.getParameter("updateFlag");
		
		// 区块方案
		String floorBlockFaParam = request.getParameter("floorBlockFaParam");
		List<FloorBlockFaBean> floorBlockFaBeanlist = (List<FloorBlockFaBean>) JsonHelper
				.json2List(floorBlockFaParam, FloorBlockFaBean.class);

		floorFaBean.setFloorBlockFaBeanlist(floorBlockFaBeanlist);
		FloorPlanBean floorPlanBean = new FloorPlanBean();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String planId = sequenceService.getSequence("T_FLOOR_PLAN_SEQ");
		floorPlanBean.setPlanId(planId);
		floorPlanBean.setPlanNum("F_" + planId);
		floorPlanBean.setChannelNum(floorFaBean.getChannelNum());
		floorPlanBean.setCreater(userInfoBean.getLoginName());
		floorPlanBean.setCreateTime(now);
		floorFaBean.setPlanId(new Long(planId));
		// 可用
		floorPlanBean.setState("0");
		try {
			floorPlanService.addFloorPlan(floorPlanBean);
			String floorId = sequenceService.getSequence("T_FLOOR_DA_SEQ");
			floorFaBean.setFloorId(new Long(floorId));
			//创建楼层方案
			floorFaService.addFloorFa(floorFaBean);
			for(FloorBlockFaBean floorBlockFaBean : floorBlockFaBeanlist){
				floorBlockFaBean.setFloorId(floorId);
			}
			//创建楼层区块方案
			floorBlockFaService.batchInsertBlockFa(floorBlockFaBeanlist);
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
	 * 查询楼层方案信息
	 * 创建日期：2013-11-26下午4:10:12
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorPlan(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		if(!"0".equals(userInfoBean.getUserAreaCode()))
		{
			param.put("userAreaCode", userInfoBean.getUserAreaCode());
		}
		try {
			Page page = floorPlanService.queryFloorPlan(param);
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
	 * 根据主键查看楼层方案
	 * 创建日期：2013-11-26下午6:42:32
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorPlanByPkId(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String planId = request.getParameter("planId");
		
		try {
			List<FloorPlanBean> floorPlanBeanList = floorPlanService.queryFloorPlanByPk(planId);
			result.setRetObj(floorPlanBeanList);
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
	 * 根据楼层Id获取楼层区块方案
	 * 创建日期：2013-11-26下午7:14:57
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryFloorBlockByFloorId(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String floorId = request.getParameter("floorId");
		
		try {
			List<FloorBlockFaBean> list = floorBlockFaService.queryFloorBlockFa(floorId);
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
	
	/**
	 * 更改楼层区块方案采集状态
	 * 创建日期：2013-11-26下午8:28:39
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateFloorBlockCollectState(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String floorBlockFaParam = request.getParameter("floorBlockFaParam");
		List<FloorBlockFaBean> floorBlockFaBeanlist = (List<FloorBlockFaBean>) JsonHelper
				.json2List(floorBlockFaParam, FloorBlockFaBean.class);
		
		try {
			floorBlockFaService.updateCollectState(floorBlockFaBeanlist);
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
	 * 更改方案状态
	 * 创建日期：2013-11-29下午4:30:25
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult changeState(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			floorPlanService.changeState(param);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
}
