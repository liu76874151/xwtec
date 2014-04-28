/**
 * Title: FloorBlockDsService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-11 
 * @ time 下午2:32:37
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.floor.FloorBlockDsMapper;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDsBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockDsService;

/**
 * @author zhanglu
 *
 */
@Service("floorBlockDsService")
public class FloorBlockDsServiceImpl implements IFloorBlockDsService{

	@Autowired
	FloorBlockDsMapper floorBlockDsMapper;
	
	public List<FloorBlockDsBean> queryBlockDsList(){
		return floorBlockDsMapper.queryBlockDsList();
	}
	
}
