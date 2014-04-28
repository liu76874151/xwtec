package com.xwtech.uomp.base.dao.brand;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.brand.BrandBean;

/**
 * 品牌管理
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-19 下午03:46:56
 */
public interface BrandMapper {

	List<BrandBean> queryBrandList(Map<String, String> map);
	List<BrandBean> queryBrandNameById(Map<String, Object> map);

	int queryBrandCount(Map<String, String> map);

	BrandBean queryOneBrand(String brandNum);
	
	void saveBrand(BrandBean brandBean);
	
	void deleteBrandbyBrandNum(String brandNum);
	
	void updateBrand(BrandBean brandBean);
	
	int queryJbNumCount(BrandBean brandBean);
	
	String queryNewJbNum(String jbNum);
	
	BrandBean queryBrandValidate(Map<String, String> param);

	List<BrandBean> queryBrandListEx(Map<String, Object> param);
}
