package com.xwtech.uomp.base.action.handler.webinfo;

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
import com.xwtech.uomp.base.pojo.webinfo.SiteBaseInfoBean;
import com.xwtech.uomp.base.service.webinfo.ISiteBaseInfoService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
@Controller("H_webinfo")
public class WebinfoHandle implements IHandler {
	@Autowired
	ISiteBaseInfoService siteBaseInfoService;
	public HandlerResult queryWebInfoList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			Page page=siteBaseInfoService.queryWebInfoList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
		}
	public HandlerResult selectByPrimaryKey(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String channelNums=RequestUtil.getString(request, "channelNum");
		Integer channelNum=null;
		if(StringUtils.isNotBlank(channelNums)){
			channelNum=Integer.valueOf(channelNums);
		}
		try {
			SiteBaseInfoBean siteBaseInfoBean=siteBaseInfoService.selectByPrimaryKey(channelNum);
			result.setRetObj(siteBaseInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;}
	public HandlerResult updateByPrimaryKey(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		SiteBaseInfoBean siteBaseInfoBean=(SiteBaseInfoBean) BeanUtil.getBeanFromRequest(request, SiteBaseInfoBean.class);
		try {
			siteBaseInfoService.updateByPrimaryKey(siteBaseInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;}
}
