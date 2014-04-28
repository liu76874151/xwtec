package com.xwtech.uomp.base.action.handler.webinfo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.webinfo.DailyPaperBean;
import com.xwtech.uomp.base.service.webinfo.IDailyPaperService;
import com.xwtech.uomp.base.util.BeanUtil;
@Controller("H_dailyPaper")
public class DailyPaperHandle implements IHandler {
	@Autowired
	IDailyPaperService dailyPaperService;
	public HandlerResult queryDailyPaperList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			Page page=dailyPaperService.queryDailyPaper(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
		}
	public HandlerResult updateDailyPaper(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			dailyPaperService.updateDailyPaperBean(param);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;}
	public HandlerResult addDailyPaper(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		DailyPaperBean bean=(DailyPaperBean) BeanUtil.getBeanFromRequest(request, DailyPaperBean.class);
		try {
			dailyPaperService.insertSelective(bean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	public HandlerResult deleteDailyPaper(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			dailyPaperService.deleteDailyPaperBean(param);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
		}
	public HandlerResult queryOneDailyPaper(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			DailyPaperBean bean=dailyPaperService.queryOneDailyPaper(param);
			result.setRetObj(bean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;}
	
}
