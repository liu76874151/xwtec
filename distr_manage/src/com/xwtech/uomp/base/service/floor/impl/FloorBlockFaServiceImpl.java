/**
 * Title: FloorBlockFaService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-26 
 * @ time 上午10:24:36
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.floor.FloorBlockFaMapper;
import com.xwtech.uomp.base.pojo.floor.FloorBlockFaBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockFaService;

/**
 * @author zhanglu
 *
 */
@Service("floorBlockFaService")
public class FloorBlockFaServiceImpl implements IFloorBlockFaService{

	@Autowired 
	FloorBlockFaMapper floorBlockFaMapper;
	
	/**
	 * 批量保存楼层区块
	 */
	public void batchInsertBlockFa(List<FloorBlockFaBean> list){
		floorBlockFaMapper.batchInsertBlockFa(list);
	}
	
	/**
	 * 根据楼层ID获取楼层区块方案
	 */
	public List<FloorBlockFaBean> queryFloorBlockFa(String floorId){
		return floorBlockFaMapper.queryFloorBlockFa(floorId);
	}
	
	/**
	 * 更改楼层区块方案采集状态
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCollectState(List<FloorBlockFaBean> floorBlockFaList){
		Map<String, String> paramMap = new HashMap();
		for(FloorBlockFaBean floorBlockFaBean : floorBlockFaList)
		{
			paramMap.put("blockId", floorBlockFaBean.getBlockId());
			paramMap.put("collectState", floorBlockFaBean.getCollectState());
			floorBlockFaMapper.updateCollectState(paramMap);
		}
	}
	
}
