/**
 * Title: ITargetService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-7 
 * @ time 下午7:43:13
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.target;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;

/**
 * @author zhanglu
 * 
 */
public interface ITargetService {

	/**
	 * 查询目标用户组
	 * 创建日期：2013-12-7下午7:43:50
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	Page queryTargetByPage(Map<String, String> param);

}
