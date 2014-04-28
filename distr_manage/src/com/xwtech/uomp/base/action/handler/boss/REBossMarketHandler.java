/**
 * Title: REBossMarketHandler.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-29 
 * @ time 下午3:25:18
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.boss;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.boss.REBossMarketSecondBean;
import com.xwtech.uomp.base.service.boss.IREBossMarketSecondService;

/**
 * @author zhanglu
 * 
 */
@Controller("H_rebossMarket")
public class REBossMarketHandler implements IHandler {
	
	private final static Logger log = Logger.getLogger(REBossMarketHandler.class);
	
	@Autowired
	IREBossMarketSecondService rebossMarketSecondService;

	/**
	 * 根据一级预约营销CRM编码获取二级预约营销案列表
	 * 创建日期：2013-10-29下午4:31:12
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult queryCrmSecondInfoList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String crmFirstCode = request.getParameter("crmFirstCode");

		try {
			List<REBossMarketSecondBean> list = rebossMarketSecondService.queryCrmSecondInfo(crmFirstCode);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据二级预约营销CRM编码获取boss礼品信息
	 * 创建日期：2013-10-29下午7:26:12
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public HandlerResult queryCrmSecondGiftInfoList(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String crmSecondCode = request.getParameter("crmSecondCode");

		try {
			List<REBossMarketSecondBean> list = rebossMarketSecondService.queryCrmSecondGiftInfo(crmSecondCode);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

}
