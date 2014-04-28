package com.xwtech.uomp.base.service.boss.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.boss.BossBindBizMapper;
import com.xwtech.uomp.base.dao.boss.BossGiftInfoMapper;
import com.xwtech.uomp.base.dao.boss.BossMarketFirstMapper;
import com.xwtech.uomp.base.dao.boss.BossMarketSecondMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;
import com.xwtech.uomp.base.pojo.boss.BossGiftInfoBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketFirstBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
import com.xwtech.uomp.base.service.boss.IBossBindBizService;
import com.xwtech.uomp.base.service.boss.IBossGiftInfoService;
import com.xwtech.uomp.base.service.boss.IBossMarketFirstService;
import com.xwtech.uomp.base.util.JsonHelper;
/**
 * @author zhangel 
 */
@Service("bossMarketFirstService")
public class BossMarketFirstServiceImpl implements IBossMarketFirstService{

	@Autowired
	BossMarketFirstMapper bossMarketFirstMapper;
	@Autowired
	BossMarketSecondMapper bossMarketSecondMapper;
	@Autowired
	IBossBindBizService bossBindBizService;
	@Autowired
	IBossGiftInfoService bossGiftInfoService;
	@Autowired
	BossBindBizMapper bossBindBizMapper;

	@Autowired
	BossGiftInfoMapper bossGiftInfoMapper;
	public List<BossMarketFirstBean> queryBossMarketFirstList(
			Map<String, Object> map) {
		return bossMarketFirstMapper.queryBossMarketFirstList(map);
	}
	
	public boolean checkIsIn(Map<String, String> param) {
	    
	    BossMarketFirstBean bean = bossMarketFirstMapper.queryOneBossMarketFirstBean(param);
	    if (bean != null) {
		return true;
	    }
	    return false;
	}

	/**
	 * ECP 同步数据使用
	 */
	public void insert(boolean marketbool, CMarketingPlandt dt, String cityId,String channel) {
	    if (marketbool==false) {
		BossMarketFirstBean bean = new BossMarketFirstBean();
		bean.setMarketFirstCode(dt.marketingplan_type_id);
		bean.setMarketFirstName(dt.marketingplantype_name);
		bean.setCityId(cityId);
		bean.setTchannal(channel);
		bossMarketFirstMapper.insert(bean);
		
	    }
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertBosscode(BossMarketFirstBean bean) {
		bossMarketFirstMapper.insert(bean);
		List<BossMarketSecondBean> BossSecondList=bean.getBossMarketSecondBean();
		if(null!=BossSecondList){
			for (BossMarketSecondBean bossMarketSecondBean:BossSecondList) {
				bossMarketSecondBean.setCityId(bean.getCityId());
				bossMarketSecondBean.setStartTime(bean.getStartTime());
				bossMarketSecondBean.setEndTime(bean.getEndTime());
				bossMarketSecondBean.setTchannal(bean.getTchannal());
				bossMarketSecondMapper.insert(bossMarketSecondBean);
			String boosBizStr=bossMarketSecondBean.getBoosBizStr();	
			String boosBizSecondListStr=bossMarketSecondBean.getBoosBizSecondListStr();
			String boosGiftInfoStr=bossMarketSecondBean.getBoosGiftInfoStr();
			String boosGiftInfoListStr=bossMarketSecondBean.getBoosGiftInfoListStr();
			if(StringUtils.isNotBlank(boosBizStr)){
				List<BossBindBizBean> bossBindBizBean=(List<BossBindBizBean>)JsonHelper.json2List(boosBizStr,BossBindBizBean.class);
				bossBindBizService.insertBizCode(bossBindBizBean, bossMarketSecondBean, 1);
				if(StringUtils.isNotBlank(boosBizSecondListStr)){
					List<BossBindBizBean> bossBindBizList=(List<BossBindBizBean>)JsonHelper.json2List(boosBizSecondListStr,BossBindBizBean.class);
					bossBindBizService.insertBizCode(bossBindBizList, bossMarketSecondBean, 2);
				}
			}
			
			if(StringUtils.isNotBlank(boosGiftInfoStr)){
				List<BossGiftInfoBean> bossGiftInfoBean=(List<BossGiftInfoBean>)JsonHelper.json2List(boosGiftInfoStr,BossGiftInfoBean.class);
				bossGiftInfoService.insertGiftSecond(bossGiftInfoBean, bossMarketSecondBean, 1);
				if(StringUtils.isNotBlank(boosGiftInfoListStr)){
					List<BossGiftInfoBean> bossGiftInfoList=(List<BossGiftInfoBean>)JsonHelper.json2List(boosGiftInfoListStr,BossGiftInfoBean.class);
					bossGiftInfoService.insertGiftSecond(bossGiftInfoList,bossMarketSecondBean,2);
				}
			}
			
			
			}
		}
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMarketFirstCascade(String MarketFirstCode) {
		bossGiftInfoMapper.deleteBossGiftInfoSecond(MarketFirstCode);
		bossGiftInfoMapper.deleteBossGiftInfo(MarketFirstCode);
		bossBindBizMapper.deleteBossBindBizSecond(MarketFirstCode);
		bossBindBizMapper.deleteBossBindBiz(MarketFirstCode);
		bossMarketSecondMapper.deleteMarketSecond(MarketFirstCode);
		bossMarketFirstMapper.deleteMarketFirst(MarketFirstCode);
		
	}

	public Page queryBossMarketFirstPage(Map<String, Object> map) {
		int count=bossMarketFirstMapper.queryBossMarketFirstListcount(map);
		List<BossMarketFirstBean> list=bossMarketFirstMapper.queryBossMarketFirstList(map);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
}
