/**
 * Title: ConsoleService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-30 
 * @ time 下午2:17:52
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.console.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.console.ConsoleMapper;
import com.xwtech.uomp.base.pojo.console.ConsoleBean;
import com.xwtech.uomp.base.service.console.IconsoleService;

/**
 * @author zhanglu
 *
 */
@Service("consoleService")
public class ConsoleServiceImpl  implements IconsoleService{

	@Autowired
	ConsoleMapper consoleMapper;
	
	public List<ConsoleBean> queryOrderNumByMonth(Map<String, String> param){
		return consoleMapper.queryOrderNumByMonth(param);
	}
	
	public List<ConsoleBean> queryCountByCity(Map<String, String> param){
		return consoleMapper.queryCountByCity(param);
	}
	
	public List<ConsoleBean> querySumMoneyByCity(Map<String, String> param){
		return consoleMapper.querySumMoneyByCity(param);
	}
	
	public List<ConsoleBean> querySumMoneyByMonth(Map<String, String> param){
		return consoleMapper.querySumMoneyByMonth(param);
	}
	
}
