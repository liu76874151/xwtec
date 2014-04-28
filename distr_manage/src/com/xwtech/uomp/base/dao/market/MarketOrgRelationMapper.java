package com.xwtech.uomp.base.dao.market;

import java.util.List;

import com.xwtech.uomp.base.pojo.market.MarketOrgRelationBean;

/**
 * 二级营销案与营业厅关联
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 上午11:16:03
 */
public interface MarketOrgRelationMapper {
	void batchUpdate(List<MarketOrgRelationBean> list);
	List<MarketOrgRelationBean> selectOrgInfoCodeBySecondPkid(String marketSecondPkid);
	void deleteBySecondPkid(String marketSecondPkid);
	void insertOrgInfo(MarketOrgRelationBean marketOrgRelationBean);
}