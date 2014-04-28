/**
 * Title: IconsoleServiceImpl.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-30 
 * @ time 下午2:13:53
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.console;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.console.ConsoleBean;

/**
 * @author zhanglu
 *
 */
public interface IconsoleService {

	List<ConsoleBean> queryOrderNumByMonth(Map<String, String> param);
	
	List<ConsoleBean> queryCountByCity(Map<String, String> param);
	
	List<ConsoleBean> querySumMoneyByCity(Map<String, String> param);
	
	List<ConsoleBean> querySumMoneyByMonth(Map<String, String> param);
}
