package com.xwtech.uomp.base.pojo.boss.ecp;

public class CPresentGoodspackcfgdt {
	/*
	 * 
	 * 礼品包信息
	 */
	public String presentgoodspackcfg_state	           ="";//1	long		是否有效 0表示“无效”，1表示“有效”
	public String presentgoodspackcfg_pack_name	       ="";//1	string		物品包名称 不超过16个汉字；
	public String presentgoodspackcfg_pack_id	       ="";//1	string		赠送物品包编码,地市+序列号（就使用营销方案编号的序列号seq_plan_id）
	public String presentgoodspackcfg_plan_id	       ="";//1	string		营销方案编码

}
