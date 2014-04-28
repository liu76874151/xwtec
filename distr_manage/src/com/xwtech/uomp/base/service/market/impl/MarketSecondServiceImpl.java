package com.xwtech.uomp.base.service.market.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketSecondDao;
import com.xwtech.uomp.base.dao.market.MarketSecondDaoImpl;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketSecondBean;
import com.xwtech.uomp.base.service.gift.IGiftInfoService;
import com.xwtech.uomp.base.service.market.IMarketAuditService;
import com.xwtech.uomp.base.service.market.IMarketGroupRelationService;
import com.xwtech.uomp.base.service.market.IMarketOrgRelationService;
import com.xwtech.uomp.base.service.market.IMarketPayShowService;
import com.xwtech.uomp.base.service.market.IMarketSecondBindBizService;
import com.xwtech.uomp.base.service.market.IMarketSecondService;
/**
 * 二级营销案管理
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-17 下午07:12:30
 */
@Service("marketSecondService")
public class MarketSecondServiceImpl implements IMarketSecondService {

	@Autowired
	MarketSecondDao marketSecondDao;
	@Autowired
	MarketSecondDaoImpl marketSecondDaoImpl;
	@Autowired
	IMarketSecondBindBizService marketSecondBindBizService;
	@Autowired	
	IGiftInfoService giftInfoService;
	@Autowired
	IMarketGroupRelationService marketGroupRelationService;
	@Autowired
	IMarketOrgRelationService marketOrgRelationService;
	@Autowired
	IMarketPayShowService marketPayShowService;
	@Autowired
	IMarketAuditService marketAuditService;

