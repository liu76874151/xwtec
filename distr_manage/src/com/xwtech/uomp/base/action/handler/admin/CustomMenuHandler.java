package com.xwtech.uomp.base.action.handler.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.RequestConstants;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.CustomMenuBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.ICustomMenuService;
import com.xwtech.uomp.base.util.SessionUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CustomMenuHandler
 * @Description: 自定义菜单管理
 * @date 2013-2-27 下午3:03:44
 */
@Component("H_customMenu")
public class CustomMenuHandler implements IHandler {

    @Autowired
    ICustomMenuService customMenuService;

    /**
     * @return void
     * @Title: queryCustomMenuInfo
     * @Description: 查询所有的菜单，根据已经登录的用户来判断登录人所拥有的菜单
     * @date 2013-2-27 下午4:11:46
     */
    public HandlerResult queryCustomMenuInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            //UserInfoBean userInfoBean=(UserInfoBean) context.getRequest().getSession().getAttribute("admin");
            UserInfoBean userInfoBean = (UserInfoBean) SessionUtil.getObjectAttribute(context.getRequest(), RequestConstants.ADMIN_SESSION_KEY);
            String loginUserCode = userInfoBean.getLoginName(); //根据登录名来查询用户信息  
            if (null != loginUserCode && !"".equals(loginUserCode)) {
                List<TreeNode> list = customMenuService.queryCustomMenuByUser(loginUserCode);
                TreeNode titleTree = new TreeNode();
                titleTree.setId("01");
                titleTree.setText("自定义菜单");
                TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list, titleTree);
                result.setRetObj(root);
            } else {
                // 返回错误提示信息
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
            }
        } catch (Exception e) {
            // 返回错误提示信息
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    /**
     * @return HandlerResult
     * @Title: queryCustomMenuInfo
     * @Description: 查看功能的详细的信息
     * @date 2013-3-3 下午7:16:21
     */
    public HandlerResult viewCustomMenu(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();
        UserInfoBean userInfoBean = (UserInfoBean) context.getRequest().getSession().getAttribute("admin");
        String funcId = context.getRequest().getParameter("funcId").trim();
        String subSysNum = context.getRequest().getParameter("subSysNum").trim();
        String jbNum = context.getRequest().getParameter("jbNum").trim();
        String loginUserCode = userInfoBean.getLoginName().trim();
        if (loginUserCode == null || loginUserCode == "" || subSysNum == null || subSysNum == "" || jbNum == null || jbNum == "" || funcId == "" || funcId == null) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }
        List<CustomMenuBean> customMenuList = null;
        try {
            CustomMenuBean bean = new CustomMenuBean(subSysNum, loginUserCode, "", funcId, jbNum, 0, 0, "");
            customMenuList = customMenuService.viewCustomMenu(bean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }
        map.put("customMenuList", customMenuList);
        result.setRetObj(map);
        return result;
    }


    /**
     * @return HandlerResult
     * @Title: deleteCustomMenu
     * @Description: 删除菜单节点选项
     * @date 2013-3-5 下午8:34:12
     */
    public HandlerResult deleteCustomMenu(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map2 = new HashMap<String, Object>();
        UserInfoBean userInfoBean = (UserInfoBean) context.getRequest().getSession().getAttribute("admin");
        String loginUserCode = userInfoBean.getLoginName().trim();
        String funcId = context.getRequest().getParameter("funcId").trim();
        String subSysNum = context.getRequest().getParameter("subSysNum").trim();
        String jbNum = context.getRequest().getParameter("jbNum").trim();
        map2.put("loginUserCode", loginUserCode);
        map2.put("jbNum", jbNum);
        if (loginUserCode == null || loginUserCode == "" || funcId == null || funcId == "" || jbNum == null || jbNum == "" || subSysNum == null || subSysNum == "") {
            // 提示必须填写子段为空
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }
        try {
            CustomMenuBean bean = new CustomMenuBean(subSysNum, loginUserCode, "", funcId, jbNum, 0, 0, "");
            int res = customMenuService.deleteCustomMenu(bean);
            if (res == 0) {
                result.setRetCode(IResultCode.SYS_SUCCESS);
                result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
            } else {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }
        //父模块下子模块个数
        int count = customMenuService.queryJbNumBySup(map2);
        //如果该模块下已没有子模块，则将末级信息修改为1
        if (count == 0) {
            customMenuService.updateMjToOne(jbNum, loginUserCode);
        }
        result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        return result;
    }


    /**
     * @return HandlerResult
     * @Title: updateCustomMenu
     * @Description: 修改自定义菜单信息
     * @date 2013-3-6 下午2:58:18
     */
    public HandlerResult updateCustomMenu(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        UserInfoBean userInfoBean = (UserInfoBean) context.getRequest().getSession().getAttribute("admin");
        Map<String, Object> map = new HashMap<String, Object>();
        String subSysNum = context.getRequest().getParameter("subSysNum");
        String loginUserCode = userInfoBean.getLoginName();
        String moudleSortName = context.getRequest().getParameter("moudleSortName");
        String funcId = context.getRequest().getParameter("funcId");
        String jbNum = context.getRequest().getParameter("jbNum");
        String bz = context.getRequest().getParameter("bz");
        map.put("subSysNum", subSysNum);
        map.put("loginUserCode", loginUserCode);
        map.put("moudleSortName", moudleSortName);
        map.put("funcId", funcId);
        map.put("jbNum", jbNum);
        map.put("bz", bz);
        if (!(moudleSortName.equals(""))) {
            try {
                customMenuService.updateCustomMenu(map);
            } catch (Exception e) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
            }
        } else {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }
        result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        return result;
    }


    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: addCustomMenu
     * @Description: 自顶部添加一个新增的菜单（即菜单级别为0，增加一个菜单）
     */
    public HandlerResult addCustomMenu(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String type = context.getRequest().getParameter("type"); //类型 ，0是标题开始，1表示不是标题，新增自定义菜单
        if (type != null && type != "") {
            UserInfoBean userInfoBean = (UserInfoBean) context.getRequest().getSession().getAttribute("admin");
            if ("0".equals(type)) { //添加标题
                String jbNum = context.getRequest().getParameter("jbNum"); //级别编码
                if ("ROOT".equals(jbNum)) {
                    String subSysNum = "00";//子系统编码
                    String loginUserCode = userInfoBean.getLoginName();  //登陆名称
                    String moudleSortName = context.getRequest().getParameter("mouldSortName");//菜单分类名称
                    String bz = context.getRequest().getParameter("bz");      //备注
                    // 一级编码
                    jbNum = customMenuService.queryFirstJbNum(jbNum, loginUserCode, subSysNum);
                    Integer jb = (jbNum.length() / 2);
                    // 判断groupInfo是否有效
                    if (loginUserCode == null || loginUserCode == "" || moudleSortName == null || moudleSortName == "" || jbNum == null || jbNum == "") {
                        // 提示必须填写子段为空
                        result.setRetCode(IResultCode.SYS_FAILED);
                        result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
                    } else {
                        CustomMenuBean bean = new CustomMenuBean("00", loginUserCode, moudleSortName, "TITLE", jbNum, jb, 1, bz);
                        // 更新数据
                        int intResult = customMenuService.insertCustomMenu(bean);
                        if (intResult == 0) {
                            result.setRetCode(IResultCode.SYS_SUCCESS);
                            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                        } else {
                            result.setRetCode(IResultCode.SYS_FAILED);
                            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                        }
                    }
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                }
            } else if ("1".equals(type)) { //添加新增的模块信息
                String jbNumOld = context.getRequest().getParameter("jbNum"); //级别编码
                String jsonForm = context.getRequest().getParameter("jsonForm"); //级别编码
                String loginUserCode = userInfoBean.getLoginName();  //  登录名
                if (jsonForm != null && jsonForm != "" && jbNumOld != null && jbNumOld != "") {
                    try {
                        JSONArray jArray = JSONArray.parseArray(jsonForm);
                        JSONObject jsonObject = null;
                        String jb = "";
                        for (int i = 0; i < jArray.size(); i++) {
                            String jbNum = jbNumOld;
                            jsonObject = jArray.getJSONObject(i);
                            String subSysNum = jsonObject.getString("subSysNum").trim(); //子系统编码
                            String funcId = jsonObject.getString("funcId").trim(); //功能id
                            String moudleSortName = jsonObject.getString("funName").trim(); //功能id
                            if (funcId == null || funcId == "undefined") {
                                funcId = "''";
                            }
                            if ("ROOT".equals(jbNumOld)) {
                                // 一级编码
                                jbNum = customMenuService.queryFirstJbNum(jbNum, loginUserCode, subSysNum);
                            } else {
                                CustomMenuBean bean = new CustomMenuBean(subSysNum, loginUserCode, "", jbNum, 0);
                                jbNum = customMenuService.querySubJbNumFromBYSup(bean); //从父类级别中查询子类的级别编码
                                if (jbNum == null || jbNum == "") {
                                    result.setRetCode(IResultCode.SYS_FAILED);
                                    result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
                                }
                            }
                            jb = Integer.toString(jbNum.length() / 2).trim();//级别
                            if (loginUserCode == null || loginUserCode == "" || moudleSortName == null || moudleSortName == "" || jbNum == null || jbNum == "" || jb == null || jb == "") {
                                // 提示必须填写子段为空
                                result.setRetCode(IResultCode.SYS_FAILED);
                                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
                            }
                            // 更新数据
                            CustomMenuBean bean = new CustomMenuBean(subSysNum, loginUserCode, moudleSortName, funcId, jbNum, Integer.parseInt(jb), 1, "");
                            int intResult = customMenuService.insertCustomMenu(bean);
                            if (intResult == 0) {
                                result.setRetCode(IResultCode.SYS_SUCCESS);
                                result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                            }
                            if (intResult == 0) {
                                if (!"ROOT".equals(jbNumOld)) {
                                    // 增加子级时变更父级的末级信息
                                    intResult = customMenuService.updateMjByIdByJbNum(jbNumOld, loginUserCode);
                                    if (intResult == 0) {
                                        result.setRetCode(IResultCode.SYS_SUCCESS);
                                        result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                                    }
                                }
                                result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                            } else {
                                result.setRetCode(IResultCode.SYS_FAILED);
                                result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                }
            }
        } else {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }

    /**
     * @return HandlerResult
     * @throws
     * @Title: queryCustomMenuForUser
     * @Description: 根据用户id查询用户所具有的权限的菜单
     */
    public HandlerResult queryCustomMenuForUser(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        UserInfoBean userInfoBean = (UserInfoBean) context.getRequest().getSession(true).getAttribute(RequestConstants.ADMIN_SESSION_KEY);
        String loginName = userInfoBean.getLoginName();
        if (loginName.equals("") || loginName == "0") {
            result.setRetCode(IResultCode.SYS_FAILED);
        }
        TreeNode root = null;
        try {
            TreeNode titleTree = new TreeNode();
            titleTree.setId("-1");
            titleTree.setPid("-2");
            titleTree.setText("可供选择的菜单");
            String userId = userInfoBean.getLoginName().trim(); // 用户ID，用户组或用户ID
            String userType = "1";  // 用户类型，0为用户组，1为用户
            List<TreeNode> list = customMenuService.getPrivilegeTree(userId, userType, loginName);//获取用户所拥有的菜单权限
            root = DhtmlTreeUtil.mergeTreeNodeList(list, titleTree);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
        }
        result.setRetObj(root);
        return result;
    }
}
