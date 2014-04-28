package com.xwtech.uomp.base.service.market.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketSecondBindBizMapper;
import com.xwtech.uomp.base.pojo.market.MarketSecondBindBizBean;
import com.xwtech.uomp.base.service.market.IMarketSecondBindBizService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.service.tools.IToolsService;

/**
 * 二级营销案板绑定业务 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-17 下午08:37:03
 */
@Service("marketSecondBindBizService")
public class MarketSecondBindBizServiceImpl implements
		IMarketSecondBindBizService {

	@Autowired
	MarketSecondBindBizMapper marketSecondBindBizMapper;
    @Autowired
    ISequenceService sequenceService;
    @Autowired 
	IToolsService  toolsService;

	public int insert(MarketSecondBindBizBean record) {
		marketSecondBindBizMapper.insert(record);
		return 0;
	}

	public int insertSelective(MarketSecondBindBizBean record) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int updateByPrimaryKey(MarketSecondBindBizBean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(MarketSecondBindBizBean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 批量插入绑定业务
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchUpdate(List<MarketSecondBindBizBean> list,
			String marketSecondPkid) {
		//System.out.println(list.size());
		for (MarketSecondBindBizBean marketSecondBindBizBean : list) {
			//TODO 
			String city=marketSecondBindBizBean.getCity()+"";
			if(null!=marketSecondBindBizBean.getCity()&&marketSecondBindBizBean.getCity()>24){
			city=toolsService.reverseCityBOSSCode(city);}
			if(StringUtils.isNotBlank(city)){
				marketSecondBindBizBean.setCity(Short.parseShort(city));
			}
			
			marketSecondBindBizBean.setMarketSecondPkid(marketSecondPkid);
			marketSecondBindBizBean.setBizId(sequenceService.getSequence("BIND_BIZ_PKID_SEQ"));
			marketSecondBindBizMapper.insert(marketSecondBindBizBean);
		}
	}

	/**
	 * 根据 marketSecondPkid 查询绑定业务信息
	 */
	public List<MarketSecondBindBizBean> selectBySecondPkid(Map<String, String> map) {
		
		return marketSecondBindBizMapper.selectBySecondPkid(map);
	}

	/**
	 * 根据二级营销案pkid删除
	 */
	public void deleteBySecondPkid(String marketSecondPkid) {
		marketSecondBindBizMapper.deleteBySecondPkid(marketSecondPkid);
	}
}
