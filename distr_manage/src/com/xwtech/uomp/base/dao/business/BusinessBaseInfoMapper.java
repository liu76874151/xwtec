package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusinessBaseInfoBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-21 上午11:03:41
 */
public interface BusinessBaseInfoMapper {
    
    int deleteByPrimaryKey(String fBusiNum);

    int updateByPrimaryKey(BusinessBaseInfoBean record);

    List<Map<String, Object>> queryBusiBaseInfoTree();
    
    void insertSelective(BusinessBaseInfoBean businessBaseInfoBean);
    
    void insert(BusinessBaseInfoBean businessBaseInfoBean);

    int queryJbNumCount(BusinessBaseInfoBean businessBaseInfoBean);

    String queryNewJbNum(String jbNum);
    
    BusinessBaseInfoBean queryBusiInfoBynum(String pkid);

    void updateByPrimaryKeySelective(BusinessBaseInfoBean businessBaseInfoBean);

    void deleteBusinessBaseInfo(String busiNum);
}