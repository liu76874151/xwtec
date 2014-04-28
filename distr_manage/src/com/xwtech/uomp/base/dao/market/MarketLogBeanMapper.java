package com.xwtech.uomp.base.dao.market;

import com.xwtech.uomp.base.pojo.market.MarketLogBean;



public interface MarketLogBeanMapper {



    int saveMarketLog(MarketLogBean record);
    void updateSyncCache();
    



}