package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessExattrBean;

/**
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-5 下午03:07:42
 */
public interface BusinessExattrMapper {
    List<BusinessExattrBean> queryByName(Map<String, String> map);

    int queryBusinessExtraCount(Map<String, String> map);

    void updateBusinessExattrBean(BusinessExattrBean businessExattrBean);

    void addBusiExtra(BusinessExattrBean businessExattrBean);

    BusinessExattrBean queryByKey(String attrKey);

    void deleteBusiExtraByAttrKey(String attrKey);
}