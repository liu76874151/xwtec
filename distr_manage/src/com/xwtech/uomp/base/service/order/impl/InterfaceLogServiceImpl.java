package com.xwtech.uomp.base.service.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.order.InterfaceLogBeanMapper;
import com.xwtech.uomp.base.pojo.order.InterfaceLogBean;
import com.xwtech.uomp.base.service.order.IInterfaceLogService;
@Service("interfaceLogService")
public class InterfaceLogServiceImpl implements IInterfaceLogService {
@Autowired
InterfaceLogBeanMapper interfaceLogBeanMapper;
	public void saveInterfaceLog(InterfaceLogBean interfaceLogBean) {
		interfaceLogBeanMapper.saveInterfaceLogBean(interfaceLogBean);
	}

}
