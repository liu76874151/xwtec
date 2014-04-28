package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.pojo.business.BusinessBrandDzBean;

/*
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:53:34
 */
public interface IBusinessBrandDzService {

    int insert(BusinessBrandDzBean record);

    int insertSelective(BusinessBrandDzBean record);

    void batchInsert(List<BusinessBrandDzBean> businessBrandDzBeans, String busiNum);

    void deleteByBusiNum(String busiNum);

    List<BrandBean> queryBrandByBusiNum(Map<String, String> bean);
}
