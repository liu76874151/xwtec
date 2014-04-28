package com.xwtech.uomp.base.dao.visitor;

import com.xwtech.uomp.base.dao.visitor.IVisitorLogDAO;
import com.xwtech.uomp.base.pojo.admin.VisitorLogBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("visitorLogDAO")
public class VisitorLogDAOImpl implements IVisitorLogDAO {
    @Autowired
    JdbcOperations jdbcTemplate;


    public void batchAddLog(List<VisitorLogBean> logBean) {
        String sql = "INSERT INTO XW_VISIT_LOG(F_LOG_ID,F_V_TIME,F_V_HOST,F_V_ACCOUNT,F_V_URI,F_V_SYSNUM)VALUES(SEQ_XW_VISIT_LOG.nextval,?,?,?,?,?)";

        List<Object[]> batchArgs = new ArrayList<Object[]>();

        for (VisitorLogBean log : logBean) {
            Object[] args = new Object[]{log.getF_v_time(), log.getF_v_host(), log.getF_v_account(), log.getF_v_uri(), log.getF_v_sysNum()};

            batchArgs.add(args);

        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

}
