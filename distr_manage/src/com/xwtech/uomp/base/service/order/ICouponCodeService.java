package com.xwtech.uomp.base.service.order;

import com.xwtech.uomp.base.pojo.order.LclBean;

public interface ICouponCodeService {
	LclBean selectOneCouponcode();

    int updateCouponcode(LclBean record);
}
