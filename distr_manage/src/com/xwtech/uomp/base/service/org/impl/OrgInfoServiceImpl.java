package com.xwtech.uomp.base.service.org.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.org.OrgInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.org.OrgInfoBean;
import com.xwtech.uomp.base.service.org.IOrgInfoService;

/**
 * @author zhangel
 *营业厅
 */
@Service("orgInfoService")
public class OrgInfoServiceImpl implements IOrgInfoService {

	@Autowired
	OrgInfoMapper orgInfoMapper;
	/**
	 * 营业厅查询
	 */
	public List<OrgInfoBean> queryOrgInfo(Map<String, Object> map) {
		return orgInfoMapper.queryOrgInfo(map);
	}
	
	/**
	 * 分页查询
	 */
	public Page queryOrgInfoByPage(Map<String, Object> map){
		List<OrgInfoBean> list = orgInfoMapper.queryOrgInfo(map);
		int count = orgInfoMapper.queryOrgInfoCount(map);
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	
	public void addOrgInfo(OrgInfoBean orginfobean){
		orgInfoMapper.addOrgInfo(orginfobean);
	}
	
	public OrgInfoBean queryOrgInfoByPk(Map<String ,String> map){
		return orgInfoMapper.queryOrgInfoByPk(map);
	}
	
	public void updateOrgInfo(OrgInfoBean orginfobean){
		orgInfoMapper.updateOrgInfo(orginfobean);
	}
	
	public void updateOrgInfoState(OrgInfoBean orginfobean){
		orgInfoMapper.updateOrgInfoState(orginfobean);
	}

}
