package com.xwtech.uomp.base.dao.business;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBrandDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 下午05:30:07
 */
public interface RelationBusinessInfoBrandDzMapper {
    int deleteByPrimaryKey(RelationBusinessInfoBrandDzBean relationBusinessInfoBrandDzBean);

    int insert(RelationBusinessInfoBrandDzBean record);

    int insertSelective(RelationBusinessInfoBrandDzBean record);

    void deleteByBusiNum(String busiNum);

    void deleteRelaBusiBrandDz(BusinessInfoBean businessInfoBean);
}