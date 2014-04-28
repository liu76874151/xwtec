/**
 * Title: ReservationMarketSecondServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-25 
 * @ time 下午4:20:59
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.market.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.ReservationMarketSecondMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.MarketSecondBean;
import com.xwtech.uomp.base.pojo.market.ReservationMarketSecondBean;
import com.xwtech.uomp.base.service.market.IMarketAuditService;
import com.xwtech.uomp.base.service.market.IReservationMarketSecondService;

/**
 * @author zhanglu
 *
 */
@Service("reservationMarketSecondService")
public class ReservationMarketSecondServiceImpl implements IReservationMarketSecondService{

	@Autowired
	ReservationMarketSecondMapper reservationMarketSecondMapper;
	@Autowired
	IMarketAuditService marketAuditService;
	
	/**
	 * 根据一级预约营销案主键查询二级预约营销案列表
	 */
	public Page queryReservationMarketSecondList(Map<String, String> param)
	{
		List<ReservationMarketSecondBean> list = reservationMarketSecondMapper.queryReservationMarketSecondList(param);
        int count = reservationMarketSecondMapper.queryReservationMarketSecondListCount(param);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
	}
	
	/**
	 * 保存二级预约营销案
	 */
	public void addReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean){
		reservationMarketSecondMapper.addReservationMarketSecond(reservationSecondBean);
	}
	
	/**
	 * 查询某一个二级预约营销案
	 * 创建日期：2013-10-30下午7:21:03
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public ReservationMarketSecondBean queryReservationMarketSecondByPkid(String marketSecondPkid){
		return reservationMarketSecondMapper.queryReservationMarketSecondByPkid(marketSecondPkid);
	}
	
	/**
	 * 修改二级预约营销案
	 * 创建日期：2013-10-30下午8:27:08
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public void updateReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean){
		reservationMarketSecondMapper.updateReservationMarketSecond(reservationSecondBean);
	}
	
	/**
	 * 删除二级预约营销案，状态state为0
	 * 创建日期：2013-10-31下午1:51:42
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public void deleteReservationMarketSecond(String marketSecondPkid){
		reservationMarketSecondMapper.deleteReservationMarketSecond(marketSecondPkid);
	}
	
	/**
	 * 更新二级预约营销案列表展示状态，0为未展示，1为展示
	 * 创建日期：2013-11-1上午11:19:49
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public void updateIsViewListState(ReservationMarketSecondBean reservationSecondBean){
		reservationMarketSecondMapper.updateIsViewListState(reservationSecondBean);
	}
	
	/**
	 * 更新二级预约营销案审核状态
	 * 创建日期：2013-11-1下午4:57:22
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public void updateAuditState(ReservationMarketSecondBean reservationSecondBean){
		reservationMarketSecondMapper.updateAuditState(reservationSecondBean);
	}
	
	/**
	 * 根据条件查询二级预约营销案审核列表
	 * 创建日期：2013-11-1下午3:29:00
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public Page queryReservationMarketSecondAuditList(Map<String, String> param){
		List<ReservationMarketSecondBean> list = reservationMarketSecondMapper.queryReservationMarketSecondAuditList(param);
        int count = reservationMarketSecondMapper.queryReservationMarketSecondAuditCount(param);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
	}
	
	/**
	 * 查询预约营销线上测试列表
	 */
	public Page queryMarketForTest(Map<String, String> param){
		List<ReservationMarketSecondBean> list = reservationMarketSecondMapper.queryMarketForTest(param);
		List<ReservationMarketSecondBean> newlist = new ArrayList<ReservationMarketSecondBean>();
		String channalNum = param.get("channalData");
		for(ReservationMarketSecondBean secondBean : list){
			String onlineTestState = secondBean.getTestOnlineState();
			StringBuffer testUrl = new StringBuffer();
			String wtOnlineTestState = secondBean.getWtTestOnlineState();
			//掌厅
			if("0".equals(onlineTestState) && ("5".equals(channalNum) || "02".equals(channalNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("1".equals(onlineTestState) && ("5".equals(channalNum) || "02".equals(channalNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("2".equals(onlineTestState) && ("5".equals(channalNum) || "02".equals(channalNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}
			
			//网厅
			if("0".equals(wtOnlineTestState) && ("4".equals(channalNum) || "01".equals(channalNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("1".equals(wtOnlineTestState) && ("4".equals(channalNum) || "01".equals(channalNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("2".equals(wtOnlineTestState) && ("4".equals(channalNum) || "01".equals(channalNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketSecond("
								+ secondBean.getMarketSecondPkId()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}
			
			//掌厅地市审核状态
			if("0".equals(secondBean.getLocalVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setLocalOper("待审核");
			}else if("1".equals(secondBean.getLocalVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setLocalOper("审核通过");
			}else if("2".equals(secondBean.getLocalVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setLocalOper("审核不通过");
			}
			//掌厅省级审核状态
			if("0".equals(secondBean.getProVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setProOper("待审核");
			}else if("1".equals(secondBean.getProVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setProOper("审核通过");
			}else if("2".equals(secondBean.getProVerifyState()) && ("5".equals(channalNum) || "02".equals(channalNum))){
				secondBean.setProOper("审核不通过");
			}
			
			//网厅地市审核状态
			if("0".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setLocalOper("待审核");
			}else if("1".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setLocalOper("审核通过");
			}else if("2".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setLocalOper("审核不通过");
			}
			//网厅省级审核状态
			if("0".equals(secondBean.getWtProVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setProOper("待审核");
			}else if("1".equals(secondBean.getWtProVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setProOper("审核通过");
			}else if("2".equals(secondBean.getWtProVerifyState()) && ("4".equals(channalNum) || "01".equals(channalNum))){
				secondBean.setProOper("审核不通过");
			}
			
			newlist.add(secondBean);
		}
		Page page = new Page();
		int totalRecord = reservationMarketSecondMapper.queryCountForTest(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 更新测试状态
	 */
	@Transactional
	public void updateTestState(ReservationMarketSecondBean marketMarketSecondBean){
		// 如果是测试通过，则将最终审核状态置为“审核通过”
		if (null != marketMarketSecondBean.getWtTestOnlineState()) {
			if ("1".equals(marketMarketSecondBean.getWtTestOnlineState())) {
				marketMarketSecondBean.setWtVerifyState("1");
			} else {
				marketMarketSecondBean.setWtVerifyState("4");
			}
		}
		if (null != marketMarketSecondBean.getTestOnlineState()) {
			if ("1".equals(marketMarketSecondBean.getTestOnlineState())) {
				marketMarketSecondBean.setVerifyState("1");
			} else {
				marketMarketSecondBean.setVerifyState("4");// 测试不通过
			}
		}
		reservationMarketSecondMapper.updateTestState(marketMarketSecondBean);
		MarketAuditBean marketAuditBean = marketMarketSecondBean.getMarketAuditBean();
		//营销案类型:2预约营销案
		marketAuditBean.setMarketType("2");
		//二级营销案
		marketAuditBean.setMarketLevel("2");
		//操作类型为测试
		marketAuditBean.setOperType("2");
		marketAuditBean.setMarketPkid(Long.parseLong(marketMarketSecondBean.getMarketSecondPkId()));
		marketAuditService.insert(marketAuditBean);
	}
	
	/**
	 * 省级、地市审核列表
	 * 创建日期：2013-12-17下午7:56:31
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	public Page queryMarketForAudit(Map<String, String> param){
		List<ReservationMarketSecondBean> list = reservationMarketSecondMapper.queryMarketSecondForVerify(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channelNum = param.get("channalData");
		List<ReservationMarketSecondBean> newlist = new ArrayList<ReservationMarketSecondBean>();
		for(ReservationMarketSecondBean secondBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			//掌厅
			if ("1".equals(cityLevel)) {
				if ("0".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				if ("0".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("审核不通过");
				}
			}
			
			//网厅
			if ("1".equals(cityLevel)) {
				if ("0".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				if ("0".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("审核不通过");
				}
			}
			
			//掌厅
			if("0".equals(secondBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("待测试");
			}else if("1".equals(secondBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("测试通过");
			}else if("2".equals(secondBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("测试不通过");
			}
			//网厅
			if("0".equals(secondBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("待测试");
			}else if("1".equals(secondBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("测试通过");
			}else if("2".equals(secondBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("测试不通过");
			}
			newlist.add(secondBean);
		}
		Page page = new Page();
		int totalRecord = reservationMarketSecondMapper.queryMarketSecondCountForVerify(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 省级审批
	 */
	@Transactional
	public void updateMarketSecondForVerify(ReservationMarketSecondBean marketSecondBean) {
		
		reservationMarketSecondMapper.updateMarketSecondForVerify(marketSecondBean);
		MarketAuditBean marketAuditBean = marketSecondBean.getMarketAuditBean();
//		marketAuditBean.setAutitState(marketSecondBean.getVerifyState());
		marketAuditBean.setMarketType("2");
		marketAuditBean.setMarketLevel("2");
		// 操作类型为审核
		marketAuditBean.setOperType("1");
		marketAuditBean.setMarketPkid(Long.parseLong(marketSecondBean.getMarketSecondPkId()));
		marketAuditService.insert(marketAuditBean);
	}
	
	/**
	 * 控制台二级预约营销案列表
	 * 创建日期：2014-1-7下午4:25:23
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	public Page queryMarketSecondForVerifyOnConsole(Map<String, String> param){
		List<ReservationMarketSecondBean> list = reservationMarketSecondMapper.queryMarketSecondForVerifyOnConsole(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channelNum = param.get("channalData");
		List<ReservationMarketSecondBean> newlist = new ArrayList<ReservationMarketSecondBean>();
		for(ReservationMarketSecondBean secondBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			if ("1".equals(cityLevel)) {
				//掌厅
				if ("0".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setProOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondCity("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				//掌厅
				if ("0".equals(secondBean.getProVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getProVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getProVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getLocalVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getLocalVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getLocalVerifyState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
					secondBean.setLocalOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(secondBean.getWtProVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(secondBean.getWtProVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(secondBean.getWtProVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketSecondPro("
									+ secondBean.getMarketSecondPkId()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					secondBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(secondBean.getWtLocalVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getWtLocalVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getWtLocalVerifyState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
					secondBean.setLocalOper("审核不通过");
				}
			}
			
			//掌厅
			if("0".equals(secondBean.getTestOnlineState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("待测试");
			}else if("1".equals(secondBean.getTestOnlineState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("测试通过");
			}else if("2".equals(secondBean.getTestOnlineState()) &&  ("5".equals(channelNum) || "02".equals(channelNum))){
				secondBean.setTestOper("测试不通过");
			}
			
			//网厅
			if("0".equals(secondBean.getTestOnlineState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("待测试");
			}else if("1".equals(secondBean.getTestOnlineState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("测试通过");
			}else if("2".equals(secondBean.getTestOnlineState()) &&  ("4".equals(channelNum) || "01".equals(channelNum))){
				secondBean.setTestOper("测试不通过");
			}
			String openUrl = secondBean.getViewName();
			if(openUrl.length() > 10){
				openUrl = openUrl.substring(0,7)+"...";
			}
			openUrl = "<a href='javascript:component.openWin2(\""+secondBean.getMarketSecondPkId()+"\")'>" + openUrl + "</a>";
			secondBean.setViewName(openUrl);
			
			newlist.add(secondBean);
		}
		Page page = new Page();
		int totalRecord = reservationMarketSecondMapper.queryMarketSecondCountForVerify(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
}
