package com.xwtech.uomp.base.service.market.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.market.ReMsRelationMapper;
import com.xwtech.uomp.base.pojo.market.MarketGroupRelationBean;
import com.xwtech.uomp.base.pojo.market.ReMsRelationBean;
import com.xwtech.uomp.base.service.market.IReMsRelationService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 *@ClassName:ReMsRelationServiceImpl.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-30 time：上午09:20:31
 *@version 1.0
 */
@Service("reMsRelationService")
public class ReMsRelationServiceImpl implements IReMsRelationService {
	@Autowired
	ReMsRelationMapper reMsRelationMapper;
	@Autowired
	ISequenceService sequenceService;

	public int saveReMsRelationBean(ReMsRelationBean record) {
		return reMsRelationMapper.saveReMsRelationBean(record);
	}

	public int saveReMsRelationBean(List<ReMsRelationBean> list) {
		if(list!=null){
			for (ReMsRelationBean reMsRelationBean : list) {
				reMsRelationMapper.saveReMsRelationBean(reMsRelationBean);
				}
			}
			return 0;
		
	}

	public void deleteReMsRelation(String marketFirstPkid) {
		reMsRelationMapper.deleteReMsRelation(marketFirstPkid);
		
	}

}
