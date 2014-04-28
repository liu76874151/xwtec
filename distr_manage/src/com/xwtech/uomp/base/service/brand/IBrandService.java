package com.xwtech.uomp.base.service.brand;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.brand.BrandBean;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-19 下午03:44:19
 */
public interface IBrandService {
	Page queryBrandList(Map<String, String> paramMap);

	BrandBean queryOneBrand(String brandNum);

	void saveBrand(BrandBean brandBean);
	
	void deleteBrandbyBrandNum(String brandNum);
	
	void updateBrand(BrandBean brandBean);
	
	int queryJbNumCount(BrandBean brandBean);
	
	String queryNewJbNum(String jbNum);
	
	BrandBean queryBrandValidate(Map<String, String> param);

	List<BrandBean> queryBrandListEx(Map<String, Object> param);

	List<BrandBean> queryBrandNameById(Map<String, Object> map);
}
