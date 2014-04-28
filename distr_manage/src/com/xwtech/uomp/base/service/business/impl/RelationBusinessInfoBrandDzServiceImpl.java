package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.RelationBusinessInfoBrandDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBrandDzBean;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoBrandDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:56:41
 */
@Service("relationBusinessInfoBrandDzService")
public class RelationBusinessInfoBrandDzServiceImpl implements IRelationBusinessInfoBrandDzService {

    @Autowired
    RelationBusinessInfoBrandDzMapper relationBusinessInfoBrandDzMapper;
    
    public void deleteByPrimaryKey(RelationBusinessInfoBrandDzBean relationBusinessInfoBrandDzBean) {
	relationBusinessInfoBrandDzMapper.deleteByPrimaryKey(relationBusinessInfoBrandDzBean);
    }

    public void insert(RelationBusinessInfoBrandDzBean record) {
	relationBusinessInfoBrandDzMapper.insert(record);
    }

    public void insertSelective(RelationBusinessInfoBrandDzBean record) {
	relationBusinessInfoBrandDzMapper.insertSelective(record);
    }

    @Transactional
    public void batchInsert(List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans,
	    RelationBusinessInfoBean relationBusinessInfoBean) {
	for (RelationBusinessInfoBrandDzBean relationBusinessInfoBrandDzBean : relationBusinessInfoBrandDzBeans) {
	    relationBusinessInfoBrandDzBean.setBusiNum(relationBusinessInfoBean.getBusiNum());
	    relationBusinessInfoBrandDzBean.setRelativeBusi(relationBusinessInfoBean.getRelativeBusi());
	    relationBusinessInfoBrandDzMapper.insertSelective(relationBusinessInfoBrandDzBean);
	}
	
    }

    public void deleteByBusiNum(String busiNum) {
	relationBusinessInfoBrandDzMapper.deleteByBusiNum(busiNum);
	
    }

    public void deleteRelaBusiBrandDz(BusinessInfoBean businessInfoBean) {
	relationBusinessInfoBrandDzMapper.deleteRelaBusiBrandDz(businessInfoBean);
	
    }

}
