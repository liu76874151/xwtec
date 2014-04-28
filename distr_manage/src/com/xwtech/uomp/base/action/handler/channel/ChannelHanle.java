/**
 * Title: ChannelHanle.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-8 
 * @ time 下午5:27:51
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.action.handler.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.channel.ChannelBean;
import com.xwtech.uomp.base.service.channel.IChannelService;
import com.xwtech.uomp.base.util.BeanUtil;

/**
 * @author zhanglu
 *
 */
@Controller("H_channel")
public class ChannelHanle implements IHandler {

	@Autowired
	IChannelService channelService;
	
	public HandlerResult queryAllChannels(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			Page page = channelService.queryChannel(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	}
	
	public HandlerResult queryChannelByNum(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
	    Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			ChannelBean channelBean = channelService.queryChannelByNum(param);
			result.setRetObj(channelBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		
	}
	
	/**
	 * 添加渠道
	 * 创建日期：2013-12-8下午8:24:42
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult addChannelInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		ChannelBean channelBean = (ChannelBean)BeanUtil.getBeanFromRequest(request, ChannelBean.class);
		try {
			channelService.addChannel(channelBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 修改渠道
	 * 创建日期：2013-12-8下午9:02:49
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateChannelInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		ChannelBean channelBean = (ChannelBean)BeanUtil.getBeanFromRequest(request, ChannelBean.class);
		try {
			channelService.updateChannel(channelBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
	
	public HandlerResult deleteChannelInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String[] channelNums = request.getParameterValues("channeNums[]");
		List<String> channelNumsList = new ArrayList<String>();
		for(int i = 0; i < channelNums.length; i++){
			channelNumsList.add(channelNums[i]);
		}
		try {
			channelService.deleteChannel(channelNumsList);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;
	}
	
}
