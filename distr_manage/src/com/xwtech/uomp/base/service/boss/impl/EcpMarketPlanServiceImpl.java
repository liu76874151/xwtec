package com.xwtech.uomp.base.service.boss.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingbusiPackcfgdt;
import com.xwtech.uomp.base.pojo.boss.ecp.CPresentGoodspackcfgdt;
import com.xwtech.uomp.base.service.boss.IEcpMarketPlanService;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryYxfaList1Service;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryYxfaLpbService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryYxfaMxService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryYxfaYwbService;
import com.xwtech.xwecp.service.logic.pojo.QRY070001Result;
import com.xwtech.xwecp.service.logic.pojo.QRY070002Result;
import com.xwtech.xwecp.service.logic.pojo.QRY070003Result;
import com.xwtech.xwecp.service.logic.pojo.QRY070004Result;
import com.xwtech.xwecp.service.logic.pojo.YxfaInfo;
import com.xwtech.xwecp.service.logic.pojo.YxfaLpbInfo;
import com.xwtech.xwecp.service.logic.pojo.YxfaYwbInfo;

/**
 * 
 * This class is used for ...
 * 
 * @author unique
 * @version 1.0, 2014-1-22 下午02:06:07
 */
@Service("ecpMarketPlanService")
public class EcpMarketPlanServiceImpl implements IEcpMarketPlanService {

    private static final Logger logger = Logger.getLogger("ecplog");
    @Autowired
    IQueryYxfaList1Service queryYxfaList1ServiceClient;
    @Autowired
    IQueryYxfaMxService queryYxfaMxServiceClient;
    @Autowired
    IQueryYxfaYwbService queryYxfaYwbServiceClient;
    @Autowired
    IQueryYxfaLpbService queryYxfaLpbServiceClient;

    public List<CMarketingPlandt> queryMarketPlan(String cityId) {

	LIInvocationContext lic = LIInvocationContext.getContext();

	lic.setBizCode("biz_code_19234");
	lic.setOpType("开通/关闭/查询/变更");
	lic.setUserBrand("动感地带");
	lic.setUserCity("14");
	InvocationContext ic = new InvocationContext();
	ic.addContextParameter("route_type", "1");
	ic.addContextParameter("route_value", "14");
	ic.addContextParameter("loginiplock_login_ip", "127.0.0.1");
	ic.addContextParameter("ddr_city", "14");
	ic.addContextParameter("brand", "");
	ic.addContextParameter("country", "");
	ic.addContextParameter("user_change_remark", "");
	lic.setContextParameter(ic);

	List<CMarketingPlandt> resultList = null;
	try {
	    QRY070001Result re = queryYxfaList1ServiceClient.queryYxfaList1(cityId);
	    if ("0".equals(re.getResultCode())) {

		List<YxfaInfo> list = re.getYxfaInfo();
		resultList = new ArrayList<CMarketingPlandt>();
		for (YxfaInfo p : list) {
		    CMarketingPlandt info = new CMarketingPlandt();
		    info.bossmms_special_account_amount = p.getBossmms_special_account_amount();
		    info.creditrelease_credit_amount = p.getCreditrelease_credit_amount();
		    info.marketingplan_cash_amount = p.getMarketingplan_cash_amount();
		    info.marketingplan_end_date = p.getMarketingplan_end_date();
		    info.marketingplan_name = p.getMarketingplan_name();
		    info.marketingplan_permit_brand_ids = p.getMarketingplan_permit_brand_ids();
		    info.marketingplan_plan_id = p.getMarketingplan_plan_id();
		    info.marketingplan_start_date = p.getMarketingplan_start_date();
		    info.marketingplan_type_id = p.getMarketingplan_type_id();
		    info.marketingplantype_name = p.getMarketingplantype_name();

		    resultList.add(info);
		}

	    } else {

	    }

	} catch (LIException e) {
	    logger.info("EcpMarketPlanServiceImpl>>queryMarketPlan:"+cityId + e);
	}
	return resultList;
    }

    public CMarketingPlandt queryMarketPlanInfo(Map<String, String> param) {
	String cityId = param.get("cityId");
	String planId = param.get("marketSecondCode");
	CMarketingPlandt dt = null;

	LIInvocationContext lic = LIInvocationContext.getContext();

	lic.setBizCode("biz_code_19234");
	lic.setOpType("开通/关闭/查询/变更");
	lic.setUserBrand("动感地带");
	lic.setUserCity("14");
	InvocationContext ic = new InvocationContext();
	ic.addContextParameter("route_type", "1");
	ic.addContextParameter("route_value", "14");
	ic.addContextParameter("loginiplock_login_ip", "127.0.0.1");
	ic.addContextParameter("ddr_city", "14");
	ic.addContextParameter("brand", "");
	ic.addContextParameter("country", "");
	ic.addContextParameter("user_change_remark", "");
	lic.setContextParameter(ic);

	QRY070002Result re = null;
	try {
	    re = queryYxfaMxServiceClient.queryYxfaMx(cityId, planId);
	    if ("0".equals(re.getResultCode())) {
		dt = new CMarketingPlandt();
		dt.bossmms_special_account_amount = re.getBossmms_special_account_amount();
		dt.creditrelease_credit_amount = re.getCreditrelease_credit_amount();

	    } else {

	    }
	} catch (LIException e) {
	    logger.info("EcpMarketPlanServiceImpl>>queryMarketPlanInfo:"+cityId+":"+planId + e);
	}
	return dt;
    }

