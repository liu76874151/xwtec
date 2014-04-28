package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.pojo.business.BusinessAreaDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:47:13
 */
public interface IBusinessAreaDzService {
    void insert(BusinessAreaDzBean record);

    void insertSelective(BusinessAreaDzBean record);

    void batchInsert(List<BusinessAreaDzBean> businessAreaDzBeans, String busiNum);

    void deleteByBusiNum(String busiNum);
    
    List<AreaDABean> queryAreaByBusiNum(Map<String, String> param);
}
