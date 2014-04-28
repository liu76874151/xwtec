package com.xwtech.uomp.base.action.handler.org;

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
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.org.OrgInfoBean;
import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.org.IOrgInfoService;
import com.xwtech.uomp.base.service.org.ITargetGroupInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 * 营业厅,目标组织
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-16 下午03:57:57
 */
@Controller("H_orgHandle")
public class OrgHandler implements IHandler {

	@Autowired
	IOrgInfoService orgInfoService;
	@Autowired
	ITargetGroupInfoService targetGroupInfoService;
	@Autowired
	ISequenceService sequenceService;
	
	/**
	 * 查询营业厅
	 * @param context
	 * @return
	 */
	public HandlerResult queryOrgInfoList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);

		try {
			List<OrgInfoBean> list = orgInfoService.queryOrgInfo(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 分页查询营销案
	 * 创建日期：2013-12-14上午11:49:59
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryOrgInfoByPage(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		String city = userInfoBean.getUserAreaCode();
		 if("0".equals(city)){
		    	String cityStr = request.getParameter("cityId");
		    	param.put("cityId", cityStr);
		    }else{
		    	param.put("cityId", city);
		    }
		try {
			Page page = orgInfoService.queryOrgInfoByPage(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询目标组织
	 * @param context
	 * @return
	 */
	public HandlerResult queryTargetGroupInfo(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		if(param.get("city")==null){
			String city=userInfoBean.getUserAreaCode();
			   city="0".equals(city)?null:city;//--省级用户全查出来
			   if(null==city)city="";
			   param.put("cityId", city);
		}
		
		try {
			List<TargetGroupInfoBean> list = targetGroupInfoService.querytargetGroupInfoList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 根据二级营销PKid过滤目标组织
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryTargetGroupFilterBySecondPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			List<TargetGroupInfoBean> list = targetGroupInfoService.selectTargetGroupFilterBySecondPkid(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	public HandlerResult addOrgInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		OrgInfoBean orginfobean = (OrgInfoBean)BeanUtil.getBeanFromRequest(request, OrgInfoBean.class);
		try {
			String orgCode = sequenceService.getSequence("JSMSS_ORG_INFO_SEQ");
			orginfobean.setOrgCode(new Long(orgCode));
			orgInfoService.addOrgInfo(orginfobean);
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
	
	public HandlerResult queryOrgByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			OrgInfoBean orgInfoBean = orgInfoService.queryOrgInfoByPk(param);
			result.setRetObj(orgInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	public HandlerResult updateOrgInfo(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		OrgInfoBean orginfobean = (OrgInfoBean)BeanUtil.getBeanFromRequest(request, OrgInfoBean.class);
		try {
			orgInfoService.updateOrgInfo(orginfobean);
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
	
	public HandlerResult updateOrgInfoState(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		OrgInfoBean orginfobean = (OrgInfoBean)BeanUtil.getBeanFromRequest(request, OrgInfoBean.class);
		try {
			orgInfoService.updateOrgInfoState(orginfobean);
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
