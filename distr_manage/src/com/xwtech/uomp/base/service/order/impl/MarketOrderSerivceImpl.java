package com.xwtech.uomp.base.service.order.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.order.MarketOrderMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.order.MarketOrderBean;
import com.xwtech.uomp.base.service.order.IMarketOrderSerivce;
@Service("marketOrderSerivce")
public class MarketOrderSerivceImpl implements IMarketOrderSerivce {
	@Autowired
	MarketOrderMapper marketOrderMapper;
	public Page queryMarketOrderList(Map<String, Object> map) {
		List<MarketOrderBean> list=marketOrderMapper.queryMarketOrderList(map);
		int count=marketOrderMapper.queryMarketOrderListCount(map);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	public Page queryMarketOrderCountList(Map<String, Object> paramMap) {
		 List<Map<String, String>>list=marketOrderMapper.queryMarketOrderCountList(paramMap);
		 int count=marketOrderMapper.queryMarketOrderCountListCount(paramMap);
		 Page page=new Page();
		 page.setRecords(list);
		 page.setTotalRecord(count);
		 return page;
	}

}
