package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketSecondBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-29 下午02:19:27
 */
public interface MarketSecondDao {

	List<MarketSecondBean> queryMarketSecondList(Map<String, String> map);
	int queryMarketSecondCount(Map<String, String> map);
	void updateMarketSecondBean(MarketSecondBean marketSecondBean);
	void addMarketSecondBean(MarketSecondBean marketSecondBean);
	MarketSecondBean queryMarketSecondBean(String pkid);
	void deleteMarketSecondBeanByPkid(MarketSecondBean marketSecondBean);
	List<MarketSecondBean> queryMarketSecondForVerify(Map<String, String> param);
	List<MarketSecondBean> queryMarketSecondForVerifyOnConsle(Map<String, String> param);
	int queryMarketSecondCountForVerify(Map<String, String> param);
	void updateMarketSecondForVerify(MarketSecondBean marketSecondBean);
	List<MarketSecondBean> queryMarketForTest(Map<String, String> param);
	int queryCountForTest(Map<String, String> param);
	void updateTestState(MarketSecondBean marketSecondBean);
	/**
	 * 更新二级营销案列表展示状态，0为未展示，1为展示
	 */
	void updateIsViewListState(MarketSecondBean marketSecondBean);
	/**
	 * 根据一级id按wtmarketorder查询二级营销案
	 */
	List<MarketSecondBean>  queryViewNameOrderByWTMarketOrder(String marketFirstPKID);
	List<MarketSecondBean>  queryViewNameOrderByZTMarketOrder(String marketFirstPKID);
}
