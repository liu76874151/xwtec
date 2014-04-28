package com.xwtech.uomp.base.service.brand.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.brand.BrandSortMapper;
import com.xwtech.uomp.base.pojo.brand.BrandSortBean;
import com.xwtech.uomp.base.service.brand.IBrandSortService;

/**
 * 
 * This class is used for ...
 * 
 * @author unique
 * @version 1.0, 2014-1-23 上午10:12:40
 */
@Service("brandSortService")
public class BrandSortServiceImpl implements IBrandSortService {

    @Autowired
    BrandSortMapper brandSortMapper;

    public int deleteByPrimaryKey(Long brandId) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int insert(BrandSortBean record) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int insertSelective(BrandSortBean record) {
	// TODO Auto-generated method stub
	return 0;
    }

    public BrandSortBean selectByPrimaryKey(Long brandId) {
	// TODO Auto-generated method stub
	return null;
    }

    public int updateByPrimaryKey(BrandSortBean record) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int updateByPrimaryKeySelective(BrandSortBean record) {
	// TODO Auto-generated method stub
	return 0;
    }

    public String queryBrandId(String bossCode) {
	String brandstr = "";
	if (!bossCode.equals("99") && !bossCode.equals("")) {

	    List<BrandSortBean> brandSortBeans = brandSortMapper.queryBrandId(bossCode);

	    if (brandSortBeans != null && brandSortBeans.size() > 0)
		for (int i = 0; i < brandSortBeans.size(); i++) {
		    brandstr += brandSortBeans.get(i).getBrandId() + ",";
		}
	} else {
	    brandstr = "1,2,3,";
	}

	return brandstr;
    }

}
