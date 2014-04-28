package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.RelationBusinessInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoAreaDzBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBrandDzBean;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoAreaDzService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoBrandDzService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-4 上午09:32:08
 */
@Service("relationBusinessInfoService")
public class RelationBusinessInfoServiceImpl implements IRelationBusinessInfoService {

    @Autowired
    RelationBusinessInfoMapper relationBusinessInfoMapper;
    @Autowired
    IRelationBusinessInfoAreaDzService relationBusinessInfoAreaDzService;
    @Autowired
    IRelationBusinessInfoBrandDzService relationBusinessInfoBrandDzService;
    
    public void insert(RelationBusinessInfoBean record) {
	relationBusinessInfoMapper.insert(record);
    }

    public void insertSelective(RelationBusinessInfoBean record) {
	relationBusinessInfoMapper.insertSelective(record);
    }

    public void updateByPrimaryKey(RelationBusinessInfoBean record) {
	relationBusinessInfoMapper.updateByPrimaryKey(record);

    }

    public void updateByPrimaryKeySelective(RelationBusinessInfoBean record) {
	relationBusinessInfoMapper.updateByPrimaryKeySelective(record);

    }

    @Transactional
    public void batchInsert(List<RelationBusinessInfoBean> relationBusinessInfoBeans, String busiNum) {
	//TODO
	for (RelationBusinessInfoBean relationBusinessInfoBean : relationBusinessInfoBeans) {
	    relationBusinessInfoBean.setBusiNum(busiNum);
	    relationBusinessInfoMapper.insertSelective(relationBusinessInfoBean);
	    List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans = relationBusinessInfoBean.getRelationBusinessInfoAreaDzBeans();
	    List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans = relationBusinessInfoBean.getRelationBusinessInfoBrandDzBeans();
	    if (relationBusinessInfoAreaDzBeans!=null && relationBusinessInfoAreaDzBeans.size()>0) {
		relationBusinessInfoAreaDzService.batchInsert(relationBusinessInfoAreaDzBeans,relationBusinessInfoBean);
	    }
	    if (relationBusinessInfoBrandDzBeans!=null && relationBusinessInfoBrandDzBeans.size()>0) {
		relationBusinessInfoBrandDzService.batchInsert(relationBusinessInfoBrandDzBeans,relationBusinessInfoBean);
	    }
	}
    }

    public RelationBusinessInfoBean queryRelationInfos(Map<String, String> param) {
	
	return relationBusinessInfoMapper.queryRelationInfos(param);
    }

    /**
     * 关联业务更新,先删,后插入
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void batchUpdate(List<RelationBusinessInfoBean> relationBusinessInfoBeans, String busiNum) {
	//TODO
	if (relationBusinessInfoBeans!=null && relationBusinessInfoBeans.size()>0) {
	    for (RelationBusinessInfoBean relationBusinessInfoBean : relationBusinessInfoBeans) {
		    relationBusinessInfoBean.setBusiNum(busiNum);
		    relationBusinessInfoMapper.insertSelective(relationBusinessInfoBean);
		    List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans = relationBusinessInfoBean.getRelationBusinessInfoAreaDzBeans();
		    List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans = relationBusinessInfoBean.getRelationBusinessInfoBrandDzBeans();
		    if (relationBusinessInfoAreaDzBeans!=null && relationBusinessInfoAreaDzBeans.size()>0) {
			relationBusinessInfoAreaDzService.batchInsert(relationBusinessInfoAreaDzBeans,relationBusinessInfoBean);
		    }
		    if (relationBusinessInfoBrandDzBeans!=null && relationBusinessInfoBrandDzBeans.size()>0) {
			relationBusinessInfoBrandDzService.batchInsert(relationBusinessInfoBrandDzBeans,relationBusinessInfoBean);
		    }
		}
	}else{
	    
	}
	
    }

    public void deleteRelaBusiness(BusinessInfoBean businessInfoBean) {
	relationBusinessInfoMapper.deleteRelaBusiness(businessInfoBean);
	
    }

    public void deleteByBusiNum(String busiNum) {
	relationBusinessInfoMapper.deleteByBusiNum(busiNum);
	
    }

    public Page queryRelaBusiDetail(Map<String, String> param) {
	List<BusinessInfoBean> list = relationBusinessInfoMapper.queryRelaBusiDetail(param);
	int totalRecord = relationBusinessInfoMapper.queryRelaBusiDetailCount(param);
	Page page = new Page();
	page.setRecords(list);
	page.setTotalRecord(totalRecord);
	return page;
    }

}
