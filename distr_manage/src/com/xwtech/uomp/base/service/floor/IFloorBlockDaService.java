/**
 * Title: IFloorBlockDaService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 下午3:13:11
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBlockDaBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorBlockDaService {
	
	/**
	 * 根据楼层ID获取区块
	 * 创建日期：2013-11-18下午2:33:54
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorBlockDaBean
	 */
	List<Map<String, Object>> queryBlockByFloorId(Map<String, String> paramMap);
	List<FloorBlockDaBean>	queryBlockDAContentByFloorId(Map<String,String> paramMap);
	
	/**
	 * 添加区块信息
	 * 创建日期：2013-11-15下午3:13:40
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addFloorBlockInfo(FloorBlockDaBean floorBlockDaBean);
	
	/**
	 * 删除区块信息
	 * 创建日期：2013-11-19上午9:38:00
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteFloorBlock(String floorId);

	/**
	 * 根据楼层ID获取区块信息
	 * 创建日期：2013-11-28下午3:43:52
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBlockDaBean>
	 */
	List<FloorBlockDaBean> queryBlockDaByFloorId(String floorId);
}
