package com.xwtech.uomp.base.service.boss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.boss.BossGiftInfoMapper;
import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;
import com.xwtech.uomp.base.pojo.boss.BossGiftInfoBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CPresentGoodspackcfgdt;
import com.xwtech.uomp.base.service.boss.IBossGiftInfoService;

/**
 * @author zhangel
 */

@Service("bossGiftInfoService")
public class BossGiftInfoServiceImpl implements IBossGiftInfoService{

	@Autowired
	BossGiftInfoMapper bossGiftInfoMapper;
	public List<BossGiftInfoBean> queryBossGiftInfoList(Map<String, String> map) {
		return bossGiftInfoMapper.queryBossGiftInfoList(map);
	}
	
	public boolean checkIsIn(CPresentGoodspackcfgdt bean, String cityId) {
	    
	    Map<String, String> param = new HashMap<String, String>();
	    param.put("marketSecondCode", bean.presentgoodspackcfg_plan_id);
	    param.put("bossGiftId", bean.presentgoodspackcfg_pack_id);
	    param.put("cityId", cityId);
	    BossGiftInfoBean bossGiftInfoBean = bossGiftInfoMapper.queryOneBossGiftInfo(param);
	    if (null != bossGiftInfoBean) {
		return true;
	    }
	    return false;
	}

	public void insert(boolean marketbool, CPresentGoodspackcfgdt bean, String cityId) {
	    
	    BossGiftInfoBean bossGiftInfoBean = new BossGiftInfoBean();
	    bossGiftInfoBean.setBossGiftId(bean.presentgoodspackcfg_pack_id);
	    bossGiftInfoBean.setCityId(Short.parseShort(cityId));
	    bossGiftInfoBean.setGiftComment(bean.presentgoodspackcfg_pack_name);
	    bossGiftInfoBean.setGiftName(bean.presentgoodspackcfg_pack_name);
	    bossGiftInfoBean.setMarketSecondCode(bean.presentgoodspackcfg_plan_id);
	    
	    if (marketbool) {
		bossGiftInfoMapper.update(bossGiftInfoBean);
	    }else {
		bossGiftInfoMapper.insert(bossGiftInfoBean);
	    }
	}
	
	public List<BossGiftInfoBean> selectBySecondPkid(Map<String, String> map){
		return bossGiftInfoMapper.selectBySecondPkid(map);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void insertGiftSecond(List<BossGiftInfoBean> list,
			BossMarketSecondBean bean, int type) {
		for(BossGiftInfoBean bossGiftInfoBean:list){
			bossGiftInfoBean.setCityId(Short.parseShort(bean.getCityId()));
			bossGiftInfoBean.setMarketSecondCode(bean.getMarketSecondCode());
			bossGiftInfoBean.setTchannal(bean.getTchannal());
			if(type==1){
				bossGiftInfoMapper.insert(bossGiftInfoBean);
			}else{
				bossGiftInfoMapper.insertGiftSecond(bossGiftInfoBean);
			}
		}
		
	}

}
