package com.xwtech.uomp.base.service.market.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xwtech.uomp.base.dao.market.ReSaleofficeInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.ReSaleofficeInfoBean;
import com.xwtech.uomp.base.service.market.IReSaleOfficeInfoService;
/**
 *@ClassName:ReSaleOfficeInfoServiceImpl.java
 *@Description：
 *@author: xushiwei
 *@date： date：2013-11-1 time：下午03:09:41
 *@version 1.0
 */
@Service("reSaleOfficeInfoService")
public class ReSaleOfficeInfoServiceImpl implements IReSaleOfficeInfoService {
	@Autowired
	ReSaleofficeInfoMapper reSaleofficeInfoMapper;
	public Page queryReSaleOfficeInfo(String marketFirstPkid) {
		List<ReSaleofficeInfoBean> list=reSaleofficeInfoMapper.queryReSaleOfficeInfo(marketFirstPkid);
		Page page=new Page();
		page.setRecords(list);
		return page;
	}

}
