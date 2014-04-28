package com.xwtech.uomp.base.action.handler.market;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketGroupRelationBean;
import com.xwtech.uomp.base.pojo.market.MarketLogBean;
import com.xwtech.uomp.base.pojo.market.MarketOrgRelationBean;
import com.xwtech.uomp.base.pojo.market.MarketPayShowBean;
import com.xwtech.uomp.base.pojo.market.MarketSecondBean;
import com.xwtech.uomp.base.pojo.market.MarketSecondBindBizBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.gift.IGiftInfoService;
import com.xwtech.uomp.base.service.market.IMarketGroupRelationService;
import com.xwtech.uomp.base.service.market.IMarketOrgRelationService;
import com.xwtech.uomp.base.service.market.IMarketPayShowService;
import com.xwtech.uomp.base.service.market.IMarketSecondBindBizService;
import com.xwtech.uomp.base.service.market.IMarketSecondService;
import com.xwtech.uomp.base.service.market.IMarketLogService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/**
 * 二级营销案管理
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-12 下午06:50:35
 */
@Controller("H_marketSecond")
public class MarketSecondHandler implements IHandler {
	@Autowired
	IMarketLogService marketLogService;
	@Autowired
	IMarketSecondService marketSecondService;
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
	ISequenceService sequenceService;
	
