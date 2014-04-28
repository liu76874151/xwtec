package com.xwtech.uomp.base.service.market.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketPayShowMapper;
import com.xwtech.uomp.base.pojo.market.MarketPayShowBean;
import com.xwtech.uomp.base.service.market.IMarketPayShowService;
/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午03:14:04
 */

@Service("marketPayShowService")
public class MarketPayShowServiceImpl implements IMarketPayShowService {

	@Autowired
	MarketPayShowMapper marketPayShowMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchUpdate(List<MarketPayShowBean> list, String marketSecondPkid) {
		for (MarketPayShowBean marketPayShowBean : list) {
//TODO			
			marketPayShowBean.setMarketSecondPkid(marketSecondPkid);
			marketPayShowMapper.insertMarketPayShow(marketPayShowBean);
		}
	}
	
	/**
	 * 
	 */
	public List<MarketPayShowBean> selectPayShowBeansBySecondPkid(
			Map<String, String> param) {
		return marketPayShowMapper.selectPayShowBeansBySecondPkid(param);
	}

	public void deleteBySecondPkid(String marketSecondPkid) {
		marketPayShowMapper.deleteBySecondPkid(marketSecondPkid);
		
	}
}
