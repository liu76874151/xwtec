package com.xwtech.uomp.base.service.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.boss.BossMarketFirstBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
/**
 * @author zhangel 
 */
public interface IBossMarketFirstService {

	List<BossMarketFirstBean> queryBossMarketFirstList(Map<String, Object> map);
	Page queryBossMarketFirstPage(Map<String, Object> map);
	
	boolean checkIsIn(Map<String, String> param);

	void insert(boolean marketbool, CMarketingPlandt dt, String cityId,String channel);
	void insertBosscode(BossMarketFirstBean bean);
	void deleteMarketFirstCascade(String MarketFirstCode);
}
