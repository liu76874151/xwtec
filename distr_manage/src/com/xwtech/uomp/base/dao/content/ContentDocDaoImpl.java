package com.xwtech.uomp.base.dao.content;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("contentDocDao")
public class ContentDocDaoImpl implements IContentDocDao {
	   @Autowired
	   JdbcOperations jdbcTemplate;
	public boolean sort(String[] ContentDocPkids, String ContentDocPkid) {
		

		String sql = "update T_CONTENT_DOC set F_SORT_NUM =? where F_DOC_ID = ?" ;
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        int order=1;
        for (int i=0;i<ContentDocPkids.length;i++)
        {
        	
       	 if("add".equals(ContentDocPkids[i])){
       		ContentDocPkids[i]=ContentDocPkid;
       	 }
            Object[] args=new Object[]{order,ContentDocPkids[i]}; 
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
