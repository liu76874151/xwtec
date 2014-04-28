package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessEffectWayBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:26:28
 */
public interface IBusinessEffectWayDzService {

    void insert(BusinessEffectWayBean businessEffectWayBean);
    
    void insertSelective(BusinessEffectWayBean businessEffectWayBean);

    void batchInsert(List<BusinessEffectWayBean> businessEffectWayBeans, String busiNum);

    void deleteEffectWayByBusiNum(String busiNum);
}
