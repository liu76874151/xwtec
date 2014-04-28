package com.xwtech.uomp.base.service;

import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

/**
 * @author yugonggan
 * @ClassName: IUserPasswordService
 * @Description: TODO(密码修改)
 * @date Mar 6, 2013 8:48:17 PM
 */
public interface IUserPasswordService {

    /**
     * add by yugonggan
     * 修改管理员密码
     *
     * @param userInfoBean 用户信息对象
     * @return
     */
    public int modifyUserPasswd(UserInfoBean userInfoBean);
}
