package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

public interface UserInfoMapper {
    /**
     * 根据用户组查询所属市区信息
     *
     * @param groupName
     * @return
     * @throws BaseException
     */

    public List<CityInfoBean> queryAreaNume(String groupName);

    /**
     * 查询管理员信息
     *
     * @return
     */
    public List<UserInfoBean> queryUserInfo(Map<Object, Object> queryMap);

    /**
     * 查看用户信息
     */
    public List<UserInfoBean> viewUserInfo(String loginName);

    /**
     * 查看登录名是否已经存在
     */
    public int findUserInfo(String loginName);

    /**
     * 保存管理员信息
     */
    public void insertUserInfo(UserInfoBean userInfo);

    /**
     * 删除管理员信息
     *
     * @param loginName
     * @return
     */
    public void deleteUserInfo(String loginName);

    /**
     * 根据用户名删除权限
     *
     * @param loginName
     * @return
     */
    public void deleteQxByUser(String loginName);

    /**
     * 更新管理员信息
     *
     * @param userInfo
     * @return
     */
    public void updateUserInfo(UserInfoBean userInfo);

    /**
     * 根据用户组名查询用户个数
     *
     * @param loginName
     * @return
     */
    public int sumByGroupId(String groupId);

    /**
     * @return int
     * @throws
     * @Title: queryUserInfoForCount
     * @Description: 查询用户总数量
     */
    public int queryUserInfoForCount(Map<String, Object> map);

    /**
     * @return List<UserInfoBean>
     * @throws
     * @Title: queryUserInfoForPage
     * @Description:查询用户分页
     */
    public List<UserInfoBean> queryUserInfoForPage(Map<String, Object> mapage);
}
