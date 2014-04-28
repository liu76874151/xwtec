package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusiTagDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-24 下午02:54:28
 */
public interface IBusinessTagDzService {

    void batchInsert(List<BusiTagDzBean> busiTagDzBeans, String busiNum);
    
    void insert(BusiTagDzBean record);

    void insertSelective(BusiTagDzBean record);
    
    void deleteByBusiNum(String busiNum);
}
