/**
 * Title: FloorPlanMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-21 
 * @ time 下午4:40:18
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorPlanBean;

/**
 * @author zhanglu
 *
 */
public interface FloorPlanMapper {

	/**
	 * 添加方案
	 * 创建日期：2013-11-21下午4:52:14
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorPlan(FloorPlanBean floorPlanBean);
	
	/**
	 * 查询楼层方案
	 * 创建日期：2013-11-26下午3:36:47
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorPlanBean>
	 */
	List<FloorPlanBean> queryFloorPlan(Map<String, String> paramMap);
	
	/**
	 * 根据条件获取楼层方案信息个数
	 * 创建日期：2013-11-26下午4:05:34
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int getFloorPlanCount(Map<String, String> paramMap);
	
	/**
	 * 根据主键查看楼层方案信息
	 * 创建日期：2013-11-26下午6:37:10
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorPlanBean
	 */
	List<FloorPlanBean> queryFloorPlanByPk(String planId);
	
	/**
	 * 更新楼层方案可用状态
	 * 创建日期：2013-11-27下午3:12:10
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateState(FloorPlanBean floorPlanBean);
	
	/**
	 * 根据主键楼层方案可用状态
	 * 创建日期：2013-11-29下午4:19:05
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void changeState(Map<String, String>paramMap);
}
