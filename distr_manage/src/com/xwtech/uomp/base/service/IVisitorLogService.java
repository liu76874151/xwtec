package com.xwtech.uomp.base.service;

import java.util.List;

import com.xwtech.uomp.base.pojo.admin.VisitorLogBean;

public interface IVisitorLogService {
    void batchAddLog(List<VisitorLogBean> logBean);
}
