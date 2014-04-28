package com.xwtech.uomp.base.dao.tools;

public interface IToolsDAO {
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
