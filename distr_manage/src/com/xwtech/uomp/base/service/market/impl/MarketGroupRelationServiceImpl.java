package com.xwtech.uomp.base.service.market.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketGroupRelationMapper;
import com.xwtech.uomp.base.pojo.market.MarketGroupRelationBean;
import com.xwtech.uomp.base.service.market.IMarketGroupRelationService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * 二级营销案目标组织
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 上午09:50:24
 */
@Service("marketGroupRelationService")
public class MarketGroupRelationServiceImpl implements
		IMarketGroupRelationService {

	@Autowired
	ISequenceService sequenceService;
	@Autowired
	MarketGroupRelationMapper marketGroupRelationMapper;
	
	/**
	 * 二级营销案目标组织保存
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchUpdate(List<MarketGroupRelationBean> list,
			String marketSecondPkid) {

		for (MarketGroupRelationBean marketGroupRelationBean : list) {
			// TODO 批量插入临时解决办法
			marketGroupRelationBean.setRelationId(sequenceService.getSequence("GROUP_RELATION_RELATIONID_SEQ"));
			marketGroupRelationBean.setMarketSecondPkid(marketSecondPkid);
			marketGroupRelationMapper.insertMarketGroupRelation(marketGroupRelationBean);
		}
	}

	/**
	 * 查询二级营销案的目标组织
	 */
	public List<MarketGroupRelationBean> selectSecondMarketGroups(
			Map<String, String> param) {
		return marketGroupRelationMapper.selectSecondMarketGroups(param);
	}

	public void deleteBySecondPkid(String marketSecondPkid) {
		marketGroupRelationMapper.deleteBySecondPkid(marketSecondPkid);
	}

}
