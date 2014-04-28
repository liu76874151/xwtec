package com.xwtech.uomp.base.service.market.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.IReservationFirstDao;
import com.xwtech.uomp.base.dao.market.ReservationFirstBeanMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.ReservationFirstBean;
import com.xwtech.uomp.base.service.market.IMarketAuditService;
import com.xwtech.uomp.base.service.market.IReservationFirstService;

/**
 *类描述：
 * 
 * @author: xsw
 *@date： 日期：2013-10-24 时间：下午04:09:37
 *@version 1.0
 */
@Service("reservationFirstService")
public class ReservationFirstServiceImpl implements IReservationFirstService {
	@Autowired
	ReservationFirstBeanMapper ReservationFirstBeanMapper;
	@Autowired
	IReservationFirstDao reservationFirstDao;
	@Autowired
	IMarketAuditService marketAuditService;

	public Page queryReservationFirstList(Map<String, String> map) {
		List<ReservationFirstBean> list = ReservationFirstBeanMapper
				.queryReservationFirstList(map);
		int count = ReservationFirstBeanMapper.queryReservationFirstListCount(map);
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public Page queryAllMarketOrder(String city) {
		List<ReservationFirstBean> list = ReservationFirstBeanMapper
				.queryAllMarketOrder(city);
		Page page = new Page();
		page.setRecords(list);
		return page;
	}

	public int saveReservationFirst(ReservationFirstBean reservationFirstBean) {
		return ReservationFirstBeanMapper.saveReservationFirst(reservationFirstBean);
	}

	public boolean sort(String[] reservationFirstPkids,
			String reservationFirstPkid) {
		return reservationFirstDao.sort(reservationFirstPkids,
				reservationFirstPkid);
	}

	public void deleteReservationFirst(Map<String, Object> reservationFirstPkids) {
		ReservationFirstBeanMapper.deleteReservationFirst(reservationFirstPkids);
		
	}

	public int queryReservationSencodCount(String reservationFirstPkid) {
		return ReservationFirstBeanMapper.queryReservationSencodCount(reservationFirstPkid);
	}

	public ReservationFirstBean queryByPrimaryKey(String reservationFirstPkid) {
		return ReservationFirstBeanMapper.queryByPrimaryKey(reservationFirstPkid);
	}

	public void updateReservationFirst(Map<String, String> map) {
		
		
		
		ReservationFirstBeanMapper.updateReservationFirst(map);
		
	}
	
	/**
	 * 一级预约营销案分渠道审核列表
	 */
	public Page queryMarketForTest(Map<String, String> param){
		List<ReservationFirstBean> list = ReservationFirstBeanMapper.queryMarketForTest(param);
		List<ReservationFirstBean> newlist = new ArrayList<ReservationFirstBean>();
		String channalData = param.get("channalData");
		for(ReservationFirstBean fistBean : list){
			String onlineTestState = fistBean.getTestOnlineState();
			String wtOnlineTestState = fistBean.getWtTestOnlineState();
			StringBuffer testUrl = new StringBuffer();
			//掌厅
			if("0".equals(onlineTestState) && ("5".equals(channalData) || "02".equals(channalData))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img title='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img title='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("1".equals(onlineTestState) && ("5".equals(channalData) || "02".equals(channalData))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img title='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("2".equals(onlineTestState) && ("5".equals(channalData) || "02".equals(channalData))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img title='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}
			
			//网厅
			if("0".equals(wtOnlineTestState) && ("4".equals(channalData) || "01".equals(channalData))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img title='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img title='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("1".equals(wtOnlineTestState) && ("4".equals(channalData) || "01".equals(channalData))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img title='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("2".equals(wtOnlineTestState) && ("4".equals(channalData) || "01".equals(channalData))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img title='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}
			
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级预约营销案</a>";
			fistBean.setLinkOper(linkInfo);
			//掌厅地市审核状态
			if("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setLocalOper("待审核");
			}else if("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setLocalOper("审核通过");
			}else if("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setLocalOper("审核不通过");
			}
			//掌厅省级审核状态
			if("0".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setProOper("待审核");
			}else if("1".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setProOper("审核通过");
			}else if("2".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setProOper("审核不通过");
			}
			
			//网厅地市审核状态
			if("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setLocalOper("待审核");
			}else if("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setLocalOper("审核通过");
			}else if("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setLocalOper("审核不通过");
			}
			//网厅省级审核状态
			if("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setProOper("待审核");
			}else if("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setProOper("审核通过");
			}else if("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setProOper("审核不通过");
			}
			
			newlist.add(fistBean);
		}
		Page page = new Page();
		int totalRecord = ReservationFirstBeanMapper.queryCountForTest(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 预约营销案线上测试
	 */
	@Transactional
	public void updateTestState(ReservationFirstBean marketFirstBean){
		// 如果是测试通过，则将最终审核状态置为“审核通过”
		if (null != marketFirstBean.getWtTestOnlineState()) {
			if ("1".equals(marketFirstBean.getWtTestOnlineState())) {
				marketFirstBean.setWtVerifyState("1");
			} else {
				marketFirstBean.setWtVerifyState("4");
			}
		}
		if (null != marketFirstBean.getTestOnlineState()) {
			if ("1".equals(marketFirstBean.getTestOnlineState())) {
				marketFirstBean.setVerifyState("1");
			} else {
				marketFirstBean.setVerifyState("4");// 测试不通过
			}
		}
		ReservationFirstBeanMapper.updateTestState(marketFirstBean);
		MarketAuditBean marketAuditBean = marketFirstBean.getMarketAuditBean();
		//营销案类型:2预约营销案
		marketAuditBean.setMarketType("2");
		//一级营销案
		marketAuditBean.setMarketLevel("1");
		//操作类型为测试
		marketAuditBean.setOperType("2");
		marketAuditBean.setMarketPkid(Long.parseLong(marketFirstBean.getMarketFirstPkid()));
		marketAuditService.insert(marketAuditBean);
	}
	
	/**
	 * 一级预约营销案审核
	 */
	public Page queryMarketForAudit(Map<String, String> param){
		List<ReservationFirstBean> list = ReservationFirstBeanMapper.queryMarketForAudit(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channalData = param.get("channalData");
		List<ReservationFirstBean> newlist = new ArrayList<ReservationFirstBean>();
		for(ReservationFirstBean fistBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			//掌厅
			if ("1".equals(cityLevel)) {
				if ("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				if ("0".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("审核不通过");
				}
			}
			
			//网厅
			if ("1".equals(cityLevel)) {
				if ("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				if ("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("审核不通过");
				}
			}
			
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级营销案</a> ";
			fistBean.setLinkOper(linkInfo);
			if("0".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("测试不通过");
			}
			newlist.add(fistBean);
		}
		Page page = new Page();
		int totalRecord = ReservationFirstBeanMapper.queryCountForAudit(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 控制台一级预约营销案审核
	 */
	public Page queryMarketForAuditOnConsole(Map<String, String> param){
		List<ReservationFirstBean> list = ReservationFirstBeanMapper.queryMarketForAuditOnConsole(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channalData = param.get("channalData");
		List<ReservationFirstBean> newlist = new ArrayList<ReservationFirstBean>();
		for(ReservationFirstBean fistBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			if ("1".equals(cityLevel)) {
				//掌厅
				if ("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setProOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(fistBean.getWtLocalVerifyState()) && ("5".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setProOper("审核不通过");
				}
				
			}
			if("1".equals(proLevel)){
				//掌厅
				if ("0".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					fistBean.setLocalOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {
					verifyUrl
							.append("<a href='javascript:component.verifyReservationMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					fistBean.setLocalOper("审核不通过");
				}
			}
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级营销案</a> ";
			fistBean.setLinkOper(linkInfo);
			//掌厅
			if("0".equals(fistBean.getTestOnlineState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getTestOnlineState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getTestOnlineState()) && ("5".equals(channalData) || "02".equals(channalData))){
				fistBean.setTestOper("测试不通过");
			}
			//网厅
			if("0".equals(fistBean.getTestOnlineState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getTestOnlineState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getTestOnlineState()) && ("4".equals(channalData) || "01".equals(channalData))){
				fistBean.setTestOper("测试不通过");
			}
			String openUrl = fistBean.getViewName();
			if(openUrl.length() > 10){
				openUrl = openUrl.substring(0,7)+"...";
			}
			openUrl = "<a href='javascript:component.openWin(\""+fistBean.getMarketFirstPkid()+"\")'>" + openUrl + "</a>";
			fistBean.setViewName(openUrl);
			newlist.add(fistBean);
		}
		Page page = new Page();
		int totalRecord = ReservationFirstBeanMapper.queryCountForAudit(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 预约营销案审核
	 */
	@Transactional
	public void updateAuditState(ReservationFirstBean marketFirstBean){
		ReservationFirstBeanMapper.updateAuditState(marketFirstBean);
		MarketAuditBean marketAuditBean = marketFirstBean.getMarketAuditBean();
		//营销案类型-预约营销案
		marketAuditBean.setMarketType("2");
		//一级营销案
		marketAuditBean.setMarketLevel("1");
		//操作类型为审核
		marketAuditBean.setOperType("1");
		marketAuditBean.setMarketPkid(Long.parseLong(marketFirstBean.getMarketFirstPkid()));
		marketAuditService.insert(marketAuditBean);
		
	}

}
