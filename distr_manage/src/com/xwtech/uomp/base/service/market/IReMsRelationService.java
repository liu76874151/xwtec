package com.xwtech.uomp.base.service.market;

import java.util.List;

import com.xwtech.uomp.base.pojo.market.ReMsRelationBean;

/**
 *@ClassName:IReMsRelationService.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-30 time：上午09:19:30
 *@version 1.0
 */
public interface IReMsRelationService {
	/**
	 * 保存预约目标用户
	 * @param relationBeans
	 * @returns
	 */
	int saveReMsRelationBean(List<ReMsRelationBean> list);
	void deleteReMsRelation(String marketFirstPkid);
}
