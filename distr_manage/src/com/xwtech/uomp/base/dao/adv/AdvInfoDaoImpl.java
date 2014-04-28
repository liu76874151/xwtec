package com.xwtech.uomp.base.dao.adv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("advInfoDao")
public class AdvInfoDaoImpl implements IAdvInfoDao {
	@Autowired
	   JdbcOperations jdbcTemplate;

	public boolean sort(String[] advNums) {
		

		String sql = "update T_ADV_INFO set F_SHOW_XH =? where F_ADV_NUM = ?" ;
		System.out.println(sql);
		
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        int order=1;
        for (int i=0;i<advNums.length;i++)
        {
        	System.out.println(advNums[i]);
            Object[] args=new Object[]{order,advNums[i]}; 
            order ++;	
            batchArgs.add(args);
        
        }
        
        try {
        	jdbcTemplate.batchUpdate(sql,batchArgs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

}
