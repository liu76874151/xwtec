package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

public interface IUserInfoService {

    /**
     * @param @param  loginName
     * @param @return 设定文件
     * @return UserInfoBean 返回类型
     * @throws
     * @Title: viewUserInfo
     * @Description: 查看用户信息
     */
    public UserInfoBean viewUserInfo(String loginName);

    public CityInfoBean queryByGrouName(String groupId);

    /**
     * 查询管理员信息
     *
     * @return
     */
    public List<UserInfoBean> queryUserInfo(String userGroup, String userName);

    /**
     * @param @param  loginName
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     * @Title: findUserInfo
     * @Description: 查看登录名是否已经存在
     */
    public int findUserInfo(String loginName);

    /**
     * @param @param  userInfo
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     * @Title: insertUserInfo
     * @Description: 保存管理员信息
     */
    public int insertUserInfo(UserInfoBean userInfo);

    /**
     * 删除管理员信息
     *
     * @param loginName
     * @return
     */
    public int deleteUserInfo(String loginName);

    /**
     * 根据用户名删除权限
     *
     * @param loginName
     * @return
     */
    public int deleteQxByUser(String loginName);

    /**
     * 更新管理员信息
     *
     * @param userInfo
     * @return
     */
    public int updateUserInfo(UserInfoBean userInfo);

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
     * @Description:查询总的用户数量
     */
    public int queryUserInfoForCount(Map<String, Object> map);

    /**
     * @return List<UserInfoBean>
     * @throws
     * @Title: queryUserInfoForPage
     * @Description:查询用户
     */
    public List<UserInfoBean> queryUserInfoForPage(Map<String, Object> mapage);
}
