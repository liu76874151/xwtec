/**
 * Title: REBossMarketServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-29 
 * @ time 下午3:29:38
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.boss.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.boss.REBossMarketSecondMapper;
import com.xwtech.uomp.base.pojo.boss.REBossMarketSecondBean;
import com.xwtech.uomp.base.service.boss.IREBossMarketSecondService;

/**
 * @author zhanglu
 * 
 */
@Service("rebossMarketSecondService")
public class REBossMarketSecondServiceImpl implements
		IREBossMarketSecondService {

	@Autowired
	REBossMarketSecondMapper rebossMarketSecondMapper;
	
	/**
	 * 根据一级预约营销案crm编码获取二级预约营销案信息
	 * 创建日期：2013-10-29下午4:18:16
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	public List<REBossMarketSecondBean> queryCrmSecondInfo(String crmFirstCode) {
		return rebossMarketSecondMapper.queryCrmSecondInfo(crmFirstCode);
	}
	
	/**
	 * 根据二级预约营销crm编码获取boss礼品信息
	 */
	public List<REBossMarketSecondBean> queryCrmSecondGiftInfo(String crmSecondCode){
		return rebossMarketSecondMapper.queryCrmSecondGiftInfo(crmSecondCode);
	}

}
