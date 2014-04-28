package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketPayShowBean;

public interface IMarketPayShowService {
	void batchUpdate(List<MarketPayShowBean> list,String marketSecondPkid);
	List<MarketPayShowBean> selectPayShowBeansBySecondPkid(Map<String, String> param);
	void deleteBySecondPkid(String marketSecondPkid);
}
