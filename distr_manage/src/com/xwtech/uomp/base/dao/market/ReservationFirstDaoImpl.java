package com.xwtech.uomp.base.dao.market;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 *@ClassName:ReservationFirstDaoImpl.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-1 time：下午04:40:32
 *@version 1.0
 */
@Repository("reservationFirstDao")
public class ReservationFirstDaoImpl implements IReservationFirstDao {
	   @Autowired
	   JdbcOperations jdbcTemplate;
	   /**
	    * 一级预约营销案展示顺序
	    */
	public boolean sort(String[] reservationFirstPkids,
			String reservationFirstPkid) {
		String sql = "update JSMSS_RESERVATION_FIRST set MARKET_ORDER =? where MARKET_FIRST_PKID = ?" ;
        List<Object[]> batchArgs=new ArrayList<Object[]>();
        int order=1;
        for (int i=0;i<reservationFirstPkids.length;i++)
        {
       	 if("add".equals(reservationFirstPkids[i])){
       		reservationFirstPkids[i]=reservationFirstPkid;
       	 }
            Object[] args=new Object[]{order,reservationFirstPkids[i]}; 
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
