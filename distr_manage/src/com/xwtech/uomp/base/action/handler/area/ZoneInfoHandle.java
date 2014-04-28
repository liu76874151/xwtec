/**
 * Title: ZoneInfoHandle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-15 
 * @ time 上午11:15:32
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.area;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.org.ZoneInfoBean;
import com.xwtech.uomp.base.service.org.IZoneInfoService;
import com.xwtech.uomp.base.util.BeanUtil;

/**
 * @author zhanglu
 *
 */
@Controller("H_zoneInfo")
public class ZoneInfoHandle implements IHandler{

	@Autowired
	IZoneInfoService zoneInfoService;
	
	public HandlerResult queryZoneInfoList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			List<ZoneInfoBean> list = zoneInfoService.queryZoneByCity(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	public HandlerResult queryBossAreaList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param =BeanUtil.getMapFromRequest(request);
		try {
			List<ZoneInfoBean> list = zoneInfoService.queryBossAreaList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
}
