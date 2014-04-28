package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:31:11
 */
public interface IRelationBusinessInfoService {

    void insert(RelationBusinessInfoBean record);

    void insertSelective(RelationBusinessInfoBean record);

    void updateByPrimaryKeySelective(RelationBusinessInfoBean record);

    void updateByPrimaryKey(RelationBusinessInfoBean record);

    void batchInsert(List<RelationBusinessInfoBean> relationBusinessInfoBeans, String busiNum);

    RelationBusinessInfoBean queryRelationInfos(Map<String, String> param);

    void deleteRelaBusiness(BusinessInfoBean businessInfoBean);

    void batchUpdate(List<RelationBusinessInfoBean> relationBusinessInfoBeans, String busiNum);

    void deleteByBusiNum(String busiNum);
    
    Page queryRelaBusiDetail(Map<String, String> param);
}