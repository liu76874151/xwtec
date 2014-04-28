package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-6 下午02:52:10
 */
public interface IBusinessExattrDzService {
    List<BusinessExattrDzBean> queryBusiExtraDzByAttrKey(String attrKey);

    void deleteBusiExtraDzByAttrKey(String attrKey);

    void batchInsert(List<BusinessExattrDzBean> businessExattrDzBeans, String busiNum);

    void deleteByBusiNum(String busiNum);
}
