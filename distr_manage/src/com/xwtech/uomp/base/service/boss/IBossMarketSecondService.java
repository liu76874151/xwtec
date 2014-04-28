package com.xwtech.uomp.base.service.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;

/**
 * @author zhangel
 */
public interface IBossMarketSecondService {
    List<BossMarketSecondBean> queryBossMarketSecondList(Map<String, String> map);

    /**
     * boos编码查询
     * 
     * @param paramMap
     * @return
     */
    Page queryBossCodeList(Map<String, String> paramMap);

    /**
     * 
     * @param param
     * @return  
     * @author unique
     */
    boolean checkIsIn(Map<String, String> param);
    
    boolean insert(boolean isIn,CMarketingPlandt dt,String cityId,CMarketingPlandt dtallinfo);
}