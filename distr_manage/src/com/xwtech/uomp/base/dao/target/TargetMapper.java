/**
 * Title: TargetMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-7 
 * @ time 下午7:12:52
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.target;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.target.TargetBean;

/**
 * @author zhanglu
 *
 */
public interface TargetMapper {

	/**
	 * 查询目标用户组
	 * 创建日期：2013-12-7下午7:40:47
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<TargetBean>
	 */
	List<TargetBean> queryTargetByPage(Map<String, String> param);
	
	/**
	 * 查询目标用户组个数
	 * 创建日期：2013-12-7下午7:54:32
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryTargetCount(Map<String, String> param);
}
