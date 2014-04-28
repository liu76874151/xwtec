package com.xwtech.uomp.base.dao.market;

public interface IMarketFirstDao {
	/**
	 * 更新营销案展示顺序(MARKET_ORDER)
	 * @param marketFirstPkids
	 * @param marketFirstPkid
	 * @return
	 */
	public boolean sort(String[] marketFirstPkids,String marketFirstPkid);
	public boolean sortZT(String[] marketFirstPkids,String marketFirstPkid);
}
