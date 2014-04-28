package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.pojo.business.BusinessAreaDzBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午04:44:51
 */
public interface BusinessAreaDzMapper {

    int insert(BusinessAreaDzBean record);

    int insertSelective(BusinessAreaDzBean record);

    void deleteByBusiNum(String busiNum);
    
    List<AreaDABean> queryAreaByBusiNum(Map<String, String> param);
}