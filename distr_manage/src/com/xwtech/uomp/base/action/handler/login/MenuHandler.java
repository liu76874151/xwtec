package com.xwtech.uomp.base.action.handler.login;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.pojo.MenuInfoBean;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.service.ISubSystemService;
import com.xwtech.uomp.base.util.SessionUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("H_menu")
public class MenuHandler implements IHandler {

    private final static Logger log = Logger.getLogger(MenuHandler.class);

    @Autowired
    ISubSystemService subSystemService;

    @Autowired
    ILoginService loginService;

    public HandlerResult getMenuInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> menuMap = new HashMap<String, Object>();
        try {
            UserInfoBean userInfo = (UserInfoBean) SessionUtil.getObjectAttribute(context.getRequest(), RequestConstants.ADMIN_SESSION_KEY);
            List<SubSystemBean> subSystemList = subSystemService.querySubSystemAll();
            Map<String, List<TreeNode>> funInfos = loginService.getFunInfo(userInfo.getLoginName());
            List<MenuInfoBean> menuList = loginService.getMenuInfo(funInfos, subSystemList);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUserName(userInfo.getUserName());
            menuMap.put("list", menuList);
            menuMap.put("user", userInfo);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setRetObj(menuMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setResMsg("获取模块信息失败！");
        }
        return result;
    }

    public HandlerResult getCustomFunInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> menuMap = new HashMap<String, Object>();
        try {
            UserInfoBean userInfo = (UserInfoBean) SessionUtil.getObjectAttribute(context.getRequest(), RequestConstants.ADMIN_SESSION_KEY);
            // List<SubSystemBean> subSystemList = subSystemService.querySubSystemAll();
            // Map<String,List<TreeNode>> funInfos = loginService.getCustomFunInfo(userInfo.getLoginName());
            // List<MenuInfoBean> menuList = loginService.getMenuInfo(funInfos, subSystemList);
            // menuMap.put("list", menuList);
            List<TreeNode> list = loginService.queryCustomMenuByUser(userInfo.getLoginName());
            TreeNode titleTree = new TreeNode();
            titleTree.setId("-1");
            titleTree.setPid("-2");
            titleTree.setText("自定义菜单");
            TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list, titleTree);
            menuMap.put("user", userInfo);
            menuMap.put("root", root);
            result.setRetObj(menuMap);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setResMsg("获取模块信息失败！");
        }
        return result;
    }
}
