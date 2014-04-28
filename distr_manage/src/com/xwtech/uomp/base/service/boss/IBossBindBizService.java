package com.xwtech.uomp.base.service.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingbusiPackcfgdt;

/**
 * @author zhangel
 */
public interface IBossBindBizService {

	List<BossBindBizBean> queryBossBindBizList(Map<String, String> map);
	List<BossBindBizBean> queryBossBindBizBySecondPkid(Map<String, String> map);
	boolean checkIsIn(CMarketingbusiPackcfgdt bean, String cityId);
	void insert(boolean marketbool, CMarketingbusiPackcfgdt bean, String cityId);
	void insertBizCode(List<BossBindBizBean> list,BossMarketSecondBean bossMarketSecondBean,int type);
}
