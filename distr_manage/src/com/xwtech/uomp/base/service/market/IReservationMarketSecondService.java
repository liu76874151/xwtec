/**
 * Title: IReservationMarketSecondService.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-25 
 * @ time 下午4:19:15
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.ReservationFirstBean;
import com.xwtech.uomp.base.pojo.market.ReservationMarketSecondBean;

/**
 * @author zhanglu
 *
 */
public interface IReservationMarketSecondService {

	Page queryReservationMarketSecondList(Map<String, String> param);
	
	void addReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean);
	
	ReservationMarketSecondBean queryReservationMarketSecondByPkid(String marketSecondPkid);
	
	void updateReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean);
	
	void deleteReservationMarketSecond(String marketSecondPkid);
	
	void updateIsViewListState(ReservationMarketSecondBean reservationSecondBean);
	
	void updateAuditState(ReservationMarketSecondBean reservationSecondBean);
	
	Page queryReservationMarketSecondAuditList(Map<String, String> param);
	
	Page queryMarketForTest(Map<String, String> param);
	
	void updateTestState(ReservationMarketSecondBean reservationSecondBean);
	
	Page queryMarketForAudit(Map<String, String> param);
	
	void updateMarketSecondForVerify(ReservationMarketSecondBean reservationSecondBean);
	
	Page queryMarketSecondForVerifyOnConsole(Map<String, String> param);
	
}
