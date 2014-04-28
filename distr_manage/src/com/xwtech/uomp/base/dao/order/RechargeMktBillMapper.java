package com.xwtech.uomp.base.dao.order;

import java.util.List;

import com.xwtech.uomp.base.pojo.order.OrderGiftBean;
import com.xwtech.uomp.base.pojo.order.OrderPayBean;
import com.xwtech.uomp.base.pojo.order.RechargeMktBillBean;



public interface RechargeMktBillMapper {
   
    List<RechargeMktBillBean> queryRechargeMktBill();
    List<RechargeMktBillBean> queryRechargeMktAllBill();
    List<OrderGiftBean>  queryOrderGift();
    List<OrderPayBean>  queryOrderPay();
    List<OrderPayBean>  queryOrderPaySuccess();
    List<OrderPayBean>  queryOrderPayAllSuccess();
    void updateOrderGift(OrderGiftBean orderGiftBean );
}