	@Transactional(propagation=Propagation.REQUIRED)
	public void addMarketSecondBean(MarketSecondBean marketSecondBean) {
		marketSecondDao.addMarketSecondBean(marketSecondBean);
		if(StringUtils.isNotBlank(marketSecondBean.getZtMarketOrder())){
			String marketSecondPkids[]=marketSecondBean.getZtMarketOrder().split("\\.");
			marketSecondDaoImpl.sortZT(marketSecondPkids, marketSecondBean.getMarketSecondPkid());
		}
		if(StringUtils.isNotBlank(marketSecondBean.getWtMarketOrder())){
			String marketSecondPkids[]=marketSecondBean.getWtMarketOrder().split("\\.");
			marketSecondDaoImpl.sort(marketSecondPkids, marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getMarketSecondBindBizBeans()!=null){
			marketSecondBindBizService.batchUpdate(marketSecondBean.getMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getWtMarketSecondBindBizBeans()!=null){
		marketSecondBindBizService.batchUpdate(marketSecondBean.getWtMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getDtMarketSecondBindBizBeans()!=null){
			marketSecondBindBizService.batchUpdate(marketSecondBean.getDtMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getGiftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getGiftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getWtGiftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getWtGiftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getDtGftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getDtGftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getRelationBeans()!=null){
		marketGroupRelationService.batchUpdate(marketSecondBean.getRelationBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getWtRelationBeans()!=null){
		marketGroupRelationService.batchUpdate(marketSecondBean.getWtRelationBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getDtRelationBeans()!=null){
		marketGroupRelationService.batchUpdate(marketSecondBean.getDtRelationBeans(), marketSecondBean.getMarketSecondPkid());
		}
		marketOrgRelationService.batchUpdate(marketSecondBean.getOrgRelationBeans(), marketSecondBean.getMarketSecondPkid());
		marketPayShowService.batchUpdate(marketSecondBean.getPayShowBeans(), marketSecondBean.getMarketSecondPkid());
	}

	/**
	 * 查询单个
	 */
	public MarketSecondBean queryMarketSecondBean(String pkid) {
		return marketSecondDao.queryMarketSecondBean(pkid);
	}

	public Page queryMarketSecondList(Map<String, String> map) {
		List<MarketSecondBean> list = marketSecondDao.queryMarketSecondList(map);
		List<MarketSecondBean> newList = new ArrayList<MarketSecondBean>();
		String userChannelNum = map.get("userChannalData");
		for (int i = 0; i < list.size(); i++) {
			MarketSecondBean bean = list.get(i);
			//掌厅列表展示状态
			if("5".equals(userChannelNum)){
				if("0".equals(bean.getIsListView())){
					String isViewAlink = "未展示<a href='javascript:component.updateListView("+bean.getMarketSecondPkid()+",1);' title='点击更新为在列表中展示'><img src='../../../../resource/img/toggle_enabled.gif'/></a>";
					bean.setIsListViewLink(isViewAlink);
				}
				else{
					String isViewAlink = "展示<a href='javascript:component.updateListView("+bean.getMarketSecondPkid()+",0)' title='点击更新为在列表中不展示'><img src='../../../../resource/img/toggle_disabled.gif'/></a>";
					bean.setIsListViewLink(isViewAlink);
				}
			}
			//网厅列表展示状态
			if("4".equals(userChannelNum)){
				if("0".equals(bean.getWtIsListView())){
					String isViewAlink = "未展示<a href='javascript:component.updateListView("+bean.getMarketSecondPkid()+",1);' title='点击更新为在列表中展示'><img src='../../../../resource/img/toggle_enabled.gif'/></a>";
					bean.setIsListViewLink(isViewAlink);
				}
				else{
					String isViewAlink = "展示<a href='javascript:component.updateListView("+bean.getMarketSecondPkid()+",0)' title='点击更新为在列表中不展示'><img src='../../../../resource/img/toggle_disabled.gif'/></a>";
					bean.setIsListViewLink(isViewAlink);
				}
			}
			bean.setModify("<a href='javascript:component.showMarketSecondWin("+bean.getMarketSecondPkid()+","+bean.getChannalData()+");' >编辑</a>");
			newList.add(bean);
		}
		
		int totalRecord = marketSecondDao.queryMarketSecondCount(map);
		
		Page page = new Page();
		page.setRecords(newList);
		page.setTotalRecord(totalRecord);
		return page;
	}

	@Transactional
	public void updateMarketSecondBean(MarketSecondBean marketSecondBean) {
		marketSecondDao.updateMarketSecondBean(marketSecondBean);
		if(StringUtils.isNotBlank(marketSecondBean.getZtMarketOrder())){
			String marketSecondPkids[]=marketSecondBean.getZtMarketOrder().split("\\.");
			marketSecondDaoImpl.sortZT(marketSecondPkids,"0");
		}
		if(StringUtils.isNotBlank(marketSecondBean.getWtMarketOrder())){
			String marketSecondPkids[]=marketSecondBean.getWtMarketOrder().split("\\.");
			marketSecondDaoImpl.sort(marketSecondPkids, "0");
		}
		//绑定业务
		marketSecondBindBizService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		if(marketSecondBean.getMarketSecondBindBizBeans()!=null){
			marketSecondBindBizService.batchUpdate(marketSecondBean.getMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getWtMarketSecondBindBizBeans()!=null){
		marketSecondBindBizService.batchUpdate(marketSecondBean.getWtMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getDtMarketSecondBindBizBeans()!=null){
			marketSecondBindBizService.batchUpdate(marketSecondBean.getDtMarketSecondBindBizBeans(), marketSecondBean.getMarketSecondPkid());
		}
		//礼品
		giftInfoService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		if(marketSecondBean.getGiftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getGiftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getWtGiftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getWtGiftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		if(marketSecondBean.getDtGftInfoBeans()!=null){
			giftInfoService.batchUpdate(marketSecondBean.getDtGftInfoBeans(), marketSecondBean.getMarketSecondPkid());
		}
		//目标组织
		marketGroupRelationService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		if(marketSecondBean.getRelationBeans()!=null){
			marketGroupRelationService.batchUpdate(marketSecondBean.getRelationBeans(), marketSecondBean.getMarketSecondPkid());
			}
			if(marketSecondBean.getWtRelationBeans()!=null){
			marketGroupRelationService.batchUpdate(marketSecondBean.getWtRelationBeans(), marketSecondBean.getMarketSecondPkid());
			}
			if(marketSecondBean.getDtRelationBeans()!=null){
			marketGroupRelationService.batchUpdate(marketSecondBean.getDtRelationBeans(), marketSecondBean.getMarketSecondPkid());
			}
			
			
		//营业厅
		marketOrgRelationService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		marketOrgRelationService.batchUpdate(marketSecondBean.getOrgRelationBeans(), marketSecondBean.getMarketSecondPkid());
		//支付方式
		marketPayShowService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		marketPayShowService.batchUpdate(marketSecondBean.getPayShowBeans(), marketSecondBean.getMarketSecondPkid());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMarketSecondBeanByPkid(MarketSecondBean marketSecondBean) {
		marketSecondDao.deleteMarketSecondBeanByPkid(marketSecondBean);
		
		marketSecondBindBizService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		giftInfoService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		marketGroupRelationService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		marketOrgRelationService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
		marketPayShowService.deleteBySecondPkid(marketSecondBean.getMarketSecondPkid());
	}

	public int queryMarketSecondCount(Map<String, String> param) {
		return marketSecondDao.queryMarketSecondCount(param);
		
	}

	/**
	 * 二级营销案审核用查询
	 */
	public Page queryMarketSecondForVerify(Map<String, String> param) {
		List<MarketSecondBean> list = marketSecondDao.queryMarketSecondForVerify(param);
		List<MarketSecondBean> newList = new ArrayList<MarketSecondBean>();
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channalData = param.get("channalData");
		for (int i = 0; i < list.size(); i++) {
			MarketSecondBean bean = list.get(i);
			StringBuffer verifyUrl = new StringBuffer();
			if ("1".equals(proLevel)) {
				// 掌厅
				if ("5".equals(channalData) || "02".equals(channalData)) {
					if ("0".equals(bean.getProVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("1".equals(bean.getProVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("2".equals(bean.getProVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getLocalVerifyState())) {
						bean.setLocalOper("待审核");
					} else if ("1".equals(bean.getLocalVerifyState())) {
						bean.setLocalOper("审核通过");
					} else if ("2".equals(bean.getLocalVerifyState())) {
						bean.setLocalOper("审核不通过");
					}
				}
				// 短厅
				if ("6".equals(channalData) || "03".equals(channalData)) {
					if ("0".equals(bean.getDtProVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("1".equals(bean.getDtProVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("2".equals(bean.getDtProVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getDtLocalVerifyState())) {
						bean.setLocalOper("待审核");
					} else if ("1".equals(bean.getDtLocalVerifyState())) {
						bean.setLocalOper("审核通过");
					} else if ("2".equals(bean.getDtLocalVerifyState())) {
						bean.setLocalOper("审核不通过");
					}
				}
				// 网厅
				if ("4".equals(channalData) || "01".equals(channalData)) {
					if ("0".equals(bean.getWtProVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("1".equals(bean.getWtProVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					} else if ("2".equals(bean.getWtProVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondPro("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setProOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getWtLocalVerifyState())) {
						bean.setLocalOper("待审核");
					} else if ("1".equals(bean.getWtLocalVerifyState())) {
						bean.setLocalOper("审核通过");
					} else if ("2".equals(bean.getWtLocalVerifyState())) {
						bean.setLocalOper("审核不通过");
					}
					if ("0".equals(bean.getWtTestOnlineState())) {
						bean.setTestOper("待测试");
					} else if ("1".equals(bean.getWtTestOnlineState())) {
						bean.setTestOper("测试通过");
					} else if ("2".equals(bean.getWtTestOnlineState())) {
						bean.setTestOper("测试不通过");
					}
				}
			} else if ("1".equals(cityLevel)) {
				// 掌厅
				if ("5".equals(channalData) || "02".equals(channalData)) {
					if ("0".equals(bean.getLocalVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("1".equals(bean.getLocalVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("2".equals(bean.getLocalVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getProVerifyState())) {
						bean.setProOper("待审核");
					} else if ("1".equals(bean.getProVerifyState())) {
						bean.setProOper("审核通过");
					} else if ("2".equals(bean.getProVerifyState())) {
						bean.setProOper("审核不通过");
					}
					if ("0".equals(bean.getTestOnlineState())) {
						bean.setTestOper("待测试");
					} else if ("1".equals(bean.getTestOnlineState())) {
						bean.setTestOper("测试通过");
					} else if ("2".equals(bean.getTestOnlineState())) {
						bean.setTestOper("测试不通过");
					}
				}

				// 短厅
				if ("6".equals(channalData) || "03".equals(channalData)) {
					if ("0".equals(bean.getDtLocalVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("1".equals(bean.getDtLocalVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("2".equals(bean.getDtLocalVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getDtProVerifyState())) {
						bean.setProOper("待审核");
					} else if ("1".equals(bean.getDtProVerifyState())) {
						bean.setProOper("审核通过");
					} else if ("2".equals(bean.getDtProVerifyState())) {
						bean.setProOper("审核不通过");
					}
					if ("0".equals(bean.getDtTestOnlineState())) {
						bean.setTestOper("待测试");
					} else if ("1".equals(bean.getDtTestOnlineState())) {
						bean.setTestOper("测试通过");
					} else if ("2".equals(bean.getDtTestOnlineState())) {
						bean.setTestOper("测试不通过");
					}
				}
				// 网厅
				if ("4".equals(channalData) || "01".equals(channalData)) {
					if ("0".equals(bean.getWtLocalVerifyState())) {// 待审核
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("1".equals(bean.getWtLocalVerifyState())) {// 审核通过
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					} else if ("2".equals(bean.getWtLocalVerifyState())) {// 审核未通过
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketSecondCity("
										+ bean.getMarketSecondPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						bean.setLocalOper(verifyUrl.toString());
					}
					if ("0".equals(bean.getWtProVerifyState())) {
						bean.setProOper("待审核");
					} else if ("1".equals(bean.getWtProVerifyState())) {
						bean.setProOper("审核通过");
					} else if ("2".equals(bean.getWtProVerifyState())) {
						bean.setProOper("审核不通过");
					}
				}
			}
			
			bean.setPreview("<a href='javascript:component.previewMarketSecond("
					+ bean.getMarketSecondPkid()
					+ ","
					+ bean.getChannalData()
					+ ");' >预览</a>");
			newList.add(bean);
		}
		
		int totalRecord = marketSecondDao.queryMarketSecondCountForVerify(param);
		Page page = new Page();
		page.setRecords(newList);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 首页二级营销案待审核列表
	 */
	public Page queryMarketSecondForVerifyOnConsole(Map<String, String> param) {
		List<MarketSecondBean> list = marketSecondDao.queryMarketSecondForVerifyOnConsle(param);
		List<MarketSecondBean> newList = new ArrayList<MarketSecondBean>();
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channalData = param.get("channalData");
		for (int i = 0; i < list.size(); i++) {
			MarketSecondBean bean = list.get(i);
			StringBuffer verifyUrl= new StringBuffer();
			if ("1".equals(proLevel)) {
				//掌厅
				if ("0".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("1".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("2".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				}
				if("0".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setLocalOper("待审核");
				}else if("1".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setLocalOper("审核通过");
				}else if("2".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setLocalOper("审核不通过");
				}
				
				//短厅
				if ("0".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("1".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("2".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				}
				if("0".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setLocalOper("待审核");
				}else if("1".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setLocalOper("审核通过");
				}else if("2".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setLocalOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("1".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				} else if ("2".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondPro("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setProOper(verifyUrl.toString());
				}
				if("0".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setLocalOper("待审核");
				}else if("1".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setLocalOper("审核通过");
				}else if("2".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setLocalOper("审核不通过");
				}
			}else if("1".equals(cityLevel)){
				//掌厅
				if ("0".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("1".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("2".equals(bean.getLocalVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setProOper("待审核");
				}else if("1".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setProOper("审核通过");
				}else if("2".equals(bean.getProVerifyState()) && ("5".equals(channalData) || "02".equals(channalData))){
					bean.setProOper("审核不通过");
				}
				
				//短厅
				if ("0".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("1".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("2".equals(bean.getDtLocalVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setProOper("待审核");
				}else if("1".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setProOper("审核通过");
				}else if("2".equals(bean.getDtProVerifyState()) && ("6".equals(channalData) || "03".equals(channalData))){
					bean.setProOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 待审核
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("1".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 审核通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",false);' ><img alt='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				} else if ("2".equals(bean.getWtLocalVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))) {// 审核未通过
					verifyUrl
							.append("<a href='javascript:component.verifyMarketSecondCity("
									+ bean.getMarketSecondPkid()
									+ ",true);' ><img alt='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					bean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setProOper("待审核");
				}else if("1".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setProOper("审核通过");
				}else if("2".equals(bean.getWtProVerifyState()) && ("4".equals(channalData) || "01".equals(channalData))){
					bean.setProOper("审核不通过");
				}
			}
			if("0".equals(bean.getTestOnlineState())){
				bean.setTestOper("待测试");
			}else if("1".equals(bean.getTestOnlineState())){
				bean.setTestOper("测试通过");
			}else if("2".equals(bean.getTestOnlineState())){
				bean.setTestOper("测试不通过");
			}
			String viewName =  bean.getViewName();
			if(viewName.length() > 10){
				viewName = viewName.substring(0,7)+"...";
			}
			bean.setPreview("<a href='javascript:component.previewMarketSecond("+bean.getMarketSecondPkid()+","+bean.getChannalData()+");' >"+viewName+"</a>");
			newList.add(bean);
		}
		
		int totalRecord = marketSecondDao.queryMarketSecondCountForVerify(param);
		Page page = new Page();
		page.setRecords(newList);
		page.setTotalRecord(totalRecord);
		return page;
	}

	/**
	 * 测试列表
	 */
	public Page queryMarketForTest(Map<String, String> param){
		List<MarketSecondBean> list = marketSecondDao.queryMarketForTest(param);
		List<MarketSecondBean> newlist = new ArrayList<MarketSecondBean>();
		String channelNum = param.get("channalData");
		for(MarketSecondBean secondBean : list){
			String onlineTestState = secondBean.getTestOnlineState();
			String dtOnlineTestState = secondBean.getDtTestOnlineState();
			String wtOnlineTestState = secondBean.getWtTestOnlineState();
			StringBuffer testUrl = new StringBuffer();
			//掌厅
			if("0".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("1".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("2".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}
			//短厅
			if("0".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("1".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("2".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}
			//网厅
			if("0".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("1".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}else if("2".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ secondBean.getMarketSecondPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				secondBean.setTestOper(testUrl.toString());
			}
			
			
			//网厅
			if("4".equals(channelNum) || "01".equals(channelNum)){
				//地市审核状态
				if("0".equals(secondBean.getWtLocalVerifyState())){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getWtLocalVerifyState())){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getWtLocalVerifyState())){
					secondBean.setLocalOper("审核不通过");
				}
				//省级审核状态
				if("0".equals(secondBean.getWtProVerifyState())){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getWtProVerifyState())){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getWtProVerifyState())){
					secondBean.setProOper("审核不通过");
				}
			}
			//掌厅
			if("5".equals(channelNum) || "02".equals(channelNum)){
				//地市审核状态
				if("0".equals(secondBean.getLocalVerifyState())){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getLocalVerifyState())){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getLocalVerifyState())){
					secondBean.setLocalOper("审核不通过");
				}
				//省级审核状态
				if("0".equals(secondBean.getProVerifyState())){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getProVerifyState())){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getProVerifyState())){
					secondBean.setProOper("审核不通过");
				}
			}
			//短厅
			if("6".equals(channelNum) || "03".equals(channelNum)){
				//地市审核状态
				if("0".equals(secondBean.getDtLocalVerifyState())){
					secondBean.setLocalOper("待审核");
				}else if("1".equals(secondBean.getDtLocalVerifyState())){
					secondBean.setLocalOper("审核通过");
				}else if("2".equals(secondBean.getDtLocalVerifyState())){
					secondBean.setLocalOper("审核不通过");
				}
				//省级审核状态
				if("0".equals(secondBean.getDtProVerifyState())){
					secondBean.setProOper("待审核");
				}else if("1".equals(secondBean.getDtProVerifyState())){
					secondBean.setProOper("审核通过");
				}else if("2".equals(secondBean.getDtProVerifyState())){
					secondBean.setProOper("审核不通过");
				}
			}
			
			newlist.add(secondBean);
		}
		Page page = new Page();
		int totalRecord = marketSecondDao.queryCountForTest(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	 
	/**
	 * 省级审批
	 */
	@Transactional
	public void updateMarketSecondForVerify(MarketSecondBean marketSecondBean) {
		
		marketSecondDao.updateMarketSecondForVerify(marketSecondBean);
		MarketAuditBean marketAuditBean = marketSecondBean.getMarketAuditBean();
//		marketAuditBean.setAutitState(marketSecondBean.getVerifyState());
		marketAuditBean.setMarketType("1");
		marketAuditBean.setMarketLevel("2");
		// 操作类型为审核
		marketAuditBean.setOperType("1");
		marketAuditBean.setMarketPkid(Long.parseLong(marketSecondBean.getMarketSecondPkid()));
		marketAuditService.insert(marketAuditBean);
	}
	
	@Transactional
	public void updateTestState(MarketSecondBean marketSecondBean){
		//如果是测试通过，则将最终审核状态置为“审核通过”
		if(null != marketSecondBean.getWtTestOnlineState()){
			if("1".equals(marketSecondBean.getWtTestOnlineState())){
				marketSecondBean.setWtVerifyState("1");
			}else{
				marketSecondBean.setWtVerifyState("4");
			}
		}
		if(null != marketSecondBean.getDtTestOnlineState()){
			if("1".equals(marketSecondBean.getDtTestOnlineState())){
				marketSecondBean.setDtVerifyState("1");
			}else{
				marketSecondBean.setDtVerifyState("4");
			}
		}
		if(null != marketSecondBean.getTestOnlineState()){
			if("1".equals(marketSecondBean.getTestOnlineState())){
				marketSecondBean.setVerifyState("1");
			}else{
				marketSecondBean.setVerifyState("4");//测试不通过
			}
		}
		marketSecondDao.updateTestState(marketSecondBean);
		MarketAuditBean marketAuditBean = marketSecondBean.getMarketAuditBean();
		//营销案类型
		marketAuditBean.setMarketType("1");
		//二级营销案
		marketAuditBean.setMarketLevel("2");
		//操作类型为测试
		marketAuditBean.setOperType("2");
		marketAuditBean.setMarketPkid(Long.parseLong(marketSecondBean.getMarketSecondPkid()));
		marketAuditService.insert(marketAuditBean);
	}
	
	public void updateIsViewListState(MarketSecondBean marketSecondBean) {
		marketSecondDao.updateIsViewListState(marketSecondBean);
		
	}

	public Page queryViewNameOrderByWTMarketOrder(String marketFirstpkid) {
		Page page = new Page();
		List<MarketSecondBean> list=marketSecondDao.queryViewNameOrderByWTMarketOrder(marketFirstpkid);
		page.setRecords(list);
		return page;
	}

	public Page queryViewNameOrderByZTMarketOrder(String marketFirstpkid) {
		Page page = new Page();
		List<MarketSecondBean> list=marketSecondDao.queryViewNameOrderByZTMarketOrder(marketFirstpkid);
		page.setRecords(list);
		return page;
	}

}
