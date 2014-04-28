package com.xwtech.uomp.base.dao.boss;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.boss.BossGiftInfoBean;

/**
 * @author zhangel
 */
public interface BossGiftInfoMapper {
	
	List<BossGiftInfoBean> queryBossGiftInfoList(Map<String, String> map);
	
	BossGiftInfoBean queryOneBossGiftInfo(Map<String, String> param);

	void update(BossGiftInfoBean bossGiftInfoBean);

	void insert(BossGiftInfoBean bossGiftInfoBean);
	void insertGiftSecond(BossGiftInfoBean bossGiftInfoBean);

	void deleteBossGiftInfo(String MarketFirstCode);
	void deleteBossGiftInfoSecond(String MarketFirstCode);

	
	List<BossGiftInfoBean> selectBySecondPkid(Map<String, String> map);

}