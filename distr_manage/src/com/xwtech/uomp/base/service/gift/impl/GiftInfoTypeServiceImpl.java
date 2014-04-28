package com.xwtech.uomp.base.service.gift.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.gift.GiftInfoTypeMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.gift.GiftInfoTypeBean;
import com.xwtech.uomp.base.service.gift.IGiftInfoTypeService;
/**
 * 礼品类型
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-22 下午02:49:59
 */
@Service("giftInfoTypeService")
public class GiftInfoTypeServiceImpl implements IGiftInfoTypeService {

	@Autowired
	GiftInfoTypeMapper giftInfoTypeMapper;
	public List<GiftInfoTypeBean> queryGiftInfoTypeList(
			Map<String, String> param) {
		return giftInfoTypeMapper.queryGiftInfoTypeList(param);
	}
	
	public Page queryGifgInfoTypeList(Map<String, String> param){
		List<GiftInfoTypeBean> list = giftInfoTypeMapper.queryGiftInfoTypeList(param);
		int count = giftInfoTypeMapper.queryCount(param);
		
		Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
	}
	
	public void addGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean){
		giftInfoTypeMapper.addGiftTypeInfo(giftInfoTypeBean);
	}
	
	public int queryMaxGiftTypeCode(){
		return giftInfoTypeMapper.queryMaxGiftTypeCode();
	}
	
	public void updateGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean){
		giftInfoTypeMapper.updateGiftTypeInfo(giftInfoTypeBean);
	}
	
	public GiftInfoTypeBean queryGiftTypeById(String giftType){
		return giftInfoTypeMapper.queryGiftTypeById(giftType);
	}

}
