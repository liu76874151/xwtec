/**
 * Title: FloorBlockDaServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 下午3:14:08
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.floor.FloorBlockDaMapper;
import com.xwtech.uomp.base.pojo.adv.AdvInfoBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockDaService;

/**
 * @author zhanglu
 *
 */
@Service("floorBlockDaService")
public class FloorBlockDaServiceImpl implements IFloorBlockDaService {

	@Autowired
	FloorBlockDaMapper floorBlockDaMapper;
	
	/**
	 * 根据楼层ID获取区块信息
	 */
	public List<Map<String, Object>> queryBlockByFloorId(Map<String,String> paramMap){
		return floorBlockDaMapper.queryBlockByFloorId(paramMap);
	}
	
	/**
	 * 添加区块信息
	 */
	public void addFloorBlockInfo(FloorBlockDaBean floorBlockDaBean){
		floorBlockDaMapper.addFloorBlockInfo(floorBlockDaBean);
	}
	
	/**
	 * 删除区块信息
	 */
	public void deleteFloorBlock(String floorId){
		floorBlockDaMapper.deleteFloorBlock(floorId);
	}
	
	/**
	 * 根据楼层ID获取区块信息
	 */
	public List<FloorBlockDaBean> queryBlockDaByFloorId(String floorId){
		return floorBlockDaMapper.queryBlockDaByFloorId(floorId);
	}

	public List<FloorBlockDaBean> queryBlockDAContentByFloorId(
			Map<String, String> paramMap) {
		return floorBlockDaMapper.queryBlockDAContentByFloorId(paramMap);
	}
	
}
