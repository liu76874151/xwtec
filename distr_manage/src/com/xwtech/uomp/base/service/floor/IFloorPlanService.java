/**
 * Title: IFloorPlanService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-21 
 * @ time 下午5:05:38
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorPlanBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorPlanService {

	/**
	 * 添加方案
	 * 创建日期：2013-11-21下午5:07:07
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorPlan(FloorPlanBean floorPlanBean);
	
	/**
	 * 查询楼层方案信息
	 * 创建日期：2013-11-26下午3:39:55
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorPlanBean>
	 */
	Page queryFloorPlan(Map<String, String> paramMap);
	
	/**
	 * 根据主键查看楼层方案信息
	 * 创建日期：2013-11-26下午6:37:57
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorPlanBean
	 */
	List<FloorPlanBean> queryFloorPlanByPk(String planId);
	
	/**
	 * 更新楼层方案可用状态
	 * 创建日期：2013-11-27下午3:13:25
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateState(FloorPlanBean floorPlanBean);

	/**
	 * 批量插入楼层方案镜像
	 * 创建日期：2013-11-28下午2:38:44
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void batchAddFloorPlan(FloorPlanBean floorPlanBean);
	
	/**
	 * 根据主键更新方案可用状态
	 * 创建日期：2013-11-29下午4:23:36
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void changeState(Map<String, String>paramMap);
}
