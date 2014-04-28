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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.MarketLogBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.market.IMarketFirstService;
import com.xwtech.uomp.base.service.market.IMarketLogService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;
import com.xwtech.uomp.base.util.order.Sendhttp;

/**
 *@ClassName:MarketFirstHandle.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-17 time：下午07:01:18
 *@version 1.0
 */
@Controller("H_marketFirst")
public class MarketFirstHandle implements IHandler {
	
	@Autowired 
	IMarketFirstService marketFirstService ;
	@Autowired
	IMarketLogService marketLogService;
	@Autowired
    ISequenceService  sequenceService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileYXAUpload.URL}")
	private String targetURL;
	@Value("${syncDT.URL}")
	private String syncDTURL;
/**
 * 查询一级营销案
 * @param context
 * @return
 */
public HandlerResult queryMarketFirstList(HandlerRequestContext context) {
    HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
    Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
    String channalDatasTemp = RequestUtil.getString(request, "channalData");
    String []channalDatas=null;	
    if(StringUtils.isNotBlank(channalDatasTemp)){
       channalDatas=new String[]{channalDatasTemp};
    }
  
   // if(!StringUtil.isNull(channalDatasTemp)){
    	//channalDatas=channalDatasTemp.split(",");
   // }
    

    param.put("channalData", channalDatas);
    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	String city=userInfoBean.getUserAreaCode();
	if(param.get("city")==null){
		   city="0".equals(city)?"":city;//--省级用户全查出来like '%'
		   if(null==city)city="";
		   param.put("city", city);
	}

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
		param.put("userChannalData", channelNum);
	}
	
    try {
		Page page=marketFirstService.queryMarketFirstList(param);
		List <MarketFirstBean> list=(List<MarketFirstBean>) page.getRecords();
		List <MarketFirstBean> newMarketFirstBeanList=new ArrayList<MarketFirstBean>();
		for(MarketFirstBean marketFirstBean:list){
			String marketFirstCode=marketFirstBean.getMarketFirstCode();
			String cityId=marketFirstBean.getCityId();
			String channalData=marketFirstBean.getChannalData();
			String marketFirstPkid=marketFirstBean.getMarketFirstPkid();
			String marketFirstName=marketFirstBean.getMarketFirstName();
			String brand=marketFirstBean.getIsInBrand();
			String linkInfo = "<a href='javascript:component.viewSecond(\""+marketFirstPkid+"\",\""+marketFirstCode+"\",\""+
								marketFirstName+"\",\""+cityId+"\",\""+channalData+"\",\""+brand+"\");'>关联二级营销案</a> ";
			marketFirstBean.setRelateSecondList(linkInfo);
		String isViewList=marketFirstBean.getIsListView();
		String wtIsViewList = marketFirstBean.getWtIsListView();
		String isViewAlink="";
		if("5".equals(channelNum)){
			if(isViewList!=null&&"1".equals(isViewList)){
				 isViewAlink = "<a href='javascript:component.updateListView(0,\""+marketFirstPkid+"\")' title='点击更新为在列表中不展示'>展示<img src='../../../resource/img/toggle_disabled.gif'/></a>";
			}
			else{
				 isViewAlink = "<a href='javascript:component.updateListView(1,\""+marketFirstPkid+"\");' title='点击更新为在列表中展示'>未展示<img src='../../../resource/img/toggle_enabled.gif'/></a>";
			}
		}
		if("4".equals(channelNum)){
			if(wtIsViewList!=null&&"1".equals(wtIsViewList)){
				 isViewAlink = "<a href='javascript:component.updateListView(0,\""+marketFirstPkid+"\")' title='点击更新为在列表中不展示'>展示<img src='../../../resource/img/toggle_disabled.gif'/></a>";
			}
			else{
				 isViewAlink = "<a href='javascript:component.updateListView(1,\""+marketFirstPkid+"\");' title='点击更新为在列表中展示'>未展示<img src='../../../resource/img/toggle_enabled.gif'/></a>";
			}
		}
		marketFirstBean.setLinkOper(isViewAlink);
		newMarketFirstBeanList.add(marketFirstBean);
		}
		page.setRecords(newMarketFirstBeanList);
		result.setRetObj(page);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
		e.printStackTrace();
		result.setRetCode(IResultCode.SYS_FAILED);
	}
  
	return result;
	}
