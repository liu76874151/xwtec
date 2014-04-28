/**
 * Title: FloorBlockDsBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-11 
 * @ time 下午2:22:46
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

/**
 * @author zhanglu
 *
 */
public class FloorBlockDsBean {

	/**
	 * 数据源服务名
	 */
	String dsService;
	/**
	 * 数据源名
	 */
	String dsName;
	/**
	 * 数据源描述
	 */
	String dsDesc;

	/**
	 * @return the dsService
	 */
	public String getDsService() {
		return dsService;
	}
	/**
	 * @param dsService the dsService to set
	 */
	public void setDsService(String dsService) {
		this.dsService = dsService;
	}
	/**
	 * @return the dsName
	 */
	public String getDsName() {
		return dsName;
	}
	/**
	 * @param dsName the dsName to set
	 */
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
	/**
	 * @return the dsDesc
	 */
	public String getDsDesc() {
		return dsDesc;
	}
	/**
	 * @param dsDesc the dsDesc to set
	 */
	public void setDsDesc(String dsDesc) {
		this.dsDesc = dsDesc;
	}
	
}
