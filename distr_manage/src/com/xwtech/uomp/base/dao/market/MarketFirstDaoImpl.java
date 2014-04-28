package com.xwtech.uomp.base.dao.market;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;


@Repository("marketFirstDao")
//public class MarketFirstDaoImpl extends JdbcDaoSupport   implements IMarketFirstDao{
	public class MarketFirstDaoImpl  extends BaseDAO    implements IMarketFirstDao{
   //@Autowired
  // JdbcOperations jdbcTemplate;

	/**
	 * 更新营销案展示顺序(MARKET_ORDER)
	 * @param marketFirstPkids
	 * @param marketFirstPkid
	 * @return
	 */
	public boolean sort(String[] marketFirstPkids, String marketFirstPkid) {
		String sql = "update JSMSS_MARKET_FIRST set MARKET_ORDER =? where MARKET_FIRST_PKID = ?" ;
         List<Object[]> batchArgs=new ArrayList<Object[]>();
         int order=1;
         //System.out.println(marketFirstPkids.length+"=====marketFirstPkids.length====");
         for (int i=0;i<marketFirstPkids.length;i++)
         {
        	 System.out.println(marketFirstPkids[i]);
        	 if("add".equals(marketFirstPkids[i])){
        		
        		 marketFirstPkids[i]=marketFirstPkid;
        	 }
        	 //System.out.println(order+"===order=====");
        	// System.out.println(marketFirstPkid+"===marketFirstPkid=====");
             Object[] args=new Object[]{order,marketFirstPkids[i]}; 
             order ++;	
             batchArgs.add(args);
         
         }
         
         try {
        	 update(sql,batchArgs);
        	//this.getJdbcTemplate().batchUpdate(sql,batchArgs);
       // jdbcTemplate.batchUpdate(sql,batchArgs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean sortZT(String[] marketFirstPkids, String marketFirstPkid) {
		String sql = "update JSMSS_MARKET_FIRST set ZT_MARKET_ORDER =? where MARKET_FIRST_PKID = ?" ;
         List<Object[]> batchArgs=new ArrayList<Object[]>();
         int order=1;
        // System.out.println(marketFirstPkids.length+"=====marketFirstPkids.length====");
         for (int i=0;i<marketFirstPkids.length;i++)
         {
        	// System.out.println(marketFirstPkids[i]);
        	 if("add".equals(marketFirstPkids[i])){
        		
        		 marketFirstPkids[i]=marketFirstPkid;
        	 }
        	// System.out.println(order+"==azzzz=order=====");
        	// System.out.println(marketFirstPkid+"==zzzz=marketFirstPkid=====");
             Object[] args=new Object[]{order,marketFirstPkids[i]}; 
             order ++;	
             batchArgs.add(args);
         
         }
         
         try {
        	 update(sql,batchArgs);
        	//this.getJdbcTemplate().batchUpdate(sql,batchArgs);
       // jdbcTemplate.batchUpdate(sql,batchArgs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
