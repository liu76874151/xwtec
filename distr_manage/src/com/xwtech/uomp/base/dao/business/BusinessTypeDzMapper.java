package com.xwtech.uomp.base.dao.business;

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
 *       1.0, 2013-12-2 下午02:20:20
 */
public interface BusinessTypeDzMapper {

    int insert(BusinessTypeDzBean record);
    int insertSelective(BusinessTypeDzBean record);
    void deleteBusiTypeDz(BusinessInfoBean businessInfoBean);
    void deleteByBusiNum(String busiNum);
    List<BusinessTypeBean> queryBusiTypeByBusiNum(Map<String, String> param);
}