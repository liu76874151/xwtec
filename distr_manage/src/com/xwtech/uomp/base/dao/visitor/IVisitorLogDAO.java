package com.xwtech.uomp.base.dao.visitor;

import java.util.List;

import com.xwtech.uomp.base.pojo.admin.VisitorLogBean;

public interface IVisitorLogDAO {
    void batchAddLog(List<VisitorLogBean> logBean);
}
