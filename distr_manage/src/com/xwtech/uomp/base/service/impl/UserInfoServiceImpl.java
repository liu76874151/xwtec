package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.UserInfoMapper;
import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.service.IUserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    protected static final Logger log = Logger.getLogger(UserInfoServiceImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;


    /**
     * 查看用户信息
     */
    public UserInfoBean viewUserInfo(String loginName) {
        UserInfoBean bean = null;
        try {
            List<UserInfoBean> list = userInfoMapper.viewUserInfo(loginName);
            if (list != null && list.size() > 0) {
                bean = list.get(0);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            bean = null;
        }
        return bean;
    }

    public CityInfoBean queryByGrouName(String groupName) {
        CityInfoBean bean = null;
        try {
            List<CityInfoBean> list = userInfoMapper.queryAreaNume(groupName);
            if (list != null && list.size() > 0) {
                bean = list.get(0);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            bean = null;
        }
        return bean;
    }

    /**
     * 查询管理员信息
     *
     * @return
     */
    public List<UserInfoBean> queryUserInfo(String userGroup, String userName) {
        List<UserInfoBean> userInfoList = null;
        try {
            Map<Object, Object> queryMap = new HashMap<Object, Object>();
            queryMap.put("userGroup", userGroup);
            queryMap.put("userName", userName);
            userInfoList = userInfoMapper.queryUserInfo(queryMap);

        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return userInfoList;
    }

    /**
     * 查看登录名是否已经存在
     */
    public int findUserInfo(String loginName) {
        int intResult = 0;
        try {
            intResult = userInfoMapper.findUserInfo(loginName);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 保存管理员信息
     */
    public int insertUserInfo(UserInfoBean userInfo) {
        int intResult = 0;
        try {
            userInfoMapper.insertUserInfo(userInfo);
        } catch (Exception be) {
            log.info(be.getMessage());
            be.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 删除管理员信息
     *
     * @param loginName
     * @return
     */
    public int deleteUserInfo(String loginName) {
        int intResult = 0;
        try {
            userInfoMapper.deleteUserInfo(loginName);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 根据用户名删除权限
     *
     * @param loginName
     * @return
     */
    public int deleteQxByUser(String loginName) {
        int intResult = 0;
        try {
            userInfoMapper.deleteQxByUser(loginName);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 更新管理员信息
     *
     * @param userInfo
     * @return
     */
    public int updateUserInfo(UserInfoBean userInfo) {
        int intResult = 0;
        try {
            userInfoMapper.updateUserInfo(userInfo);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 根据用户组名查询用户个数
     *
     * @param loginName
     * @return
     */
    public int sumByGroupId(String groupId) {
        int intResult = 0;
        try {
            intResult = userInfoMapper.sumByGroupId(groupId);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = -1;
        }
        return intResult;
    }

    public int queryUserInfoForCount(Map<String, Object> map) {
        int intResult = 0;
        try {
            intResult = userInfoMapper.queryUserInfoForCount(map);
        } catch (Exception be) {
            log.info(be.getMessage());
            intResult = 0;
        }
        return intResult;
    }

    public List<UserInfoBean> queryUserInfoForPage(Map<String, Object> mapage) {
        List<UserInfoBean> userInfoList = null;
        try {
            userInfoList = userInfoMapper.queryUserInfoForPage(mapage);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return userInfoList;
    }

}
