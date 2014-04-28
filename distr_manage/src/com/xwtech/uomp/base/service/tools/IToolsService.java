package com.xwtech.uomp.base.service.tools;

/**
 *@ClassName:IToolsService.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-29 time：上午11:17:18
 *@version 1.0
 */
public interface IToolsService {
	
    /**
     * boss表中的城市编码转换成用户一般编码
     * eg:14-->250
     *
     * @param cityCode
     * @return
     */
	 String reverseCityCode(String cityCode);
	 String reverseCityBOSSCode(String cityCode);
}
