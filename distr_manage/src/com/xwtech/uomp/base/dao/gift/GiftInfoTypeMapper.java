package com.xwtech.uomp.base.dao.gift;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.gift.GiftInfoTypeBean;

/**
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-22 下午02:46:17
 */
public interface GiftInfoTypeMapper {
    
	List<GiftInfoTypeBean> queryGiftInfoTypeList(Map<String, String> map);
	
	int queryCount(Map<String, String> map);
	
	void addGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean);
	
	int queryMaxGiftTypeCode();
	
	void updateGiftTypeInfo(GiftInfoTypeBean giftInfoTypeBean);
	
	GiftInfoTypeBean queryGiftTypeById(String giftType);
}