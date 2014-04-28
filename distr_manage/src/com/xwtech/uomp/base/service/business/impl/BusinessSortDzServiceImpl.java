package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusinessSortDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;
import com.xwtech.uomp.base.service.business.IBusinessSortDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午01:46:08
 */

@Service("businessSortDzService")
public class BusinessSortDzServiceImpl implements IBusinessSortDzService {

    @Autowired
    BusinessSortDzMapper businessSortDzMapper;
    
    public List<BusinessSortDzBean> queryByBusiTypeNum(String busiTypeNum) {
	
	return businessSortDzMapper.queryByBusiTypeNum(busiTypeNum);
    }

    public List<BusinessSortDzBean> queryByjbNum(String jbNum) {
	
	return businessSortDzMapper.queryByjbNum(jbNum);
    }

}
