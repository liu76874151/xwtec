/**
 * Title: ZoneInfoServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-15 
 * @ time 上午11:13:08
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.org.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.org.ZoneInfoMapper;
import com.xwtech.uomp.base.pojo.org.ZoneInfoBean;
import com.xwtech.uomp.base.service.org.IZoneInfoService;

/**
 * @author zhanglu
 *
 */
@Service("zoneInfoService")
public class ZoneInfoServiceImpl implements IZoneInfoService{

	@Autowired
	ZoneInfoMapper zoneInfoMapper;
	
	public List<ZoneInfoBean> queryZoneByCity(Map<String, String> paramMap){
		return zoneInfoMapper.queryZoneByCity(paramMap);
	}

	public List<ZoneInfoBean> queryZoneByParentId(String parentId) {
	    
	    return zoneInfoMapper.queryZoneByParentId(parentId);
	}

	public List<ZoneInfoBean> queryBossAreaList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return zoneInfoMapper.queryBossAreaList(paramMap);
	}
	
}
