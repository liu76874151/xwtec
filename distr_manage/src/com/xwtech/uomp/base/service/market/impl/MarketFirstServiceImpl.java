package com.xwtech.uomp.base.service.market.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketFirstBeanMapper;
import com.xwtech.uomp.base.dao.market.MarketFirstDaoImpl;
import com.xwtech.uomp.base.dao.sequence.ISequenceDAO;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.MarketLogBean;
import com.xwtech.uomp.base.pojo.market.MarketUnityRecodeBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.market.IMarketAuditService;
import com.xwtech.uomp.base.service.market.IMarketFirstService;
import com.xwtech.uomp.base.service.market.IMarketLogService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.SSOUtil;

@Service("marketFirstService")
public class MarketFirstServiceImpl implements IMarketFirstService {
	@Autowired
	MarketFirstBeanMapper marketFirstBeanMapper;
	@Autowired
	MarketFirstDaoImpl marketFirstDao;
	@Autowired
    ISequenceService  sequenceService;
	@Autowired
	IMarketAuditService marketAuditService;
	@Autowired
	IMarketLogService marketLogService;
	
	public Page queryMarketFirstList(Map<String, Object> param) {
		List<MarketFirstBean> list=marketFirstBeanMapper.queryMarketFirstList(param);
		int count=marketFirstBeanMapper.queryMarketFirstListCount(param);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public int queryMarketFirstListCount(Map<String, Object> param) {
		return marketFirstBeanMapper.queryMarketFirstListCount(param);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveMarketFirst(MarketFirstBean marketFirstBean) {
		marketFirstBean.setState("1");
		marketFirstBean.setVerifyState("0");
		marketFirstBean.setIsListView("0");
		String marketOrder = "";
		String ztMarketOrder = "";
		String citys[] = {};
		String channalData = marketFirstBean.getChannalData();
		String unityFlag = marketFirstBean.getUnityFlag();
		int cityCount = 1;// ---添加地市的数量
		if ("1".equals(unityFlag)) {
			String cityStr = marketFirstBean.getCity();
			citys = cityStr.split("\\,");// --渠道 4,5,6: 网 掌 短
			cityCount = citys.length;
		}
		String marketOrderStr = marketFirstBean.getMarketOrder();
		String ztMarketOrderStr = marketFirstBean.getZtMarketOrder();
		String marketFirstNameStr = marketFirstBean.getMarketFirstName();
		String marketFirstCodeStr = marketFirstBean.getMarketFirstCode();
		String tChannal = marketFirstBean.getTChannal();
		String marketOrders[] = marketOrderStr.split("\\,");
		String ztMarketOrders[] = ztMarketOrderStr.split("\\,");
		String marketFirstNames[] = marketFirstNameStr.split("\\,");
		String marketFirstCodes[] = marketFirstCodeStr.split("\\,");
		MarketLogBean marketLogBean=new MarketLogBean();
		marketLogBean.setCaseLevel("1");
		marketLogBean.setType("1");
		marketLogBean.setFlag("0");
		
		for (int j = 0; j < cityCount; j++) {
			String marketFirstPkid=sequenceService.getSequence("MARKET_FIRST_PKID_SEQ");
			marketFirstBean.setMarketFirstPkid(marketFirstPkid);
			if ("1".equals(unityFlag)) {// --统一营销案数据拼装
				marketFirstBean.setMarketFirstCode(marketFirstCodes[j]);
				marketFirstBean.setCity(citys[j]);
				marketFirstBean.setMarketFirstName(marketFirstNames[j]);
			}
				marketOrder = marketOrders[j];
				ztMarketOrder=ztMarketOrders[j];
			
			String marketFirstPkids[] = marketOrder.split("\\.");
			String ztMarketFirstPkids[] = ztMarketOrder.split("\\.");

			if (channalData.contains("4") && (StringUtils.isNotBlank(tChannal))) {// --添加网厅个性数据//(!"".equals(tChannal)
				// --网厅渠道(二进制存入数据库)：0-无权限，1-有权限，第1位代表网厅,第2位代表网村组服站,第3位代表校园e站
				String channalGroup[] = tChannal.split("\\.");
				int channalValue = 0;
				if (null != channalGroup && "" != channalGroup[0]) {
					int[] cGroup = new int[channalGroup.length];
					for (int i = 0; i < channalGroup.length; i++) {
						cGroup[i] = Integer.valueOf(channalGroup[i]);
					}
					if (null == channalGroup || channalGroup.length <= 0) {
						channalValue = 0;
					} else {
						channalValue = 1 << (cGroup[0] - 1);
						for (int i = 1; i < cGroup.length; i++) {
							channalValue = channalValue
									| (1 << (cGroup[i] - 1));
						}
					}
				}
				marketFirstBean.setTChannal(channalValue + "");
			} else {
				marketFirstBean.setTChannal(null);
			}
			marketFirstBeanMapper.saveMarketFirst(marketFirstBean);
			MarketUnityRecodeBean marketUnityRecodeBean=new MarketUnityRecodeBean();
			marketUnityRecodeBean.setMarketFirstPkid(marketFirstBean.getMarketFirstPkid());
			marketUnityRecodeBean.setRecordId(sequenceService.getSequence("UNITY_RECORD_PKID_SEQ"));
			if ("1".equals(unityFlag)) {
			saveUnityRecode(marketUnityRecodeBean);
			}
			if (channalData.contains("4")) {
				sort(marketFirstPkids, marketFirstBean.getMarketFirstPkid());
			}
			if (channalData.contains("5")) {
				sortZT(ztMarketFirstPkids, marketFirstBean.getMarketFirstPkid());
			}
			if("1".equals(marketFirstBean.getIsDT())){
				marketLogBean.setFirstCode(marketFirstBean.getMarketFirstCode());
				marketLogBean.setCity(marketFirstBean.getCity());
				marketLogBean.setOptUser(marketFirstBean.getCfgUserId());
				marketLogBean.setMarketFirstPkid(marketFirstPkid);
				marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
				marketLogService.saveMarketLog(marketLogBean);
			}
		}
		return 0;
	}

	public Page queryViewNameOrderByMarketOrder(String city) {
		List<MarketFirstBean> list=marketFirstBeanMapper.queryViewNameOrderByMarketOrder(city);
		int count=0;
		count=list.size();
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public String queryMarketFirstSequence() {
		return marketFirstBeanMapper.queryMarketFirstSequence();
	}

	public boolean sort(String[] marketFirstPkids, String marketFirstPkid) {
	
		return marketFirstDao.sort(marketFirstPkids, marketFirstPkid);
	}
	public boolean sortZT(String[] marketFirstPkids, String marketFirstPkid) {
		
		return marketFirstDao.sortZT(marketFirstPkids, marketFirstPkid);
	}
	public int queryMarketSencodCount(String marketFirstPkid) {
		return marketFirstBeanMapper.queryMarketSencodCount(marketFirstPkid);
	}

	public void deleteMarketFirst(Map<String, Object> marketFirstPkids) {
		marketFirstBeanMapper.deleteMarketFirst(marketFirstPkids);
		
	}

	public MarketFirstBean queryByPrimaryKey(String marketFirstPkid) {
		return marketFirstBeanMapper.queryByPrimaryKey(marketFirstPkid);
	}
	/**
	 *  修改一级营销案 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateByPrimaryKey(Map<String, String> map) {
		int cityCouts=1;
		String marketFirstCodeStrs[]={};
		String marketFirstNameStrs[]={};
		String marketOrderStrs[]={};
		String ztMarketOrderStrs[]={};
		String marketFirstPkidStrs[]={};
		String marketOrderStr=StringUtils.trimToEmpty(map.get("marketOrder"));
		String ztMarketOrderStr=StringUtils.trimToEmpty(map.get("ztMarketOrder"));
		String marketFirstCodeStr=StringUtils.trimToEmpty(map.get("marketFirstCode"));
		String marketFirstNameStr=StringUtils.trimToEmpty(map.get("marketFirstName"));
		String marketFirstPkidStr=StringUtils.trimToEmpty(map.get("marketFirstPkid"));
		String marketFirstCode="";
		String marketFirstName="";
		String marketFirstPkid="";
		String marketOrder="";
		String ztMarketOrder="";
		marketOrderStrs=marketOrderStr.split("\\,",-1);
		ztMarketOrderStrs=ztMarketOrderStr.split("\\,",-1);
		marketFirstCodeStrs=marketFirstCodeStr.split("\\,");
		marketFirstNameStrs=marketFirstNameStr.split("\\,");
		marketFirstPkidStrs=marketFirstPkidStr.split("\\,");
		cityCouts=marketFirstCodeStrs.length;

		  //--渠道：0-无权限，1-有权限，第1位代表网厅,第2位代表网村组服站,第3位代表校园e站
	    String tChannal = map.get("tChannal");
	    String city = map.get("cityLog");
	    String channalGroup[] =null;
	    String citys[]=null;
	    if(null!=tChannal){
	    	channalGroup=tChannal.split("\\.");}
	    
	  
		
		
	    if(null!=city){
	    	citys=city.split("\\,");}
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
	   		map.put("tChannal", channalValue+"");
	   	}
	   	MarketLogBean marketLogBean=new MarketLogBean();
		marketLogBean.setCaseLevel("1");
		marketLogBean.setType("2");
		marketLogBean.setFlag("0");
	 	for (int i = 0; i < cityCouts; i++) {
			marketFirstCode=marketFirstCodeStrs[i];
			marketFirstName=marketFirstNameStrs[i];
			marketFirstPkid=marketFirstPkidStrs[i];
			marketOrder =marketOrderStrs[i];
			ztMarketOrder =ztMarketOrderStrs[i];
			 
			 if(StringUtils.isNotBlank(marketFirstCode)){
			map.put("marketFirstCode",marketFirstCode);}
			 if(StringUtils.isNotBlank(marketFirstName)){
			map.put("marketFirstName",marketFirstName);}
			map.put("marketFirstPkid",marketFirstPkid);
			String marketFirstPkids[]=marketOrder.split("\\.");
			String ztMarketFirstPkids[]=ztMarketOrder.split("\\.");
			marketFirstBeanMapper.updateByPrimaryKeySelective(map);
			if(!"-1".equals(marketFirstPkids[0])&&!"0".equals(map.get("isSort"))){//---上传图片的时候重命名图片  不排序
			sort(marketFirstPkids, "");  
			sortZT(ztMarketFirstPkids, "");  
			}
			
			if("1".equals(map.get("isDT"))){//TODO
				marketLogBean.setFirstCode(map.get("marketFirstCode"));
				marketLogBean.setCity(citys[i]);
				marketLogBean.setMarketFirstPkid(marketFirstPkid);
				marketLogBean.setOptUser(map.get("userName1"));
				marketLogBean.setPkid(sequenceService.getSequence("JSMSS_MARKET_OPT_LOG_SEQ"));
				marketLogService.saveMarketLog(marketLogBean);
			}
			
	 	}
		 return 1;
	}
	/**
	 * 判断一级营销案名称是否存在 
	 */
	public     int isExistViewName( Map<String, Object> param) {
		//String channalDataStr=marketFirstBean.getChannalData();
		String channalDataStr=(String) param.get("channalData");
		String channalData[]=channalDataStr.split("\\,");
		int count=0;
			for (int i = 0; i < channalData.length; i++) {
				param.put("channalData", channalData[i]);
				count=marketFirstBeanMapper.isExistViewName(param);
				if(count!=0){
					break;
				}
			}
			return count;
	}

	public void saveUnityRecode(MarketUnityRecodeBean marketUnityRecodeBean) {
		marketFirstBeanMapper.saveUnityRecode(marketUnityRecodeBean);
		
	}

	public Page queryUnityMarketFirstList(Map<String, String> map) {
		List<MarketFirstBean> list= marketFirstBeanMapper.queryUnityMarketFirstList(map);
		Page page=new Page();
		int totalRecord=0;
		if(null!=list){
			totalRecord=list.size();
			}
		page.setTotalRecord(totalRecord);
		page.setRecords(list);
		return page;
	}
	
	/**
	 * 查找审核列表
	 */
	public Page queryMarketForAudit(Map<String, String> param){
		List<MarketFirstBean> list = marketFirstBeanMapper.queryMarketForAudit(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channalData = param.get("channalData");
		List<MarketFirstBean> newlist = new ArrayList<MarketFirstBean>();
		for(MarketFirstBean fistBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			if ("1".equals(cityLevel)) {
				// 掌厅
				if ("5".equals(channalData) || "02".equals(channalData)) {
					if ("0".equals(fistBean.getLocalVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getLocalVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getLocalVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getProVerifyState())){
						fistBean.setProOper("待审核");
					}else if("1".equals(fistBean.getProVerifyState())){
						fistBean.setProOper("审核通过");
					}else if("2".equals(fistBean.getProVerifyState())){
						fistBean.setProOper("审核不通过");
					}
				}
				//短厅
				if ("6".equals(channalData) || "03".equals(channalData)) {
					if ("0".equals(fistBean.getDtLocalVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getDtLocalVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getDtLocalVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getDtProVerifyState())){
						fistBean.setProOper("待审核");
					}else if("1".equals(fistBean.getDtProVerifyState())){
						fistBean.setProOper("审核通过");
					}else if("2".equals(fistBean.getDtProVerifyState())){
						fistBean.setProOper("审核不通过");
					}
				}
				//网厅
				if ("4".equals(channalData) || "01".equals(channalData)) {
					if ("0".equals(fistBean.getWtLocalVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getWtLocalVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getWtLocalVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstCity("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setLocalOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getWtProVerifyState())){
						fistBean.setProOper("待审核");
					}else if("1".equals(fistBean.getWtProVerifyState())){
						fistBean.setProOper("审核通过");
					}else if("2".equals(fistBean.getWtProVerifyState())){
						fistBean.setProOper("审核不通过");
					}
				}
			}
			if("1".equals(proLevel)){
				// 掌厅
				if ("5".equals(channalData) || "02".equals(channalData)) {
					if ("0".equals(fistBean.getProVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getProVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getProVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getLocalVerifyState())){
						fistBean.setLocalOper("待审核");
					}else if("1".equals(fistBean.getLocalVerifyState())){
						fistBean.setLocalOper("审核通过");
					}else if("2".equals(fistBean.getLocalVerifyState())){
						fistBean.setLocalOper("审核不通过");
					}
				}
				//短厅
				if ("6".equals(channalData) || "03".equals(channalData)) {
					//System.out.println(channalData+">>>>>>");
					//System.out.println(fistBean.getDtProVerifyState());
					if ("0".equals(fistBean.getDtProVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getDtProVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getDtProVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getDtLocalVerifyState())){
						fistBean.setLocalOper("待审核");
					}else if("1".equals(fistBean.getDtLocalVerifyState())){
						fistBean.setLocalOper("审核通过");
					}else if("2".equals(fistBean.getDtLocalVerifyState())){
						fistBean.setLocalOper("审核不通过");
					}
				}
				//网厅
				if ("4".equals(channalData) || "01".equals(channalData)) {
					if ("0".equals(fistBean.getWtProVerifyState())) {
						verifyUrl
								.append("待审核&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						verifyUrl
								.append("&nbsp;&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("1".equals(fistBean.getWtProVerifyState())) {
						verifyUrl
								.append("审核通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",false);' ><img alt='审核不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if ("2".equals(fistBean.getWtProVerifyState())) {
						verifyUrl
								.append("审核不通过&nbsp;<a href='javascript:component.verifyMarketFirstPro("
										+ fistBean.getMarketFirstPkid()
										+ ",true);' ><img alt='审核通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
						fistBean.setProOper(verifyUrl.toString());
					}
					if("0".equals(fistBean.getWtLocalVerifyState())){
						fistBean.setLocalOper("待审核");
					}else if("1".equals(fistBean.getWtLocalVerifyState())){
						fistBean.setLocalOper("审核通过");
					}else if("2".equals(fistBean.getWtLocalVerifyState())){
						fistBean.setLocalOper("审核不通过");
					}
				}
			}
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级营销案</a> ";
			fistBean.setLinkOper(linkInfo);
			if("0".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getTestOnlineState())){
				fistBean.setTestOper("测试不通过");
			}
			newlist.add(fistBean);
		}
		Page page = new Page();
		int totalRecord = marketFirstBeanMapper.queryCountForAudit(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 线上测试列表
	 */
	public Page queryMarketForTest(Map<String, String> param){
		List<MarketFirstBean> list = marketFirstBeanMapper.queryMarketForTest(param);
		List<MarketFirstBean> newlist = new ArrayList<MarketFirstBean>();
		String channelNum = param.get("channalData");
		for(MarketFirstBean fistBean : list){
			String dtOnlineTestState = fistBean.getDtTestOnlineState();
			String wtOnlineTestState = fistBean.getWtTestOnlineState();
			String onlineTestState = fistBean.getTestOnlineState();
			StringBuffer testUrl = new StringBuffer();
			if("0".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("1".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("2".equals(onlineTestState) && ("5".equals(channelNum) || "02".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}
			
			//短厅
			if("0".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("1".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("2".equals(dtOnlineTestState) && ("6".equals(channelNum) || "03".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}
			
			//网厅
			if("0".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("待测试&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				testUrl
						.append("&nbsp;&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("1".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("测试通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",false);' ><img alt='测试不通过' src='../../../../resource/img/toggle_disabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}else if("2".equals(wtOnlineTestState) && ("4".equals(channelNum) || "01".equals(channelNum))){
				testUrl
						.append("测试不通过&nbsp;<a href='javascript:component.testMarketFirst("
								+ fistBean.getMarketFirstPkid()
								+ ",true);' ><img alt='测试通过' src='../../../../resource/img/toggle_enabled.gif' height='10'></a>");
				fistBean.setTestOper(testUrl.toString());
			}
			
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级营销案</a>";
			fistBean.setLinkOper(linkInfo);
			
			//掌厅
			if("5".equals(channelNum) || "02".equals(channelNum)){
				//地市审核状态
				if("0".equals(fistBean.getLocalVerifyState())){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getLocalVerifyState())){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getLocalVerifyState())){
					fistBean.setLocalOper("审核不通过");
				}
				
				//省级审核状态
				if("0".equals(fistBean.getProVerifyState())){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getProVerifyState())){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getProVerifyState())){
					fistBean.setProOper("审核不通过");
				}
			}
			
			//网厅
			if("4".equals(channelNum) || "01".equals(channelNum)){
				//地市审核状态
				if("0".equals(fistBean.getWtLocalVerifyState())){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getWtLocalVerifyState())){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getWtLocalVerifyState())){
					fistBean.setLocalOper("审核不通过");
				}
				
				//省级审核状态
				if("0".equals(fistBean.getWtProVerifyState())){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getWtProVerifyState())){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getWtProVerifyState())){
					fistBean.setProOper("审核不通过");
				}
			}
			
			//短厅
			if("6".equals(channelNum) || "03".equals(channelNum)){
				//地市审核状态
				if("0".equals(fistBean.getDtLocalVerifyState())){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getDtLocalVerifyState())){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getDtLocalVerifyState())){
					fistBean.setLocalOper("审核不通过");
				}
				
				//省级审核状态
				if("0".equals(fistBean.getDtProVerifyState())){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getDtProVerifyState())){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getDtProVerifyState())){
					fistBean.setProOper("审核不通过");
				}
			}
			
			
			newlist.add(fistBean);
		}
		Page page = new Page();
		int totalRecord = marketFirstBeanMapper.queryCountForTest(param);
		page.setRecords(newlist);
		page.setTotalRecord(totalRecord);
		return page;
	}
	
	/**
	 * 一级营销案审核
	 */
	@Transactional
	public void updateAuditState(MarketFirstBean marketFirstBean){
		marketFirstBeanMapper.updateAuditState(marketFirstBean);
		MarketAuditBean marketAuditBean = marketFirstBean.getMarketAuditBean();
		//营销案类型
		marketAuditBean.setMarketType("1");
		//一级营销案
		marketAuditBean.setMarketLevel("1");
		//操作类型为审核
		marketAuditBean.setOperType("1");
		marketAuditBean.setMarketPkid(Long.parseLong(marketFirstBean.getMarketFirstPkid()));
		marketAuditService.insert(marketAuditBean);
		
	}
	
	/**
	 * 一级营销案测试
	 */
	@Transactional
	public void updateTestState(MarketFirstBean marketFirstBean){
		//如果是测试通过，则将最终审核状态置为“审核通过”
		if(null != marketFirstBean.getWtTestOnlineState()){
			if("1".equals(marketFirstBean.getWtTestOnlineState())){
				marketFirstBean.setWtVerifyState("1");
			}else{
				marketFirstBean.setWtVerifyState("4");
			}
		}
		if(null != marketFirstBean.getDtTestOnlineState()){
			if("1".equals(marketFirstBean.getDtTestOnlineState())){
				marketFirstBean.setDtVerifyState("1");
			}else{
				marketFirstBean.setDtVerifyState("4");
			}
		}
		if(null != marketFirstBean.getTestOnlineState()){
			if("1".equals(marketFirstBean.getTestOnlineState())){
				marketFirstBean.setVerifyState("1");
			}else{
				marketFirstBean.setVerifyState("4");//测试不通过
			}
		}
		marketFirstBeanMapper.updateTestState(marketFirstBean);
		MarketAuditBean marketAuditBean = marketFirstBean.getMarketAuditBean();
		//营销案类型
		marketAuditBean.setMarketType("1");
		//一级营销案
		marketAuditBean.setMarketLevel("1");
		//操作类型为测试
		marketAuditBean.setOperType("2");
		marketAuditBean.setMarketPkid(Long.parseLong(marketFirstBean.getMarketFirstPkid()));
		marketAuditService.insert(marketAuditBean);
	}
	
	
	public void updateSecondMarketChannel(Map<String, String> map) {
		marketFirstBeanMapper.updateSecondMarketChannel(map);		
	}
	
	public int queryCountHaveSecond(Map<String, Object> param){
		return marketFirstBeanMapper.queryCountHaveSecond(param);
	}
	
	public List<MarketFirstBean> queryMarketAuditOnConsole(Map<String, String> param){
		List<MarketFirstBean> list = marketFirstBeanMapper.queryMarketAuditOnConsole(param);
		String cityLevel = param.get("cityManager");
		String proLevel = param.get("proManager");
		String channelNum = param.get("channalData");
		List<MarketFirstBean> newlist = new ArrayList<MarketFirstBean>();
		for(MarketFirstBean fistBean : list){
			StringBuffer verifyUrl= new StringBuffer();
			if ("1".equals(cityLevel)) {
				//掌厅
				if ("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setProOper("审核不通过");
				}
				
				//短厅
				if ("0".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setProOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstCity("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setLocalOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setProOper("待审核");
				}else if("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setProOper("审核通过");
				}else if("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setProOper("审核不通过");
				}
			}
			if("1".equals(proLevel)){
				//掌厅
				if ("0".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getProVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getLocalVerifyState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
					fistBean.setLocalOper("审核不通过");
				}
				
				//短厅
				if ("0".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getDtProVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getDtLocalVerifyState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
					fistBean.setLocalOper("审核不通过");
				}
				
				//网厅
				if ("0".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("1".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",false);' ><img title='审核不通过' src='../resource/img/toggle_disabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if ("2".equals(fistBean.getWtProVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))) {
					verifyUrl
							.append("<a href='javascript:component.verifyMarketFirstPro("
									+ fistBean.getMarketFirstPkid()
									+ ",true);' ><img title='审核通过' src='../resource/img/toggle_enabled.gif' height='10'></a>");
					fistBean.setProOper(verifyUrl.toString());
				}
				if("0".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setLocalOper("待审核");
				}else if("1".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setLocalOper("审核通过");
				}else if("2".equals(fistBean.getWtLocalVerifyState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
					fistBean.setLocalOper("审核不通过");
				}
			}
			String linkInfo = "<a href='javascript:component.viewSecond(\""+fistBean.getMarketFirstPkid()+"\",\""+fistBean.getMarketFirstCode()+"\",\""+
					fistBean.getMarketFirstName()+"\",\""+fistBean.getCity()+"\",\""+fistBean.getChannalData()+"\");'>关联二级营销案</a> ";
			fistBean.setLinkOper(linkInfo);
			//掌厅
			if("0".equals(fistBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getTestOnlineState()) && ("5".equals(channelNum) || "02".equals(channelNum))){
				fistBean.setTestOper("测试不通过");
			}
			//短厅
			if("0".equals(fistBean.getDtTestOnlineState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getDtTestOnlineState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getDtTestOnlineState()) && ("6".equals(channelNum) || "03".equals(channelNum))){
				fistBean.setTestOper("测试不通过");
			}
			//网厅
			if("0".equals(fistBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				fistBean.setTestOper("待测试");
			}else if("1".equals(fistBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				fistBean.setTestOper("测试通过");
			}else if("2".equals(fistBean.getWtTestOnlineState()) && ("4".equals(channelNum) || "01".equals(channelNum))){
				fistBean.setTestOper("测试不通过");
			}
			String viewName = fistBean.getViewName();
			if(viewName.length() > 10){
				viewName = viewName.substring(0,7)+"...";
			}
			viewName = "<a href='javascript:component.openMarketFirst(\""+fistBean.getMarketFirstPkid()+"\")'>"+viewName+"</a>";
			fistBean.setViewName(viewName);
			newlist.add(fistBean);
		}
		return newlist;
	}

	public Page queryViewNameOrderByZTMarketOrder(String city) {

		List<MarketFirstBean> list=marketFirstBeanMapper.queryViewNameOrderByZTMarketOrder(city);
		int count=0;
		count=list.size();
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	
	}

}
