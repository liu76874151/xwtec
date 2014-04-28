/**
 * Title: FloorMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-5 
 * @ time 下午3:52:24
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBean;

/**
 * @author zhanglu
 *
 */
public interface FloorMapper {

	/**
	 * 根据楼层信息查询楼层信息
	 * 创建日期：2013-11-5下午3:58:14
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBean>
	 */
	List<FloorBean> queryFloorList(Map<String, String> paramMap);
	
	/**
	 * 根据条件查询楼层信息条数
	 * 创建日期：2013-11-5下午4:54:42
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryFloorCount(Map<String, String> paramMap);
	
	/**
	 * 查找关联某个模版的楼层数量
	 * 创建日期：2013-11-8下午5:03:37
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryFloorByTemplate(Map<String,String> paramMap);
	
	/**
	 * 根据主键获取楼层
	 * 创建日期：2013-11-18下午2:15:23
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorBean
	 */
	FloorBean getFloorByPKid(Map<String,String> paramMap);
	
	/**
	 * 更新展示状态
	 * 创建日期：2013-11-6上午11:03:05
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateState(Map<String, String> paramMap);
	
	/**
	 * 添加楼层
	 * 创建日期：2013-11-13下午4:32:07
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorInfo(FloorBean floorbean);
	
	/**
	 * 修改楼层
	 * 创建日期：2013-11-19上午10:38:26
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateFloorInfo(FloorBean floorbean);
	
	/**
	 * 删除楼层
	 * 创建日期：2013-11-26下午1:42:48
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorInfo(String floorId);
	
	/**
	 * 批量查询
	 * 创建日期：2013-11-28下午1:56:35
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBean>
	 */
	List<FloorBean> queryFloorByIds(Map<String, Object> floorids);
}
