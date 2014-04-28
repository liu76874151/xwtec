package com.xwtech.uomp.base.action.handler.gift;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;
import com.xwtech.uomp.base.pojo.gift.GiftInfoTypeBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.gift.IGiftInfoService;
import com.xwtech.uomp.base.service.gift.IGiftInfoTypeService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.service.tools.IToolsService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 * 二级营销案管理
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-12 下午06:50:35
 */
@Controller("H_giftInfoHandler")
public class GiftInfoHandler implements IHandler {

	@Autowired
	IGiftInfoTypeService giftInfoTypeService;
	@Autowired
	IGiftInfoService giftInfoService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired 
	IToolsService  toolsService;
	/**
	 * 查询礼品类型
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryGiftInfoTypeList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			List<GiftInfoTypeBean> list = giftInfoTypeService.queryGiftInfoTypeList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 维护列表
	 * 创建日期：2013-12-25上午11:19:28
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryGiftInfoTypeListForManage(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			Page page = giftInfoTypeService.queryGifgInfoTypeList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询礼品信息
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryGiftInfoBeans(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
//		String marketSecondPkid = RequestUtil.getString(request, "marketSecondPkid");
//		String marketSecondCode = RequestUtil.getString(request, "marketSecondCode");
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String userarea = userInfoBean.getUserAreaCode();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		userarea=toolsService.reverseCityCode(userarea);
		param.put("cityId", userarea);
		try {
			List<GiftInfoBean> giftInfoBeans = giftInfoService.selectBySecondPkid(param); 
			result.setRetObj(giftInfoBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	public HandlerResult queryGiftInfoTypeById(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String giftType = request.getParameter("giftType");

		try {
			GiftInfoTypeBean bean = giftInfoTypeService.queryGiftTypeById(giftType);
			result.setRetObj(bean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	public HandlerResult addGiftTypeInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		GiftInfoTypeBean giftInfoTypeBean = (GiftInfoTypeBean)BeanUtil.getBeanFromRequest(request, GiftInfoTypeBean.class);
		
		try {
			String giftType = sequenceService.getSequence("JSMSS_GIFT_INFO_TYPE_SEQ");
			giftInfoTypeBean.setGiftType(Integer.parseInt(giftType));
			giftInfoTypeService.addGiftTypeInfo(giftInfoTypeBean);
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
	
	public HandlerResult updateGiftTypeInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String giftType = request.getParameter("giftType");
		GiftInfoTypeBean giftInfoTypeBean = (GiftInfoTypeBean)BeanUtil.getBeanFromRequest(request, GiftInfoTypeBean.class);
		giftInfoTypeBean.setGiftType(Integer.parseInt(giftType));
		try {
			giftInfoTypeService.updateGiftTypeInfo(giftInfoTypeBean);
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
