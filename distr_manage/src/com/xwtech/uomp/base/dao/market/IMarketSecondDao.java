package com.xwtech.uomp.base.dao.market;

public interface IMarketSecondDao {
	public boolean sort(String[] marketSecondPkids,String marketSecondPkid);
	public boolean sortZT(String[] marketSecondPkids,String marketSecondPkid);
	public String queryMarketSecondBossCode(String pkid);

}
