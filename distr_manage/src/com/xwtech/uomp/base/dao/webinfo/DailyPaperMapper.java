package com.xwtech.uomp.base.dao.webinfo;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.webinfo.DailyPaperBean;



public interface DailyPaperMapper {
     List<DailyPaperBean> queryDailyPaper(Map<String, String> map);
     DailyPaperBean queryOneDailyPaper(Map<String, String> map);
    int queryDailyPaperCount(Map<String, String> map);
    int insertSelective(DailyPaperBean record);
    void updateDailyPaperBean(Map<String, String> map);
    void deleteDailyPaperBean(Map<String, String> map);
}