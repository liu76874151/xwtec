package com.xwtech.uomp.base.dao.business;


import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.pojo.business.BusinessBrandDzBean;

/*
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午04:59:11
 */
public interface BusinessBrandDzMapper {

    int insert(BusinessBrandDzBean record);

    int insertSelective(BusinessBrandDzBean record);

    void deleteByBusiNum(String busiNum);

    List<BrandBean> queryBrandByBusiNum(Map<String, String> bean);
}