/**
 *选择保存一级营销案
 * @param context
 * @return
 */

public HandlerResult saveMarketFirst(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		MarketFirstBean marketFirstBean = (MarketFirstBean) BeanUtil
				.getBeanFromRequest(request, MarketFirstBean.class);
		String unityFlag = marketFirstBean.getUnityFlag();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String cfgUserId = userInfoBean.getLoginName();
		String city = userInfoBean.getUserAreaCode();
		marketFirstBean.setCfgUserId(cfgUserId);
		String citys[]={};
		Map<String, Object> param=new HashMap<String, Object>();
		
		int isExist = 0;
		if ("0".equals(unityFlag)) {
			marketFirstBean.setCity(city);
			citys=new String[]{city};
		}else{
			String cityStr = marketFirstBean.getCity();
			cityStr=cityStr+",-1";
			citys = cityStr.split("\\,");
		}
		param.put("viewName", marketFirstBean.getViewName());
		param.put("city", citys);
		param.put("channalData", marketFirstBean.getChannalData());
		try {
			isExist = marketFirstService.isExistViewName(param);
			if (isExist != 0) {
				result.setRetCode(IResultCode.SYS_FAILED);
				result.setResMsg("一级方案名称已经存在!");
				//result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
				
			} else {
				marketFirstService.saveMarketFirst(marketFirstBean);
				long nanoTime = System.nanoTime(); 
				Map<String, String> fileNameMap = new HashMap<String, String>();
				fileNameMap.put("fileName", Long.toString(nanoTime));
				fileNameMap.put("marketFirstPkid", marketFirstBean.getMarketFirstPkid());
				result.setRetObj(fileNameMap);
				result.setRetCode(IResultCode.SYS_SUCCESS);
				result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}

		return result;
    }

/**
 * 按展示顺序查询一级营销案
 * @param context
 * @return
 */
public HandlerResult queryViewNameOrderByMarketOrder(HandlerRequestContext context) {
    HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
    String city="";
    String city1 = RequestUtil.getString(request, "city");
	if(city1==null||"".equals(city1)){
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		city=userInfoBean.getUserAreaCode();
	}else{
		city=city1;
	}

	try {
		Page page=marketFirstService.queryViewNameOrderByMarketOrder(city);
		result.setRetObj(page);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
        e.printStackTrace();
        result.setRetCode(IResultCode.SYS_FAILED);
        result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
	}
	return result;
	
}
public HandlerResult queryViewNameOrderByZTMarketOrder(HandlerRequestContext context) {
    HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
    String city="";
    String city1 = RequestUtil.getString(request, "city");
	if(city1==null||"".equals(city1)){
	    LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		city=userInfoBean.getUserAreaCode();
	}else{
		city=city1;
	}

	try {
		Page page=marketFirstService.queryViewNameOrderByZTMarketOrder(city);
		result.setRetObj(page);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
        e.printStackTrace();
        result.setRetCode(IResultCode.SYS_FAILED);
        result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
	}
	return result;
	
}
/**
 * 逻辑删除一条记录
 * @param context
 * @return
 */
@Transactional(propagation=Propagation.REQUIRED)
public HandlerResult deleteMarketFirst(HandlerRequestContext context) {
    HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
    Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
    
    String marketFirstPkidsTemp = RequestUtil.getString(request, "pkid");
    String[] marketFirstPkids={};
    if(!StringUtil.isNull(marketFirstPkidsTemp)){
    	marketFirstPkids=marketFirstPkidsTemp.split(",");
    }
    String cityID = RequestUtil.getString(request, "cityID");
    String channel = RequestUtil.getString(request, "channel");
    String marketFirstCode = RequestUtil.getString(request, "marketFirstCode");
    MarketLogBean marketLogBean=new MarketLogBean();
    //System.out.println("channel========="+channel);
    if(channel.indexOf("短厅")>-1){
    	  LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
  	    UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
    	
    	marketLogBean.setOptUser(userInfoBean.getLoginName());
    	marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
    	marketLogBean.setType("3");
    	marketLogBean.setCity(cityID);
    	marketLogBean.setCaseLevel("1");
    	marketLogBean.setFirstCode(marketFirstCode);
    	marketLogBean.setMarketFirstPkid(marketFirstPkids[0]);
    	marketLogBean.setFlag("0");
    }
    
	//组装in里面主键编号
	StringBuffer ids = new StringBuffer();
	ids.append("-1");//开始组装无效值
    for(int i=0; i<marketFirstPkids.length; i++){
    	if(marketFirstService.queryMarketSencodCount(marketFirstPkids[i])==0){
    		ids.append(",").append(marketFirstPkids[i]);
    	}
    }
    
    marketFirstPkids=ids.toString().split(",");
if(marketFirstPkids.length!=1){
    try {
        param.put("marketFirstPkids", marketFirstPkids);
    	marketFirstService.deleteMarketFirst(param);
    	if(channel.indexOf("短厅")>-1){
    	marketLogService.saveMarketLog(marketLogBean);}
        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.DELETE_CACHE_SUCCEED);
    } catch (Exception e) {
        result.setRetCode(IResultCode.SYS_FAILED);
        result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
    }

  
    }else{
    	result.setResMsg("请先删除关联的二级营销案!");
    	result.setRetCode(IResultCode.SYS_FAILED);
        //result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
    }
return result;
	}

