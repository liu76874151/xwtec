package com.xwtech.uomp.base.pojo.boss;

/**
 *@ClassName:REBossMarketFirstBean.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-10-25 time：下午03:43:15
 *@version 1.0
 */
public class REBossMarketFirstBean {
		/*
		 * 一级营销方案系统编码
		 */
	   private String marketLevel1Id;
	   /*
	    * 一级营销方案名称
	    */
	    private String marketLevel1Name;
	    /*
	     * 地市编码（南京：14）
	     */
	    private String city;
	    /*
	     * 开始时间
	     */
	    private String startTime;
	    /*
	     * 结束时间
	     */
	    private String endTime;
	    /*
	     * 营销案类型：0-一般营销案，1-宽带营销案
	     */
	    private String type;
	    /*
	     * 0-失效；1-正常
	     */
	    private String useFlag;
	    /*
	     * 0-未审核；1-审核通过
	     */
	    private String ischeck;
	    /*
	     * 一级营销案boss编码,保留用
	     */
	    private String maketLevel1Boss;
	    /*
	     * 品牌
	     */
	    private String brand;

		public String getMarketLevel1Id() {
			return marketLevel1Id;
		}

		public void setMarketLevel1Id(String marketLevel1Id) {
			this.marketLevel1Id = marketLevel1Id;
		}

		public String getMarketLevel1Name() {
			return marketLevel1Name;
		}

		public void setMarketLevel1Name(String marketLevel1Name) {
			this.marketLevel1Name = marketLevel1Name;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getUseFlag() {
			return useFlag;
		}

		public void setUseFlag(String useFlag) {
			this.useFlag = useFlag;
		}

		public String getIscheck() {
			return ischeck;
		}

		public void setIscheck(String ischeck) {
			this.ischeck = ischeck;
		}

		public String getMaketLevel1Boss() {
			return maketLevel1Boss;
		}

		public void setMaketLevel1Boss(String maketLevel1Boss) {
			this.maketLevel1Boss = maketLevel1Boss;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}
	    

}
