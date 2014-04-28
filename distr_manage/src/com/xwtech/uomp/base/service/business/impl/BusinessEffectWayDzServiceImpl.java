package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessEffectWayDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessEffectWayBean;
import com.xwtech.uomp.base.service.business.IBusinessEffectWayDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:28:04
 */
@Service("businessEffectWayDzService")
public class BusinessEffectWayDzServiceImpl implements IBusinessEffectWayDzService {

    @Autowired
    BusinessEffectWayDzMapper businessEffectWayDzMapper;
    
    public void insert(BusinessEffectWayBean businessEffectWayBean) {
	businessEffectWayDzMapper.insert(businessEffectWayBean);

    }

    public void insertSelective(BusinessEffectWayBean businessEffectWayBean) {
	businessEffectWayDzMapper.insertSelective(businessEffectWayBean);

    }

    @Transactional
    public void batchInsert(List<BusinessEffectWayBean> businessEffectWayBeans, String busiNum) {
	// TODO
	for (int i = 0; i < businessEffectWayBeans.size(); i++) {
	    BusinessEffectWayBean bean = businessEffectWayBeans.get(i);
	    bean.setBusiNum(busiNum);
	    businessEffectWayDzMapper.insert(bean);
	}
	
    }

    public void deleteEffectWayByBusiNum(String busiNum) {
	businessEffectWayDzMapper.deleteEffectWayByBusiNum(busiNum);
	
    }

}
