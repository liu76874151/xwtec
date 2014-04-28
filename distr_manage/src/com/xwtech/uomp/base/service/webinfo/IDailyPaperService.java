package com.xwtech.uomp.base.service.webinfo;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.webinfo.DailyPaperBean;

public interface IDailyPaperService {
		Page  queryDailyPaper(Map<String, String> map);
		DailyPaperBean queryOneDailyPaper(Map<String, String> map);
	    int insertSelective(DailyPaperBean record);
	    void updateDailyPaperBean(Map<String, String> map);
	    void deleteDailyPaperBean(Map<String, String> map);
}
