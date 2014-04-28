package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessTypeDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeDzBean;
import com.xwtech.uomp.base.service.business.IBusinessTypeDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-2 下午02:26:49
 */
@Service("businessTypeDzService")
public class BusinessTypeDzServiceImpl implements IBusinessTypeDzService {

    @Autowired
    BusinessTypeDzMapper businessTypeDzMapper;
    
    public void insert(BusinessTypeDzBean record) {
	businessTypeDzMapper.insert(record);
	
    }

    public void insertSelective(BusinessTypeDzBean record) {
	businessTypeDzMapper.insertSelective(record);
	
    }

    @Transactional
    public void batchInsert(List<BusinessTypeDzBean> businessTypeDzBeans, String busiNum) {
	for (BusinessTypeDzBean businessTypeDzBean : businessTypeDzBeans) {
	    businessTypeDzBean.setBusiNum(busiNum);
	    businessTypeDzMapper.insertSelective(businessTypeDzBean);
	}
    }

    public void deleteBusiTypeDz(BusinessInfoBean businessInfoBean) {
	businessTypeDzMapper.deleteBusiTypeDz(businessInfoBean);
	
    }

    public void deleteByBusiNum(String busiNum) {
	businessTypeDzMapper.deleteByBusiNum(busiNum);
	
    }

    public List<BusinessTypeBean> queryBusiTypeByBusiNum(Map<String, String> param) {
	return businessTypeDzMapper.queryBusiTypeByBusiNum(param);
    }

}
