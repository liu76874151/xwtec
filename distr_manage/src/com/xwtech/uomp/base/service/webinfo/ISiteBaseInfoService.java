package com.xwtech.uomp.base.service.webinfo;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.webinfo.SiteBaseInfoBean;

public interface ISiteBaseInfoService {
	  Page queryWebInfoList(Map<String, String> map);
	  
	  int updateByPrimaryKey(SiteBaseInfoBean record);
	   
	   SiteBaseInfoBean selectByPrimaryKey(int channelNum);
}
