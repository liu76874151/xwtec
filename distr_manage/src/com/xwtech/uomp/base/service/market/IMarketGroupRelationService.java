package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketGroupRelationBean;

public interface IMarketGroupRelationService {

	void batchUpdate(List<MarketGroupRelationBean> list,String marketSecondPkid);
	List<MarketGroupRelationBean> selectSecondMarketGroups(Map<String, String> param);
	void deleteBySecondPkid(String marketSecondPkid);
}
