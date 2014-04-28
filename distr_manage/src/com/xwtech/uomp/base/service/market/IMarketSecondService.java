package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketSecondBean;

public interface IMarketSecondService {

	Page queryMarketSecondList(Map<String, String> map);
	void updateMarketSecondBean(MarketSecondBean marketSecondBean);
	void addMarketSecondBean(MarketSecondBean marketSecondBean);
	MarketSecondBean queryMarketSecondBean(String pkid);
	void deleteMarketSecondBeanByPkid(MarketSecondBean marketSecondBean);
	int queryMarketSecondCount(Map<String, String> param);
	Page queryMarketSecondForVerify(Map<String, String> param);
	Page queryMarketSecondForVerifyOnConsole(Map<String, String> param);
	void updateMarketSecondForVerify(MarketSecondBean marketSecondBean);
	Page queryMarketForTest(Map<String, String> param);
	void updateTestState(MarketSecondBean marketSecondBean);
	void updateIsViewListState(MarketSecondBean marketSecondBean);
	Page queryViewNameOrderByZTMarketOrder(String marketFirstpkid);
	Page queryViewNameOrderByWTMarketOrder(String marketFirstpkid);
}
