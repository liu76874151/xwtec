package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.customize.IPrivilegeInfoDAO;
import com.xwtech.uomp.base.pojo.dhtmlx.CheckedTreeNode;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IPrivilegeInfoService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("privilegeInfoService")
public class PrivilegeInfoServiceImpl implements IPrivilegeInfoService {
    protected static final Logger log = Logger.getLogger(PrivilegeInfoServiceImpl.class);

    @Autowired
    IPrivilegeInfoDAO privilegeInfoDAO;

    public List<TreeNode> getUserTree() {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = privilegeInfoDAO.queryUserInfo();
            if (listResult == null || listResult.size() <= 0) {
                return treeNodeList;
            }
            treeNodeList = new ArrayList<TreeNode>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = DhtmlTreeUtil.getTreeNode(dataMap);
                treeNodeList.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }

    public List<TreeNode> getPrivilegeTree(String userId, String userType) {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = privilegeInfoDAO.queryPrivilegeInfo(userId, userType);
            if (listResult == null || listResult.size() <= 0) {
                return treeNodeList;
            }
            treeNodeList = new ArrayList<TreeNode>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = listResult.get(i);
                CheckedTreeNode node = DhtmlTreeUtil.getCheckedTreeNode(dataMap);
                treeNodeList.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }

    public void modifyPrivilegeInfo(String userId, String userType, Map<String, String> subSysNumAndPrivilege) {
        privilegeInfoDAO.modifyPrivilegeInfo(userId, userType, subSysNumAndPrivilege);
    }
}