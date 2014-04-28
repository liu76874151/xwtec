package com.xwtech.uomp.base.dao.market;

import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.MarketUnityRecodeBean;

import java.util.List;
import java.util.Map;

public interface MarketFirstBeanMapper {

    int saveMarketFirst(MarketFirstBean record);

    int queryMarketFirstListCount(Map<String, Object> map);

    List<MarketFirstBean> queryMarketFirstList(Map<String, Object> map);
    List<MarketFirstBean> queryUnityMarketFirstList(Map<String, String> map);

    List<MarketFirstBean> queryViewNameOrderByMarketOrder(String city);
    List<MarketFirstBean> queryViewNameOrderByZTMarketOrder(String city);

    String queryMarketFirstSequence();

    int queryMarketSencodCount(String marketFirstPkid);

    void deleteMarketFirst(Map<String, Object> marketFirstPkids);

    MarketFirstBean queryByPrimaryKey(String marketFirstPkid);

    int updateByPrimaryKeySelective(Map<String, String> map);
    
    void updateSecondMarketChannel(Map<String, String> map);

    int isExistViewName( Map<String, Object> param);
    
    List<MarketFirstBean> queryMarketForAudit(Map<String, String> param);
    
    int queryCountForAudit(Map<String, String> param);
	/*
	 *   保存统一营销案记录 
	 */
    void saveUnityRecode(MarketUnityRecodeBean marketUnityRecodeBean);
    //TODO----delete--------

    int deleteByPrimaryKey(Long marketFirstPkid);
    
    void updateAuditState(MarketFirstBean marketFirstBean);
    
    List<MarketFirstBean> queryMarketForTest(Map<String, String> param);
    
    int queryCountForTest(Map<String, String> param);
    
    void updateTestState(MarketFirstBean marketFirstBean);
    
    int queryCountHaveSecond(Map<String, Object> param);
    
    List<MarketFirstBean> queryMarketAuditOnConsole(Map<String, String> param);

}
