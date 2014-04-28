package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-7 上午11:22:17
 */
public interface BusinessTypeMapper {

	int insert(BusinessTypeBean record);

	int insertSelective(BusinessTypeBean record);

	List<BusinessTypeBean> queryBusinessTypeList(Map<String, String> param);

	int queryBusinessTypeCount(Map<String, String> param);

	BusinessTypeBean queryBusiTypeBykey(BusinessTypeBean businessTypeBean);

	List<BusinessTypeBean> queryParentBusiType(Map<String, Object> param);
	
	String getMaxjbNum(Map<String, String> param);
	
	List<BusinessTypeBean> queryBusiType(Map<String, Object> param);
	
	void updateByBusiTypeNum(BusinessTypeBean businessTypeBean);

	void deleteBusiType(String jbNum);
}