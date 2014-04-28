package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 下午05:03:33
 */
public interface RelationBusinessInfoMapper {

    int insert(RelationBusinessInfoBean record);

    int insertSelective(RelationBusinessInfoBean record);

    int updateByPrimaryKeySelective(RelationBusinessInfoBean record);

    int updateByPrimaryKey(RelationBusinessInfoBean record);

    RelationBusinessInfoBean queryRelationInfos(Map<String, String> param);

    void deleteRelaBusiness(BusinessInfoBean businessInfoBean);

    void deleteByBusiNum(String busiNum);
    
    List<BusinessInfoBean> queryRelaBusiDetail(Map<String, String> param);
    
    int queryRelaBusiDetailCount(Map<String, String> param);
}