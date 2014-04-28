package com.xwtech.uomp.base.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;
import com.xwtech.uomp.base.pojo.order.OrderGiftBean;
@Repository("orderGiftDao")
public class OrderGiftDaoImpl extends BaseDAO implements IOrderGiftDao {

	public List<OrderGiftBean> queryOrderGiftList() {
		String sql="select * from JSMSS_ORDER_GIFT where GIFT_SEND_TYPE='1' and GIFT_SEND_STATUS=0 ";
		return (List<OrderGiftBean>) queryForList(sql,OrderGiftBean.class);
	}

	public void updateOrderGift(List<Object[]> list) {
		String sql="update JSMSS_ORDER_GIFT set GIFT_SEND_STATUS='1',GIFT_SEND_TIME =to_char(SYSDATE,'YYYYMMDDHH24MISS')"+
		"where  ORDER_ID=?";
		update(sql,list);
		
	}

}
