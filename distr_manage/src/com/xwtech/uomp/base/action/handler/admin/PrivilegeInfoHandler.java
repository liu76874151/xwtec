package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IPrivilegeInfoService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component("H_privilegeinfo")
public class PrivilegeInfoHandler implements IHandler {
    @Autowired
    IPrivilegeInfoService privilegeInfoService;

    public HandlerResult queryUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            List<TreeNode> list = privilegeInfoService.getUserTree();
            TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list);
            result.setRetObj(root);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    public HandlerResult viewPrivilegeInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        // 用户ID，用户组或用户ID
        String userId = context.getRequest().getParameter("userId");
        // 用户类型，0为用户组，1为用户
        String userType = context.getRequest().getParameter("userType");
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userType)) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
            return result;
        }
        try {
            List<TreeNode> list = privilegeInfoService.getPrivilegeTree(userId, userType);
            TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list);
            result.setRetObj(root);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    public HandlerResult modifyPrivilegeInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String userId = context.getRequest().getParameter("userId");
        String userType = context.getRequest().getParameter("userType");
        String selectedSubSysNum = context.getRequest().getParameter("selectedSubSysNum");
        String selectedPrivilege = context.getRequest().getParameter("selectedPrivilege");

        // 参数传入错误
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userType)) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
            return result;
        }

        Map<String, String> map = new HashMap<String, String>();
        try {
            if (StringUtils.isNotBlank(selectedSubSysNum) && StringUtils.isNotBlank(selectedPrivilege)) {
                String[] subSysNums = selectedSubSysNum.split(",");
                String[] privileges = selectedPrivilege.split(",");
                if (subSysNums.length == privileges.length) {
                    for (int i = 0; i < subSysNums.length; i++) {
                        String subSysNum = subSysNums[i];
                        if (StringUtils.isNotEmpty(subSysNum)) {
                            String temp = "";
                            if (map.containsKey(subSysNum)) {
                                temp = map.get(subSysNum);
                            }
                            temp += "'" + privileges[i] + "',";
                            map.put(subSysNum, temp);
                        }
                    }
                    // 处理权限信息字符串，去掉最后多余的逗号","
                    for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
                        String subSysNum = (String) iter.next();
                        String privilege = map.get(subSysNum);
                        if ("".equals(privilege)) {
                            privilege = "''";
                        } else {
                            privilege = privilege.substring(0, privilege.length() - 1);
                        }
                        map.put(subSysNum, privilege);
                    }
                }
            }
            privilegeInfoService.modifyPrivilegeInfo(userId, userType, map);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_ERROR);
        }

        return result;
    }
}
