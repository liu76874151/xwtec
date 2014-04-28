package com.xwtech.uomp.base.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.order.LclBeanMapper;
import com.xwtech.uomp.base.pojo.order.LclBean;
import com.xwtech.uomp.base.service.order.ICouponCodeService;
@Service("couponCodeService")
public class CouponCodeServiceImpl implements ICouponCodeService {
	@Autowired
	LclBeanMapper lclBeanMapper;
	public LclBean selectOneCouponcode() {
		return lclBeanMapper.selectOneCouponcode();
	}

	public int updateCouponcode(LclBean record) {
		lclBeanMapper.updateCouponcode(record);
		return 0;
	}

}
