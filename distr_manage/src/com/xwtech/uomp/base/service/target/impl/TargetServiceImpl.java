/**
 * Title: TargetService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-12-7 
 * @ time 下午7:45:52
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.target.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.target.TargetMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.target.TargetBean;
import com.xwtech.uomp.base.service.target.ITargetService;

/**
 * @author zhanglu
 * 
 */
@Service("targetService")
public class TargetServiceImpl implements ITargetService {

	@Autowired
	TargetMapper targetMapper;

	/**
	 * 根据条件查询目标用户组
	 */
	public Page queryTargetByPage(Map<String, String> param) {

		List<TargetBean> list = targetMapper.queryTargetByPage(param);
		int count = targetMapper.queryTargetCount(param);

		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);

		return page;

	}

}
