/**
 * Title: REBossMarketSecondMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-29 
 * @ time 下午3:58:28
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.boss;

import java.util.List;

import com.xwtech.uomp.base.pojo.boss.REBossMarketSecondBean;

/**
 * @author zhanglu
 *
 */
public interface REBossMarketSecondMapper {

	List<REBossMarketSecondBean> queryCrmSecondInfo(String crmFirstCode);
	
	List<REBossMarketSecondBean> queryCrmSecondGiftInfo(String crmSecondCode);
	
}
