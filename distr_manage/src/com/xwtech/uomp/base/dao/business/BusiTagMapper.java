package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusiTagBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-23 下午03:05:05
 */
public interface BusiTagMapper {
    
    int deleteByPrimaryKey(Long tagId);

    int insert(BusiTagBean record);

    int insertSelective(BusiTagBean record);

    BusiTagBean selectByPrimaryKey(Long tagId);

    int updateByPrimaryKeySelective(BusiTagBean record);

    int updateByPrimaryKey(BusiTagBean record);
    
    List<BusiTagBean> queryBusiTagList(Map<String, String> param);

    void deleteBusinessTag(Map<String, Object> param);
    
    BusiTagBean selectBusiTagBean(Map<String, String> param);
}