/**
 * Title: FloorBlockDaMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 下午3:05:23
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;

/**
 * @author zhanglu
 *
 */
public interface FloorBlockDaMapper {

	/**
	 * 根据楼层ID获取区块和区块内容
	 * 创建日期：2013-11-18下午2:32:25
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorBlockDaBean
	 */
	List<FloorBlockDaBean>	queryBlockDAContentByFloorId(Map<String,String> paramMap);
	List<Map<String, Object>> queryBlockByFloorId(Map<String,String> paramMap);
	
	/**
	 * 添加区块信息
	 * 创建日期：2013-11-15下午3:12:22
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorBlockInfo(FloorBlockDaBean floorBlockDaBean);
	
	/**
	 * 删除区块信息
	 * 创建日期：2013-11-19上午9:36:03
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorBlock(String floorId);
	
	/**
	 * 根据楼层ID获取区块内容ID
	 * 创建日期：2013-11-26下午2:08:54
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryBlockContentId(Map<String,String> paramMap);
	
	/**
	 * 根据楼层ID获取区块信息
	 * 创建日期：2013-11-28下午3:43:15
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBlockDaBean>
	 */
	List<FloorBlockDaBean> queryBlockDaByFloorId(String floorId);
	
}
