package com.xwtech.uomp.base.service.area.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xwtech.uomp.base.dao.area.AreaDAMapper;
import com.xwtech.uomp.base.dao.area.AreaMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.area.AreaBean;
import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.service.area.IAreaService;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    AreaMapper areaMapper;
    @Autowired
    AreaDAMapper areaDAMapper;
	public void deleteArea(String id) {
	
		areaMapper.deleteArea(id);
		
	}


	public Page queryAreaList(Map<String, String> paramMap) {
	
		List<AreaBean> list=areaMapper.queryAreaList(paramMap);
		int count=areaMapper.queryAreaListCount(paramMap);
        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);
        return page;
	}

	public int queryAreaListCount(Map<String, String> param) {
	
		return areaMapper.queryAreaListCount(param);
	}

	public void saveArea(AreaBean areaBean) {
		
		areaMapper.saveArea(areaBean);
		
	}


	public AreaBean findOneArea(String pkid) {
	
		return areaMapper.findOneAreaBean(pkid);
	}

	public void updateArea(AreaBean areaBean) {
		
		areaMapper.updateArea(areaBean);
		
	}


	public Page queryAreaDAList(String bossCode) {
		List<AreaDABean> list=areaDAMapper.queryAreaDAList(bossCode);
        Page page = new Page();
        page.setRecords(list);
		return page;
	}


	public Page queryCityList() {
		List<AreaBean> list=areaMapper.queryCityList();
        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(list.size());
        return page;
	}
	
	public List<AreaDABean> queryAreaCityList(String bossCode){
		List<AreaDABean> list = areaDAMapper.queryAreaCityList(bossCode);
		return list;
	}

	public List<AreaBean> findAreaList(Map<String, String> param) {
	    return areaMapper.findAreaList(param);
	}


	public int isExistAreaJbNum(Map<String, String> map) {
		return areaMapper.isExistAreaJbNum(map);
	}



}
