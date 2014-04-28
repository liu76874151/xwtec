package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusinessBrandDzMapper;
import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.pojo.business.BusinessBrandDzBean;
import com.xwtech.uomp.base.service.business.IBusinessBrandDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:55:37
 */
@Service("businessBrandDzService")
public class BusinessBrandDzServiceImpl implements IBusinessBrandDzService {

    @Autowired
    BusinessBrandDzMapper businessBrandDzMapper;
    public int insert(BusinessBrandDzBean record) {
	return businessBrandDzMapper.insert(record);
    }

    public int insertSelective(BusinessBrandDzBean record) {
	return businessBrandDzMapper.insertSelective(record);
    }

    public void batchInsert(List<BusinessBrandDzBean> businessBrandDzBeans, String busiNum) {
	for (int i = 0; i < businessBrandDzBeans.size(); i++) {
	    BusinessBrandDzBean bean = businessBrandDzBeans.get(i);
	    bean.setBusiNum(busiNum);
	    businessBrandDzMapper.insert(bean);
	}
	
    }

    public void deleteByBusiNum(String busiNum) {
	businessBrandDzMapper.deleteByBusiNum(busiNum);
	
    }

    public List<BrandBean> queryBrandByBusiNum(Map<String, String> bean) {
	return businessBrandDzMapper.queryBrandByBusiNum(bean);
    }

}
