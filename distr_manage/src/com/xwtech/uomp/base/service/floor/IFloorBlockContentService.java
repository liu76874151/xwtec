/**
 * Title: IFloorBlockContentService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 下午2:44:19
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;

/**
 * @author zhanglu
 *
 */
public interface IFloorBlockContentService {

	/**
	 * 添加区块内容
	 * 创建日期：2013-11-15下午2:49:46
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void addBlockContent(FloorBlockContentBean floorBlockContentBean);
	
	/**
	 * 批量删除区块内容
	 * 创建日期：2013-11-19上午10:48:46
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void deleteBlockContentInfo(List<String> list);
	
	/**
	 * 根据楼层Id获取区块内容
	 * 创建日期：2013-11-26下午2:32:17
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<FloorBlockContentBean>
	 */
	List<FloorBlockContentBean> queryBlockContentByFloorId(String floorId);
	
	void updateBlockContentInfo(Map<String,String> map);
	void updateBlockImg(String[] contentId,String[] contentImg);
}
