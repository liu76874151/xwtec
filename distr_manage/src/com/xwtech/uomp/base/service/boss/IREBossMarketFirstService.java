package com.xwtech.uomp.base.service.boss;

import com.xwtech.uomp.base.pojo.Page;

public interface IREBossMarketFirstService {
	public Page queryCrmInfo(String city) ;
	
	public String queryCrmBrandInfo(String crmfirstCode);
}
