package com.xwtech.uomp.base.service.verify;

import com.xwtech.uomp.base.pojo.verify.AuditLogBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-16 上午11:25:59
 */
public interface IAuditLogService {
    
    int deleteByPrimaryKey(Long pkid);

    int insert(AuditLogBean auditLogBean);

    int insertSelective(AuditLogBean auditLogBean);

    AuditLogBean selectByPrimaryKey(Long pkid);

    int updateByPrimaryKeySelective(AuditLogBean auditLogBean);

    int updateByPrimaryKey(AuditLogBean auditLogBean);
    
}