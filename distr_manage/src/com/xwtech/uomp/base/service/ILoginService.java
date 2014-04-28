package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.MenuInfoBean;
import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;


public interface ILoginService {
    /**
     * @param loginName
     * @return UserInfoBean 返回类型
     * @throws
     * @Title: checkUserInfo
     * @Description: 检查用户信息
     */
    public UserInfoBean checkUserInfo(String loginName);

    /**
     * @param handleBean
     * @return boolean 返回类型
     * @throws
     * @Title: findCompletePer
     * @Description: 权限校验
     */
    public boolean findCompletePer(RequestHandleUrlBean handleBean);

    /**
     * @param handleBean
     * @return String 返回类型
     * @throws
     * @Title: checkPrivilege
     * @Description: 权限校验
     */
    public String checkPrivilege(RequestHandleUrlBean handleBean);

    /**
     * @param resultCode
     * @return String 返回类型
     * @throws
     * @Title: getCheckPrivilegeInfo
     * @Description: 返回权限校验信息
     */
    public String getCheckPrivilegeInfo(String resultCode);

    /**
     * @param handleBean
     * @return boolean 返回类型
     * @throws
     * @Title: existsFuncInfo
     * @Description: 判断权限是否存在
     */
    public boolean existsFuncInfo(RequestHandleUrlBean handleBean);

    /**
     * @param requestUrl
     * @param targetUrl
     * @return boolean 返回类型
     * @throws
     * @Title: needValidate
     * @Description: 判断是否需要校验
     */
    public boolean needValidate(String requestUrl, List<String> targetUrl);

    /**
     * @param subNum
     * @return List<String> 返回类型
     * @throws
     * @Title: getNotLoginUrl
     * @Description: 查询不需要登录的URL
     */
    public List<String> getNotLoginUrl(String subNum);

    /**
     * @param sunNum
     * @return List<String> 返回类型
     * @throws
     * @Title: getNotCheckUrl
     * @Description: 查询不需要校验权限的URL
     */
    public List<String> getNotCheckUrl(String sunNum);

    /**
     * @param loginName
     * @return Map<String,List<TreeNode>> 返回类型
     * @throws
     * @Title: getFunInfo
     * @Description: 获取默认模块信息
     */
    public Map<String, List<TreeNode>> getFunInfo(String loginName);

    /**
     * @param funInfos
     * @param list
     * @return List<MenuInfoBean> 返回类型
     * @throws
     * @Title: getMenuInfo
     * @Description: 组装菜单数据
     */
    public List<MenuInfoBean> getMenuInfo(Map<String, List<TreeNode>> funInfos, List<SubSystemBean> list);

    /**
     * @param loginName
     * @return Map<String,List<TreeNode>> 返回类型
     * @throws
     * @Title: getCustomFunInfo
     * @Description: 获取自定义模块信息
     */
    public Map<String, List<TreeNode>> getCustomFunInfo(String loginName);

    public List<TreeNode> queryCustomMenuByUser(String loginName);

    /**
     * @param handleBean
     * @return int 返回类型
     * @throws
     * @Title: checkPermission
     * @Description: 用户是否有资源的访问权限
     */
    boolean checkPermission(RequestHandleUrlBean handleBean);
}
