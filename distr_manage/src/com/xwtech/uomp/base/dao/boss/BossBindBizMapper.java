package com.xwtech.uomp.base.dao.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;

public interface BossBindBizMapper {
	
	List<BossBindBizBean> queryBossBindBizList(Map<String, String> map);
	List<BossBindBizBean> queryBossBindBizBySecondPkid(Map<String, String> map);
	
	BossBindBizBean queryOneBossBindBiz(Map<String, String> param);
	void insert(BossBindBizBean bossBindBizBean);
	void insertBizSecond(BossBindBizBean bossBindBizBean);
	void update(BossBindBizBean bossBindBizBean);
	void deleteBossBindBiz(String MarketFirstCode);
	void deleteBossBindBizSecond(String MarketFirstCode);
}