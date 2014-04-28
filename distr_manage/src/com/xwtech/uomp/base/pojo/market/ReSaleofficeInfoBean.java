package com.xwtech.uomp.base.pojo.market;

public class ReSaleofficeInfoBean {
	/*
	 * 一级营销方案主键标识
	 */
    private String marketFirstPkid;
    /*
     * 营业厅编码：地市|区县|街道|编码
     */
    private String officeNum;
    /*
     * 营业厅类型（营业厅，网点，自营店，其他）
     */
    private String officeType;
    /*
     * 营业厅名称
     */
    private String officeName;
    /*
     * 营业厅所属区县编码
     */
    private String officeBoroughNum;
    /*
     * 营业厅所属区县名称
     */
    private String officeBoroughName;
    /*
     * 营业厅地址
     */
    private String officeAddr;
    /*
     * 联系电话
     */
    private String officeTel;
    /*
     * 联系人
     */
    private String officeConcator;
    /*
     * 营业厅所属地市
     */
    private String officeCity;
    
    
	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}
	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}
	public String getOfficeNum() {
		return officeNum;
	}
	public void setOfficeNum(String officeNum) {
		this.officeNum = officeNum;
	}
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeBoroughNum() {
		return officeBoroughNum;
	}
	public void setOfficeBoroughNum(String officeBoroughNum) {
		this.officeBoroughNum = officeBoroughNum;
	}
	public String getOfficeBoroughName() {
		return officeBoroughName;
	}
	public void setOfficeBoroughName(String officeBoroughName) {
		this.officeBoroughName = officeBoroughName;
	}
	public String getOfficeAddr() {
		return officeAddr;
	}
	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getOfficeConcator() {
		return officeConcator;
	}
	public void setOfficeConcator(String officeConcator) {
		this.officeConcator = officeConcator;
	}
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
    
}
