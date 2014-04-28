/**
 * Title: IFloorBlockFaService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-26 
 * @ time 上午10:23:41
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBlockFaBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorBlockFaService {

	/**
	 * 批量保存楼层区块信息
	 * 创建日期：2013-11-26上午10:24:09
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void batchInsertBlockFa(List<FloorBlockFaBean> list);
	
	/**
	 * 根据楼层ID获取楼层方案
	 * 创建日期：2013-11-26下午7:09:30
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:FloorBlockFaBean
	 */
	List<FloorBlockFaBean> queryFloorBlockFa(String floorId);
	
	/**
	 * 更改楼层区块采集状态
	 * 创建日期：2013-11-26下午7:56:15
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateCollectState(List<FloorBlockFaBean> floorBlockFaList);
	
}
