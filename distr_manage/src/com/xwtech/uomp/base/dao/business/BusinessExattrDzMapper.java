package com.xwtech.uomp.base.dao.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-6 下午02:53:06
 */
public interface BusinessExattrDzMapper {
    
	List<BusinessExattrDzBean> queryBusiExtraDzByAttrKey(String attrKey);
	
	void deleteBusiExtraDzByAttrKey(String attrKey);
	
	void insert(BusinessExattrDzBean businessExattrDzBean);

	void deleteByBusiNum(String busiNum);
}