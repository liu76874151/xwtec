package com.xwtech.uomp.base.service.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.business.BusinessTypeMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;
import com.xwtech.uomp.base.service.business.IBusinessSortDzService;
import com.xwtech.uomp.base.service.business.IBusinessTypeService;
import com.xwtech.uomp.base.util.StringUtil;
/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-7 下午04:32:42
 */
@Service("businessTypeService")
public class BusinessTypeServiceImpl implements IBusinessTypeService {

	@Autowired
	BusinessTypeMapper businessTypeMapper;
	@Autowired
	IBusinessSortDzService businessSortDzService;
	
	public int insert(BusinessTypeBean record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 添加业务分类
	 */
	public BusinessTypeBean insertSelective(BusinessTypeBean record) {
		Map<String, String> param = new HashMap<String, String>();
		String parentNum = record.getParentNum();
		String derivedValue = "";
		try {
		    	BusinessTypeBean bean = new BusinessTypeBean();
		    	if (StringUtils.isNotBlank(parentNum)) {
        		    	bean.setBusiTypeNum(parentNum);
        		    	bean.setChannelNum(record.getChannelNum());
        		    	BusinessTypeBean parentBean = this.queryBusiTypeBykey(bean);
        			String parentjbNum = parentBean.getJbNum();
        			int jb = parentBean.getJb();
        			param.put("jb", jb+1+"");
        			param.put("jbNum", parentjbNum);
        			String maxjbNum = businessTypeMapper.getMaxjbNum(param);
        			
        			if (StringUtil.isNull(maxjbNum)) {
        			    record.setJbNum(parentjbNum + "01");
        			}else{
        			    derivedValue = maxjbNum.substring(parentjbNum.length());
        			    int i = (Integer.parseInt(derivedValue))+1;
        			    if ((i+"").length()==1) {
        				record.setJbNum(parentjbNum+"0"+i);
        			    }else{
        				record.setJbNum(parentjbNum+i);
        			    }
        			}
        			
        			record.setJb(jb+1);
			}else {
			    	bean.setChannelNum(record.getChannelNum());
        			param.put("jb", "1");
        			param.put("jbNum", null);
        			String maxjbNum = businessTypeMapper.getMaxjbNum(param);
        			
        			if (StringUtil.isNull(maxjbNum)) {
        			    record.setJbNum("01");
        			}else{
        			    int i = (Integer.parseInt(maxjbNum))+1;
        			    if ((i+"").length()==1) {
        				record.setJbNum("0"+i);
        			    }else{
        				record.setJbNum(i+"");
        			    }
        			}
        			
        			record.setJb(1);
			}
			businessTypeMapper.insertSelective(record);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return record;
	}

	public BusinessTypeBean queryBusiTypeBykey(BusinessTypeBean businessTypeBean) {
		return businessTypeMapper.queryBusiTypeBykey(businessTypeBean);
	}

	public Page queryBusinessTypeList(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BusinessTypeBean> queryParentBusiType(Map<String, Object> param) {
		return businessTypeMapper.queryParentBusiType(param);
	}

	public void updateByPrimaryKey(BusinessTypeBean businessTypeBean) {
		// TODO Auto-generated method stub

	}

	public List<BusinessTypeBean> queryBusiType(Map<String, Object> param) {
	    
	    return businessTypeMapper.queryBusiType(param);
	}

	public void updateByBusiTypeNum(BusinessTypeBean businessTypeBean) {
	    
	    businessTypeMapper.updateByBusiTypeNum(businessTypeBean);
	}

	@Transactional
	public List<BusinessSortDzBean> deleteBusiType(String[] jbNums) {
	    List<BusinessSortDzBean> list = null;
	    String jbNum;
	    if(!ArrayUtils.isEmpty(jbNums)){
		for (int i = 0; i < jbNums.length; i++) {
		    jbNum = jbNums[i];
		    list = businessSortDzService.queryByjbNum(jbNum);
		    if (list!=null && list.size()>0) {
		    
			break;
		    }
		}
	    }else{
		throw new RuntimeException();
	    }
	    if (list==null || list.size()==0) {
		if(!ArrayUtils.isEmpty(jbNums)){
		    for (int i = 0; i < jbNums.length; i++) {
			jbNum = jbNums[i];
			businessTypeMapper.deleteBusiType(jbNum);
		    }
		}
	    }
		return list;
	}
}
