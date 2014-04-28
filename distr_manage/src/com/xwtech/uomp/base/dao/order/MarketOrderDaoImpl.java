package com.xwtech.uomp.base.dao.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;
import com.xwtech.uomp.base.pojo.order.MarketOrderBean;
@Repository("marketOrderDao")
public class MarketOrderDaoImpl extends BaseDAO implements IMarketOrderDao {

	public List<MarketOrderBean> queryMarketOrderList(int flag) {
		String sql ="";
		if(0==flag){
		 sql ="select * from JSMSS_MARKET_ORDER j where j.CURR_STATE !=1 and substr(j.CREATE_TIME,1,8) between to_char(sysdate-1, 'yyyymmdd')and to_char(sysdate, 'yyyymmdd')";
		}else{
		 sql ="select * from JSMSS_MARKET_ORDER j where j.CURR_STATE =1 and substr(j.CREATE_TIME,1,8) between to_char(sysdate-1, 'yyyymmdd')and to_char(sysdate, 'yyyymmdd')";
				
		}
		 return (List<MarketOrderBean>) queryForList(sql,MarketOrderBean.class);
	}

	public void updateMarketOrder(List<Object[]> list) {
		String sql="update JSMSS_MARKET_ORDER set CURR_STATE =? , COMPLETE_TIME= ? where MARKET_ORDER_ID = ?" ;
		update(sql,list);
	}

}
