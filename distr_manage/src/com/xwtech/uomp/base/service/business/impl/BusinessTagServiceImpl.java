package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusiTagMapper;
import com.xwtech.uomp.base.pojo.business.BusiTagBean;
import com.xwtech.uomp.base.service.business.IBusinessTagService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-23 下午03:17:30
 */
@Service("businessTagService")
public class BusinessTagServiceImpl implements IBusinessTagService {

    @Autowired
    BusiTagMapper busiTagMapper;
    @Autowired
    ISequenceService sequenceService;
    
    public void deleteByPrimaryKey(Long tagId) {
	// TODO Auto-generated method stub
    }

    public void insert(BusiTagBean record) {
	// TODO Auto-generated method stub
    }

    public void insertSelective(BusiTagBean record) {
	record.setTagId(Long.parseLong(sequenceService.getSequence("BUSI_TAG_DA_SEQ")));
	record.setTagState("1");
	busiTagMapper.insertSelective(record);
    }

    public BusiTagBean selectByPrimaryKey(Long tagId) {
	return busiTagMapper.selectByPrimaryKey(tagId);
    }

    public void updateByPrimaryKey(BusiTagBean record) {
	busiTagMapper.updateByPrimaryKey(record);
    }

    public void updateByPrimaryKeySelective(BusiTagBean record) {
	busiTagMapper.updateByPrimaryKeySelective(record);
    }

    public List<BusiTagBean> queryBusiTagList(Map<String, String> param) {
	return busiTagMapper.queryBusiTagList(param);
    }

    public void deleteBusinessTag(Map<String, Object> param) {
	busiTagMapper.deleteBusinessTag(param);
	
    }

    public BusiTagBean selectBusiTagBean(Map<String, String> param) {
	
	return busiTagMapper.selectBusiTagBean(param);
    }

}
