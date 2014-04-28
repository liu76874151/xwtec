package com.xwtech.uomp.base.service.tools.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.tools.IToolsDAO;
import com.xwtech.uomp.base.service.tools.IToolsService;
/**
 *@ClassName:ToolsServiceImpl.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-29 time：上午11:17:25
 *@version 1.0
 */
@Service("toolsService")
public class ToolsServiceImpl implements IToolsService {
	@Autowired
	IToolsDAO toolsDAO;
	
    /**
     * boss表中的城市编码转换成用户一般编码
     * eg:14-->250
     *
     * @param cityCode
     * @return
     */
	public String reverseCityCode(String cityCode) {
		return toolsDAO.reverseCityCode(cityCode);
	}

	public String reverseCityBOSSCode(String cityCode) {
		return toolsDAO.reverseCityBOSSCode(cityCode);
	}

}
