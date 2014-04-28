package com.xwtech.uomp.base.dao.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessEffectWayBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:21:50
 */
public interface BusinessEffectWayDzMapper {
    
    int insert(BusinessEffectWayBean record);

    int insertSelective(BusinessEffectWayBean record);

    void batchInsert(List<BusinessEffectWayBean> newList);

    void deleteEffectWayByBusiNum(String busiNum);
}