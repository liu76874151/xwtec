package com.xwtech.uomp.base.dao.content;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 *@ClassName:ContentChannelDaoImpl.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-7 time：上午10:38:48
 *@version 1.0
 */
@Repository("contentChannelDao")
public class ContentChannelDaoImpl implements IContentChannelDao {
	   @Autowired
	   JdbcOperations jdbcTemplate;
	public boolean sort(String[] contentChannelPkids, String contentChannelPkid) {
		

		String sql = "update T_CONTENT_CHANNEL set F_SORT_NUM =? where F_CHAN_ID = ?" ;
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        int order=1;
        for (int i=0;i<contentChannelPkids.length;i++)
        {
        	
       	 if("add".equals(contentChannelPkids[i])){
       		contentChannelPkids[i]=contentChannelPkid;
       	 }
            Object[] args=new Object[]{order,contentChannelPkids[i]}; 
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
