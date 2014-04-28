package com.xwtech.uomp.base.service.market.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.market.MarketLogBeanMapper;
import com.xwtech.uomp.base.pojo.market.MarketLogBean;
import com.xwtech.uomp.base.service.market.IMarketLogService;


/**
 *@ClassName:MarketLogServiceImpl.java
 *@Description：
 *@author: Mars
 *@date： date：2014-1-22 time：上午11:26:19
 *@version 1.0
 */
@Service("marketLogService")
public class MarketLogServiceImpl implements IMarketLogService {

	@Autowired
	MarketLogBeanMapper marketLogBeanMapper;
	
	

	public void saveMarketLog(MarketLogBean marketLogBean) {
		// TODO Auto-generated method stub
		marketLogBeanMapper.saveMarketLog(marketLogBean);
		
	}



	public void updateSyncCache() {
		// TODO Auto-generated method stub
		marketLogBeanMapper.updateSyncCache();
	}

}
