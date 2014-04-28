package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.dao.automated.LoginMapper;
import com.xwtech.uomp.base.pojo.MenuInfoBean;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.FunInfoBean;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.ILoginService;
import com.xwtech.uomp.base.service.IResultMsgFormat;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    protected static final Logger log = Logger.getLogger(UserInfoServiceImpl.class);

    @Autowired
    ICacheServiceFactory cacheServiceFactory;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private IResultMsgFormat resultMsgFormat;
    @Value("${isPermissions}")
	private String isPermissions;

    public UserInfoBean checkUserInfo(String loginName) {
        UserInfoBean userInfoBean = null;
        try {
            userInfoBean = loginMapper.checkUserInfo(loginName);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return userInfoBean;
    }

    public boolean findCompletePer(RequestHandleUrlBean handleBean) {
        boolean result = false;
        try {
            int flag = loginMapper.findCompletePer(handleBean);
            if (flag > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return result;
    }

    /*
     * 判断权限是否存在
     */
    public boolean existsFuncInfo(RequestHandleUrlBean handleBean) {
        boolean isExists = false;
        if (handleBean != null && !StringUtils.isBlank(handleBean.getHandleUrl())) {
            FunInfoBean funInfoBean = loginMapper.existsFuncInfo(handleBean);
            if (funInfoBean != null) {
                isExists = true;
            }
        }
        return isExists;
    }

    public String checkPrivilege(RequestHandleUrlBean handleBean) {
        String systemCode = null;
        try {
            if (handleBean != null && !StringUtils.isBlank(handleBean.getHandleUrl())) {
                FunInfoBean funInfoBean = loginMapper.existsFuncInfo(handleBean);
                if (funInfoBean != null) {
                    boolean resultFlag = this.findCompletePer(handleBean);
                    if (resultFlag) {
                        systemCode = SystemCodeConstants.OPERATE_SUCCEED;
                    } else {
                        if ("1".equals(funInfoBean.getFuncType()) || "2".equals(funInfoBean.getFuncType())) {
                            systemCode = SystemCodeConstants.NOT_ACCESS_PAGE;
                        } else {
                            systemCode = SystemCodeConstants.NOT_OPERATOR;
                        }
                    }
                } else {
                    systemCode = SystemCodeConstants.NOT_FUNC;
                    String handleUrl = handleBean.getHandleUrl();
                    if (handleUrl.indexOf(".jsp") != -1) {
                        systemCode = SystemCodeConstants.NOT_PAGE_FUNC;
                    } else {
                        systemCode = SystemCodeConstants.NOT_OPER_FUNC;
                    }
                }
            } else {
                systemCode = SystemCodeConstants.URL_IS_NULL;
            }
        } catch (Exception e) {
            log.info("校验出错：" + e.getMessage());
            systemCode = SystemCodeConstants.QUERY_FUNC_ERROR;
        }
        return systemCode;
    }

    public String getCheckPrivilegeInfo(String systemCode) {
        HandlerResult result = HandlerResult.newInstance();
        result.setSysCode(systemCode);
        resultMsgFormat.formatResultMsg(result);
        return result.getResMsg();
    }

    /**
     * 判断是否需要校验请求权限 true：需要 false：不需要
     */
    public boolean needValidate(String requestUrl, List<String> targetUrl) {
        boolean validate = true;
        if (targetUrl != null && targetUrl.size() > 0) {
            for (String url : targetUrl) {
                if (requestUrl.indexOf(url) != -1) {
                    validate = false;
                }
            }
        }
        return validate;
    }

    public Map<String, List<TreeNode>> getFunInfo(String loginName) {
        List<TreeNode> treeNodeList = null;
        Map<String, List<TreeNode>> menuMap = new HashMap<String, List<TreeNode>>();
        String oldSysNum = null;
        try {
            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("loginName", loginName);
            List<Map<String, Object>> funcInfo = loginMapper.getFunInfo(parameterMap);
            if (funcInfo != null && !funcInfo.isEmpty()) {
                for (int i = 0; i < funcInfo.size(); i++) {
                    Map<String, Object> dataMap = (Map<String, Object>) funcInfo.get(i);
                    TreeNode node = TreeNode.newInstance();
                    for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                        String key = (String) iter.next();
                        if ("id".equals(key)) {
                            node.setId((String) dataMap.get(key));
                        } else if ("text".equals(key)) {
                            node.setText((String) dataMap.get(key));
                        } else if ("pid".equals(key)) {
                            node.setPid((String) dataMap.get(key));
                        } else if ("mj".equals(key)) {
                            node.setMj(((BigDecimal) dataMap.get(key)).toString());
                        } else {
                            Map<String, Object> tmpMap = new HashMap<String, Object>();
                            tmpMap.put("name", key);
                            tmpMap.put("content", dataMap.get(key));
                            node.getUserdata().add(tmpMap);
                        }
                    }
                    String sysNum = (String) dataMap.get("subNum");
                    if (!sysNum.equals(oldSysNum)) {
                        treeNodeList = new ArrayList<TreeNode>();
                        oldSysNum = sysNum;
                        menuMap.put(oldSysNum, treeNodeList);
                    }
                    treeNodeList.add(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuMap;
    }

    public List<MenuInfoBean> getMenuInfo(Map<String, List<TreeNode>> funInfos, List<SubSystemBean> list) {
        List<MenuInfoBean> menuList = null;
        if (list != null && list.size() > 0 && funInfos != null && funInfos.size() > 0) {
            Iterator<SubSystemBean> it = list.iterator();
            menuList = new ArrayList<MenuInfoBean>();
            while (it.hasNext()) {
                SubSystemBean subSystem = it.next();
                List<TreeNode> funInfoList = funInfos.get(subSystem.getSysNum());
                if (funInfoList != null && funInfoList.size() > 0) {
                    MenuInfoBean menuBena = getMenuInfoBean(subSystem.getSysNum(), funInfoList, list);
                    menuList.add(menuBena);
                }
            }
        }
        return menuList;
    }

    private MenuInfoBean getMenuInfoBean(String sysNum, List<TreeNode> funInfos, List<SubSystemBean> list) {
        MenuInfoBean menuBean = new MenuInfoBean();
        SubSystemBean sysBean = getSubSystemBean(sysNum, list);
        TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(funInfos);
        menuBean.setSysNum(sysNum);
        menuBean.setSysTitle(sysBean.getSysName());
        menuBean.setTreeNode(root);
        return menuBean;
    }


    private SubSystemBean getSubSystemBean(String sysNum, List<SubSystemBean> list) {
        SubSystemBean sysBean = null;
        if (list != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SubSystemBean bean = (SubSystemBean) it.next();
                if (bean.getSysNum().equals(sysNum)) {
                    sysBean = bean;
                    break;
                }
            }
        }
        return sysBean;
    }

    public List<String> getNotLoginUrl(String subNum) {
        List<String> list = new ArrayList<String>();
        try {
            List resultList = loginMapper.getNotLoginUrl(subNum);
            if (resultList != null && !resultList.isEmpty()) {
                Iterator it = resultList.iterator();
                while (it.hasNext()) {
                    RequestHandleUrlBean bean = (RequestHandleUrlBean) it.next();
                    list.add(bean.getHandleUrl());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }

        return list;
    }

    public List<String> getNotCheckUrl(String subNum) {
        List<String> list = new ArrayList<String>();
        try {
            List resultList = loginMapper.getNotCheckUrl(subNum);
            if (resultList != null && !resultList.isEmpty()) {
                Iterator it = resultList.iterator();
                while (it.hasNext()) {
                    RequestHandleUrlBean bean = (RequestHandleUrlBean) it.next();
                    list.add(bean.getHandleUrl());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return list;
    }

    public Map<String, List<TreeNode>> getCustomFunInfo(String loginName) {
        List<TreeNode> treeNodeList = null;
        Map<String, List<TreeNode>> menuMap = new HashMap<String, List<TreeNode>>();
        String oldSysNum = null;
        try {
            List<Map<String, Object>> funcInfo = loginMapper.getCustomFunInfo(loginName);
            if (funcInfo != null && !funcInfo.isEmpty()) {
                for (int i = 0; i < funcInfo.size(); i++) {
                    Map<String, Object> dataMap = (Map<String, Object>) funcInfo.get(i);
                    TreeNode node = TreeNode.newInstance();
                    for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                        String key = (String) iter.next();
                        if ("id".equals(key)) {
                            node.setId((String) dataMap.get(key));
                        } else if ("text".equals(key)) {
                            node.setText((String) dataMap.get(key));
                        } else if ("pid".equals(key)) {
                            node.setPid((String) dataMap.get(key));
                        } else if ("mj".equals(key)) {
                            node.setMj(((BigDecimal) dataMap.get(key)).toString());
                        } else {
                            Map<String, Object> tmpMap = new HashMap<String, Object>();
                            tmpMap.put("name", key);
                            tmpMap.put("content", dataMap.get(key));
                            node.getUserdata().add(tmpMap);
                        }
                    }
                    String sysNum = (String) dataMap.get("subNum");
                    if (!sysNum.equals(oldSysNum)) {
                        treeNodeList = new ArrayList<TreeNode>();
                        oldSysNum = sysNum;
                        menuMap.put(oldSysNum, treeNodeList);
                    }
                    treeNodeList.add(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuMap;
    }

    public List<TreeNode> queryCustomMenuByUser(String loginName) {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = loginMapper.getCustomFunInfo(loginName);
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

    public boolean checkPermission(RequestHandleUrlBean handleBean) {
        ICacheService cache = cacheServiceFactory.getCacheService("ecm_code");

        String cache_Key = "KEY_PERMISSION_" + handleBean.getLoginName();
        Object obj= cache.get(cache_Key);
        if("true".equals(isPermissions)){//--权限控制开关
            return true;
        }
	        Object uriObj = cache.get("KEY_USER_FUNC_URI");
	        if (null == uriObj) {//--没有在后台配置就返回true表示允许访问
	           cache.add("KEY_USER_FUNC_URI", loginMapper.getFuncUrl(handleBean));
	        }
	        List<Object> uriList = (List<Object>) cache.get("KEY_USER_FUNC_URI");
	        if (!uriList.contains(handleBean.getHandleUrl())) {
	           return true;
	        }
        if (null == obj) {//--后台配置且勾选的请求才允许访问
            List<Map<String, Object>> datamap = loginMapper.getPermissionList(handleBean);

            List<Object> newURIList = new ArrayList<Object>();

            for (Map<String, Object> map : datamap) {
                newURIList.add(map.get("F_FUNC_URI"));
                
                
            }

            cache.add(cache_Key, newURIList);
        }

        List<Object> permissions = (List<Object>) cache.get(cache_Key);
        //System.out.println("permissions.contains(handleBean.getHandleUrl()==="+permissions.contains(handleBean.getHandleUrl()));
        return permissions.contains(handleBean.getHandleUrl());
    }

}
