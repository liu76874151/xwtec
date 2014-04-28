package com.xwtech.uomp.base.service.org.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.org.TargetGroupInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;
import com.xwtech.uomp.base.pojo.target.TargetBean;
import com.xwtech.uomp.base.service.org.ITargetGroupInfoService;

/**
 * 目标组织
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-16 下午03:55:28
 */
@Service("targetGroupInfoService")
public class TargetGroupInfoServiceImpl implements ITargetGroupInfoService{

	@Autowired
	TargetGroupInfoMapper targetGroupInfoMapper;
	
	public List<TargetGroupInfoBean> querytargetGroupInfoList(
			Map<String, String> param) {
		return targetGroupInfoMapper.queryTargetGroupInfoList(param);
	}

	public List<TargetGroupInfoBean> selectTargetGroupFilterBySecondPkid(
			Map<String, String> param) {
		
		return targetGroupInfoMapper.selectTargetGroupFilterBySecondPkid(param);
	}
	
	public Page queryTargetByPage(Map<String, String> param) {

		List<TargetGroupInfoBean> list = targetGroupInfoMapper.queryTargetGroupInfoList(param);
		List<TargetGroupInfoBean> newList = new ArrayList<TargetGroupInfoBean>();
		for(TargetGroupInfoBean targetGroupInfoBean : list){
			String importOper = "";
			if ("1".equals(targetGroupInfoBean.getState())) {
				if (targetGroupInfoBean.getImportCount() == null
						|| "".equals(targetGroupInfoBean.getImportCount())) {
					importOper = "<a href='javascript:component.importData();' title='点击导入数据'>导入号码</a>";
				} else {
					importOper = "<a href='javascript:component.importData();' title='点击追加数据'>追加号码</a>";
				}
			}
			targetGroupInfoBean.setImportOper(importOper);
			newList.add(targetGroupInfoBean);
		}
		int count = targetGroupInfoMapper.queryTargetGroupInfoCount(param);

		Page page = new Page();
		page.setRecords(newList);
		page.setTotalRecord(count);

		return page;

	}
	
	public void addGroupInfo(TargetGroupInfoBean targetGroupInfoBean){
		targetGroupInfoMapper.addGroupInfo(targetGroupInfoBean);
	}
	
	public TargetGroupInfoBean queryGroupInfoById(Map<String, String> param){
		return targetGroupInfoMapper.queryGroupInfoById(param);
	}
	
	public int queryPhoneNumber(Map<String, String> param){
		return targetGroupInfoMapper.queryPhoneNumber(param);
	}
	
	public void addPhoneNumber(TargetGroupInfoBean targetGroupInfoBean){
		targetGroupInfoMapper.addPhoneNumber(targetGroupInfoBean);
	}
	
	public void updateState(Map<String, String> param){
		targetGroupInfoMapper.updateState(param);
	}

	public void updateGroupInfo(TargetGroupInfoBean targetGroupInfoBean){
		targetGroupInfoMapper.updateGroupInfo(targetGroupInfoBean);
	}

	public void deletePhoneNumber(String groupId) {
		targetGroupInfoMapper.deletePhoneNumber(groupId);		
	}
}
