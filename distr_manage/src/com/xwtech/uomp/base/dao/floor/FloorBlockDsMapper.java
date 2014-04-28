/**
 * Title: FloorBlockDsMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-11 
 * @ time 下午2:25:21
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.floor;

import java.util.List;

import com.xwtech.uomp.base.pojo.floor.FloorBlockDsBean;

/**
 * @author zhanglu
 *
 */
public interface FloorBlockDsMapper {

	List<FloorBlockDsBean> queryBlockDsList();
	
}
