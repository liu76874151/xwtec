package com.xwtech.uomp.base.dao.business;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoAreaDzBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 下午05:53:01
 */
public interface RelationBusinessInfoAreaDzMapper {
    int deleteByPrimaryKey(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    int insert(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    int insertSelective(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean);

    void deleteByBusiNum(String busiNum);

    void deleteRelaBusiAreaDz(BusinessInfoBean businessInfoBean);
}