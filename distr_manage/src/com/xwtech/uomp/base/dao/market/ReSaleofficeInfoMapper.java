package com.xwtech.uomp.base.dao.market;

import java.util.List;

import com.xwtech.uomp.base.pojo.market.ReSaleofficeInfoBean;



public interface ReSaleofficeInfoMapper {
	/**
	 * 查询营业厅
	 * @param marketFirstPkid
	 * @return
	 */
	List<ReSaleofficeInfoBean> queryReSaleOfficeInfo(String marketFirstPkid);
    //int insertSelective(ReSaleofficeInfoBean record);
}