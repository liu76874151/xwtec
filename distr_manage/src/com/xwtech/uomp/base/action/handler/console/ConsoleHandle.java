/**
 * Title: ConsoleHandle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-30 
 * @ time 下午2:20:13
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.console;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.console.ConsoleBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.ICustomMenuService;
import com.xwtech.uomp.base.service.adv.IAdvinfoService;
import com.xwtech.uomp.base.service.business.IBusinessInfoService;
import com.xwtech.uomp.base.service.console.IconsoleService;
import com.xwtech.uomp.base.service.market.IMarketFirstService;
import com.xwtech.uomp.base.service.market.IMarketSecondService;
import com.xwtech.uomp.base.service.market.IReservationFirstService;
import com.xwtech.uomp.base.service.market.IReservationMarketSecondService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/**
 * @author zhanglu
 *
 */
@Controller("H_console")
public class ConsoleHandle implements IHandler{

	@Autowired
	IconsoleService consoleService;
	@Autowired
	IAdvinfoService advinfoService;
	@Autowired
	IMarketFirstService marketFirstService;
	@Autowired
	IMarketSecondService marketSecondService;
	@Autowired
	IBusinessInfoService businessInfoService;
	@Autowired
	IReservationFirstService reservationFirstService;
	@Autowired
	IReservationMarketSecondService reservationMarketSecondService;
	@Autowired
	ICustomMenuService customMenuService;
	
	public HandlerResult queryOrderCountByMonth(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
//	    String city = userInfoBean.getUserAreaCode();
//	    if("0".equals(city)){
	    	String cityStr = request.getParameter("city");
	    	param.put("city", cityStr);
//	    }else{
//	    	param.put("city", city);
//	    }
		try {
			List<ConsoleBean> list = consoleService.queryOrderNumByMonth(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	} 
	
	public HandlerResult queryOrderCountByCity(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    String city = userInfoBean.getUserAreaCode();
	    if(!"0".equals(city)){
	    	param.put("city", city);
	    }
		try {
			List<ConsoleBean> list = consoleService.queryCountByCity(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	} 
	
	public HandlerResult querySumMoneyByCity(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    String city = userInfoBean.getUserAreaCode();
	    if(!"0".equals(city)){
	    	param.put("city", city);
	    }
		try {
			List<ConsoleBean> list = consoleService.querySumMoneyByCity(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	} 
	
	public HandlerResult querySumMoneyByMonth(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
//	    String city = userInfoBean.getUserAreaCode();
	    String cityStr = request.getParameter("city");
    	param.put("city", cityStr);
		try {
			List<ConsoleBean> list = consoleService.querySumMoneyByMonth(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	} 
	
	/**
	 * 控制台广告审核列表
	 * 创建日期：2014-1-6上午9:39:17
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryAdvInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
	    String city = userInfoBean.getUserAreaCode();
	    if(!"0".equals(city)){
	    	param.put("city", city);
	    }
	    String channelNum = userInfoBean.getChannelNum();
	    if(channelNum != null && !"".equals(channelNum)){
	    	param.put("channelNum", channelNum);
	    }
        try {
			Page page=advinfoService.queryAdvInfoListOnConsole(param);
			result.setRetObj(JSONArray.toJSONString(page.getRecords()));
			result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	
	/**
	 * 控制台一级营销案审核列表
	 * 创建日期：2014-1-6下午2:59:56
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketFirstAuditList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		if ("0".equals(city)) {// 0 为江苏省
			city = "";
			param.put("proManager", "1");
		} else {
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
			List<MarketFirstBean> list = marketFirstService.queryMarketAuditOnConsole(param);
			result.setRetObj(JSONArray.toJSONString(list));
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}

		return result;
	}
	
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
			Page page = marketSecondService.queryMarketSecondForVerifyOnConsole(param);
			result.setRetObj(JSONArray.toJSONString(page.getRecords()));
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);			
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
		}
		return result;
	}
	
	public HandlerResult selectBusiInfoListForVerify(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		try {
		    String userarea = userInfoBean.getUserAreaCode();
		    Integer isProUser = 0;
		    if ("0".equals(userarea)) {
			isProUser = 1;
			param.put("isProUser", isProUser.toString());
		    }
		    
		    if (StringUtils.isEmpty(userarea)) {
			result.setRetCode(IResultCode.SYS_FAILED);
			return result;
		    }
		    
		    String state = (String) param.get("state");
		    if (StringUtils.isEmpty(state)) {
			state = "2,3,4";
		    }
		    String[] arr = state.split(",");
		    param.put("state", arr);
		    
		    String brandNum = (String) param.get("brandNum");
		    if (StringUtils.isEmpty(brandNum)) {
			brandNum = null;
			param.put("brandNum", brandNum);
		    }else{
			String[] barr = brandNum.split(",");
			param.put("brandNum", barr);
		    }
		    String channelNum = (String) param.get("channelNum");
		    if (StringUtils.isEmpty(channelNum)) {
			channelNum = null;
			param.put("channelNum", channelNum);
		    }else{
			String[] barr = channelNum.split(",");
			param.put("channelNum", barr);
		    }
		    
		    String city = (String) param.get("city");
		    if (StringUtils.isEmpty(city)) {
			if ("0".equals(userarea)) {//省级用户
			    param.put("city", null);
			}else {
			    param.put("city", userarea);
			}
		    }else {
			param.put("city", city);
		    }
		    
		    Page page = businessInfoService.selectBusiInfoListForVerifyOnConsole(param,userInfoBean);
		    result.setRetObj(JSONArray.toJSONString(page.getRecords()));
		    result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
		    e.printStackTrace();
		    result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	    }
	
	/**
	 * 控制台一级预约营销案审核
	 * 创建日期：2014-1-7下午2:12:22
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketFirstAuditListOnConsole(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		if ("0".equals(city)) {// 0 为江苏省
			param.put("proManager", "1");
		} else {
			param.put("cityManager", "1");
		}
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
			Page page = reservationFirstService.queryMarketForAuditOnConsole(param);
			result.setRetObj(JSONArray.toJSONString(page.getRecords()));
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
	 * 预约营销案二级审核-首页
	 * 创建日期：2014-3-4下午4:01:45
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketSecondForVerifyOnConsole(HandlerRequestContext context) {
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
			Page page = reservationMarketSecondService.queryMarketSecondForVerifyOnConsole(param);
			result.setRetObj(JSONArray.toJSONString(page.getRecords()));
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
	 * 控制台自定义菜单展示
	 * 创建日期：2014-1-8下午2:37:20
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryCustomMenuList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
        try {
			List<Map<String, Object>> list=customMenuService.queryCustomMenuOnConsole(userInfoBean.getLoginName());
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	
}
