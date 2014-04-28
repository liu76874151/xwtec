package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.RequestHandleUrlBean;
import com.xwtech.uomp.base.pojo.admin.FunInfoBean;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

public interface LoginMapper {
    /**
     * 判断该登录用户系统中是否存在
     *
     * @param loginName
     * @return
     */
    public UserInfoBean checkUserInfo(String loginName);

    /**
     * 判断是否有访问权限
     *
     * @param loginName
     * @param funcId
     * @param sysNum
     * @return
     */
    public int findCompletePer(RequestHandleUrlBean handleBean);

    /**
     * 查询模块信息
     *
     * @param sysNum
     * @param funcUrl
     * @return
     */
    public FunInfoBean existsFuncInfo(RequestHandleUrlBean handleBean);

    /**
     * 获取用户模块信息
     *
     * @param map
     * @return
     */
    public List<Map<String, Object>> getFunInfo(Map map);

    /**
     * 获取子系统信息
     *
     * @return
     */
    public List<SubSystemBean> getSystemInfo();

    /**
     * @param subNum
     * @return List<RequestHandleUrlBean> 返回类型
     * @throws
     * @Title: getNotCheckUrl
     * @Description: 查询不需要登录的Url
     */
    public List<RequestHandleUrlBean> getNotCheckUrl(String subNum);

    /**
     * @param subNum
     * @return List<RequestHandleUrlBean> 返回类型
     * @throws
     * @Title: getNotLoginUrl
     * @Description: 查询不需要登录的Url
     */
    public List<RequestHandleUrlBean> getNotLoginUrl(String subNum);

    /**
     * @param loginName
     * @return List<Map<String,Object>> 返回类型
     * @throws
     * @Title: getCustomFuncInfo
     * @Description: 查询自定义菜单模块
     */
    public List<Map<String, Object>> getCustomFunInfo(String loginName);

    List<Map<String, Object>> getPermissionList(RequestHandleUrlBean handleBean);

    List<Object> getFuncUrl(RequestHandleUrlBean handleBean);
}
