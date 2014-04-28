package com.xwtech.uomp.base.service.market.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketOrgRelationMapper;
import com.xwtech.uomp.base.pojo.market.MarketOrgRelationBean;
import com.xwtech.uomp.base.service.market.IMarketOrgRelationService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * 二级营销案营业厅关联 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-23 上午11:23:12
 */
@Service("marketOrgRelationService")
public class MarketOrgRelationServiceImpl implements IMarketOrgRelationService {

	@Autowired
	ISequenceService sequenceService;
	@Autowired
	MarketOrgRelationMapper marketOrgRelationMapper;

	@Transactional(propagation=Propagation.REQUIRED)
	public void batchUpdate(List<MarketOrgRelationBean> list,
			String marketSecondPkid) {

		for (MarketOrgRelationBean marketOrgRelationBean : list) {
			// TODO  批量插入临时解决方案
			marketOrgRelationBean.setMarketSecondPkid(marketSecondPkid);
			marketOrgRelationBean.setOrgId(sequenceService.getSequence("MARKET_ORG_RELATION_ID_SEQ"));
			marketOrgRelationMapper.insertOrgInfo(marketOrgRelationBean);
		}
	}

	/**
	 * 根据marketSecondPkid 查询绑定营业厅编码
	 */
	public List<MarketOrgRelationBean> selectOrgInfoCodeBySecondPkid(
			String marketSecondPkid) {
		return marketOrgRelationMapper.selectOrgInfoCodeBySecondPkid(marketSecondPkid);
	}

	public void deleteBySecondPkid(String marketSecondPkid) {
		marketOrgRelationMapper.deleteBySecondPkid(marketSecondPkid);
		
	}


}
