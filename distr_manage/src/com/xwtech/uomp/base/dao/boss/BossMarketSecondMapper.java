package com.xwtech.uomp.base.dao.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;

public interface BossMarketSecondMapper {
    List<BossMarketSecondBean> queryBossMarketSecondList(Map<String, String> map);

    /**
     * boos编码查询
     * 
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> queryBossCodeList(Map<String, String> paramMap);

    int queryBossCodeListCount(Map<String, String> paramMap);

    /**
     * ECP同步数据使用
     * 
     * @param param
     * @return
     * @author unique
     */
    BossMarketSecondBean queryOneBossMarketSecondBean(Map<String, String> param);

    void updateBossMarketSecondBean(BossMarketSecondBean bossMarketSecondBean);
    
    void insert(BossMarketSecondBean bossMarketSecondBean);
    void deleteMarketSecond (String marketFirstCode);
}