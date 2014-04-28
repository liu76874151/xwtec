package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketTemplateBean;

/**
 * 协议模板
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午05:13:47
 */
public interface MarketTemplateMapper {
    List<MarketTemplateBean> queryMarketTemplate(Map<String, Object> paramMap);
    int queryMarketTemplateCount(Map<String, Object> paramMap);
    void saveMarketTemplate(Map<String, String> paramMap);
    void updateMarketTemplate(Map<String, String> paramMap);
    void deleteMarketTemplate(String templateId);
    MarketTemplateBean queryOneMarketTemplate(Map<String, String> paramMap);
    int isExistName( Map<String, String> param);
}