package com.xwtech.uomp.base.service.business;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessDeductBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-6 下午04:42:53
 */
public interface IBusinessDeductService {
	
	Page queryBusinessDeductList(Map<String, String> param);
	void deleteByPrimaryKey(String deductNum);
	void updateByPrimaryKeySelective(BusinessDeductBean businessDeductBean);
	void updateByPrimaryKey(BusinessDeductBean businessDeductBean);
	void addBusiDeduct(BusinessDeductBean businessDeductBean);
	BusinessDeductBean queryBusiDeductBykey(String deductNum);
}