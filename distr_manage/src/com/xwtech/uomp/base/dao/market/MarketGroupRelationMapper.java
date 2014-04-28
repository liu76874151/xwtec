package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketGroupRelationBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 上午09:38:40
 */
public interface MarketGroupRelationMapper {
	void batchUpdate(List<MarketGroupRelationBean> list);
	List<MarketGroupRelationBean> selectSecondMarketGroups(Map<String, String> param);
	void deleteBySecondPkid(String marketSecondPkid);
	void insertMarketGroupRelation(MarketGroupRelationBean relationBean);
}