package com.xwtech.uomp.base.dao.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossMarketFirstBean;


public interface BossMarketFirstMapper {
    
 	List<BossMarketFirstBean> queryBossMarketFirstList(Map<String, Object> map);
 	int queryBossMarketFirstListcount(Map<String, Object> map);
 	
 	BossMarketFirstBean queryOneBossMarketFirstBean(Map<String, String> param);

	void insert(BossMarketFirstBean bean);
	void deleteMarketFirst(String MarketFirstCode);
}