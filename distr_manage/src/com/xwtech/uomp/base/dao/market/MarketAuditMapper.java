package com.xwtech.uomp.base.dao.market;

import com.xwtech.uomp.base.pojo.market.MarketAuditBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-31 下午05:53:12
 */
public interface MarketAuditMapper {
    int insert(MarketAuditBean record);

    int insertSelective(MarketAuditBean record);
}