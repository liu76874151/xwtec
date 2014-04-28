package com.xwtech.uomp.base.action.handler.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.ReMsRelationBean;
import com.xwtech.uomp.base.pojo.market.ReservationFirstBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.market.IMarketGroupRelationService;
import com.xwtech.uomp.base.service.market.IReMsRelationService;
import com.xwtech.uomp.base.service.market.IReSaleOfficeInfoService;
import com.xwtech.uomp.base.service.market.IReservationFirstService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/**
 *@ClassName:ReservationFirstHandle.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-25 time：下午04:22:06
 *@version 1.0
 */
@Controller("H_reservationFirst")
public class ReservationFirstHandler implements IHandler {
	@Autowired
	IReservationFirstService reservationFirstService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IReMsRelationService reMsRelationService;
	@Autowired
	IReSaleOfficeInfoService reSaleOfficeInfoService;
	@Autowired
	IFileuploadService fileuploadService;
	@Autowired
	IMarketGroupRelationService marketGroupRelationService;

	@Value("${fileUpload.URL}")
	private String targetURL;
	
	/**
	 * 查询一级预约营销案
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult queryReservationFirstList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String channelNum = userInfoBean.getChannelNum();
		String city = userInfoBean.getUserAreaCode();
		if (param.get("city") == null) {
			city = "0".equals(city) ? "" : city;// --省级用户全查出来like '%'
			if(null==city)city="";
			param.put("city", city);
		}
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
			param.put("userChannalData", channelNum);
		}
		try {
			Page page = reservationFirstService
					.queryReservationFirstList(param);
			List<ReservationFirstBean> fistMarketList = (List<ReservationFirstBean>)page.getRecords();
			List<ReservationFirstBean> newFistMarketList = new ArrayList<ReservationFirstBean>();
			if(null != fistMarketList && fistMarketList.size() > 0){
				for(int i = 0 ; i < fistMarketList.size(); i++){
					ReservationFirstBean fistBean = (ReservationFirstBean)fistMarketList.get(i);
					//一级预约营销案主键
					String marketFirstPkid = fistBean.getMarketFirstPkid();
					//一级预约营销案boss编码
					String marketFirstCode = fistBean.getMarketFirstCode();
					//一级预约营销案名称
					String viewName = fistBean.getViewName();
					String linkInfo = "<a href='javascript:component.viewSecond(\""+marketFirstPkid+"\",\""+marketFirstCode+"\",\""+viewName+"\");'>关联二级预约营销案</a> ";
					
					String isViewList=fistBean.getIsListView();
					String wtIsViewList = fistBean.getWtIsListView();
					//掌厅列表展示状态
					if("5".equals(channelNum)){
						if(isViewList!=null&&"1".equals(isViewList)){
							String isViewAlink = "<a href='javascript:component.updateListView()' title='点击更新为在列表中不展示'>展示<img src='../../../resource/img/toggle_disabled.gif'/></a>";
							fistBean.setLinkOper(isViewAlink);
						}
						else{
							String isViewAlink = "<a href='javascript:component.updateListView();' title='点击更新为在列表中展示'>未展示<img src='../../../resource/img/toggle_enabled.gif'/></a>";
							fistBean.setLinkOper(isViewAlink);
						
						}
					}
					//网厅列表展示状态
					if("4".equals(channelNum)){
						if(wtIsViewList!=null&&"1".equals(wtIsViewList)){
							String isViewAlink = "<a href='javascript:component.updateListView()' title='点击更新为在列表中不展示'>展示<img src='../../../resource/img/toggle_disabled.gif'/></a>";
							fistBean.setLinkOper(isViewAlink);
						}
						else{
							String isViewAlink = "<a href='javascript:component.updateListView();' title='点击更新为在列表中展示'>未展示<img src='../../../resource/img/toggle_enabled.gif'/></a>";
							fistBean.setLinkOper(isViewAlink);
						
						}
					}
					fistBean.setRelateSecondList(linkInfo);
					newFistMarketList.add(fistBean);
				}
				page.setRecords(newFistMarketList);
			}
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

	/**
	 * 查询展示顺序
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult queryAllMarketOrder(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		try {
			Page page = reservationFirstService.queryAllMarketOrder(city);
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

	/**
	 * 增加预约一级营销案
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult saveReservationFirst(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String city = userInfoBean.getUserAreaCode();
		String cfgUserId = userInfoBean.getUserName();
		ReservationFirstBean reservationFirstBean = (ReservationFirstBean) BeanUtil
		.getBeanFromRequest(request, ReservationFirstBean.class);
		List<ReMsRelationBean> relationBeansList = new ArrayList<ReMsRelationBean>();
		// --渠道：0-无权限，1-有权限，第1位代表网厅,第2位代表网村组服站,第3位代表校园e站
		String tChannal = RequestUtil.getString(request, "tChannal");
		//if (channalData.contains("4") && (StringUtils.isNotBlank(tChannal))) {/
		if(StringUtils.isNotBlank(tChannal)){
		String channalGroup[] = tChannal.split("\\.");
		int channalValue = 0;
		if (null != channalGroup && !"".equals(channalGroup[0])) {
			int[] cGroup = new int[channalGroup.length];
			for (int i = 0; i < channalGroup.length; i++) {
				cGroup[i] = Integer.valueOf(channalGroup[i]);
			}
			if (null == channalGroup || channalGroup.length <= 0) {
				channalValue = 0;
			} else {
				channalValue = 1 << (cGroup[0] - 1);
				for (int i = 1; i < cGroup.length; i++) {
					channalValue = channalValue | (1 << (cGroup[i] - 1));
				}
			}
		}
		reservationFirstBean.settChannal(channalValue + "");
	}else{
		reservationFirstBean.settChannal(null);
	}
		String marketOrder = RequestUtil.getString(request, "marketOrder");
		String reservationFirstPkids[] = marketOrder.split("\\.");
		String targetGroupInfo = RequestUtil.getString(request,
				"targetGroupInfo");
		String reservationFirstPkid = sequenceService
				.getSequence("RESERVATION_FIRST_PKID_SEQ");
		
		reservationFirstBean.setCity(city);
		reservationFirstBean.setCfgUserId(cfgUserId);
		reservationFirstBean.setState("1");
		reservationFirstBean.setVerifyState("0");
		reservationFirstBean.setIsListView("0");
		
		reservationFirstBean.setMarketFirstPkid(reservationFirstPkid);
		if ("".equals(targetGroupInfo)) {
			reservationFirstBean.setIsInGroup("0");
		} else {
			reservationFirstBean.setIsInGroup("1");
			relationBeansList = JSONArray.parseArray(targetGroupInfo,
					ReMsRelationBean.class);
			for (ReMsRelationBean reMsRelationBean : relationBeansList) {
				reMsRelationBean.setMarketFirstPkid(reservationFirstPkid);
			}

		}
		try {
			int i = reservationFirstService
					.saveReservationFirst(reservationFirstBean);// --插入
			if (i == 1) {
				reservationFirstService.sort(reservationFirstPkids,
						reservationFirstPkid);// --排序营销案顺序
				reMsRelationService.saveReMsRelationBean(relationBeansList);// --添加一级预约营销案目标组关系

			}
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			fileNameMap.put("marketFirstPkid", reservationFirstPkid);
			result.setRetObj(fileNameMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}
		return result;

	}

	/**
	 * 逻辑删除预约一级营销案
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult deleteReservationFirst(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		String reservationFirstPkidsTemp = RequestUtil.getString(request,
				"pkid");
		String[] reservationFirstPkids = {};
		if (!StringUtil.isNull(reservationFirstPkidsTemp)) {
			reservationFirstPkids = reservationFirstPkidsTemp.split(",");
		}
		// 组装in里面主键编号
		StringBuffer ids = new StringBuffer();
		ids.append("-1");// 开始组装无效值
		for (int i = 0; i < reservationFirstPkids.length; i++) {
			if (reservationFirstService
					.queryReservationSencodCount(reservationFirstPkids[i]) == 0) {// --无关联二级才能删除
				ids.append(",").append(reservationFirstPkids[i]);
			}
		}
		reservationFirstPkids = ids.toString().split(",");
		param.put("reservationFirstPkids", reservationFirstPkids);
		try {
			reservationFirstService.deleteReservationFirst(param);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.DELETE_CACHE_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
		}

		return result;
	}

	/**
	 * 查询一条预约营销案
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult queryByPrimaryKey(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String reservationFirstPkid = RequestUtil.getString(request, "pkid");
		try {
			ReservationFirstBean reservationFirstBean = reservationFirstService
					.queryByPrimaryKey(reservationFirstPkid);
			result.setRetObj(reservationFirstBean);
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
	 * 修改预约营销案
	 * Map<String, String> map
	 * @param context
	 * @return
	 */
	public HandlerResult updateReservationFirst(HandlerRequestContext context) {

		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String tChannal = RequestUtil.getString(request, "tChannal");
		String targetGroupInfo = request.getParameter("targetGroupInfo");//目标组织
		List<ReMsRelationBean> relationBeans = JsonHelper.json2List(targetGroupInfo, ReMsRelationBean.class);
		  Map<String, String> param = BeanUtil.getMapFromRequest(request);
		  String channalGroup[] =null;
		    if(null!=tChannal){
		    	channalGroup=tChannal.split("\\.");}
		    Integer channalValue=null;
		   	if (null != channalGroup && !"".equals(channalGroup[0])) {
		   		
		        int[] cGroup=new int[channalGroup.length];
		        for(int i=0;i<channalGroup.length;i++){
		            cGroup[i]=Integer.valueOf(channalGroup[i]);
		         }
		        if(null==channalGroup||channalGroup.length<=0)
		        {
		            channalValue = 0;
		        }
		        else
		        {
		            channalValue = 1<<(cGroup[0]-1);
		            for (int i = 1; i < cGroup.length; i++)
		            {
		                channalValue = channalValue | (1<<(cGroup[i]-1));
		            }
		        }
		     	
		     	}
		   	if(null!=channalValue){
		   	param.put("tChannal", channalValue+"");
		   	}
		try {
			reservationFirstService.updateReservationFirst(param);
			reMsRelationService.deleteReMsRelation(param.get("marketFirstPkid"));
			reMsRelationService.saveReMsRelationBean(relationBeans);
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			result.setRetObj(fileNameMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}

		return result;
	}
	/**
	 * 查询营业厅
	 * @param context
	 * @return
	 */
	public HandlerResult queryReSaleOfficeInfo(HandlerRequestContext context) {
		HandlerResult result =HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String marketFirstPkid=RequestUtil.getString(request, "marketFirstPkid");
		try {
		Page page=reSaleOfficeInfoService.queryReSaleOfficeInfo(marketFirstPkid);
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
	
	/**
	 * 上传图片
	 * 创建日期：2013-12-9下午2:51:49
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult fileUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String fileName = request.getParameter("fileName");
		String marketFirstPkid = request.getParameter("marketFirstPkid");
		try {
			boolean flag = fileuploadService.fileuploadReName(request,targetURL,fileName);
			if(flag){
				 Map<String, String> param = BeanUtil.getMapFromRequest(request);
				 param.put("marketFirstPkid", marketFirstPkid);
				 param.put("imgDir",targetURL + "/"+ fileName);
				reservationFirstService.updateReservationFirst(param);
			}
			result.setRetObj(flag);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 线上测试列表查询
	 * 创建日期：2013-12-17下午3:27:32
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryMarketFirstTestList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
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
			Page page = reservationFirstService.queryMarketForTest(param);
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
	
	/**
	 * 预约营销案线上测试
	 * 创建日期：2013-12-17下午3:55:41
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateTestState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      ReservationFirstBean marketFirstBean = (ReservationFirstBean) BeanUtil
					.getBeanFromRequest(request, ReservationFirstBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String channelNum = userInfoBean.getChannelNum();
		String verifyState = marketFirstBean.getTestOnlineState();
		if(channelNum != null && !"".equals(channelNum)){
			//网厅
			if("01".equals(channelNum) || "4".equals(channelNum)){
				marketFirstBean.setWtTestOnlineState(marketFirstBean.getTestOnlineState());
				marketFirstBean.setTestOnlineState(null);
			}
			//掌厅
		}
		try {
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketFirstBean.setMarketAuditBean(marketAuditBean);
			reservationFirstService.updateTestState(marketFirstBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		
		return result;
		}
	
	/**
	 * 地市及省级审核
	 * 创建日期：2013-12-17下午7:00:12
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
			//全渠道
			if("0".equals(channelNum)){
				channelNum = param.get("channalData");
			}
			param.put("channalData", channelNum);
		}
		
		if ("0".equals(city)) {// 0 为江苏省
			param.put("proManager", "1");
		} else {
			param.put("cityManager", "1");
		}
		try {
			Page page = reservationFirstService.queryMarketForAudit(param);
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
	
	/**
	 * 一级预约营销案审核
	 * 创建日期：2013-12-17下午7:12:19
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateAuditState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      ReservationFirstBean marketFirstBean = (ReservationFirstBean) BeanUtil
					.getBeanFromRequest(request, ReservationFirstBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String verifyState = "";
		String city = userInfoBean.getUserAreaCode();
		String auditLevel = "";
		if ("0".equals(city)) {// 0 为江苏省
			verifyState = request.getParameter("proVerifyState");
			auditLevel = "1";
			//如果是省级管理员，并且是审核通过的将最终状态改为“待测试”
			marketFirstBean.setVerifyState("3");
		} else {
			verifyState = request.getParameter("localVerifyState");
			auditLevel = "2";
		}
		try {
			String channelNum = userInfoBean.getChannelNum();
			if ("0".equals(city)) {// 0 为江苏省
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketFirstBean.setWtProVerifyState(verifyState);
						marketFirstBean.setProVerifyState(null);
						marketFirstBean.setLocalVerifyState(null);
					}
				}
			}else{
				if(channelNum != null && !"".equals(channelNum)){
					//网厅
					if("01".equals(channelNum) || "4".equals(channelNum)){
						marketFirstBean.setWtLocalVerifyState(verifyState);
						marketFirstBean.setProVerifyState(null);
						marketFirstBean.setLocalVerifyState(null);
					}
				}
			}
			
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketAuditBean.setAuditLevel(auditLevel);
			marketFirstBean.setMarketAuditBean(marketAuditBean);
			reservationFirstService.updateAuditState(marketFirstBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		
		return result;
		}
}
