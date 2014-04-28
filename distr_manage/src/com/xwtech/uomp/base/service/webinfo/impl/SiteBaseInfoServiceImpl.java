package com.xwtech.uomp.base.service.webinfo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.webinfo.SiteBaseInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.webinfo.SiteBaseInfoBean;
import com.xwtech.uomp.base.service.webinfo.ISiteBaseInfoService;
@Service("stiteBaseInfoServiceImpl")
public class SiteBaseInfoServiceImpl implements ISiteBaseInfoService {
	@Autowired
	SiteBaseInfoMapper siteBaseInfoMapper;
	public Page queryWebInfoList(Map<String, String> map) {
		List<SiteBaseInfoBean> list=siteBaseInfoMapper.queryWebInfoList(map);
		int count=siteBaseInfoMapper.queryWebInfoListCount(map);
        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);
        return page;
	}
	public SiteBaseInfoBean selectByPrimaryKey(int channelNum) {
		return siteBaseInfoMapper.selectByPrimaryKey(channelNum);
	}
	public int updateByPrimaryKey(SiteBaseInfoBean record) {
		return siteBaseInfoMapper.updateByPrimaryKey(record);
	}

}
