package com.xwtech.uomp.base.service.boss.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xwtech.uomp.base.dao.boss.REBossMarketFirstMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.boss.REBossMarketFirstBean;
import com.xwtech.uomp.base.service.boss.IREBossMarketFirstService;
/**
 *@ClassName:REBossMarketFirstService.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-28 time：下午04:15:32
 *@version 1.0
 */
@Service("rebossMarketFirstService")
public class REBossMarketFirstService implements IREBossMarketFirstService {
	@Autowired
	REBossMarketFirstMapper rebossMarketFirstMapper;
	public Page queryCrmInfo(String city) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("city", city);
		List<REBossMarketFirstBean> list =rebossMarketFirstMapper.queryCrmInfo(param);
		Page page=new Page();
		page.setRecords(list);
		return page;
	}
	public String queryCrmBrandInfo(String crmfirstCode) {
		return  rebossMarketFirstMapper.queryCrmBrandInfo(crmfirstCode);
	}

}
