package com.xwtech.uomp.base.service.gift;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.gift.GiftInfoTypeBean;

/**
 * 礼品类型
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-22 下午02:48:44
 */
public interface IGiftInfoTypeService {

	List<GiftInfoTypeBean> queryGiftInfoTypeList(Map<String, String> param);
	
	Page queryGifgInfoTypeList(Map<String, String> param);
	
	void addGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean);
	
	int queryMaxGiftTypeCode();
	
	void updateGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean);
	
	GiftInfoTypeBean queryGiftTypeById(String giftType);
}
