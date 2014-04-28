/**
 * Title: ZoneInfoMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-15 
 * @ time 上午11:10:25
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.org;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.org.ZoneInfoBean;

/**
 * @author zhanglu
 *
 */
public interface ZoneInfoMapper {

	List<ZoneInfoBean> queryZoneByCity(Map<String, String> paramMap);

	/**
	 * 根据parentId 查询boss_code
	 * @param parentId
	 * @return  
	 * @author unique
	 */
	List<ZoneInfoBean> queryZoneByParentId(String parentId);
	
	List<ZoneInfoBean> queryBossAreaList(Map<String, String> paramMap);
	
}
