package com.xwtech.uomp.base.dao.brand;

import java.util.List;

import com.xwtech.uomp.base.pojo.brand.BrandSortBean;


/**
 * 
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-23 上午10:10:04
 */
public interface BrandSortMapper {
    
    int deleteByPrimaryKey(Long brandId);

    int insert(BrandSortBean record);

    int insertSelective(BrandSortBean record);

    BrandSortBean selectByPrimaryKey(Long brandId);

    int updateByPrimaryKeySelective(BrandSortBean record);

    int updateByPrimaryKey(BrandSortBean record);

    List<BrandSortBean> queryBrandId(String bossCode);
}