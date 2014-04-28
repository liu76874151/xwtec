/**
 * Title: ReservationMarketFirstMapper.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-25 
 * @ time 下午2:50:42
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.ReservationMarketSecondBean;

/**
 * @author zhanglu
 *
 */
public interface ReservationMarketSecondMapper {
	
	/**
	 * 根据一级预约营销主键查询二级预约营销列表
	 * 创建日期：2013-10-25下午5:31:32
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	List<ReservationMarketSecondBean> queryReservationMarketSecondList(Map<String, String> param);
	
	/**
	 * 查询某一个一级预约营销案下的二级预约营销案个数
	 * 创建日期：2013-10-30下午7:18:29
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	int queryReservationMarketSecondListCount(Map<String, String> param);
	
	/**
	 * 添加二级预约营销案
	 * 创建日期：2013-10-30下午7:18:53
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	void addReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean);
	
	/**
	 * 查询某一个二级预约营销案
	 * 创建日期：2013-10-30下午7:19:05
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	ReservationMarketSecondBean queryReservationMarketSecondByPkid(String marketSecondPkid);
	
	/**
	 * 修改二级预约营销案
	 * 创建日期：2013-10-30下午8:26:05
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	void updateReservationMarketSecond(ReservationMarketSecondBean reservationSecondBean);
	
	/**
	 * 删除二级预约营销案，state状态为0
	 * 创建日期：2013-10-31下午1:49:43
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	void deleteReservationMarketSecond(String marketSecondPkid);
	
	/**
	 * 更新二级预约营销案列表展示状态，0为未展示，1为展示
	 * 创建日期：2013-11-1上午11:18:23
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	void updateIsViewListState(ReservationMarketSecondBean reservationSecondBean);
	
	/**
	 * 根据条件查询预约营销案审核列表
	 * 创建日期：2013-11-1下午3:00:01
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	List<ReservationMarketSecondBean> queryReservationMarketSecondAuditList(Map<String, String> param);
	
	/**
	 * 根据条件查询预约营销案审核列表条数
	 * 创建日期：2013-11-1下午3:01:10
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	int queryReservationMarketSecondAuditCount(Map<String, String> param);
	
	/**
	 * 更新二级预约营销案审核状态
	 * 创建日期：2013-11-1下午4:55:58
	 * 修改日期：
	 * 作者：zhanglu
	 * TODO
	 * return
	 */
	void updateAuditState(ReservationMarketSecondBean reservationSecondBean);
	
	/**
	 * 查询线上测试列表
	 * 创建日期：2013-12-17下午4:28:48
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	List<ReservationMarketSecondBean> queryMarketForTest(Map<String, String> param);
	
	/**
	 * 获取线上测试列表行数
	 * 创建日期：2013-12-17下午4:42:00
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryCountForTest(Map<String, String> param);
	
	/**
	 * 更新测试状态
	 * 创建日期：2013-12-17下午5:20:17
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateTestState(ReservationMarketSecondBean reservationSecondBean);
	
	/**
	 * 查询二级预约营销案审核列表
	 * 创建日期：2013-12-17下午7:46:59
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:List<ReservationMarketSecondBean>
	 */
	List<ReservationMarketSecondBean> queryMarketSecondForVerify(Map<String, String> param);
	
	/**
	 * 二级预约营销案审核列表行数
	 * 创建日期：2013-12-17下午7:47:46
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:int
	 */
	int queryMarketSecondCountForVerify(Map<String, String> param);
	
	/**
	 * 更新二级预约营销案审核状态
	 * 创建日期：2013-12-18上午10:34:37
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:void
	 */
	void updateMarketSecondForVerify(ReservationMarketSecondBean reservationSecondBean);
	
	List<ReservationMarketSecondBean> queryMarketSecondForVerifyOnConsole(Map<String, String> param);
}
