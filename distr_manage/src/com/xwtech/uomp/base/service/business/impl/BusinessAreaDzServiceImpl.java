package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessAreaDzMapper;
import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.pojo.business.BusinessAreaDzBean;
import com.xwtech.uomp.base.service.business.IBusinessAreaDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-26 下午02:49:17
 */
@Service("businessAreaDzService")
public class BusinessAreaDzServiceImpl implements IBusinessAreaDzService {

    @Autowired
    BusinessAreaDzMapper businessAreaDzMapper;
    
    public void insert(BusinessAreaDzBean record) {
	businessAreaDzMapper.insert(record);
    }

    public void insertSelective(BusinessAreaDzBean record) {
	businessAreaDzMapper.insertSelective(record);
    }

    @Transactional
    public void batchInsert(List<BusinessAreaDzBean> businessAreaDzBeans, String busiNum) {
	
	//TODO
	for (int i = 0; i < businessAreaDzBeans.size(); i++) {
	    BusinessAreaDzBean bean = businessAreaDzBeans.get(i);
	    bean.setBusiNum(busiNum);
	    businessAreaDzMapper.insert(bean);
	}
	
    }

    public void deleteByBusiNum(String busiNum) {
	businessAreaDzMapper.deleteByBusiNum(busiNum);
	
    }

    public List<AreaDABean> queryAreaByBusiNum(Map<String, String> param) {
	return businessAreaDzMapper.queryAreaByBusiNum(param);
    }

}