	/**
	 * 二级营销案查询
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryMarketSecondList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String channelNum = userInfoBean.getChannelNum();
		if (channelNum != null && !"".equals(channelNum)) {
			// 掌厅
			if ("02".equals(channelNum)) {
				channelNum = "5";
			}
			// 网厅
			if ("01".equals(channelNum)) {
				channelNum = "4";
			}
			// 短厅
			if ("03".equals(channelNum)) {
				channelNum = "6";
			}
			param.put("userChannalData", channelNum);
		}
		try {
			Page page = marketSecondService.queryMarketSecondList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 单一二级营销案查询
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryMarketSecondBean(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketSecondPkid = RequestUtil.getString(request, "pkid");
		try {
			MarketSecondBean marketSecondBean = marketSecondService.queryMarketSecondBean(marketSecondPkid);
			result.setRetObj(marketSecondBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询绑定信息
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult querySecondBindBizBeans(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			List<MarketSecondBindBizBean> secondBindBizBeans = marketSecondBindBizService.selectBySecondPkid(param);//绑定业务信息
			result.setRetObj(secondBindBizBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	/**
	 * 查询二级营销关联的营业厅编码
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult querySecondOrgRelationBeans(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketSecondPkid = RequestUtil.getString(request, "marketSecondPkid");
		try {
			List<MarketOrgRelationBean> orgRelationBeans = marketOrgRelationService.selectOrgInfoCodeBySecondPkid(marketSecondPkid);
			result.setRetObj(orgRelationBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询二级营销案 支付方式
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult querySecondPayShowBeans(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			List<MarketPayShowBean> payShowBeans =  marketPayShowService.selectPayShowBeansBySecondPkid(param);
			result.setRetObj(payShowBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

	/**
	 * 查询二级营销案的目标组织
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult querySecondMarketGroups(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			List<MarketGroupRelationBean> groupRelationBeans = marketGroupRelationService.selectSecondMarketGroups(param);
			result.setRetObj(groupRelationBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	
	/**
	 * 增加二级营销案
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public HandlerResult addMarketSecondBean(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		
		MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil.getBeanFromRequest(request,
				MarketSecondBean.class);
		String marketSecondBizInfo = request.getParameter("marketSecondBizInfo");//掌厅绑定业务
		String wtMarketSecondBizInfo = request.getParameter("wtMarketSecondBizInfo");//网厅绑定业务
		String dtMarketSecondBizInfo = request.getParameter("dtMarketSecondBizInfo");//短厅绑定业务
		String marketSecondGiftInfo = request.getParameter("marketSecondGiftInfo");//掌厅礼品信息
		String wtMarketSecondGiftInfo = request.getParameter("wtMarketSecondGiftInfo");//网厅礼品信息
		String dtMarketSecondGiftInfo = request.getParameter("dtMarketSecondGiftInfo");//短厅礼品信息
		String targetGroupInfo = request.getParameter("targetGroupInfo");//目标组织--掌厅
		String wtTargetGroupInfo = request.getParameter("wtTargetGroupInfo");//目标组织--网厅
		String dtTargetGroupInfo = request.getParameter("dtTargetGroupInfo");//目标组织--短厅
		String orgInfoList = request.getParameter("OrgInfoList");//营业厅--
		String payShow = request.getParameter("payShow");
		String isDT = request.getParameter("isDT");
		List<MarketSecondBindBizBean> list = JsonHelper.json2List(marketSecondBizInfo, MarketSecondBindBizBean.class);
		List<MarketSecondBindBizBean> listWT = JsonHelper.json2List(dtMarketSecondBizInfo, MarketSecondBindBizBean.class);
		List<MarketSecondBindBizBean> listDT = JsonHelper.json2List(wtMarketSecondBizInfo, MarketSecondBindBizBean.class);
		List<GiftInfoBean> giftInfoBeans = JsonHelper.json2List(marketSecondGiftInfo, GiftInfoBean.class);
		List<GiftInfoBean> wtGiftInfoBeans = JsonHelper.json2List(wtMarketSecondGiftInfo, GiftInfoBean.class);
		List<GiftInfoBean> dtGiftInfoBeans = JsonHelper.json2List(dtMarketSecondGiftInfo, GiftInfoBean.class);
		List<MarketGroupRelationBean> relationBeans = JsonHelper.json2List(targetGroupInfo, MarketGroupRelationBean.class);
		List<MarketGroupRelationBean> wtRelationBeans = JsonHelper.json2List(wtTargetGroupInfo, MarketGroupRelationBean.class);
		List<MarketGroupRelationBean> dtRelationBeans = JsonHelper.json2List(dtTargetGroupInfo, MarketGroupRelationBean.class);
		List<MarketOrgRelationBean> orgRelationBeans = JsonHelper.json2List(orgInfoList, MarketOrgRelationBean.class);
		List<MarketPayShowBean> payShowBeans = JsonHelper.json2List(payShow, MarketPayShowBean.class);
		
		marketSecondBean.setMarketSecondBindBizBeans(list);
		marketSecondBean.setWtMarketSecondBindBizBeans(listWT);
		marketSecondBean.setDtMarketSecondBindBizBeans(listDT);
		marketSecondBean.setGiftInfoBeans(giftInfoBeans);
		marketSecondBean.setWtGiftInfoBeans(wtGiftInfoBeans);
		marketSecondBean.setDtGftInfoBeans(dtGiftInfoBeans);
		marketSecondBean.setRelationBeans(relationBeans);
		marketSecondBean.setWtRelationBeans(wtRelationBeans);
		marketSecondBean.setDtRelationBeans(dtRelationBeans);
		marketSecondBean.setOrgRelationBeans(orgRelationBeans);
		marketSecondBean.setPayShowBeans(payShowBeans);
		
		String marketSecondPkid = sequenceService.getSequence("MARKET_SECOND_PKID_SEQ");
		String moneyVal = request.getParameter("moneyVal");
		if(StringUtil.isNull(moneyVal)){
			moneyVal="0";
		}
		marketSecondBean.setMoneyVal(new BigDecimal(moneyVal));
		marketSecondBean.setMarketSecondPkid(marketSecondPkid);
		marketSecondBean.setCfgUserId(userInfoBean.getLoginName());
		MarketLogBean marketLogBean=new MarketLogBean();
		if("1".equals(isDT)){
			marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
			marketLogBean.setCity(marketSecondBean.getCity());
			String marketFirstCode = request.getParameter("marketFirstCode");
			marketLogBean.setFirstCode(marketFirstCode);
			marketLogBean.setMarketSecondPkid(marketSecondPkid);
			marketLogBean.setSecondCode(marketSecondBean.getMarketSecondCode());
			marketLogBean.setMarketFirstPkid(marketSecondBean.getMarketFirstPkid());
			marketLogBean.setOptUser(userInfoBean.getLoginName());
			marketLogBean.setCaseLevel("2");
			marketLogBean.setType("1");
			marketLogBean.setFlag("0");
		}
		try {
			marketSecondService.addMarketSecondBean(marketSecondBean);
			if("1".equals(isDT)){
			marketLogService.saveMarketLog(marketLogBean);
			}
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	
	/**修改二级营销案
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public HandlerResult updateMarketSecondBean(HandlerRequestContext context) { 
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil.getBeanFromRequest(request,
				MarketSecondBean.class);
		String marketSecondBizInfo = request.getParameter("marketSecondBizInfo");//绑定业务
		String wtMarketSecondBizInfo = request.getParameter("wtMarketSecondBizInfo");//绑定业务
		String dtMarketSecondBizInfo = request.getParameter("dtMarketSecondBizInfo");//绑定业务
		
		String marketSecondGiftInfo = request.getParameter("marketSecondGiftInfo");//礼品信息
		String wtMarketSecondGiftInfo = request.getParameter("wtMarketSecondGiftInfo");//礼品信息
		String dtMarketSecondGiftInfo = request.getParameter("dtMarketSecondGiftInfo");//礼品信息
		String targetGroupInfo = request.getParameter("targetGroupInfo");//目标组织--掌厅
		String wtTargetGroupInfo = request.getParameter("wtTargetGroupInfo");//目标组织--网厅
		String dtTargetGroupInfo = request.getParameter("dtTargetGroupInfo");//目标组织--短厅
		String orgInfoList = request.getParameter("OrgInfoList");//营业厅
		String payShow = request.getParameter("payShow");
		String isDT = request.getParameter("isDT");
		String marketFirstCode = request.getParameter("marketFirstCode");
		
		List<MarketSecondBindBizBean> list = JsonHelper.json2List(marketSecondBizInfo, MarketSecondBindBizBean.class);
		List<MarketSecondBindBizBean> listWT = JsonHelper.json2List(wtMarketSecondBizInfo, MarketSecondBindBizBean.class);
		List<MarketSecondBindBizBean> listDT = JsonHelper.json2List(dtMarketSecondBizInfo, MarketSecondBindBizBean.class);
		
		List<GiftInfoBean> giftInfoBeans = JsonHelper.json2List(marketSecondGiftInfo, GiftInfoBean.class);
		List<GiftInfoBean> dtGiftInfoBeans = JsonHelper.json2List(dtMarketSecondGiftInfo, GiftInfoBean.class);
		List<GiftInfoBean>wtGiftInfoBeans = JsonHelper.json2List(wtMarketSecondGiftInfo, GiftInfoBean.class);
		List<MarketGroupRelationBean> relationBeans = JsonHelper.json2List(targetGroupInfo, MarketGroupRelationBean.class);
		List<MarketGroupRelationBean> wtRelationBeans = JsonHelper.json2List(wtTargetGroupInfo, MarketGroupRelationBean.class);
		List<MarketGroupRelationBean> dtRelationBeans = JsonHelper.json2List(dtTargetGroupInfo, MarketGroupRelationBean.class);
		List<MarketOrgRelationBean> orgRelationBeans = JsonHelper.json2List(orgInfoList, MarketOrgRelationBean.class);
		List<MarketPayShowBean> payShowBeans = JsonHelper.json2List(payShow, MarketPayShowBean.class);
		
		marketSecondBean.setMarketSecondBindBizBeans(list);
		marketSecondBean.setWtMarketSecondBindBizBeans(listWT);
		marketSecondBean.setDtMarketSecondBindBizBeans(listDT);
		marketSecondBean.setGiftInfoBeans(giftInfoBeans);
		marketSecondBean.setDtGftInfoBeans(dtGiftInfoBeans);
		marketSecondBean.setWtGiftInfoBeans(wtGiftInfoBeans);
		marketSecondBean.setRelationBeans(relationBeans);
		marketSecondBean.setWtRelationBeans(wtRelationBeans);
		marketSecondBean.setDtRelationBeans(dtRelationBeans);
		marketSecondBean.setOrgRelationBeans(orgRelationBeans);
		marketSecondBean.setPayShowBeans(payShowBeans);
		
		String moneyVal = request.getParameter("moneyVal");
		if(StringUtil.isNull(moneyVal)){
			moneyVal="0";
		}
		marketSecondBean.setMoneyVal(new BigDecimal(moneyVal));
		marketSecondBean.setCfgUserId(userInfoBean.getLoginName());
		
		MarketLogBean marketLogBean=new MarketLogBean();
		if("1".equals(isDT)){
			marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
			marketLogBean.setCity(marketSecondBean.getCity());
			marketLogBean.setFirstCode(marketFirstCode);
			marketLogBean.setMarketSecondPkid(marketSecondBean.getMarketSecondPkid());
			marketLogBean.setSecondCode(marketSecondBean.getMarketSecondCode());
			marketLogBean.setMarketFirstPkid(marketSecondBean.getMarketFirstPkid());
			marketLogBean.setOptUser(userInfoBean.getLoginName());
			marketLogBean.setCaseLevel("2");
			marketLogBean.setType("2");
			marketLogBean.setFlag("0");
		}
		try {
			marketSecondService.updateMarketSecondBean(marketSecondBean);//更新二级营销案
			if("1".equals(isDT)){
				marketLogService.saveMarketLog(marketLogBean);
				}
			
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
	 * 删除二级营销案
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult deleteMarketSecondByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil.getBeanFromRequest(request,
				MarketSecondBean.class);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String marketFirstCode=request.getParameter("marketFirstCode");
		MarketLogBean marketLogBean=new MarketLogBean();
		
		try {
			marketSecondService.deleteMarketSecondBeanByPkid(marketSecondBean);
			
			if(marketSecondBean.getChannalData()!=null&&marketSecondBean.getChannalData().indexOf("6")>-1){
				marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
				marketLogBean.setCity(marketSecondBean.getCity());
				marketLogBean.setFirstCode(marketFirstCode);
				marketLogBean.setMarketSecondPkid(marketSecondBean.getMarketSecondPkid());
				marketLogBean.setSecondCode(marketSecondBean.getMarketSecondCode());
				marketLogBean.setMarketFirstPkid(marketSecondBean.getMarketFirstPkid());
				marketLogBean.setOptUser(userInfoBean.getLoginName());
				marketLogBean.setCaseLevel("2");
				marketLogBean.setType("3");
				marketLogBean.setFlag("0");
				marketLogService.saveMarketLog(marketLogBean);
			}
			
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);			
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
		}
		return result;
	}
	
	public HandlerResult queryMarketSecondCount(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			int count = marketSecondService.queryMarketSecondCount(param);
			result.setRetObj(count);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);			
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
		}
		return result;
	}
	
	/**
	 * 审核用查询
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryMarketSecondForVerify(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = request.getParameter("city");
		if(StringUtil.isNull(city)){
			city = userInfoBean.getUserAreaCode();
		}
		if("0".equals(city)){//0 为江苏省
			city = "";
			param.put("proManager", "1");
		}else{
			param.put("cityManager", "1");
		}
		param.put("city", city);
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
			Page page = marketSecondService.queryMarketSecondForVerify(param);
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
	
	/**
	 * 查询测试列表
	 * 创建日期：2013-12-17下午2:04:58
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
			Page page = marketSecondService.queryMarketForTest(param);
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
	 * 省级审批
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult updateMarketSecondForVerify(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		
		MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil.getBeanFromRequest(request,
				MarketSecondBean.class);
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
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			String channelNum = userInfoBean.getChannelNum();
			if ("0".equals(city)) {// 0 为江苏省
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketSecondBean.setWtProVerifyState(verifyState);
						if("1".equals(verifyState)){
							marketSecondBean.setWtVerifyState("3");
						}else{
							marketSecondBean.setWtVerifyState("2");
						}
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
					}
					//短厅
					if("03".equals(channelNum) || "6".equals(channelNum)){
						marketSecondBean.setDtProVerifyState(verifyState);
						//如果是短厅省级审核通过，则最终审核状态为通过
						if("1".equals(verifyState)){
							marketSecondBean.setDtVerifyState("1");
						}else{
							marketSecondBean.setDtVerifyState("2");
						}
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
					}
					//掌厅
					if("02".equals(channelNum) || "5".equals(channelNum)){
						//如果是省级管理员，并且是审核通过的将最终状态改为“待测试”
						if("1".equals(verifyState)){
							marketSecondBean.setVerifyState("3");
						}else{
							marketSecondBean.setVerifyState("2");
						}
					}
				}
			}else{
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketSecondBean.setWtLocalVerifyState(verifyState);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
						if("1".equals(verifyState)){
							marketSecondBean.setWtVerifyState("3");
						}
					}
					//掌厅
					if("02".equals(channelNum) || "5".equals(channelNum)){
						marketSecondBean.setWtLocalVerifyState(null);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(verifyState);
						if("1".equals(verifyState)){
							marketSecondBean.setVerifyState("3");
						}
					}
					//短厅
					if("03".equals(channelNum) || "6".equals(channelNum)){
						marketSecondBean.setDtLocalVerifyState(verifyState);
						marketSecondBean.setProVerifyState(null);
						marketSecondBean.setLocalVerifyState(null);
						if("1".equals(verifyState)){
							marketSecondBean.setDtVerifyState("3");
						}
					}
				}
			}
			
			marketAuditBean.setAutitState(verifyState);
			marketAuditBean.setAuditLevel(auditLevel);
			marketSecondBean.setMarketAuditBean(marketAuditBean);
			marketSecondService.updateMarketSecondForVerify(marketSecondBean);//更新二级营销案
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
	 * 二级营销案线上测试
	 * 创建日期：2013-12-17下午2:23:12
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateTestState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil
					.getBeanFromRequest(request, MarketSecondBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String channelNum = userInfoBean.getChannelNum();
		String verifyState = marketSecondBean.getTestOnlineState();
		if(channelNum != null && !"".equals(channelNum)){
			//网厅
			if("01".equals(channelNum) || "4".equals(channelNum)){
				marketSecondBean.setWtTestOnlineState(marketSecondBean.getTestOnlineState());
				marketSecondBean.setTestOnlineState(null);
			}
			//短厅
			if("03".equals(channelNum) || "6".equals(channelNum)){
				marketSecondBean.setDtTestOnlineState(marketSecondBean.getTestOnlineState());
				marketSecondBean.setTestOnlineState(null);
			}
			//掌厅
		}
		try {
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketSecondBean.setMarketAuditBean(marketAuditBean);
			marketSecondService.updateTestState(marketSecondBean);
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
	 * 
	 * 更新二级营销案列表展示状态，0为未展示，1为展示
	 *
	 * @param context
	 * @return
	 */
	public HandlerResult updateIsViewListState(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		MarketSecondBean marketSecondBean = (MarketSecondBean) BeanUtil
				.getBeanFromRequest(request, MarketSecondBean.class);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    String channelNum = userInfoBean.getChannelNum();
	    if(channelNum != null && !"".equals(channelNum)){
			//掌厅，原参数入库，不处理
			//网厅
			if("01".equals(channelNum)){
				marketSecondBean.setWtIsListView(marketSecondBean.getIsListView());
				marketSecondBean.setIsListView(null);
			}
			
		}
		try {
			marketSecondService.updateIsViewListState(marketSecondBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}

		return result;
	}
	
	public HandlerResult queryViewNameOrderByZTMarketOrder(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketFirstPkid=request.getParameter("marketFirstPkid");
		try {
			Page page=marketSecondService.queryViewNameOrderByZTMarketOrder(marketFirstPkid);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}		
		return result;
		}
	
	public HandlerResult queryViewNameOrderByWTMarketOrder(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketFirstPkid=request.getParameter("marketFirstPkid");
		try {
			Page page=marketSecondService.queryViewNameOrderByWTMarketOrder(marketFirstPkid);
			result.setRetObj(page);
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
