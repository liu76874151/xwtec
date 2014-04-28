package com.xwtech.uomp.base.service.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.market.ReservationFirstBean;

public interface IReservationFirstService {

	Page queryReservationFirstList(Map<String, String> map);

	Page queryAllMarketOrder(String city);

	int saveReservationFirst(ReservationFirstBean reservationFirstBean);

	boolean sort(String[] reservationFirstPkids, String reservationFirstPkid);

	void deleteReservationFirst(Map<String, Object> reservationFirstPkids);

	int queryReservationSencodCount(String reservationFirstPkid);
	
	ReservationFirstBean queryByPrimaryKey(String reservationFirstPkid);
	void updateReservationFirst(Map<String, String> map);
	
	Page queryMarketForTest(Map<String, String> param);
	
	void updateTestState(ReservationFirstBean reservationFirstBean);
	
	Page queryMarketForAudit(Map<String, String> param);
	
	void updateAuditState(ReservationFirstBean reservationFirstBean);
	
	Page queryMarketForAuditOnConsole(Map<String, String> param);
}