/**
 * 根据pkid查询一条记录
 * @param context
 * @return
 */
public HandlerResult queryByPrimaryKey(HandlerRequestContext context) {
    HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
	String marketFirstPkid = RequestUtil.getString(request, "pkid");
	 try {
		 MarketFirstBean marketFirstBean=marketFirstService.queryByPrimaryKey(marketFirstPkid);
		 result.setRetObj(marketFirstBean);
         result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }
        return result;
	 
	}

/**
 * 修改一条一级营销案
 * @param context
 * @return
 */
public HandlerResult updateByPrimaryKey(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		Map<String, String> fileNameMap = new HashMap<String, String>();
		String yxImgDir = param.get("yxImgDir");
		String yxImgDir_old = param.get("yxImgDir_old");
		List<String> deleteFileName = new ArrayList<String>();
		String unityFlag = param.get("unityFlag");
		//System.out.println(unityFlag+"=====unityFlag========");
		//System.out.println(param.get("cityLog")+"=====city========");
		 LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		 UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		 
		String channelNum = userInfoBean.getChannelNum();
		if (channelNum != null && !"".equals(channelNum)) {
			// 掌厅
			if ("02".equals(channelNum)) {
				channelNum = "5";
			}
			// 网厅
			if ("01".equals(channelNum)) {
				channelNum = "4";
			}
			// 短厅
			if ("03".equals(channelNum)) {
				channelNum = "6";
			}
			param.put("userChannalData", channelNum);
		}
		 
		 param.put("userName1", userInfoBean.getLoginName());
		try {
			int flag = marketFirstService.updateByPrimaryKey(param);
			if (flag == 1) {
				if(!"1".equals(param.get("updateListView"))){//---修改展示状态的时候不修改
				marketFirstService.updateSecondMarketChannel(param);
				}
				if (StringUtils.isNotBlank(yxImgDir)) {
					
					fileNameMap.put("yxImgDir", yxImgDir);
				}
			
				if(StringUtils.isNotBlank(yxImgDir_old)){
					yxImgDir_old=yxImgDir_old.substring(yxImgDir_old.lastIndexOf("/")+1);
					deleteFileName.add(yxImgDir_old);
					fileuploadService.filesDelete(request, targetURL,
							deleteFileName);
				}
				
				String names = "";
				long nanoTime;
				String tempName = "";
				for (Map.Entry<String, String> entry : fileNameMap.entrySet()) {
					tempName = entry.getValue();
					nanoTime = System.nanoTime();
					names = Long.toString(nanoTime)+ tempName.substring(tempName.indexOf("."));
					fileNameMap.put(entry.getKey(), names);
					
				}

				fileNameMap.put("marketFirstPkid", param.get("marketFirstPkid"));
				result.setRetObj(fileNameMap);
    	
          result.setRetCode(IResultCode.SYS_SUCCESS);
          result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
          }else{
        	  result.setRetCode(IResultCode.SYS_FAILED);
              result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED); 
          }
      } catch (Exception e) {
    	  e.printStackTrace();
          result.setRetCode(IResultCode.SYS_FAILED);
          result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
      }
	
	return result;
	}
