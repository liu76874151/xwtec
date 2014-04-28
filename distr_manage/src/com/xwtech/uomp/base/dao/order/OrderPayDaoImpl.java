package com.xwtech.uomp.base.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;
import com.xwtech.uomp.base.pojo.order.OrderPayBean;
@Repository("orderPayDao")
public class OrderPayDaoImpl  extends BaseDAO   implements IOrderPayDao {

	public List<OrderPayBean> queryOrderPayList() {
		String sql="select * from JSMSS_ORDER_PAY j where PAY_STATUS !=1 ";
		return (List<OrderPayBean>)queryForList(sql,OrderPayBean.class);
	}

	public void updateOrderPay(List<Object[]> list) {
		String sql = "update JSMSS_ORDER_PAY set PAY_STATUS =? ,PAY_TIME= ? where SERIAL_ORDER_ID = ?" ;
		update(sql,list);
	}

	public List<OrderPayBean> queryOrderPaySuccessList() {
		String sql="select * from JSMSS_ORDER_PAY   where PAY_STATUS =1 ";
		return (List<OrderPayBean>)queryForList(sql,OrderPayBean.class);
	}

}
