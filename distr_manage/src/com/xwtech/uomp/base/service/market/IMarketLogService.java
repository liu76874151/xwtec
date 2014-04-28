package com.xwtech.uomp.base.service.market;

import com.xwtech.uomp.base.pojo.market.MarketLogBean;


/**
 *@ClassName:IMarketLogService.java
 *@Description：
 *@author: Mars
 *@date： date：2014-1-22 time：上午11:26:27
 *@version 1.0
 */
public interface IMarketLogService {
	void saveMarketLog(MarketLogBean marketLogBean);
	void updateSyncCache();

}
