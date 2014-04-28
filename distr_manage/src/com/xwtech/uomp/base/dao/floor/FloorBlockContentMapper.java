/**
 * Title: FloorBlockContent.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-15 
 * @ time 上午9:21:09
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.floor.FloorBlockContentBean;

/**
 * @author zhanglu
 *
 */
public interface FloorBlockContentMapper {

	void addBlockContent(FloorBlockContentBean floorBlockContentBean);
	
	void deleteBlockContentInfo(List<String> list);
	
	List<FloorBlockContentBean> queryBlockContentByFloorId(String floorId);
	
	void updateBlockContentInfo(Map<String, String> map) ;
	
}
