package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketTemplateBean;

/**
 * 协议模板
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午06:27:49
 */
public interface IMarketTemplateService {

	List<MarketTemplateBean> queryMarketTemplate(Map<String, Object> param);

	Page queryMarketTemplateList(Map<String, Object> map);

	void saveMarketTemplate(Map<String, String> paramMap);

	MarketTemplateBean queryOneMarketTemplate(Map<String, String> paramMap);

	void updateMarketTemplate(Map<String, String> paramMap);

	void deleteMarketTemplate(String templateId);

	int isExistName(Map<String, String> param);
}
