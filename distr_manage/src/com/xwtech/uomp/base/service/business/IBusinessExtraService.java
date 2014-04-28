package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessExattrBean;
import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-5 下午03:21:53
 */
public interface IBusinessExtraService {

    Page queryByName(Map<String, String> map);

    void updateBusinessExattrBean(BusinessExattrBean businessExattrBean);

    void addBusiExtra(BusinessExattrBean businessExattrBean);

    BusinessExattrBean queryBusinessExtraByKey(String attrKey);

    List<BusinessExattrDzBean> deleteBusiExtraByAttrKey(String attrKey);
}
