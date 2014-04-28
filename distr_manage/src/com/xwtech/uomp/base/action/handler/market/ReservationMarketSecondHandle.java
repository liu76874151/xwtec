/**
 * Title: ReservationMarketSecondHandle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-25 
 * @ time 下午4:16:11
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.ReservationMarketSecondBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.market.IReservationMarketSecondService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/**
 * @author zhanglu
 * 
 */
@Controller("H_reservationMarketSecond")
public class ReservationMarketSecondHandle implements IHandler {
	@Autowired
	IReservationMarketSecondService reservationMarketSecondService;
	@Autowired
	ISequenceService sequenceService;

	/**
	 * 根据一级预约营销案查询二级 
	 * 创建日期：2013-10-25下午5:51:20 
	 * 修改日期： 
	 * 作者：zhanglu 
	 * TODO 
	 * return
	 */
	public HandlerResult queryReservationMarketSecondList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		param.put("city", city);
		//查询有效的，即未删除的营销案
		param.put("state", "1");
		try {
			Page page = reservationMarketSecondService
					.queryReservationMarketSecondList(param);
			List<ReservationMarketSecondBean> reservationSecondlist = (List<ReservationMarketSecondBean>)page.getRecords();
			List<ReservationMarketSecondBean> newList = new ArrayList<ReservationMarketSecondBean>();
			if(reservationSecondlist != null && reservationSecondlist.size() > 0){
				for(int i = 0;i < reservationSecondlist.size(); i++){
					ReservationMarketSecondBean rmsbean = (ReservationMarketSecondBean)reservationSecondlist.get(i);
					String isViewList = rmsbean.getIsListView();
					if("0".equals(isViewList)){
						String isViewAlink = "<a href='javascript:component.updateListView();' title='点击更新为在列表中展示'>未展示<img src='../../../resource/img/toggle_enabled.gif'/></a>";
						rmsbean.setLinkOper(isViewAlink);
					}
					else{
						String isViewAlink = "<a href='javascript:component.updateListView()' title='点击更新为在列表中不展示'>展示<img src='../../../resource/img/toggle_disabled.gif'/></a>";
						rmsbean.setLinkOper(isViewAlink);
					}
					newList.add(rmsbean);
				}
				page.setRecords(newList);
			}
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询二级营销案审核列表
	 * 创建日期：2013-11-1下午4:58:48
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult queryReservationMarketSecondAuditList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		try {
			Page page = reservationMarketSecondService
					.queryReservationMarketSecondAuditList(param);
			
			List<ReservationMarketSecondBean> reservationSecondlist = (List<ReservationMarketSecondBean>)page.getRecords();
			List<ReservationMarketSecondBean> newList = new ArrayList<ReservationMarketSecondBean>();
			if(reservationSecondlist != null && reservationSecondlist.size() > 0){
				for(int i = 0;i < reservationSecondlist.size(); i++){
					ReservationMarketSecondBean rmsbean = (ReservationMarketSecondBean)reservationSecondlist.get(i);
					String verifySate = rmsbean.getVerifyState();
					if("0".equals(verifySate)){
						String auditAlink = "待审核<a href='javascript:component.updateListView(1);' title='点击审核通过'><img src='../../../resource/img/toggle_enabled.gif'/></a>";
						auditAlink += "<a href='javascript:component.updateListView(2);' title='点击审核不通过'><img src='../../../resource/img/toggle_disabled.gif'/></a>";
						rmsbean.setLinkOper(auditAlink);
					}
					else if("1".equals(verifySate)){
						String auditAlink = "审核通过<a href='javascript:component.updateListView(2)' title='点击审核不通过'><img src='../../../resource/img/toggle_disabled.gif'/></a>";
						rmsbean.setLinkOper(auditAlink);
					}
					else if("2".equals(verifySate)){
						String auditAlink = "审核不通过<a href='javascript:component.updateListView(1)' title='点击审核通过'><img src='../../../resource/img/toggle_enabled.gif'/></a>";
						rmsbean.setLinkOper(auditAlink);
					}
					String viewOper = "<a href='javascript:component.viewOper(\""+rmsbean.getMarketSecondPkId()+"\",\""+rmsbean.getMarketFirstName()+"\")'; title='点击查看详细信息'>预览</a>";
					rmsbean.setViewOper(viewOper);
					newList.add(rmsbean);
				}
				page.setRecords(newList);
			}
			
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询某一个二级预约营销案
	 * 创建日期：2013-10-30下午7:26:39
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult queryReservationMarketSecondByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketSecondPkid = request.getParameter("marketSecondPkid");
		try {
			ReservationMarketSecondBean reservationMarketSecondBean = reservationMarketSecondService
					.queryReservationMarketSecondByPkid(marketSecondPkid);
			result.setRetObj(reservationMarketSecondBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

	/**
	 * 添加二级预约营销案
	 * 创建日期：2013-10-29上午11:22:08
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult addReservationMarketSecond(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();

		ReservationMarketSecondBean rmarketSecondBean = (ReservationMarketSecondBean) BeanUtil
				.getBeanFromRequest(request, ReservationMarketSecondBean.class);
		String remarketsecondPKId = sequenceService
				.getSequence("RESERVATION_SECOND_PKID_SEQ");
		rmarketSecondBean.setMarketSecondPkId(remarketsecondPKId);
		// 从CRM获取到的礼品信息ID
		String giftIds = request.getParameter("giftIds");
		rmarketSecondBean.setBossGiftId(giftIds);
		// 从CRM获取到的礼品信息名称
		String giftNames = request.getParameter("giftNames");
		rmarketSecondBean.setBossGiftName(giftNames);
		// 从CRM获取到业务信息ID
		String bisIds = request.getParameter("bisIds");
		rmarketSecondBean.setBizCode(bisIds);
		// 从CRM获取到的业务信息名称
		String bisNames = request.getParameter("bisNames");
		rmarketSecondBean.setBizName(bisNames);
		// 从CRM获取到的礼品和业务信息备注
		String fBz = request.getParameter("fBz");
		rmarketSecondBean.setGiftBinzInfo(fBz);
		// 二级预约营销案BOSS编码
		String fmarketLevel2Boss = request.getParameter("fmarketLevel2Boss");
		rmarketSecondBean.setBossSecondCode(fmarketLevel2Boss);

		rmarketSecondBean.setCity(userInfoBean.getUserAreaCode());
		rmarketSecondBean.setCfgUserId(userInfoBean.getLoginName());

		// 新建二级预约营销案默认为有效
		rmarketSecondBean.setState("1");
		// 新建二级预约营销案默认为待审核状态
		rmarketSecondBean.setVerifyState("0");
		// 省级审核状态默认为待审核：0
		rmarketSecondBean.setProVerifyState("0");
		// 地市级审核状态默认为待审核：0
		rmarketSecondBean.setLocalVerifyState("0");
		// 是否是目标用户营销方案默认为否：0
		rmarketSecondBean.setIsInGroup("0");
		String moneyVal = request.getParameter("moneyVal");
		if (StringUtil.isNull(moneyVal)) {
			moneyVal = "0";
		}
		rmarketSecondBean.setMoneyVal(new BigDecimal(moneyVal));
		try {
			reservationMarketSecondService
					.addReservationMarketSecond(rmarketSecondBean);
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
	 * 修改二级预约营销案
	 * 创建日期：2013-10-29上午11:27:45
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult updateReservationMarketSecond(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();

		ReservationMarketSecondBean rmarketSecondBean = (ReservationMarketSecondBean) BeanUtil
				.getBeanFromRequest(request, ReservationMarketSecondBean.class);

		try {
			reservationMarketSecondService.updateReservationMarketSecond(rmarketSecondBean);
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
	 * 删除二级预约营销案，逻辑删除
	 * 创建日期：2013-10-29上午11:31:40
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult deleteReservationMarketSecond(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String marketSecondPkid = request.getParameter("marketSecondPkid");
		try {
			reservationMarketSecondService.deleteReservationMarketSecond(marketSecondPkid);
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
	 * 更新二级预约营销案列表展示状态，0为未展示，1为展示
	 * 创建日期：2013-11-1上午11:23:08
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult updateIsViewListState(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		ReservationMarketSecondBean rmarketSecondBean = (ReservationMarketSecondBean) BeanUtil
				.getBeanFromRequest(request, ReservationMarketSecondBean.class);
		try {
			reservationMarketSecondService.updateIsViewListState(rmarketSecondBean);
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
	 * 更新二级预约营销案审核状态
	 * 创建日期：2013-11-1下午5:00:11
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult updateAuditState(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		ReservationMarketSecondBean rmarketSecondBean = (ReservationMarketSecondBean) BeanUtil
				.getBeanFromRequest(request, ReservationMarketSecondBean.class);
		try {
			reservationMarketSecondService.updateAuditState(rmarketSecondBean);
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
	 * 获取预约营销案列表
	 * 创建日期：2013-12-17下午4:45:15
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketSecondTestList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String channelNum = userInfoBean.getChannelNum();
		if(channelNum != null && !"".equals(channelNum)){
			//掌厅
			if("02".equals(channelNum)){
				channelNum = "5";
			}
			//网厅
			if("01".equals(channelNum)){
				channelNum = "4";
			}
			//短厅
			if("03".equals(channelNum)){
				channelNum = "6";
			}
			//全渠道
			if("0".equals(channelNum)){
				channelNum = param.get("channalData");
			}
			param.put("channalData", channelNum);
		}
		try {
			Page page = reservationMarketSecondService.queryMarketForTest(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}

		return result;
	}
	
	/**
	 * 更新二级预约营销案测试状态
	 * 创建日期：2013-12-17下午5:24:51
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateTestState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      ReservationMarketSecondBean marketFirstBean = (ReservationMarketSecondBean) BeanUtil
					.getBeanFromRequest(request, ReservationMarketSecondBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String channelNum = userInfoBean.getChannelNum();
		String verifyState = marketFirstBean.getTestOnlineState();
		if(channelNum != null && !"".equals(channelNum)){
			//网厅
			if("01".equals(channelNum) || "4".equals(channelNum)){
				marketFirstBean.setWtTestOnlineState(marketFirstBean.getTestOnlineState());
				marketFirstBean.setTestOnlineState(null);
			}
			//掌厅
		}
		try {
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketFirstBean.setMarketAuditBean(marketAuditBean);
			reservationMarketSecondService.updateTestState(marketFirstBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		
		return result;
		}
	
	/**
	 * 省级、地市审核列表
	 * 创建日期：2013-12-17下午7:57:59
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketSecondForVerify(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		
		String city = userInfoBean.getUserAreaCode();
		if(StringUtil.isNull(city)){
//			city = userInfoBean.getUserAreaCode();
		}
		if("0".equals(city)){//0 为江苏省
			city = "";
			param.put("proManager", "1");
		}else{
			param.put("cityManager", "1");
		}
//		param.put("city", city);
		String channelNum = userInfoBean.getChannelNum();
		if(channelNum != null && !"".equals(channelNum)){
			//掌厅
			if("02".equals(channelNum)){
				channelNum = "5";
			}
			//网厅
			if("01".equals(channelNum)){
				channelNum = "4";
			}
			//短厅
			if("03".equals(channelNum)){
				channelNum = "6";
			}
			//全渠道
			if("0".equals(channelNum)){
				channelNum = param.get("channalData");
			}
			param.put("channalData", channelNum);
		}
		try {
			Page page = reservationMarketSecondService.queryMarketForAudit(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);			
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
		}
		return result;
	}
	
	public HandlerResult updateMarketSecondForVerify(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		
		ReservationMarketSecondBean marketSecondBean = (ReservationMarketSecondBean) BeanUtil.getBeanFromRequest(request,
				ReservationMarketSecondBean.class);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String auditContent = request.getParameter("auditContent");
		String city = userInfoBean.getUserAreaCode();
		String verifyState = "";
		String auditLevel = "";
		if ("0".equals(city)) {// 0 为江苏省
			verifyState = request.getParameter("proVerifyState");
			auditLevel = "1";
		} else {
			verifyState = request.getParameter("localVerifyState");
			auditLevel = "2";
		}
		try {
			String channelNum = userInfoBean.getChannelNum();
			if ("0".equals(city)) {// 0 为江苏省
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketSecondBean.setWtProVerifyState(verifyState);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
					}else if("02".equals(channelNum) || "5".equals(channelNum)){
						marketSecondBean.setWtProVerifyState(null);
						marketSecondBean.setProVerifyState(verifyState);
						marketSecondBean.setLocalVerifyState(null);
					}
				}
				
			}else{
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketSecondBean.setWtLocalVerifyState(verifyState);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
					}else if("02".equals(channelNum) || "5".equals(channelNum)){
						marketSecondBean.setWtLocalVerifyState(null);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(verifyState);
					}
				}
			}
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketAuditBean.setAuditLevel(auditLevel);
			marketSecondBean.setMarketAuditBean(marketAuditBean);
			reservationMarketSecondService.updateMarketSecondForVerify(marketSecondBean);//更新二级营销案
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
}
