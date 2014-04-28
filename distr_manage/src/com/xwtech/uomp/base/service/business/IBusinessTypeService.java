package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-7 上午11:22:17
 */
public interface IBusinessTypeService {

	int insert(BusinessTypeBean record);

	BusinessTypeBean insertSelective(BusinessTypeBean record);

	Page queryBusinessTypeList(Map<String, String> param);

	void updateByPrimaryKey(BusinessTypeBean businessTypeBean);

	BusinessTypeBean queryBusiTypeBykey(BusinessTypeBean businessTypeBean);

	List<BusinessTypeBean> queryParentBusiType(Map<String, Object> param);
	
	List<BusinessTypeBean> queryBusiType(Map<String, Object> param);
	
	void updateByBusiTypeNum(BusinessTypeBean businessTypeBean);

	List<BusinessSortDzBean> deleteBusiType(String[] jbNums);
}