package com.xwtech.uomp.base.dao.market;
import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketFirstBean;
import com.xwtech.uomp.base.pojo.market.ReservationFirstBean;



public interface ReservationFirstBeanMapper {
	int queryReservationFirstListCount(Map<String, String> map);
	
	List <ReservationFirstBean> queryReservationFirstList(Map<String, String> map);
	
    int saveReservationFirst(ReservationFirstBean record);
    
    void deleteReservationFirst(Map<String, Object> reservationFirstPkids);
    
    int queryReservationSencodCount(String reservationFirstPkid);
    
    ReservationFirstBean queryByPrimaryKey(String reservationFirstPkid);
    /**
     * 查询展示顺序
     * @param city
     * @return
     */
    List<ReservationFirstBean> queryAllMarketOrder(String city);
    
    void updateReservationFirst(Map<String, String> map);
    
    List<ReservationFirstBean> queryMarketForTest(Map<String, String> param);
    
    int queryCountForTest(Map<String, String> param);
    
    void updateTestState(ReservationFirstBean reservationFirstBean);
    
    List<ReservationFirstBean> queryMarketForAudit(Map<String, String> param);
    
    int queryCountForAudit(Map<String, String> param);
    
    void updateAuditState(ReservationFirstBean reservationFirstBean);
    
    List<ReservationFirstBean> queryMarketForAuditOnConsole(Map<String, String> param);
}