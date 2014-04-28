package com.xwtech.uomp.base.service.market;

import com.xwtech.uomp.base.pojo.market.MarketAuditBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-31 下午06:13:13
 */
public interface IMarketAuditService {

	int insert(MarketAuditBean record);

	int insertSelective(MarketAuditBean record);

}
