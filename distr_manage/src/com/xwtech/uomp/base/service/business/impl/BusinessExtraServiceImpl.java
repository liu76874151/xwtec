package com.xwtech.uomp.base.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusinessExattrMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessExattrBean;
import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;
import com.xwtech.uomp.base.service.business.IBusinessExattrDzService;
import com.xwtech.uomp.base.service.business.IBusinessExtraService;
import com.xwtech.uomp.base.util.StringUtil;
/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-5 下午03:24:24
 */
@Service("businessExtraService")
public class BusinessExtraServiceImpl implements IBusinessExtraService {

	@Autowired
	BusinessExattrMapper businessExattrMapper;
	@Autowired
	IBusinessExattrDzService businessExattrDzService;
	
	public Page queryByName(Map<String, String> map) {
		List<BusinessExattrBean> list = businessExattrMapper.queryByName(map);
		int totalRecord = businessExattrMapper.queryBusinessExtraCount(map);
		for (BusinessExattrBean businessExattrBean : list) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<a href=\"javascript:component.updateBusiExtra('"+businessExattrBean.getAttrKey()+"');\" >详情/编辑</a>");
			businessExattrBean.setModify(stringBuffer.toString());
		}
		
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(totalRecord);

        return page;
	}

	public void updateBusinessExattrBean(BusinessExattrBean businessExattrBean) {
		businessExattrMapper.updateBusinessExattrBean(businessExattrBean);
	}

	public void addBusiExtra(BusinessExattrBean businessExattrBean) {
		BusinessExattrBean bean = businessExattrMapper.queryByKey(businessExattrBean.getAttrKey());
		if(bean!=null){
			throw new RuntimeException("业务扩展属性已经存在");
		}else{
			businessExattrMapper.addBusiExtra(businessExattrBean);
		}
		
	}

	public BusinessExattrBean queryBusinessExtraByKey(String attrKey) {
		return businessExattrMapper.queryByKey(attrKey);
	}

	public List<BusinessExattrDzBean> deleteBusiExtraByAttrKey(String attrKey) {
		//TODO 删除规则
		List<BusinessExattrDzBean> list = null;
		if(!StringUtil.isNull(attrKey)){
			list = businessExattrDzService.queryBusiExtraDzByAttrKey(attrKey);
		}else{
			throw new RuntimeException();
		}
		if (list==null || list.size()==0) {
			businessExattrMapper.deleteBusiExtraByAttrKey(attrKey);
		}
		return list;
	}

}
