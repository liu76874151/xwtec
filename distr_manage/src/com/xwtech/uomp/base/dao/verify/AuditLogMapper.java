package com.xwtech.uomp.base.dao.verify;

import com.xwtech.uomp.base.pojo.verify.AuditLogBean;


/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-13 下午05:30:23
 */
public interface AuditLogMapper {
    
    int deleteByPrimaryKey(Long pkid);

    int insert(AuditLogBean auditLogBean);

    int insertSelective(AuditLogBean auditLogBean);

    AuditLogBean selectByPrimaryKey(Long pkid);

    int updateByPrimaryKeySelective(AuditLogBean auditLogBean);

    int updateByPrimaryKey(AuditLogBean auditLogBean);
}