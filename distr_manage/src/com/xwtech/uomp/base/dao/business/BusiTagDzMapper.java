package com.xwtech.uomp.base.dao.business;

import com.xwtech.uomp.base.pojo.business.BusiTagDzBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-24 下午02:57:40
 */
public interface BusiTagDzMapper {
    
    int insert(BusiTagDzBean record);

    int insertSelective(BusiTagDzBean record);
    
    void deleteByBusiNum(String busiNum);
}