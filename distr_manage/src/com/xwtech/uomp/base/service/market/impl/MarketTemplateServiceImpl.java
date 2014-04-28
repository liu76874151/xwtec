package com.xwtech.uomp.base.service.market.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.market.MarketTemplateMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.MarketTemplateBean;
import com.xwtech.uomp.base.service.market.IMarketTemplateService;

/**
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午06:28:22
 */
@Service("marketTemplateService")
public class MarketTemplateServiceImpl implements IMarketTemplateService {

	@Autowired
	MarketTemplateMapper marketTemplateMapper;
	/**
	 * 协议模板查询
	 */
	public List<MarketTemplateBean> queryMarketTemplate(
			Map<String, Object> param) {
		return marketTemplateMapper.queryMarketTemplate(param);
	}
	
	public Page queryMarketTemplateList(Map<String, Object> map) {
		List<MarketTemplateBean> list =marketTemplateMapper.queryMarketTemplate(map);
		for (MarketTemplateBean marketTemplateBean:list) {
			if("0".equals(marketTemplateBean.getState())){
				String isStateAlink = "无效<a href='javascript:component.updateState("+marketTemplateBean.getTemplateId()+",1);' title=''><img src='../../../resource/img/toggle_enabled.gif'/></a>";
				marketTemplateBean.setState(isStateAlink);
			}
			else{
				String isStateAlink = "有效<a href='javascript:component.updateState("+marketTemplateBean.getTemplateId()+",0)' title=''><img src='../../../resource/img/toggle_disabled.gif'/></a>";
				marketTemplateBean.setState(isStateAlink);
			}
			
		}
		int count =marketTemplateMapper.queryMarketTemplateCount(map);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public void saveMarketTemplate(Map<String, String> paramMap) {
		marketTemplateMapper.saveMarketTemplate(paramMap);
		
	}

	public MarketTemplateBean queryOneMarketTemplate(
			Map<String, String> paramMap) {
		MarketTemplateBean marketTemplateBean=marketTemplateMapper.queryOneMarketTemplate(paramMap);
		return marketTemplateBean;
	}

	public void updateMarketTemplate(Map<String, String> paramMap) {
		marketTemplateMapper.updateMarketTemplate(paramMap);
		
	}

	public void deleteMarketTemplate(String templateId) {
		marketTemplateMapper.deleteMarketTemplate(templateId);		
	}

	public int isExistName(Map<String, String> param) {
		return marketTemplateMapper.isExistName(param);
	}

}
