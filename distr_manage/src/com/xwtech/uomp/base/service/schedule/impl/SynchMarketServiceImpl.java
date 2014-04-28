package com.xwtech.uomp.base.service.schedule.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingbusiPackcfgdt;
import com.xwtech.uomp.base.pojo.boss.ecp.CPresentGoodspackcfgdt;
import com.xwtech.uomp.base.pojo.org.ZoneInfoBean;
import com.xwtech.uomp.base.service.boss.IBossBindBizService;
import com.xwtech.uomp.base.service.boss.IBossGiftInfoService;
import com.xwtech.uomp.base.service.boss.IBossMarketFirstService;
import com.xwtech.uomp.base.service.boss.IBossMarketSecondService;
import com.xwtech.uomp.base.service.boss.IEcpMarketPlanService;
import com.xwtech.uomp.base.service.org.IZoneInfoService;
import com.xwtech.uomp.base.service.schedule.ISynchMarketService;
import com.xwtech.uomp.base.util.ConfigurationRead;
import com.xwtech.uomp.base.web.init.impl.EcpClientInitImpl;

/**
 * BOSS一二级营销案 礼品包 业务包 数据同步
 * 
 * This class is used for ...
 * 
 * @author unique
 * @version 1.0, 2014-1-22 下午01:37:04
 */
@Service("synchMarketService")
public class SynchMarketServiceImpl implements ISynchMarketService {

    private static final Logger logger = Logger.getLogger("ecplog");

    @Autowired
    IZoneInfoService zoneInfoService;
    @Autowired
    IEcpMarketPlanService ecpMarketPlanService;
    @Autowired
    IBossMarketFirstService bossMarketFirstService;
    @Autowired
    IBossMarketSecondService bossMarketSecondService;
    @Autowired
    IBossBindBizService bossBindBizService;
    @Autowired
    IBossGiftInfoService bossGiftInfoService;
    @Autowired
    EcpClientInitImpl ecpClientInit;
    
    private final int maxPoolSize = Integer.parseInt(ConfigurationRead.getInstance().getValue("boss.ThreadPool.maxsize"));

    @Scheduled(cron = "0 39 23 * * ?")
    public void synchMarketPlanInfo() {
	List<ZoneInfoBean> zoneInfoBeans = zoneInfoService.queryZoneByParentId("1");// 地市

	try {
	    ExecutorService executorService = Executors.newFixedThreadPool(maxPoolSize);
	    ecpClientInit.init();
	    logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfo: 网厅营销案同步数据开始……");
	    int length = zoneInfoBeans.size();
	    for (int i = 0; i < length; i++) {
		String cityId = zoneInfoBeans.get(i).getBossCode();
		logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfo: cityId=" + cityId);
		executorService.execute(new SynchMarketThread(cityId,"4"));
	    }
	    executorService.shutdown();
	    logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfo: 网厅营销案同步数据结束……");
	    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

	    ExecutorService executorWap;
	    for (;;) {
		if (executorService.isTerminated()) {
		    ecpClientInit.initwap();
		    logger.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfo: 掌厅营销案同步数据开始……");
		    executorWap = Executors.newFixedThreadPool(maxPoolSize);

		    for (int i = 0; i < length; i++) {
			String cityId = zoneInfoBeans.get(i).getBossCode();
			logger
				.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfo: cityId="
					+ cityId);
			executorWap.execute(new SynchMarketThread(cityId,"5"));
		    }

		    executorWap.shutdown();
		    logger.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfo: 掌厅营销案同步数据结束……");
		    break;
		}
	    }
	    executorWap.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
	    
	    ExecutorService executorSms;
	    for (;;) {
		if (executorWap.isTerminated()) {
		    ecpClientInit.initsms();
		    logger.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfo: 短厅营销案同步数据开始……");
		    executorSms = Executors.newFixedThreadPool(maxPoolSize);

		    for (int i = 0; i < length; i++) {
			String cityId = zoneInfoBeans.get(i).getBossCode();
			logger
				.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfo: cityId="
					+ cityId);
			executorSms.execute(new SynchMarketThread(cityId,"6"));
		    }

		    executorSms.shutdown();
		    logger.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfo: 短厅营销案同步数据结束……");
		    break;
		} 
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void synchMarketPlanInfoW(ExecutorService service) {
	List<ZoneInfoBean> zoneInfoBeans = zoneInfoService.queryZoneByParentId("1");// 地市
	if (service != null && service.isShutdown()) {

	} else {
	    service = Executors.newFixedThreadPool(maxPoolSize);
	    ecpClientInit.init();
	    logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfoW: 网厅营销案同步数据开始……");
	    int length = zoneInfoBeans.size();
	    for (int i = 0; i < length; i++) {
		String cityId = zoneInfoBeans.get(i).getBossCode();
		logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfoW: cityId=" + cityId);
		service.execute(new SynchMarketThread(cityId,"4"));
	    }
	    service.shutdown();
	}
	 logger.info("SynchMarketServiceImpl>>WTsynchMarketPlanInfoW: 网厅营销案同步数据结束……");
    }

    public void synchMarketPlanInfoWap(ExecutorService service) {
	List<ZoneInfoBean> zoneInfoBeans = zoneInfoService.queryZoneByParentId("1");// 地市
	if (service != null && service.isShutdown()) {
	    ExecutorService executorWap;
	    for (;;) {
		if (service.isTerminated()) {
		    ecpClientInit.initwap();
		    logger.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfoWap: 掌厅营销案同步数据开始……");
		    executorWap = Executors.newFixedThreadPool(maxPoolSize);

		    int length = zoneInfoBeans.size();
		    for (int i = 0; i < length; i++) {
			String cityId = zoneInfoBeans.get(i).getBossCode();
			logger.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfoWap: cityId="
				+ cityId);
			executorWap.execute(new SynchMarketThread(cityId,"5"));
		    }

		    executorWap.shutdown();
		    break;
		}
	    }
	}
	 logger.info("SynchMarketServiceImpl>>ZTsynchMarketPlanInfoWap: 掌厅营销案同步数据结束……");
    }

