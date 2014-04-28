package com.xwtech.uomp.base.service.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusiTagBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-23 下午03:16:31
 */
public interface IBusinessTagService {

    void deleteByPrimaryKey(Long tagId);

    void insert(BusiTagBean record);

    void insertSelective(BusiTagBean record);

    BusiTagBean selectByPrimaryKey(Long tagId);

    void updateByPrimaryKeySelective(BusiTagBean record);

    void updateByPrimaryKey(BusiTagBean record);
    
    List<BusiTagBean> queryBusiTagList(Map<String, String> param);

    void deleteBusinessTag(Map<String, Object> param);
    
    BusiTagBean selectBusiTagBean(Map<String, String> param);

}
