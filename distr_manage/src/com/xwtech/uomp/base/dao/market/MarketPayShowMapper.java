package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketPayShowBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午03:06:12
 */
public interface MarketPayShowMapper {
	void batchUpdate(List<MarketPayShowBean> paramMap);
	List<MarketPayShowBean> selectPayShowBeansBySecondPkid(Map<String, String> param);
	void deleteBySecondPkid(String marketSecondPkid);
	void insertMarketPayShow(MarketPayShowBean marketPayShowBean);
}