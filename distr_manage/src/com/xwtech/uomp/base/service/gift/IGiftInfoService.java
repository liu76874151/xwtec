package com.xwtech.uomp.base.service.gift;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;

public interface IGiftInfoService {

	void batchUpdate(List<GiftInfoBean> list,String marketSecondPkid);
	
	List<GiftInfoBean> selectBySecondPkid(Map<String, String> map);
	
	void deleteBySecondPkid(String marketSecondPkid);
}
