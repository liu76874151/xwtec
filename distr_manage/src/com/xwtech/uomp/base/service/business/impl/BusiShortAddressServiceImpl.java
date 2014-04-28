package com.xwtech.uomp.base.service.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.business.BusiShortAddressMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusiShortAddressBean;
import com.xwtech.uomp.base.pojo.business.BusiShortChannelBean;
import com.xwtech.uomp.base.service.business.IBusiShortAddressService;
@Service("busiShortAddressService")
public class BusiShortAddressServiceImpl implements IBusiShortAddressService {
@Autowired
BusiShortAddressMapper busiShortAddressMapper;
	public Page queryBusiShortAdd(Map<String, String> map) {
		List<BusiShortAddressBean> list=busiShortAddressMapper.queryBusiShortAdd(map);
		List<BusiShortAddressBean> newList=new ArrayList<BusiShortAddressBean>();
		String state="";
		String stateLink="";
		for(BusiShortAddressBean busiShortAddressBean:list){
			state=busiShortAddressBean.getState();
			if("0".equals(state)){
				stateLink="启用&nbsp;<a href='javascript:component.updateState(1)'><img src='../../../resource/img/toggle_disabled.gif'/></a>";
			}else{
				stateLink="停用&nbsp;<a href='javascript:component.updateState(0)'><img src='../../../resource/img/toggle_enabled.gif'/></a>";
			}
			busiShortAddressBean.setStateLink(stateLink);
			newList.add(busiShortAddressBean);
		}
		int count=busiShortAddressMapper.queryBusiShortAddcount(map);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	public Page queryBusiShortChannel(Map<String, String> map) {
		List<BusiShortChannelBean> list=busiShortAddressMapper.queryBusiShortChannel(map);
		Page page=new Page();
		page.setRecords(list);
		return page;
	}
	public void saveBusiShortAdd(BusiShortAddressBean busiShortAddressBean) {
		busiShortAddressMapper.saveBusiShortAdd(busiShortAddressBean);
		
	}
	public BusiShortAddressBean findOnebusiShortAdd(String shortId) {
		return busiShortAddressMapper.findOnebusiShortAdd(shortId);
	}
	public void updateBusiShortAdd(BusiShortAddressBean busiShortAddressBean) {
		busiShortAddressMapper.updateBusiShortAdd(busiShortAddressBean);
		
	}
	public void deleteBusiShortAdd(String shortId) {
		busiShortAddressMapper.deleteBusiShortAdd(shortId);
		
	}
	public void updateBusiShortAddState(Map<String, String> map) {
		busiShortAddressMapper.updateBusiShortAddState(map);
		
	}

}
