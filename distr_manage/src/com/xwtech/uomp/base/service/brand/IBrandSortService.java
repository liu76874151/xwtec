package com.xwtech.uomp.base.service.brand;

import com.xwtech.uomp.base.pojo.brand.BrandSortBean;

/**
 * 
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-23 上午10:11:50
 */
public interface IBrandSortService {

    int deleteByPrimaryKey(Long brandId);

    int insert(BrandSortBean record);

    int insertSelective(BrandSortBean record);

    BrandSortBean selectByPrimaryKey(Long brandId);

    int updateByPrimaryKeySelective(BrandSortBean record);

    int updateByPrimaryKey(BrandSortBean record);
    
    String queryBrandId(String bossCode);
}
