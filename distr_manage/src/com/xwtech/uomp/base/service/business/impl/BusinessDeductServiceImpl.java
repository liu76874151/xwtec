package com.xwtech.uomp.base.service.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusinessDeductMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessDeductBean;
import com.xwtech.uomp.base.service.business.IBusinessDeductService;
/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-6 下午04:55:13
 */
@Service("businessDeductService")
public class BusinessDeductServiceImpl implements IBusinessDeductService {

	@Autowired
	BusinessDeductMapper businessDeductMapper;
	
	public void deleteByPrimaryKey(String deductNum) {
		businessDeductMapper.deleteByPrimaryKey(deductNum);
	}

	public Page queryBusinessDeductList(Map<String, String> param) {
		List<BusinessDeductBean> records = businessDeductMapper.queryBusinessDeductList(param);
		int totalRecord =  businessDeductMapper.queryBusinessDeductCount(param);
		
		for (BusinessDeductBean businessDeductBean : records) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<a href=\"javascript:component.updateBusiDeduct('"+businessDeductBean.getDeductNum()+"');\" >详情/编辑</a>");
			businessDeductBean.setModify(stringBuffer.toString());
		}
		
		Page page = new Page();
		page.setRecords(records);
		page.setTotalRecord(totalRecord);
		return page;
	}

	public void updateByPrimaryKey(BusinessDeductBean businessDeductBean) {
		businessDeductMapper.updateByPrimaryKey(businessDeductBean);
	}

	public void updateByPrimaryKeySelective(
			BusinessDeductBean businessDeductBean) {
		businessDeductMapper.updateByPrimaryKeySelective(businessDeductBean);
	}

	public void addBusiDeduct(BusinessDeductBean businessDeductBean) {
		BusinessDeductBean deductBean = businessDeductMapper.queryBusiDeductBykey(businessDeductBean.getDeductNum());
		if(deductBean==null){
			businessDeductMapper.addBusiDeduct(businessDeductBean);
		}else{
			throw new RuntimeException("扣费方式编码已存在!");
		}
	}

	public BusinessDeductBean queryBusiDeductBykey(String deductNum) {
		return businessDeductMapper.queryBusiDeductBykey(deductNum);
	}

}
