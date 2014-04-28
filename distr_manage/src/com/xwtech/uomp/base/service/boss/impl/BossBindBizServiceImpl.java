package com.xwtech.uomp.base.service.boss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.boss.BossBindBizMapper;
import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingbusiPackcfgdt;
import com.xwtech.uomp.base.service.boss.IBossBindBizService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
/**
 * @author zhangel 
 */
@Service("bossBindBizService")
public class BossBindBizServiceImpl implements IBossBindBizService {

	@Autowired
	BossBindBizMapper bossBindBizMapper;
	@Autowired
	ISequenceService sequenceService;

	public List<BossBindBizBean> queryBossBindBizList(Map<String, String> map) {
		return bossBindBizMapper.queryBossBindBizList(map);
	}

	public List<BossBindBizBean> queryBossBindBizBySecondPkid(
			Map<String, String> map) {
		return bossBindBizMapper.queryBossBindBizBySecondPkid(map);
	}

	public boolean checkIsIn(CMarketingbusiPackcfgdt bean, String cityId) {
	    Map<String, String> param = new HashMap<String, String>();
	    param.put("marketSecondCode", bean.marketingbusipackcfg_plan_id);
	    param.put("bizCode", bean.marketingbusipackcfg_busi_pack_id);
	    param.put("cityId", cityId);
	    
	    BossBindBizBean bossBindBizBean = bossBindBizMapper.queryOneBossBindBiz(param);
	    if (bossBindBizBean != null) {
		return true;
	    }
	    return false;
	}

	public void insert(boolean marketbool, CMarketingbusiPackcfgdt bean, String cityId) {
	    
	    BossBindBizBean bossBindBizBean = new BossBindBizBean();
	    bossBindBizBean.setBizId(Long.parseLong(sequenceService.getSequence("BIND_BIZ_PKID_SEQ")));
	    bossBindBizBean.setBizCode(bean.marketingbusipackcfg_busi_pack_id);
	    bossBindBizBean.setBizName(bean.marketingbusipackcfg_busi_pack_name);
	    bossBindBizBean.setCityId(cityId);
	    bossBindBizBean.setMarketSecondCode(bean.marketingbusipackcfg_plan_id);
	    if (marketbool) {
		bossBindBizMapper.update(bossBindBizBean);
	    }else{
		bossBindBizMapper.insert(bossBindBizBean);
	    }
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertBizCode(List<BossBindBizBean> list,BossMarketSecondBean bossMarketSecondBean,int type) {
		for(BossBindBizBean bossBindBizBean: list){
			bossBindBizBean.setMarketSecondCode(bossMarketSecondBean.getMarketSecondCode());
			bossBindBizBean.setCityId(bossMarketSecondBean.getCityId());
			bossBindBizBean.setTchannal(bossMarketSecondBean.getTchannal());
			if(type==1){
				bossBindBizBean.setBizId(Long.parseLong(sequenceService.getSequence("BIND_BIZ_PKID_SEQ")));
				bossBindBizMapper.insert(bossBindBizBean);
			}else{
				bossBindBizMapper.insertBizSecond(bossBindBizBean);
			}
		}
		
		
	}


}
