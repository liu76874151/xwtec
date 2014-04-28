package com.xwtech.uomp.base.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.RelationBusinessInfoAreaDzMapper;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoAreaDzBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoAreaDzService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:53:22
 */
@Service("relationBusinessInfoAreaDzService")
public class RelationBusinessInfoAreaDzServiceImpl implements IRelationBusinessInfoAreaDzService {

    @Autowired
    RelationBusinessInfoAreaDzMapper relationBusinessInfoAreaDzMapper;
    
    public void deleteByPrimaryKey(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean) {
	relationBusinessInfoAreaDzMapper.deleteByPrimaryKey(relationBusinessInfoAreaDzBean);

    }

    public void insert(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean) {
	relationBusinessInfoAreaDzMapper.insert(relationBusinessInfoAreaDzBean);

    }

    public void insertSelective(RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean) {
	relationBusinessInfoAreaDzMapper.insertSelective(relationBusinessInfoAreaDzBean);

    }

    @Transactional
    public void batchInsert(List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans,
	    RelationBusinessInfoBean relationBusinessInfoBean) {
	for (RelationBusinessInfoAreaDzBean relationBusinessInfoAreaDzBean : relationBusinessInfoAreaDzBeans) {
	    relationBusinessInfoAreaDzBean.setBusiNum(relationBusinessInfoBean.getBusiNum());
	    relationBusinessInfoAreaDzBean.setRelativeBusi(relationBusinessInfoBean.getRelativeBusi());
	    relationBusinessInfoAreaDzMapper.insertSelective(relationBusinessInfoAreaDzBean);
	}
	
    }

    public void deleteByBusiNum(String busiNum) {
	relationBusinessInfoAreaDzMapper.deleteByBusiNum(busiNum);
	
    }

    public void deleteRelaBusiAreaDz(BusinessInfoBean businessInfoBean) {
	relationBusinessInfoAreaDzMapper.deleteRelaBusiAreaDz(businessInfoBean);
	
    }

}
