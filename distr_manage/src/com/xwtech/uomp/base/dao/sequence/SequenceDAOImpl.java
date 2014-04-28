package com.xwtech.uomp.base.dao.sequence;

import com.xwtech.uomp.base.dao.BaseDAO;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-29
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Repository("sequenceDAO")
public class SequenceDAOImpl extends BaseDAO implements ISequenceDAO {

  
    public String getSequence(String sequenceName) {
        if (sequenceName != null) {
            String sql = "select  " + sequenceName + ".Nextval from dual";
            return queryForLong(sql) + "";
        }
        return "0";
    }
}
