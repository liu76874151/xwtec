/**
 * Title: IFloorService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-5 
 * @ time 下午4:45:05
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.floor.FloorBean;
import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorService {

	/**
	 * 根据条件查询楼层信息
	 * 创建日期：2013-11-5下午4:45:54
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBean>
	 */
	Page queryFloorList(Map<String, String> paramMap);
	
	/**
	 * 查找关联某个模版的楼层数量
	 * 创建日期：2013-11-8下午5:05:03
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryFloorByTemplate(Map<String,String> paramMap);
	
	/**
	 * 根据主键获取楼层
	 * 创建日期：2013-11-18下午2:16:10
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorBean
	 */
	FloorBean getFloorByPKid(Map<String,String> paramMap);
	
	/**
	 * 更新展示状态
	 * 创建日期：2013-11-6上午11:03:52
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateState(Map<String, String> paramMap);
	
	/**
	 * 添加楼层
	 * 创建日期：2013-11-13下午4:32:55
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	String addFloorInfo(FloorBean floorbean);
	
	/**
	 * 修改楼层
	 * 创建日期：2013-11-19上午10:38:53
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	String updateFloorInfo(FloorBean floorBean);
	
	/**
	 * 删除楼层
	 * 创建日期：2013-11-26下午1:43:53
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorInfo(String floorId);
	
	/**
	 * 批量查询
	 * 创建日期：2013-11-28下午1:57:33
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBean>
	 */
	List<FloorBean> queryFloorByIds(Map<String, Object> floorids);
	
}
