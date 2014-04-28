package com.xwtech.uomp.base.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.market.MarketAuditMapper;
import com.xwtech.uomp.base.pojo.market.MarketAuditBean;
import com.xwtech.uomp.base.service.market.IMarketAuditService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-31 下午06:14:06
 */
@Service("marketAuditService")
public class MarketAuditServiceImpl implements IMarketAuditService {

	@Autowired
	MarketAuditMapper marketAuditMapper;
	
	public int insert(MarketAuditBean record) {
		return marketAuditMapper.insert(record);
	}

	public int insertSelective(MarketAuditBean record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
