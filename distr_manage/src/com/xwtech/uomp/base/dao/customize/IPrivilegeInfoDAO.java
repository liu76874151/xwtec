package com.xwtech.uomp.base.dao.customize;

import java.util.List;
import java.util.Map;

public interface IPrivilegeInfoDAO {
    public List<Map<String, Object>> queryUserInfo();

    public List<Map<String, Object>> queryPrivilegeInfo(String userId, String userType);

    public void modifyPrivilegeInfo(String userId, String userType, Map<String, String> subSysNumAndPrivilege);
}
