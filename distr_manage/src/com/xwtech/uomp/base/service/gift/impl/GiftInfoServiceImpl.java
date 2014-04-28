package com.xwtech.uomp.base.service.gift.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.gift.GiftInfoMapper;
import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;
import com.xwtech.uomp.base.service.gift.IGiftInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * 礼品信息
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-21 下午03:38:04
 */
@Service("giftInfoService")
public class GiftInfoServiceImpl implements IGiftInfoService {

	@Autowired
	ISequenceService sequenceService;
	@Autowired
	GiftInfoMapper giftInfoMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchUpdate(List<GiftInfoBean> list, String marketSecondPkid) {
		for (GiftInfoBean giftInfoBean : list) {
			giftInfoBean.setGiftId(sequenceService.getSequence("GIFT_ORDER_ID_SEQ"));
			giftInfoBean.setMarketSecondPkid(marketSecondPkid);
			giftInfoMapper.insert(giftInfoBean);
		}
	}

	/**
	 * 根据marketSecondPkid 查询礼品信息
	 */
	public List<GiftInfoBean> selectBySecondPkid(Map<String, String> map) {
		return giftInfoMapper.selectBySecondPkid(map);
	}

	public void deleteBySecondPkid(String marketSecondPkid) {
		giftInfoMapper.deleteBySecondPkid(marketSecondPkid);
	}
}
