package com.xwtech.uomp.base.dao.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.REBossMarketFirstBean;

public interface REBossMarketFirstMapper {
		/**
		 * 查询一级预约boss信息
		 * @param city
		 * @return
		 */
	    List<REBossMarketFirstBean> queryCrmInfo(Map<String, String> param);
	    /**
	     * 查询对应的品牌
	     * @param crmfirstCode
	     * @return
	     */
	    String queryCrmBrandInfo(String crmfirstCode);

    //---TODO DELETE
//		int deleteByPrimaryKey(String fMarketLevel1Id);
//
//	    int insert(REBossMarketFirstBean record);
//
//	    int insertSelective(REBossMarketFirstBean record);
//	    int updateByPrimaryKeySelective(REBossMarketFirstBean record);
//
//	    int updateByPrimaryKey(REBossMarketFirstBean record);
}