    public List<CMarketingbusiPackcfgdt> queryMarketPlanBiz(Map<String, String> param) {
	String cityId = param.get("cityId");
	String planId = param.get("marketSecondCode");
	List<CMarketingbusiPackcfgdt> resultList = null;
	try {
	    LIInvocationContext lic = LIInvocationContext.getContext();

	    lic.setBizCode("biz_code_19234");
	    lic.setOpType("开通/关闭/查询/变更");
	    lic.setUserBrand("动感地带");
	    lic.setUserCity("14");
	    InvocationContext ic = new InvocationContext();
	    ic.addContextParameter("route_type", "1");
	    ic.addContextParameter("route_value", "14");
	    ic.addContextParameter("loginiplock_login_ip", "127.0.0.1");
	    ic.addContextParameter("ddr_city", "14");
	    ic.addContextParameter("brand", "");
	    ic.addContextParameter("country", "");
	    ic.addContextParameter("user_change_remark", "");
	    lic.setContextParameter(ic);

	    QRY070003Result re = queryYxfaYwbServiceClient.queryYxfaYwb(cityId, planId);

	    if ("0".equals(re.getResultCode())) {

		List<YxfaYwbInfo> list = re.getYxfaYwbInfo();

		resultList = new ArrayList<CMarketingbusiPackcfgdt>();
		for (YxfaYwbInfo p : list) {
		    CMarketingbusiPackcfgdt info = new CMarketingbusiPackcfgdt();
		    info.marketingbusipackcfg_plan_id = planId;
		    info.marketingbusipackcfg_busi_pack_id = p
			    .getMarketingbusipackcfg_busi_pack_id();
		    info.marketingbusipackcfg_busi_pack_name = p
			    .getMarketingbusipackcfg_busi_pack_name();

		    resultList.add(info);
		}

	    } else {

	    }
	} catch (Exception e) {
	    logger.info("EcpMarketPlanServiceImpl>>queryMarketPlanBiz:"+cityId+":"+planId + e);
	}
	return resultList;
    }

    /**
     * 礼品包数据查询
     * @param param
     * @return  
     * @author unique
     */
    public List<CPresentGoodspackcfgdt> queryMarketGiftBag(Map<String, String> param) {
	String cityId = param.get("cityId");
	String planId = param.get("marketSecondCode");
	List<CPresentGoodspackcfgdt> resultList = null;
	try {
	    LIInvocationContext lic = LIInvocationContext.getContext();

	    lic.setBizCode("biz_code_19234");
	    lic.setOpType("开通/关闭/查询/变更");
	    lic.setUserBrand("动感地带");
	    lic.setUserCity("14");
	    InvocationContext ic = new InvocationContext();
	    ic.addContextParameter("route_type", "1");
	    ic.addContextParameter("route_value", "14");
	    ic.addContextParameter("loginiplock_login_ip", "127.0.0.1");
	    ic.addContextParameter("ddr_city", "14");
	    ic.addContextParameter("brand", "");
	    ic.addContextParameter("country", "");
	    ic.addContextParameter("user_change_remark", "");
	    lic.setContextParameter(ic);

	    QRY070004Result re = queryYxfaLpbServiceClient.queryYxfaLpb(cityId, planId);

	    if ("0".equals(re.getResultCode())) {
		List<YxfaLpbInfo> list = re.getYxfaLpbInfo();
		resultList = new ArrayList<CPresentGoodspackcfgdt>();
		for (YxfaLpbInfo p : list) {
		    CPresentGoodspackcfgdt info = new CPresentGoodspackcfgdt();
		    info.presentgoodspackcfg_pack_name = p.getPresentgoodspackcfg_pack_name();
		    info.presentgoodspackcfg_pack_id = p.getPresentgoodspackcfg_pack_id();
		    info.presentgoodspackcfg_plan_id = planId;
		    resultList.add(info);
		}

	    } else {
	    }
	} catch (Exception e) {
	    logger.info("EcpMarketPlanServiceImpl>>queryMarketGiftBag:"+cityId+":"+planId+e);
	}
	return resultList;
    }

}
