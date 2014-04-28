package com.xwtech.uomp.base.service.verify.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.verify.AuditLogMapper;
import com.xwtech.uomp.base.pojo.verify.AuditLogBean;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.service.verify.IAuditLogService;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-16 上午11:26:06
 */
@Service("auditLogServiceImpl")
public class AuditLogServiceImpl implements IAuditLogService {
    
    @Autowired
    AuditLogMapper auditLogMapper;
    @Autowired
    ISequenceService sequenceService;

    public int deleteByPrimaryKey(Long pkid) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int insert(AuditLogBean auditLogBean) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int insertSelective(AuditLogBean auditLogBean) {
	auditLogBean.setPkid(Long.parseLong(sequenceService.getSequence("AUDIT_LOG_PKID_SEQ")));
	return auditLogMapper.insertSelective(auditLogBean);
    }

    public AuditLogBean selectByPrimaryKey(Long pkid) {
	// TODO Auto-generated method stub
	return null;
    }

    public int updateByPrimaryKey(AuditLogBean auditLogBean) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int updateByPrimaryKeySelective(AuditLogBean auditLogBean) {
	// TODO Auto-generated method stub
	return 0;
    }

}
