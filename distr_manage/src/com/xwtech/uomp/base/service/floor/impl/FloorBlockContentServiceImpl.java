/**
 * Title: FloorBlockContentServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 下午2:45:52
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

import com.xwtech.uomp.base.dao.floor.FloorBlockContentMapper;
import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockContentService;

/**
 * @author zhanglu
 *
 */
@Service("floorBlockContentService")
public class FloorBlockContentServiceImpl implements IFloorBlockContentService {

	@Autowired FloorBlockContentMapper floorBlockContentMapper;
	
	/**
	 * 添加区块内容
	 */
	public void addBlockContent(FloorBlockContentBean floorBlockContentBean){
		floorBlockContentMapper.addBlockContent(floorBlockContentBean);
	}
	
	/**
	 * 批量删除区块内容
	 */
	public void deleteBlockContentInfo(List<String> list){
		floorBlockContentMapper.deleteBlockContentInfo(list);
	}
	
	/**
	 * 根据楼层Id获取区块内容
	 */
	public List<FloorBlockContentBean> queryBlockContentByFloorId(String floorId){
		return floorBlockContentMapper.queryBlockContentByFloorId(floorId);
	}
	/**
	 * 修改区块内容
	 */
	public void updateBlockContentInfo(Map<String, String> map) {
		floorBlockContentMapper.updateBlockContentInfo(map);
		
	}
	/**
	 * 批量修改图片
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateBlockImg(String[] contentId, String[] contentImg) {
		Map<String, String> param = new HashMap<String, String>();
		for (int i = 0; i < contentId.length; i++) {
			param.clear();
			param.put("contentId", contentId[i]);
			param.put("contentImg", contentImg[i]);
		    updateBlockContentInfo(param);
		}
		
	}
}
