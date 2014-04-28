package com.xwtech.uomp.base.service.order;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;

public interface IMarketOrderSerivce {
	/**
     * 营销案订单查询
     * @return
     */
Page queryMarketOrderList(Map<String, Object> map);
/**
 * 营销案订单统计
 * @param paramMap
 * @return
 */
Page queryMarketOrderCountList(Map<String,Object> paramMap);
}
