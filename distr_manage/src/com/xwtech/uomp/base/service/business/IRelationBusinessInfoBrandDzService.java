package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBrandDzBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:56:02
 */
public interface IRelationBusinessInfoBrandDzService {
    void deleteByPrimaryKey(RelationBusinessInfoBrandDzBean relationBusinessInfoBrandDzBean);

    void insert(RelationBusinessInfoBrandDzBean record);

    void insertSelective(RelationBusinessInfoBrandDzBean record);

    void batchInsert(List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans,
	    RelationBusinessInfoBean relationBusinessInfoBean);

    void deleteByBusiNum(String busiNum);

    void deleteRelaBusiBrandDz(BusinessInfoBean businessInfoBean);
}