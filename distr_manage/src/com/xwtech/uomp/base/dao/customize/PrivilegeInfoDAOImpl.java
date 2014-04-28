package com.xwtech.uomp.base.dao.customize;

import com.xwtech.uomp.base.pojo.admin.UserPrivilegeInfoBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository("privilegeInfoDAO")
public class PrivilegeInfoDAOImpl implements IPrivilegeInfoDAO {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map<String, Object>> queryUserInfo() {
        return sqlSessionTemplate.selectList("com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.queryUserInfo");
    }

    public List<Map<String, Object>> queryPrivilegeInfo(String userId, String userType) {
        String sql = "";
        // 用户类型，0为用户组，1为用户
        if (userType.equals("0")) {
            sql = "com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.queryGroupPrivilegeInfo";

        } else if (userType.equals("1")) {
            sql = "com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.queryUserPrivilegeInfo";
        }
        List<Map<String, Object>> list = sqlSessionTemplate.selectList(sql, userId);
        return list;
    }

    public void modifyPrivilegeInfo(String userId, String userType, Map<String, String> subSysNumAndPrivilege) {
        String qSql = "";
        int optType = 0;
        // 修改用户组权限
        if ("0".equals(userType)) {
            optType = 0;
            qSql = "com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.queryGroupInsertPrivilegeInfo";
        }
        // 修改用户权限
        else if ("1".equals(userType)) {
            optType = 1;
            qSql = "com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.queryUserInsertPrivilegeInfo";
        }

        Map<String, Object> delParam = new HashMap<String, Object>();
        delParam.put("userId", userId);
        delParam.put("userType", userType);
        // 先删除，再插入
        sqlSessionTemplate.delete("com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.deletePrivilegeInfo", delParam);

        for (Iterator<String> iter = subSysNumAndPrivilege.keySet().iterator(); iter.hasNext(); ) {
            String subSysNum = (String) iter.next();
            String selectedPrivilege = subSysNumAndPrivilege.get(subSysNum);

            Map<String, Object> qryParam = new HashMap<String, Object>();
            qryParam.put("subSysNum", subSysNum);
            qryParam.put("userId", userId);
            qryParam.put("selectedPrivilege", selectedPrivilege);
            List<UserPrivilegeInfoBean> retList = sqlSessionTemplate.selectList(qSql, qryParam);

            if (retList != null && !retList.isEmpty()) {
                for (int i = 0; i < retList.size(); i++) {
                    UserPrivilegeInfoBean bean = retList.get(i);
                    bean.setOptType(optType);
                    sqlSessionTemplate.insert("com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.insertPrivilegeInfo", bean);
                    /*
                     * 注释掉插入类型为2的部分 bean.setOptType(2);
                     * this.getSqlSession().insert(
                     * "com.xwtech.uomp.base.dao.customize.PrivilegeInfoDAO.insertPrivilegeInfo"
                     * , bean);
                     */
                }
            }
        }
    }
}
