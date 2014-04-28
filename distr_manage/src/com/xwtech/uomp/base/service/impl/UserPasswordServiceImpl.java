package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.UserPasswordMapper;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.service.IUserPasswordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yugonggan
 * @ClassName: UserPasswordServiceImpl
 * @Description: TODO(用户密码修改实现类)
 * @date Mar 6, 2013 8:46:34 PM
 */
@Service("userPasswordService")
public class UserPasswordServiceImpl implements IUserPasswordService {

    protected static final Logger log = Logger.getLogger(UserPasswordServiceImpl.class);

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    /**
     * 密码修改
     */
    public int modifyUserPasswd(UserInfoBean userInfoBean) {

        int reslutInt = 0;
        try {
            userPasswordMapper.modifyUserPasswd(userInfoBean);
        } catch (Exception be) {
            log.info(be.getMessage());
            reslutInt = -1;
        }
        return reslutInt;
    }
}
