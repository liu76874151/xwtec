package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessExattrDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;
import com.xwtech.uomp.base.service.business.IBusinessExattrDzService;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-6 下午02:52:01
 */
@Service("businessExattrDzService")
public class BusinessExattrDzServiceImpl implements IBusinessExattrDzService {

    @Autowired
    BusinessExattrDzMapper businessExattrDzMapper;

    public List<BusinessExattrDzBean> queryBusiExtraDzByAttrKey(String attrKey) {
	return businessExattrDzMapper.queryBusiExtraDzByAttrKey(attrKey);
    }

    public void deleteBusiExtraDzByAttrKey(String attrKey) {
	businessExattrDzMapper.deleteBusiExtraDzByAttrKey(attrKey);
    }

    @Transactional
    public void batchInsert(List<BusinessExattrDzBean> businessExattrDzBeans, String busiNum) {
	//TODO
	for (BusinessExattrDzBean businessExattrDzBean : businessExattrDzBeans) {
	    businessExattrDzBean.setBusiNum(busiNum);
	    businessExattrDzMapper.insert(businessExattrDzBean);
	}

    }

    public void deleteByBusiNum(String busiNum) {
	businessExattrDzMapper.deleteByBusiNum(busiNum);
	
    }

}
