package com.xwtech.uomp.base.service.webinfo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.webinfo.DailyPaperMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.webinfo.DailyPaperBean;
import com.xwtech.uomp.base.service.webinfo.IDailyPaperService;
@Service("dailyPaperServiceImpl")
public class DailyPaperServiceImpl implements IDailyPaperService {
@Autowired
DailyPaperMapper  dailyPaperMapper;
	public void deleteDailyPaperBean(Map<String, String> map) {
		dailyPaperMapper.deleteDailyPaperBean(map);		
	}

	public int insertSelective(DailyPaperBean record) {
		return dailyPaperMapper.insertSelective(record);
	}

	public Page queryDailyPaper(Map<String, String> map) {
		Page page =new Page();
		List<DailyPaperBean> list=dailyPaperMapper.queryDailyPaper(map);
		int count=dailyPaperMapper.queryDailyPaperCount(map);
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public void updateDailyPaperBean(Map<String, String> map) {
		dailyPaperMapper.updateDailyPaperBean(map);		
	}

	public DailyPaperBean queryOneDailyPaper(Map<String, String> map) {
		return dailyPaperMapper.queryOneDailyPaper(map);
	}

}
