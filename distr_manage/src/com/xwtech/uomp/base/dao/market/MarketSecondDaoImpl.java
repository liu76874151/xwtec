package com.xwtech.uomp.base.dao.market;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;
@Repository("MarketSecondDaoImpl")
public class MarketSecondDaoImpl  extends BaseDAO implements IMarketSecondDao {

	public boolean sort(String[] marketSecondPkids, String marketSecondPkid) {

		String sql = "update jsmss_market_second set WT_MARKET_ORDER =? where MARKET_SECOND_PKID = ?" ;
         List<Object[]> batchArgs=new ArrayList<Object[]>();
         int order=1;
         //System.out.println(marketFirstPkids.length+"=====marketFirstPkids.length====");
         for (int i=0;i<marketSecondPkids.length;i++)
         {
        	 System.out.println(marketSecondPkids[i]);
        	 if("add".equals(marketSecondPkids[i])){
        		
        		 marketSecondPkids[i]=marketSecondPkid;
        	 }
        	 //System.out.println(order+"===order=====");
        	// System.out.println(marketFirstPkid+"===marketFirstPkid=====");
             Object[] args=new Object[]{order,marketSecondPkids[i]}; 
             order ++;	
             batchArgs.add(args);
         
         }
         
         try {
        	 update(sql,batchArgs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	
	}

	public boolean sortZT(String[] marketSecondPkids, String marketSecondPkid) {


		String sql = "update jsmss_market_second set ZT_MARKET_ORDER =? where MARKET_SECOND_PKID = ?" ;
         List<Object[]> batchArgs=new ArrayList<Object[]>();
         int order=1;
         //System.out.println(marketFirstPkids.length+"=====marketFirstPkids.length====");
         for (int i=0;i<marketSecondPkids.length;i++)
         {
        	 System.out.println(marketSecondPkids[i]);
        	 if("add".equals(marketSecondPkids[i])){
        		
        		 marketSecondPkids[i]=marketSecondPkid;
        	 }
        	 //System.out.println(order+"===order=====");
        	// System.out.println(marketFirstPkid+"===marketFirstPkid=====");
             Object[] args=new Object[]{order,marketSecondPkids[i]}; 
             order ++;	
             batchArgs.add(args);
         
         }
         
         try {
        	 update(sql,batchArgs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	
	
	}

	public String queryMarketSecondBossCode(String pkid) {
		String sql="select s.market_second_code from jsmss_market_second s where s.market_second_pkid="+pkid;
		return queryForLong(sql)+"";
	}

}
