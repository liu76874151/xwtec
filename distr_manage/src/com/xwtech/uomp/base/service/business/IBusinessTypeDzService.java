package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-2 下午02:26:19
 */
public interface IBusinessTypeDzService {

    void insert(BusinessTypeDzBean record);
    void insertSelective(BusinessTypeDzBean record);
    void batchInsert(List<BusinessTypeDzBean> businessTypeDzBeans, String busiNum);
    void deleteBusiTypeDz(BusinessInfoBean businessInfoBean);
    void deleteByBusiNum(String busiNum);
    List<BusinessTypeBean> queryBusiTypeByBusiNum(Map<String, String> param);
}
