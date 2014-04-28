package com.xwtech.uomp.base.dao.automated;

import com.xwtech.uomp.base.pojo.admin.UserInfoBean;

/**
 * @author yugonggan
 * @ClassName: UserPasswordMapper
 * @Description: TODO(密码修改Mapper)
 * @date Mar 6, 2013 8:44:52 PM
 */
public interface UserPasswordMapper {

    /**
     * @param userInfoBean 用户信息对象
     * @return void 返回类型
     * @throws
     * @Title: modifyUserPasswd
     * @Description: TODO(修改管理员密码)
     */
    public void modifyUserPasswd(UserInfoBean userInfoBean);
}
