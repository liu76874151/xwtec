/**
 * Title: IREBossMarketService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-29 
 * @ time 下午3:28:15
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.boss;

import java.util.List;

import com.xwtech.uomp.base.pojo.boss.REBossMarketSecondBean;

/**
 * @author zhanglu
 *
 */
public interface IREBossMarketSecondService {

	List<REBossMarketSecondBean> queryCrmSecondInfo(String crmFirstCode);
	
	List<REBossMarketSecondBean> queryCrmSecondGiftInfo(String crmSecondCode);
	
}
