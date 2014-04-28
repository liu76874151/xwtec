package com.xwtech.uomp.base.dao.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午01:43:31
 */
public interface BusinessSortDzMapper {

    int insert(BusinessSortDzBean record);

    int insertSelective(BusinessSortDzBean record);

    int updateByPrimaryKeySelective(BusinessSortDzBean record);

    int updateByPrimaryKey(BusinessSortDzBean record);
    
    List<BusinessSortDzBean> queryByBusiTypeNum(String busiTypeNum);

    List<BusinessSortDzBean> queryByjbNum(String jbNum);
}