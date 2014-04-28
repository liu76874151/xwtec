package com.xwtech.uomp.base.service.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingbusiPackcfgdt;
import com.xwtech.uomp.base.pojo.boss.ecp.CPresentGoodspackcfgdt;

/**
 * Ecp 营销案一二级
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-22 下午02:01:38
 */
public interface IEcpMarketPlanService {

    /**
     * 一级营销案
     * @param cityId
     * @return  
     * @author unique
     */
    List<CMarketingPlandt> queryMarketPlan(String cityId);
    
    /**
     * 营销案详细
     * @param param
     * 
     * cityId,planId
     * @return  
     * @author unique
     */
    CMarketingPlandt queryMarketPlanInfo(Map<String, String> param);
    
    /**
     * 业务包查询
     * @param param
     * @return  
     * @author unique
     */
    List<CMarketingbusiPackcfgdt> queryMarketPlanBiz(Map<String, String> param);
    
    /**
     * 礼品包查询
     * @param param
     * @return  
     * @author unique
     */
    List<CPresentGoodspackcfgdt> queryMarketGiftBag(Map<String, String> param);
}
