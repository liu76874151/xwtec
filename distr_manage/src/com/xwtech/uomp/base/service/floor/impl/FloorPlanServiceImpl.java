/**
 * Title: FloorPlanServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-21 
 * @ time 下午5:07:59
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.floor.FloorMapper;
import com.xwtech.uomp.base.dao.floor.FloorPlanMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockFaBean;
import com.xwtech.uomp.base.pojo.floor.FloorFaBean;
import com.xwtech.uomp.base.pojo.floor.FloorPlanBean;
import com.xwtech.uomp.base.service.floor.IFloorBlockDaService;
import com.xwtech.uomp.base.service.floor.IFloorBlockFaService;
import com.xwtech.uomp.base.service.floor.IFloorFaService;
import com.xwtech.uomp.base.service.floor.IFloorPlanService;
import com.xwtech.uomp.base.service.floor.IFloorService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;

/**
 * @author zhanglu
 *
 */
@Service("floorPlanService")
public class FloorPlanServiceImpl implements IFloorPlanService{

	@Autowired
	FloorPlanMapper floorPlanMapper;
	@Autowired
	IFloorService floorService;
	@Autowired
	IFloorFaService floorFaService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFloorBlockDaService floorBlockDaService;
	@Autowired
	IFloorBlockFaService floorBlockFaService;
	
	/**
	 * 添加方案
	 * 创建日期：2013-11-21下午5:09:19
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void addFloorPlan(FloorPlanBean floorPlanBean){
		floorPlanMapper.updateState(floorPlanBean);
		floorPlanMapper.addFloorPlan(floorPlanBean);
	}
	
	/**
	 * 批量插入楼层方案镜像
	 * 创建日期：2013-11-28下午2:36:37
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void batchAddFloorPlan(FloorPlanBean floorPlanBean){
		//楼层方案镜像
		List<FloorBean> floorBeanList = floorService.queryFloorByIds(floorPlanBean.getArrayMap());
		List<FloorFaBean> floorFaBeanList = new ArrayList<FloorFaBean>();
		String[] newFloorIdArray = new String[floorBeanList.size()];
		String[] newFloorNumArray = new String[floorBeanList.size()];
		int count = 0;
		try {
			for(FloorBean floorBean : floorBeanList){
				FloorFaBean floorFaBean = new FloorFaBean();
				BeanUtils.copyProperties(floorFaBean, floorBean);
				String floorId = sequenceService.getSequence("T_FLOOR_DA_SEQ");
				newFloorIdArray[count] = floorId;
				newFloorNumArray[count] = floorFaBean.getFloorNum();
				count++;
				floorFaBean.setFloorId(new Long(floorId));
				floorFaBean.setPlanId(new Long(floorPlanBean.getPlanId()));
				System.out.println("---------"+floorBean.getFloorImage());
				floorFaBean.setFloorImage(floorBean.getFloorImage());
				if(floorPlanBean.getFloorUrl() == null || "".equals(floorPlanBean.getFloorUrl())){
					floorFaBean.setFloorUrl("#");
				}
				if(floorPlanBean.getFloorImage() == null || "".equals(floorPlanBean.getFloorImage())){
					floorFaBean.setFloorImage("#");
				}
				floorFaBeanList.add(floorFaBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//楼层区块方案镜像
		String[] floorids = (String[]) floorPlanBean.getArrayMap().get("floorids");
		try {
			for(int i = 0; i < floorids.length; i++){
				List<FloorBlockDaBean> floorBlockDaBeanList = floorBlockDaService.queryBlockDaByFloorId(floorids[i]);
				List<FloorBlockFaBean> floorBlockFaBeanList = new ArrayList<FloorBlockFaBean>();
				String newFloorId = newFloorIdArray[i];
				String newFloorNum = newFloorNumArray[i];
				for(FloorBlockDaBean floorBlockDaBean : floorBlockDaBeanList){
					FloorBlockFaBean floorBlockFaBean = new FloorBlockFaBean();
					BeanUtils.copyProperties(floorBlockFaBean, floorBlockDaBean);
					floorBlockFaBean.setFloorId(newFloorId);
					floorBlockFaBean.setFloorNum(newFloorNum);
					floorBlockFaBean.setChannelNum(floorPlanBean.getChannelNum());
					//区块采集默认开启
					floorBlockFaBean.setCollectState("1");
					floorBlockFaBeanList.add(floorBlockFaBean);
				}
				floorBlockFaService.batchInsertBlockFa(floorBlockFaBeanList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//更新所属渠道下已有方案可用状态
		floorPlanMapper.updateState(floorPlanBean);
		//重新创建方案
		floorPlanMapper.addFloorPlan(floorPlanBean);
		//创建楼层方案镜像信息
		floorFaService.batchInsertFa(floorFaBeanList);
	}
	
	/**
	 * 查询楼层方案信息
	 * 创建日期：2013-11-26下午3:39:00
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorPlanBean>
	 */
	public Page queryFloorPlan(Map<String, String> paramMap){
		List<FloorPlanBean> list = floorPlanMapper.queryFloorPlan(paramMap);
        int count = floorPlanMapper.getFloorPlanCount(paramMap);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
	}
	
	/**
	 * 根据主键查看楼层方案
	 */
	public List<FloorPlanBean> queryFloorPlanByPk(String planId){
		return floorPlanMapper.queryFloorPlanByPk(planId);
	}
	
	/**
	 * 更新楼层方案可用状态
	 */
	public void updateState(FloorPlanBean floorPlanBean){
		floorPlanMapper.updateState(floorPlanBean);
	}
	
	/**
	 * 根据主键更新楼层方案可用用状态
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void changeState(Map<String, String>paramMap){
		FloorPlanBean floorPlanBean = new FloorPlanBean();
		floorPlanBean.setChannelNum(paramMap.get("channelNum"));
		floorPlanMapper.updateState(floorPlanBean);
		floorPlanMapper.changeState(paramMap);
	}

}
