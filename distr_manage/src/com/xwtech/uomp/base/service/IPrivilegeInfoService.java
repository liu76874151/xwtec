package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

public interface IPrivilegeInfoService {
    public List<TreeNode> getUserTree();

    public List<TreeNode> getPrivilegeTree(String userId, String userType);

    public void modifyPrivilegeInfo(String userId, String userType, Map<String, String> subSysNumAndPrivilege);
}
