/**
 * Title: ConsoleMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-30 
 * @ time 下午2:04:36
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.console;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.console.ConsoleBean;

/**
 * @author zhanglu
 *
 */
public interface ConsoleMapper {

	List<ConsoleBean> queryOrderNumByMonth(Map<String, String> param);
	
	List<ConsoleBean> queryCountByCity(Map<String, String> param);
	
	List<ConsoleBean> querySumMoneyByCity(Map<String, String> param);
	
	List<ConsoleBean> querySumMoneyByMonth(Map<String, String> param);
	
}
