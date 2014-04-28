package com.xwtech.uomp.base.dao.order;

import com.xwtech.uomp.base.pojo.order.LclBean;

public interface LclBeanMapper {
	LclBean selectOneCouponcode();

    int updateCouponcode(LclBean record);
}