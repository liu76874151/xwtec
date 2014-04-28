package com.xwtech.uomp.base.dao.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;

/**
 * 业务信息持久化
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-20 下午03:27:29
 */
public interface IBusinessInfoDao {
    
    void sortBusinessInfo(List<BusinessInfoBean> list);
    
    void sortBaseBusinessInfo(List<BusinessInfoBean> list);
}
