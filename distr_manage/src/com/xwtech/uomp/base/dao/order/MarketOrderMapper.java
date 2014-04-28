package com.xwtech.uomp.base.dao.order;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.order.MarketOrderBean;

public interface MarketOrderMapper {
    List<MarketOrderBean>  queryMarketOrder();
    List<MarketOrderBean>  queryMarketAllOrder();
    List<MarketOrderBean>  queryMarketOrderSuccess();
    /**
     * 营销案订单查询
     * @return
     */
    List<MarketOrderBean>  queryMarketOrderList(Map<String, Object> map);
    int queryMarketOrderListCount(Map<String, Object> map);
    /**
     * 营销案订单统计
     * @param paramMap
     * @return
     */
    List<Map<String, String>> queryMarketOrderCountList(Map<String,Object> paramMap);
   int queryMarketOrderCountListCount(Map<String,Object> paramMap);
}
