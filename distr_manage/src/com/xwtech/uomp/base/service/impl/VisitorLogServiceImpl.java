package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.visitor.IVisitorLogDAO;
import com.xwtech.uomp.base.pojo.admin.VisitorLogBean;
import com.xwtech.uomp.base.service.IVisitorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("visitorLogService")
public class VisitorLogServiceImpl implements IVisitorLogService {
    @Autowired
    IVisitorLogDAO visitorLogDAO;

    public void batchAddLog(List<VisitorLogBean> logBean) {
        visitorLogDAO.batchAddLog(logBean);

    }


}
