package com.xwtech.uomp.base.dao.order;

import java.util.List;

import com.xwtech.uomp.base.pojo.order.MarketOrderBean;

public interface IMarketOrderDao {
List<MarketOrderBean> queryMarketOrderList(int flag);
void updateMarketOrder(List<Object[]> list);
}
