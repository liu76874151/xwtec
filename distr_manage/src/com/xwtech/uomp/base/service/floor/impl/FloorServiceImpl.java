/**
 * Title: FloorServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-5 
 * @ time 下午4:46:20
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.floor.FloorMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockContentService;
import com.xwtech.uomp.base.service.floor.IFloorBlockDaService;
import com.xwtech.uomp.base.service.floor.IFloorService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * @author zhanglu
 *
 */
@Service("floorService")
public class FloorServiceImpl implements IFloorService{
	
	@Autowired
	FloorMapper floorMapper;
	@Autowired
	IFloorBlockDaService floorBlockDaService;
	@Autowired
	IFloorBlockContentService floorBlockContentService;
	@Autowired
	ISequenceService sequenceService;
	
	/**
	 * 根据条件查询楼层信息列表
	 */
	public Page queryFloorList(Map<String, String> paramMap) {
		List<FloorBean> list = floorMapper.queryFloorList(paramMap);
		int count = floorMapper.queryFloorCount(paramMap);
		List<FloorBean> newlist = new ArrayList<FloorBean>();
		Page page = new Page();
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				FloorBean floorbean = (FloorBean)list.get(i);
				String state = floorbean.getState();
				if("0".equals(state))
				{
					String showOper = "展示<a href='javascript:component.updateShowState(\""+floorbean.getFloorId()+"\",1)'><img src='../../resource/img/toggle_disabled.gif'/></a>";
					floorbean.setShowOper(showOper);
				}
				if("1".equals(state))
				{
					String showOper = "未展示<a href='javascript:component.updateShowState(\""+floorbean.getFloorId()+"\",0)'><img src='../../resource/img/toggle_enabled.gif'/></a>";
					floorbean.setShowOper(showOper);
				}
				newlist.add(floorbean);
			}
			page.setRecords(newlist);
		}
		else
		{
			page.setRecords(list);
		}
		page.setTotalRecord(count);
		return page;
	}

	/**
	 * 查找关联某个模版的楼层数量
	 */
	public int queryFloorByTemplate(Map<String,String> paramMap){
		return floorMapper.queryFloorByTemplate(paramMap);
	}
	
	/**
	 * 根据主键获取楼层
	 */
	public FloorBean getFloorByPKid(Map<String,String> paramMap){
		return floorMapper.getFloorByPKid(paramMap);
	}
	
	/**
	 * 更新展示状态
	 */
	public void updateState(Map<String, String> paramMap){
		floorMapper.updateState(paramMap);
	}
	
	/**
	 * 添加楼层
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String addFloorInfo(FloorBean floorbean){
		floorMapper.addFloorInfo(floorbean); 
		List<FloorBlockDaBean> daList = floorbean.getFloorBlockDaList();
		Map<String,String> idMap=new HashMap<String, String>();
		for(FloorBlockDaBean floorBlockDaBean : daList){
			String floorId = floorbean.getFloorId();
			floorBlockDaBean.setFloorId(floorId);
			String blockId = sequenceService.getSequence("T_FLOOR_BLOCK_DA_SEQ");
			floorBlockDaBean.setBlockId(blockId);
			floorBlockDaService.addFloorBlockInfo(floorBlockDaBean);
			idMap.put(floorBlockDaBean.getBlockNum(), floorBlockDaBean.getBlockId());
		}
		List<FloorBlockContentBean> contentList = floorbean.getFloorBlockContentList();
		String pkid="";
		
		for(int j = 0; j < contentList.size(); j++){
			FloorBlockContentBean floorBlockContentBean = (FloorBlockContentBean)contentList.get(j);
			String blockId=idMap.get(floorBlockContentBean.getBlockId());
			floorBlockContentBean.setBlockId(blockId);
			String contentId = sequenceService.getSequence("T_FLOOR_BLOCK_CONTENT_SEQ");
			floorBlockContentBean.setContentId(contentId);
			floorBlockContentService.addBlockContent(floorBlockContentBean);
			pkid+=contentId+",";
		}
		return pkid.substring(0,pkid.length()-1);
		
	}
	
	/**
	 * 修改楼层信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String updateFloorInfo(FloorBean floorBean){
		floorMapper.updateFloorInfo(floorBean);
		floorBlockDaService.deleteFloorBlock(floorBean.getFloorId());
		List<String> contentIdArray = new ArrayList<String>();
		contentIdArray.add("-1");
		List<FloorBlockContentBean> contentList = floorBean.getFloorBlockContentList();
		for(FloorBlockContentBean floorBlockContentBean : contentList){
			String contentId = floorBlockContentBean.getContentId();
			if(StringUtils.isNotBlank(contentId)){
			contentIdArray.add(contentId);}
		}
		floorBlockContentService.deleteBlockContentInfo(contentIdArray);
		List<FloorBlockDaBean> daList = floorBean.getFloorBlockDaList();
		Map<String,String> idMap=new HashMap<String, String>();
		for(FloorBlockDaBean floorBlockDaBean : daList){
			String floorId = floorBean.getFloorId();
			floorBlockDaBean.setFloorId(floorId);
			String blockId = sequenceService.getSequence("T_FLOOR_BLOCK_DA_SEQ");
			floorBlockDaBean.setBlockId(blockId);
			floorBlockDaService.addFloorBlockInfo(floorBlockDaBean);
			idMap.put(floorBlockDaBean.getBlockNum(), floorBlockDaBean.getBlockId());
		}
		String pkid="";
		for(int j = 0; j < contentList.size(); j++){
			FloorBlockContentBean floorBlockContentBean = (FloorBlockContentBean)contentList.get(j);
			String blockId=idMap.get(floorBlockContentBean.getBlockId());
			floorBlockContentBean.setBlockId(blockId);
			String contentId = sequenceService.getSequence("T_FLOOR_BLOCK_CONTENT_SEQ");
			pkid+=contentId+",";
			floorBlockContentBean.setContentId(contentId);
			floorBlockContentService.addBlockContent(floorBlockContentBean);
		}
		return pkid.substring(0,pkid.length()-1);
	}
	
	/**
	 * 删除楼层所有信息
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteFloorInfo(String floorId){
		Map<String, String> param = new HashMap<String, String>();
		param.put("floorId", floorId);
		List<FloorBlockContentBean> contentlist = floorBlockContentService.queryBlockContentByFloorId(floorId);
		//删除楼层
		floorMapper.deleteFloorInfo(floorId);
		//删除楼层区块
		floorBlockDaService.deleteFloorBlock(floorId);
		//删除楼层区块内容
		List<String> contentIdArray = new ArrayList<String>();
		contentIdArray.add("-1");
		for(FloorBlockContentBean floorBlockContentBean : contentlist){
			String contentId = floorBlockContentBean.getContentId();
			contentIdArray.add(contentId);
		}
		floorBlockContentService.deleteBlockContentInfo(contentIdArray);
	}
	
	/**
	 * 根据floorids批量查询
	 */
	public List<FloorBean> queryFloorByIds(Map<String, Object> floorids){
		return floorMapper.queryFloorByIds(floorids);
	}
}
