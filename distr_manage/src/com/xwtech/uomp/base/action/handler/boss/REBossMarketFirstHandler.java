package com.xwtech.uomp.base.action.handler.boss;

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
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.boss.IREBossMarketFirstService;
import com.xwtech.uomp.base.service.tools.IToolsService;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 *@ClassName:REBossMarketFirstHandle.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-28 time：下午04:24:24
 *@version 1.0
 */
@Controller("H_rebossMarketFirst")
public class REBossMarketFirstHandler implements IHandler {
	@Autowired 
	IREBossMarketFirstService rebossMarketFirstService ;
	@Autowired 
	IToolsService  toolsService;
	public HandlerResult queryCrmInfo(HandlerRequestContext context) {
	    HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city=userInfoBean.getUserAreaCode();
		//city=toolsService.reverseCityCode(city);
		 try {
				Page page=rebossMarketFirstService.queryCrmInfo(city);
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
	
	public HandlerResult queryCrmBrandInfo(HandlerRequestContext context) {
	    HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    String crmfirstCode = RequestUtil.getString(request, "crmFirstCode");
	    try {
			String crmBrandInfo=rebossMarketFirstService.queryCrmBrandInfo(crmfirstCode);
			result.setRetObj(crmBrandInfo);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
	    
		return result;
	}
	
}
