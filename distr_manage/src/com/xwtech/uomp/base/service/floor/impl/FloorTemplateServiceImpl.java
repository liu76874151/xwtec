/**
 * Title: FloorTemplateService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-6 
 * @ time 下午3:27:02
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.floor.FloorTemplateMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorTemplateBean;
import com.xwtech.uomp.base.service.floor.IFloorTemplateService;

/**
 * @author zhanglu
 * 
 */
@Service("floorTemplateService")
public class FloorTemplateServiceImpl implements IFloorTemplateService {

	@Autowired
	FloorTemplateMapper floorTemplateMapper;

	/**
	 * 查询楼层列表
	 */
	public Page queryFloorTemplateList(Map<String, String> paramMap) {
		List<FloorTemplateBean> list = floorTemplateMapper.queryFloorTemplateList(paramMap);
		List<FloorTemplateBean> newlist = new ArrayList<FloorTemplateBean>();
		Page page = new Page();
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				FloorTemplateBean floorTemplateBean = list.get(i);
				String imgPath = "../../.." +floorTemplateBean.getTempImg();
				String imgSrc = "<img height='181px' src='"+imgPath+"'/>";
				floorTemplateBean.setTempImg(imgSrc);
				
				String state = floorTemplateBean.getState();
				if("0".equals(state))
				{
					String userableOper = "启用<a href='javascript:component.updateUserableState(\""+floorTemplateBean.getTempNum()+"\",1)'><img src='../../../resource/img/toggle_disabled.gif'/></a>";
					floorTemplateBean.setUserableOper(userableOper);
				}
				if("1".equals(state))
				{
					String userableOper = "停用<a href='javascript:component.updateUserableState(\""+floorTemplateBean.getTempNum()+"\",0)'><img src='../../../resource/img/toggle_enabled.gif'/></a>";
					floorTemplateBean.setUserableOper(userableOper);
				}
				newlist.add(floorTemplateBean);
			}
			page.setRecords(newlist);
		}
		else
		{
			page.setRecords(list);
		}
		int count = floorTemplateMapper.queryFloorTemplateCount(paramMap);
		
		page.setTotalRecord(count);
		return page;
	}
	
	/**
	 * 根据渠道查询所有可用模版
	 * 创建日期：2013-11-11上午11:16:06
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorTemplateBean>
	 */
	public List<FloorTemplateBean> queryFloorTemplateAll(Map<String,String> paramMap){
		return floorTemplateMapper.queryFloorTemplateAll(paramMap);
	}

	/**
	 * 根据主键查询模版信息
	 */
	public FloorTemplateBean queryFloorTemplateByPk(Map<String,String> paramMap){
		return floorTemplateMapper.queryFloorTemplateByPk(paramMap);
	}
	
	/**
	 * 更新启用状态
	 */
	public void updateUseableState(Map<String,String> paramMap){
		floorTemplateMapper.updateUseableState(paramMap);
	}
	
	/**
	 * 修改模版信息
	 */
	public void updateFloorTemplate(FloorTemplateBean floorTemplateBean){
		floorTemplateMapper.updateFloorTemplate(floorTemplateBean);
	}
	
	/**
	 * 添加模版
	 */
	public void addFloorTemplate(FloorTemplateBean floorTemplateBean){
		floorTemplateMapper.addFloorTemplate(floorTemplateBean);
	}
	
	/**
	 * 删除模版
	 */
	public void deleteFloorTemplate(FloorTemplateBean floorTemplateBean){
		floorTemplateMapper.deleteFloorTemplate(floorTemplateBean);
	}
}
