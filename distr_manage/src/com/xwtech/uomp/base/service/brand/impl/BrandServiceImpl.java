package com.xwtech.uomp.base.service.brand.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.brand.BrandMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.service.brand.IBrandService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-19 下午03:44:41
 */
@Service @Qualifier("brandService")
public class BrandServiceImpl implements IBrandService {

	@Autowired
	BrandMapper brandMapper;

	public Page queryBrandList(Map<String, String> paramMap) {

		List<BrandBean> list = brandMapper.queryBrandList(paramMap);
		int totalRecord = brandMapper.queryBrandCount(paramMap);

		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(totalRecord);

        return page;
	}

	/**
	 * 根据品牌编码查询
	 */
	public BrandBean queryOneBrand(String brandNum) {
		
		return brandMapper.queryOneBrand(brandNum);
	}

	/**
	 * 品牌添加
	 */
	public void saveBrand(BrandBean brandBean) {
	    String jbNum = brandBean.getJbNum();
	    int count = this.queryJbNumCount(brandBean);
	  //级别编码
	        String newJbNum = null;
	        int jb = 2;//默认级别
	        if (null == jbNum) {
	            if (count < 10) {
	        	if ((count + 1)<10) {
	        	    newJbNum = "0" + (count + 1);
			}
	            } else {
	                newJbNum = "" + (count + 1);
	            }
	        } else {
	            newJbNum = this.queryNewJbNum(jbNum);
	        }

	        if (!(null == jbNum)) {
	            jb = newJbNum.length() / 2;//当前级别
	        }
	    brandBean.setJb(jb);
	    brandBean.setJbNum(newJbNum);
	    brandMapper.saveBrand(brandBean);
	}

	public void deleteBrandbyBrandNum(String brandNum) {
		brandMapper.deleteBrandbyBrandNum(brandNum);
	}
	
	public void updateBrand(BrandBean brandBean){
		brandMapper.updateBrand(brandBean);
	}

	public int queryJbNumCount(BrandBean brandBean) {
	    return brandMapper.queryJbNumCount(brandBean);
	}

	public String queryNewJbNum(String jbNum) {
	    return brandMapper.queryNewJbNum(jbNum);
	}

	public BrandBean queryBrandValidate(Map<String, String> param) {
	    return brandMapper.queryBrandValidate(param);
	}

	public List<BrandBean> queryBrandListEx(Map<String, Object> param) {
	    if (param!=null) {
		return brandMapper.queryBrandListEx(param);
	    }
	    return null;
	}

	public List<BrandBean> queryBrandNameById(Map<String, Object> map) {
	    
	    return brandMapper.queryBrandNameById(map);
	}
}
