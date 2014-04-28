package com.xwtech.uomp.base.dao.order;

import java.util.List;

import com.xwtech.uomp.base.pojo.order.OrderGiftBean;

public interface IOrderGiftDao {
	/**
	 * 查询定时下发类型下发状态不成功的数据
	 * @param 
	 * @return
	 */
	List<OrderGiftBean> queryOrderGiftList();
	
	void updateOrderGift(List<Object[]> list);
}
