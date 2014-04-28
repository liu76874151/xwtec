package com.xwtech.uomp.base.dao.order;

import java.util.List;

import com.xwtech.uomp.base.pojo.order.OrderPayBean;

public interface IOrderPayDao {
List<OrderPayBean> queryOrderPayList();
List<OrderPayBean> queryOrderPaySuccessList();

void updateOrderPay(List<Object[]> list);
}
