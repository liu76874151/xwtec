package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusiTagDzMapper;
import com.xwtech.uomp.base.pojo.business.BusiTagDzBean;
import com.xwtech.uomp.base.service.business.IBusinessTagDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-24 下午02:55:10
 */
@Service("businessTagDzService")
public class BusinessTagDzServiceImpl implements IBusinessTagDzService {

    @Autowired
    BusiTagDzMapper busiTagDzMapper;
    
    @Transactional
    public void batchInsert(List<BusiTagDzBean> busiTagDzBeans, String busiNum) {
	if (busiTagDzBeans!=null && busiTagDzBeans.size()>0) {
	    for (BusiTagDzBean busiTagDzBean : busiTagDzBeans) {
		busiTagDzBean.setBusiNum(busiNum);
		insertSelective(busiTagDzBean);
		//TODO
	    }
	}
    }

    public void insert(BusiTagDzBean record) {
	busiTagDzMapper.insert(record);
	
    }

    public void insertSelective(BusiTagDzBean record) {
	busiTagDzMapper.insertSelective(record);
    }

    public void deleteByBusiNum(String busiNum) {
	
	busiTagDzMapper.deleteByBusiNum(busiNum);
    }

}
