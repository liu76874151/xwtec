package com.xwtech.uomp.base.service.market;

import java.util.List;

import com.xwtech.uomp.base.pojo.market.MarketOrgRelationBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 上午11:22:24
 */
public interface IMarketOrgRelationService {
	void batchUpdate(List<MarketOrgRelationBean> list,String marketSecondPkid);
	List<MarketOrgRelationBean> selectOrgInfoCodeBySecondPkid(String marketSecondPkid);
	void deleteBySecondPkid(String marketSecondPkid);
}