/**
 * 查询一组统一营销案
 * @param context
 * @return
 */
public HandlerResult queryUnityMarketFirstList(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
    HttpServletRequest request = context.getRequest();
    Map<String, String> param = BeanUtil.getMapFromRequest(request);
    try {
		Page page=marketFirstService.queryUnityMarketFirstList(param);
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
 * 查询地市和省级审核列表
 * 创建日期：2013-12-16下午1:19:53
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
		if ("0".equals(city)) {// 0 为江苏省
			param.put("proManager", "1");
			city=null;
		} else {
			param.put("cityManager", "1");
		
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
		}}
		param.put("city", city);
		try {
			Page page = marketFirstService.queryMarketForAudit(param);
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
	 * 一级营销案审核
	 * 创建日期：2013-12-16下午2:17:24
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateAuditState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      MarketFirstBean marketFirstBean = (MarketFirstBean) BeanUtil
					.getBeanFromRequest(request, MarketFirstBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String verifyState = "";
		String city = userInfoBean.getUserAreaCode();
		String auditLevel = "";
		String channelNum = userInfoBean.getChannelNum();
		
		if ("0".equals(city)) {// 0 为江苏省
			auditLevel = "1";
			verifyState = request.getParameter("proVerifyState");
			if(channelNum != null && !"".equals(channelNum)){
				//网厅
				if("01".equals(channelNum) || "4".equals(channelNum)){
					channelNum = "4";
					marketFirstBean.setWtProVerifyState(verifyState);
					marketFirstBean.setProVerifyState(null);
				}
				//短厅
				if("03".equals(channelNum) || "6".equals(channelNum)){
					channelNum = "6";
					marketFirstBean.setDtProVerifyState(verifyState);
					//如果是短厅省级审核通过，则最终审核状态为通过
					if("1".equals(verifyState)){
						marketFirstBean.setDtVerifyState("1");
					}else{
						marketFirstBean.setDtVerifyState("2");
					}
					marketFirstBean.setProVerifyState(null);
				}
				//掌厅
				if("02".equals(channelNum) || "5".equals(channelNum)){
					channelNum = "5";
					//如果是省级管理员，并且是审核通过的将最终状态改为“待测试”
					if("1".equals(verifyState)){
						marketFirstBean.setVerifyState("3");
					}else{
						marketFirstBean.setVerifyState("2");
					}
				}
			}
			
		} else {
			verifyState = request.getParameter("localVerifyState");
			auditLevel = "2";
			if(channelNum != null && !"".equals(channelNum)){
				//网厅
				if("01".equals(channelNum) || "4".equals(channelNum)){
					channelNum = "4";
					if("1".equals(verifyState)){
						marketFirstBean.setWtVerifyState("3");
					}
					marketFirstBean.setWtProVerifyState(verifyState);
					marketFirstBean.setProVerifyState(null);
				}
				//短厅
				if("03".equals(channelNum) || "6".equals(channelNum)){
					channelNum = "6";
					marketFirstBean.setDtProVerifyState(verifyState);
					//如果是短厅省级审核通过，则最终审核状态为通过
					if("1".equals(verifyState)){
						marketFirstBean.setDtVerifyState("1");
					}
					marketFirstBean.setProVerifyState(null);
				}
				//掌厅
				if("02".equals(channelNum) || "5".equals(channelNum)){
					channelNum = "5";
					//如果是省级管理员，并且是审核通过的将最终状态改为“待测试”
					if("1".equals(verifyState)){
						marketFirstBean.setVerifyState("3");
					}
				}
			}
		}
		try {
			//审核日志
			MarketAuditBean marketAuditBean = new MarketAuditBean();
			marketAuditBean.setAuditPerson(userInfoBean.getLoginName());
			marketAuditBean.setAuditContent(auditContent);
			marketAuditBean.setAutitState(verifyState);
			marketAuditBean.setAuditLevel(auditLevel);
			marketAuditBean.setChannelNum(channelNum);
			//更新审核状态
			marketFirstBean.setMarketAuditBean(marketAuditBean);
			marketFirstService.updateAuditState(marketFirstBean);
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
	 * 查询线上测试列表
	 * 创建日期：2013-12-17上午10:57:16
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
		String city = userInfoBean.getUserAreaCode();
		if(!"0".equals(city)){
		//网厅
		if("01".equals(channelNum) || "4".equals(channelNum)){
			channelNum = "4";
		}
		//掌厅
		if("02".equals(channelNum) || "5".equals(channelNum)){
			channelNum = "5";
		}
		//短厅
		if("03".equals(channelNum) || "6".equals(channelNum)){
			channelNum = "6";
		}
		param.put("channalData", channelNum);
		}
		//用户所属渠道
		
		
		try {
			Page page = marketFirstService.queryMarketForTest(param);
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
	 * 一级营销案线上测试
	 * 创建日期：2013-12-17下午2:21:56
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult updateTestState(HandlerRequestContext context) {
		  HandlerResult result = HandlerResult.newInstance();
	      HttpServletRequest request = context.getRequest();
	    
	      MarketFirstBean marketFirstBean = (MarketFirstBean) BeanUtil
					.getBeanFromRequest(request, MarketFirstBean.class);
	      
	      LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	      UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	      
		String auditContent = request.getParameter("auditContent");
		String verifyState = marketFirstBean.getTestOnlineState();
		String channelNum = userInfoBean.getChannelNum();
		if(channelNum != null && !"".equals(channelNum)){
			//网厅
			if("01".equals(channelNum) || "4".equals(channelNum)){
				marketFirstBean.setWtTestOnlineState(marketFirstBean.getTestOnlineState());
				marketFirstBean.setTestOnlineState(null);
			}
			//短厅
			if("03".equals(channelNum) || "6".equals(channelNum)){
				marketFirstBean.setDtTestOnlineState(marketFirstBean.getTestOnlineState());
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
			marketFirstService.updateTestState(marketFirstBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		
		return result;
		}
	
	public HandlerResult filesUpload(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	 String yxImgDirName = RequestUtil.getString(request, "yxImgDirName");
	 String marketFirstPkid = RequestUtil.getString(request, "marketFirstPkid");
	try {
		boolean flag = fileuploadService.fileuploadReName(request,targetURL,yxImgDirName);
		if(flag){
			Map<String, String> param = new HashMap<String, String>();
			 param.put("yxImgDir", targetURL+"/"+yxImgDirName);
			 param.put("marketFirstPkid", marketFirstPkid);
			 marketFirstService.updateByPrimaryKey(param);
		}
		result.setRetObj(flag);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
		e.printStackTrace();
		result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;}
/**
 * 上传多个图片
 * @param context
 * @return
 */
public HandlerResult filesuploadReName(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	 Map<String, String> param = new HashMap<String, String>();
	String yxImgDir=RequestUtil.getString(request, "yxImgDir_old");
	String marketFirstPkid = RequestUtil.getString(request, "marketFirstPkid");
	param.put("marketFirstPkid", marketFirstPkid);
	param.put("yxImgDir", targetURL+"/"+yxImgDir);
	param.put("isSort", "0");  //---不修改排序
	try {
		fileuploadService.filesuploadReName(request, targetURL, new String[]{yxImgDir});
		 marketFirstService.updateByPrimaryKey(param);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
		e.printStackTrace();
		result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
	}

public HandlerResult syncDT(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	String back="";
	try {
		back = Sendhttp.moveSendPost(syncDTURL, null);
		//System.out.println(		back+"==============		back==================");
		if(back!=null&&"0000".equals(back)){
			
			marketLogService.updateSyncCache();
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setResMsg("同步成功!");
		}else{
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setResMsg("同步失败!");
		}
	} catch (Exception e) {
		e.printStackTrace();
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setResMsg("同步失败!");
	}
	return result;
	
}

}
