package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午01:45:46
 */
public interface IBusinessSortDzService {

    List<BusinessSortDzBean> queryByBusiTypeNum(String busiTypeNum);

    List<BusinessSortDzBean> queryByjbNum(String jbNum);
}