    public void synchMarketPlanInfoSms(ExecutorService service) {
	List<ZoneInfoBean> zoneInfoBeans = zoneInfoService.queryZoneByParentId("1");// 地市
	if (service != null && service.isShutdown()) {
	    ExecutorService executorSms;
	    for (;;) {
		if (service.isTerminated()) {
		    ecpClientInit.initsms();
		    logger.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfoSms: 短厅营销案同步数据开始……");
		    executorSms = Executors.newFixedThreadPool(maxPoolSize);

		    int length = zoneInfoBeans.size();
		    for (int i = 0; i < length; i++) {
			String cityId = zoneInfoBeans.get(i).getBossCode();
			logger.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfoSms: cityId="
				+ cityId);
			executorSms.execute(new SynchMarketThread(cityId,"6"));
		    }

		    executorSms.shutdown();
		    break;
		}
	    }
	}
	 logger.info("SynchMarketServiceImpl>>DTsynchMarketPlanInfoSms: 短厅营销案同步数据结束……");
    }

    public void synchMarketPlanInfoByCity(String cityId) {
	logger.info("SynchMarketServiceImpl>>synchMarketPlanInfoByCity: cityId=" + cityId);
	new Thread(new SynchMarketThread(cityId,"6")).start();
    }

    class SynchMarketThread implements Runnable {
	private String cityId;
	private String channel;

	SynchMarketThread(String cityId,String channel) {
	    this.cityId = cityId;
	    this.channel = channel;
	}

	public void run() {
	    List<CMarketingPlandt> list = ecpMarketPlanService.queryMarketPlan(cityId);
	   
	    if (null != list && list.size() > 0) {
		Map<String, String> param = new HashMap<String, String>();
		for (int h = 0; h < list.size(); h++) {
		    CMarketingPlandt dt = list.get(h);

		    param.put("cityId", cityId);
		    param.put("marketSecondCode", dt.marketingplan_plan_id);

		    CMarketingPlandt maketplanallinfo = ecpMarketPlanService
			    .queryMarketPlanInfo(param);
		    
		    
		    if (maketplanallinfo != null) {// TODO\
		   
			Map<String, String> map = new HashMap<String, String>();
			map.put("cityId", cityId);
			map.put("marketFirstCode", dt.marketingplan_type_id);

			boolean marketbool = bossMarketFirstService.checkIsIn(map);
			
			bossMarketFirstService.insert(marketbool, dt, cityId,channel);// 一级boss营销案

			marketbool = bossMarketSecondService.checkIsIn(param);
			System.out.println("dt.marketingplan_permit_brand_ids======"+dt.marketingplan_permit_brand_ids);
			
			bossMarketSecondService.insert(marketbool, dt, cityId, maketplanallinfo);// 二级营销案
		

			List<CMarketingbusiPackcfgdt> busiPacks = ecpMarketPlanService
				.queryMarketPlanBiz(param);// 业务包
			if (null != busiPacks && busiPacks.size() > 0) {
			    int len = busiPacks.size();
			    for (int j = 0; j < len; j++) {
				CMarketingbusiPackcfgdt bean = busiPacks.get(j);
				marketbool = bossBindBizService.checkIsIn(bean, cityId);
				bossBindBizService.insert(marketbool, bean, cityId);
			    }
			}

			List<CPresentGoodspackcfgdt> giftBags = ecpMarketPlanService
				.queryMarketGiftBag(param);
			if (null != giftBags && giftBags.size() > 0) {
			    int len = giftBags.size();
			    for (int j = 0; j < len; j++) {
				CPresentGoodspackcfgdt bean = giftBags.get(j);
				marketbool = bossGiftInfoService.checkIsIn(bean, cityId);
				bossGiftInfoService.insert(marketbool, bean, cityId);
			    }
			}

		    } else {
			logger.info("二级boss编码无数据=" + cityId + "-" + dt.marketingplan_plan_id);
		    }
		}
	    } else {
		logger.info("地市一级boss编码无数据=" + cityId);
	    }
	}
    }
}
