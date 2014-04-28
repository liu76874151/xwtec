package com.xwtech.uomp.base.service.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossGiftInfoBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CPresentGoodspackcfgdt;

/**
 * 
 * @author zhangel
 *
 */
public interface IBossGiftInfoService {
	List<BossGiftInfoBean> queryBossGiftInfoList(Map<String, String> map);

	boolean checkIsIn(CPresentGoodspackcfgdt bean, String cityId);

	void insert(boolean marketbool, CPresentGoodspackcfgdt bean, String cityId);


	List<BossGiftInfoBean> selectBySecondPkid(Map<String, String> map);

	void insertGiftSecond(List<BossGiftInfoBean> list,BossMarketSecondBean bean,int type);

}
