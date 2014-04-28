package com.xwtech.uomp.base.action.handler.market;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketTemplateBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.market.IMarketTemplateService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/*协议模板
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午08:23:41
 */
@Controller("H_marketTemplate")
public class MarketTemplateHandler implements IHandler{
	
	@Autowired
	IMarketTemplateService marketTemplateService;
	@Autowired
	ISequenceService sequenceService;
	
	/**
	 * 查询协议模板
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryMarketTemplate(HandlerRequestContext context) {
		
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		  Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		    String channalDatasTemp = RequestUtil.getString(request, "channalData");
		    String []channalDatas=null;
		    if(!StringUtil.isNull(channalDatasTemp)){
		    	channalDatas=channalDatasTemp.split(",");
		    }
		    param.put("channalData", channalDatas);
		    param.put("city", city);
		try {
			List<MarketTemplateBean> list = marketTemplateService.queryMarketTemplate(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询模板
	 * @param context
	 * @return
	 */
	public HandlerResult queryMarketTemplateList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city1=request.getParameter("city");
		String city = userInfoBean.getUserAreaCode();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	    String channalDatasTemp = RequestUtil.getString(request, "channalData");
	    String []channalDatas=null;
	    if(!StringUtil.isNull(channalDatasTemp)){
	    	channalDatas=channalDatasTemp.split(",");}
	    param.put("channalData", channalDatas);
	    if("0".equals(city)){
	    	if(StringUtils.isBlank(city1)){
	    	city=null;
	    	}else{
	    		city=city1;
	    	}
	    }
	    param.put("city", city);
		try {
			Page page=marketTemplateService.queryMarketTemplateList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;}
	
	
	public HandlerResult saveMarketTemplate(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> paramMap =  BeanUtil.getMapFromRequest(request);
		String id=sequenceService.getSequence("MARKET_TEMPLATE_PKID_SEQ");
		paramMap.put("templateId", id);
		try {
			int count=marketTemplateService.isExistName(paramMap);
			if(count==0){
			marketTemplateService.saveMarketTemplate(paramMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			}else{
				System.out.println("用户名已存在"+count);
				result.setResMsg("用户名已存在!");
				result.setRetCode(IResultCode.SYS_FAILED);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;}
	
	
	public HandlerResult queryOneMarketTemplate(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> paramMap =  BeanUtil.getMapFromRequest(request);
		try {
			MarketTemplateBean marketTemplateBean=marketTemplateService.queryOneMarketTemplate(paramMap);
			result.setRetObj(marketTemplateBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	}
	public HandlerResult updateMarketTemplate(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> paramMap =  BeanUtil.getMapFromRequest(request);
		try {
			marketTemplateService.updateMarketTemplate(paramMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	public HandlerResult deleteMarketTemplate(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String templateId=RequestUtil.getString(request, "templateId");
		try {
			marketTemplateService.deleteMarketTemplate(templateId);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	
}
