package com.xwtech.uomp.base.service.business;

import java.util.List;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoAreaDzBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:52:06
 */
public interface IRelationBusinessInfoAreaDzService {
    
    void deleteByPrimaryKey(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    void insert(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    void insertSelective(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    void batchInsert(List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans,
	    RelationBusinessInfoBean relationBusinessInfoBean);

    void deleteByBusiNum(String busiNum);

    void deleteRelaBusiAreaDz(BusinessInfoBean businessInfoBean);
}