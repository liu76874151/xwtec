package com.xwtech.uomp.base.service.order.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.market.MarketSecondDaoImpl;
import com.xwtech.uomp.base.dao.order.IMarketOrderDao;
import com.xwtech.uomp.base.dao.order.IOrderGiftDao;
import com.xwtech.uomp.base.dao.order.IOrderPayDao;
import com.xwtech.uomp.base.dao.order.MarketOrderMapper;
import com.xwtech.uomp.base.dao.order.RechargeMktBillMapper;
import com.xwtech.uomp.base.pojo.order.InterfaceLogBean;
import com.xwtech.uomp.base.pojo.order.LclBean;
import com.xwtech.uomp.base.pojo.order.MarketOrderBean;
import com.xwtech.uomp.base.pojo.order.OrderGiftBean;
import com.xwtech.uomp.base.pojo.order.OrderPayBean;
import com.xwtech.uomp.base.pojo.order.RechargeMktBillBean;
import com.xwtech.uomp.base.service.order.ICouponCodeService;
import com.xwtech.uomp.base.service.order.IInterfaceLogService;
import com.xwtech.uomp.base.service.order.IScheduledqueryRechargeMktBillService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.order.CzCouponResult;
import com.xwtech.uomp.base.util.order.CzCouponUtil;
import com.xwtech.uomp.base.util.order.DangDangCreateResult;
import com.xwtech.uomp.base.util.order.DangDangUtil;
import com.xwtech.uomp.base.util.order.MDLUtil;
import com.xwtech.uomp.base.util.order.MoveUtil;
import com.xwtech.uomp.base.util.order.SendTicketUtilV2;
@Service("scheduledqueryRechargeMktBillService")
public class ScheduledqueryRechargeMktBillServiceImpl implements
		IScheduledqueryRechargeMktBillService {
	@Autowired
	RechargeMktBillMapper rechargeMktBillMapper;
	@Autowired
	IMarketOrderDao marketOrderDao;
	@Autowired
	IOrderPayDao orderPayDao;
	@Autowired
	IOrderGiftDao orderGiftDao;
	@Autowired
	MarketOrderMapper marketOrderMapper;
	@Autowired
	ISequenceService sequenceService;  
	@Autowired
	IInterfaceLogService interfaceLogService;
	@Autowired
	ICouponCodeService couponCodeService;
	@Autowired
	MarketSecondDaoImpl marketSecondDaoImpl;
	private final String ztChannel="5";
	
	@Scheduled(cron = "0 10 8 ? * *")//--每天早8:10
	@Transactional(propagation=Propagation.REQUIRED)
	public void scheduledqueryRechargeMktBill() {
	        System.out.println("充值对账任务开始：" + new Date().toLocaleString());
	        checkBill();
	        System.out.println("充值对账任务已完成:" + new Date().toLocaleString());
		
	}
	/*TM_RECHARGE_MKT_BILL--充值对账表
	 * JSMSS_ORDER_PAY--充值营销案订单关联的充值表
	 * 相互比对
	 */
	private void checkBill(){
		List<OrderPayBean>  OrderPayBeanList=rechargeMktBillMapper.queryOrderPay();
		List<RechargeMktBillBean> rechargeMktBillBeanList=rechargeMktBillMapper.queryRechargeMktBill();
		Map<String,Object> rechargeMktBillBeanMap=new HashMap<String,Object> ();
		String serialOrderId="";
	    String bankState="";
	    String busiState="";
	    String payStatus="0";//--默认值0(未处理)
		List<Object[]> serialOrderIdList=new ArrayList<Object[]>();
		List orderIdList=new ArrayList();
		for (RechargeMktBillBean rechargeMktBillBean:rechargeMktBillBeanList) {
			rechargeMktBillBeanMap.put(rechargeMktBillBean.getOrderId(), rechargeMktBillBean);
			
		}
	
		for(OrderPayBean orderPayBean:OrderPayBeanList){
			
			serialOrderId=orderPayBean.getSerialOrderId();
			
			if(rechargeMktBillBeanMap.containsKey(serialOrderId)){
				RechargeMktBillBean tempRechargeMktBillBean=(RechargeMktBillBean) rechargeMktBillBeanMap.get(serialOrderId);
				bankState=tempRechargeMktBillBean.getBankState();
				busiState=tempRechargeMktBillBean.getBusiState();
				if("1".equals(bankState)&&"1".equals(busiState)){payStatus="1";}//--银行处理状态成功 &&业务处理状态成功
				if(!"1".equals(bankState)&&"1".equals(busiState)){payStatus="7";}//--银行处理状态成功&&业务处理状态失败
				if("1".equals(bankState)&&!"1".equals(busiState)){payStatus="8";}//--业务处理状态成功&&银行处理状态失败
				if("9".equals(bankState)&&"9".equals(busiState)){payStatus="9";}//--银行处理状态失败 &&业务处理状态失败
				serialOrderIdList.add(new Object[]{payStatus,tempRechargeMktBillBean.getBusiTime(),serialOrderId});
				orderIdList.add(orderPayBean.getOrderId());
			}
			
		}
		if(serialOrderIdList.size()>0){
			orderPayDao.updateOrderPay(serialOrderIdList);
			 checkOrder(orderIdList);
		}
	}
	/*
	 * JSMSS_MARKET_ORDER--营销方案订单表
	 * JSMSS_ORDER_PAY--充值营销案订单关联的充值表
	 * 相互比对
	 */
	private void checkOrder(List orderIdList1){
		List<OrderPayBean>  OrderPayBeanList=rechargeMktBillMapper.queryOrderPaySuccess();
		List<MarketOrderBean> MarketOrderList=marketOrderMapper.queryMarketOrder();
		String marketOrderId="";
		String  payStatus="";
		String  currState="";
		Map orderPayBeanMap=new HashMap();
		List<Object[]> orderIdList=new ArrayList<Object[]>();
		for(OrderPayBean orderPayBean:OrderPayBeanList){
			if(orderIdList1.contains(orderPayBean.getOrderId())){
			orderPayBeanMap.put(orderPayBean.getOrderId(), orderPayBean);
			}
		}
		for(MarketOrderBean marketOrderBean:MarketOrderList){
			marketOrderId=marketOrderBean.getMarketOrderId();
			
			if(orderPayBeanMap.containsKey(marketOrderId)){
				OrderPayBean orderPayBean1=(OrderPayBean) orderPayBeanMap.get(marketOrderId);
				payStatus=orderPayBean1.getPayStatus();//0,1,7,8,9
				if("0".equals(payStatus)){
					currState="0";
				}else if("1".equals(payStatus)){
					currState="1";
				}else{
					currState="9";
				}
				orderIdList.add(new Object[]{currState,orderPayBean1.getPayTime(), marketOrderId});
			}
			
		}
		if(orderIdList.size()>0){
			marketOrderDao.updateMarketOrder(orderIdList);
		}
	}
	/**
	 * 礼品下发
	 */
	@Scheduled(cron  = "0 0/30 * * * ? ")//--每半小时
	@Transactional(propagation=Propagation.REQUIRED)
	public void  scheduledCheckOrderGift(){
		System.out.println("================================礼品下发任务开始：" + new Date().toLocaleString());
		List<OrderGiftBean> orderGiftList= rechargeMktBillMapper.queryOrderGift();
		List<Object[]> orderIdList=new ArrayList<Object[]>();
		String  orderId="";
		String giftErrorMsg="";
		 String orderMoveId="";
		 String marketSecondBossCode="0";
		DangDangCreateResult ddFlag=new DangDangCreateResult();
		String pkid="";
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		for(OrderGiftBean orderGiftBean:orderGiftList){
			orderId=orderGiftBean.getOrderId();
			giftErrorMsg=orderGiftBean.getGiftSendError();
			pkid=sequenceService.getSequence("seq_jsmss_interface_log");
			InterfaceLogBean interfaceLogBean=new InterfaceLogBean();
			interfaceLogBean.setPkid(pkid);
			interfaceLogBean.setOrderId(orderId);
			interfaceLogBean.setMsisdn(orderGiftBean.getMsisdn());
			interfaceLogBean.setType("9");
			 Date date=new Date();
			 interfaceLogBean.setReqTime(dateFormat.format(date));
			orderIdList.add(new Object[]{orderId});
			if(StringUtils.isNotBlank(orderGiftBean.getMarketSecondPkid())){
				marketSecondBossCode=marketSecondDaoImpl.queryMarketSecondBossCode(orderGiftBean.getMarketSecondPkid());
			}
			
			if ("9".equals(orderGiftBean.getGiftType())) {// --电影票
				
				if(orderGiftBean.getGiftTicketId()!=null&&orderGiftBean.getGiftTicketId().startsWith("APP")){//--方正电影院的电影票
						try {
						
						if (MDLUtil.sendCode(orderGiftBean.getMsisdn(), orderGiftBean.getGiftTicketId(),marketSecondBossCode,ztChannel)) {
							interfaceLogBean.setRespFlag("1");
							orderGiftBean.setGiftSendStatus("1");
						}else{
							interfaceLogBean.setRespFlag("0");
							orderGiftBean.setGiftSendStatus("2");
							
//							if (StringUtils.isBlank(giftErrorMsg)){
//								orderGiftBean.setGiftSendError("1");
//							}else if("1".equals(giftErrorMsg)){
//								orderGiftBean.setGiftSendError("2");
//							}else if("2".equals(giftErrorMsg)){
//								orderGiftBean.setGiftSendStatus("2");
//								orderGiftBean.setGiftSendError("-1");
//							}
						}	
						interfaceLogService.saveInterfaceLog(interfaceLogBean);
						} catch (Exception e) {
							interfaceLogBean.setRespFlag("0");
							orderGiftBean.setGiftSendStatus("2");
							
//							if (StringUtils.isBlank(giftErrorMsg)){
//								orderGiftBean.setGiftSendError("1");
//							}else if("1".equals(giftErrorMsg)){
//								orderGiftBean.setGiftSendError("2");
//							}else if("2".equals(giftErrorMsg)){
//								orderGiftBean.setGiftSendStatus("2");
//								orderGiftBean.setGiftSendError("-1");
//							}
							
							interfaceLogService.saveInterfaceLog(interfaceLogBean);
						}
				}else{//--宽连电影院的电影票
				try {
					//--
					if(StringUtils.isBlank(orderGiftBean.getElecCouponsNum())){
						orderMoveId=SendTicketUtilV2.getOrderId();	
					}else{
						orderMoveId=orderGiftBean.getElecCouponsNum();
					}
					 
			 if ("1".equals(MoveUtil.mvTicket(orderGiftBean.getMsisdn(), orderGiftBean.getMovieId(), orderGiftBean.getGiftTicketId(), Integer.valueOf(orderGiftBean.getGiftNum()) , orderGiftBean.getGiftName(), "","", orderMoveId,marketSecondBossCode,ztChannel))) {
					interfaceLogBean.setRespFlag("1");
						orderGiftBean.setGiftSendStatus("1");
						orderGiftBean.setElecCouponsNum(orderMoveId);
					}else{
						interfaceLogBean.setRespFlag("0");
						orderGiftBean.setGiftSendStatus("0");
						if (StringUtils.isBlank(giftErrorMsg)){
							orderGiftBean.setGiftSendError("1");
							orderGiftBean.setElecCouponsNum(orderMoveId);
						}else if("1".equals(giftErrorMsg)){
							orderGiftBean.setGiftSendError("2");
							orderGiftBean.setElecCouponsNum(orderMoveId);
						}else if("2".equals(giftErrorMsg)){
							orderGiftBean.setGiftSendStatus("2");
							orderGiftBean.setGiftSendError("-1");
						}
						
						}
						interfaceLogService.saveInterfaceLog(interfaceLogBean);
				} catch (Exception e) {
					e.printStackTrace();
					interfaceLogBean.setRespFlag("0");
					orderGiftBean.setGiftSendStatus("0");
					
					if (StringUtils.isBlank(giftErrorMsg)){
						orderGiftBean.setGiftSendError("1");
						orderGiftBean.setElecCouponsNum(orderMoveId);
					}else if("1".equals(giftErrorMsg)){
						orderGiftBean.setGiftSendError("2");
						orderGiftBean.setElecCouponsNum(orderMoveId);
					}else if("2".equals(giftErrorMsg)){
						orderGiftBean.setGiftSendStatus("2");
						orderGiftBean.setGiftSendError("-1");
					}
					interfaceLogService.saveInterfaceLog(interfaceLogBean);
				}
				}
			} else if ("10".equals(orderGiftBean.getGiftType())) {// --当当券
					try {
						
					ddFlag = DangDangUtil.sendDangDang(orderGiftBean.getMsisdn(), orderGiftBean.getGiftName(), orderGiftBean.getGiftPrice(), "", "",marketSecondBossCode,ztChannel);
					if ("0".equals(ddFlag.getError_code())) {
						interfaceLogBean.setRespFlag("1");
						orderGiftBean.setGiftSendStatus("1");
					}else{
						interfaceLogBean.setRespFlag("0");
						orderGiftBean.setGiftSendStatus("2");
						
//						if (StringUtils.isBlank(giftErrorMsg)){
//							orderGiftBean.setGiftSendError("1");
//						}else if("1".equals(giftErrorMsg)){
//							orderGiftBean.setGiftSendError("2");
//						}else if("2".equals(giftErrorMsg)){
//							orderGiftBean.setGiftSendStatus("2");
//							orderGiftBean.setGiftSendError("-1");
//						}
						
						interfaceLogBean.setErrorMsg(ddFlag.getError_msg());
						interfaceLogBean.setErrorCode(ddFlag.getError_code());
					}
					interfaceLogService.saveInterfaceLog(interfaceLogBean);
					} catch (Exception e) {
						interfaceLogBean.setRespFlag("0");
						orderGiftBean.setGiftSendStatus("2");
						
//						if (StringUtils.isBlank(giftErrorMsg)){
//							orderGiftBean.setGiftSendError("1");
//						}else if("1".equals(giftErrorMsg)){
//							orderGiftBean.setGiftSendError("2");
//						}else if("2".equals(giftErrorMsg)){
//							orderGiftBean.setGiftSendStatus("2");
//							orderGiftBean.setGiftSendError("-1");
//						}
						
					  interfaceLogService.saveInterfaceLog(interfaceLogBean);
					}
			} else if("11".equals(orderGiftBean.getGiftType())){//---电子券
				
				LclBean lclBean=new LclBean();
				try {
					lclBean=couponCodeService.selectOneCouponcode();
					lclBean.setMsisdn(orderGiftBean.getMsisdn());
					lclBean.setMktorderid(orderId);
					lclBean.setPrice(orderGiftBean.getGiftPrice());
					String couponcode=lclBean.getCouponcode();
					//System.out.println(couponcode+"==========couponcode================");
					CzCouponResult re=new CzCouponResult();
					if(StringUtils.isNotBlank(couponcode)){
				   re=CzCouponUtil.create(orderGiftBean.getMsisdn(), "2", orderGiftBean.getGiftPrice(),couponcode);
					}
				
				String ret=re.getRet();
				if(ret!=null&&"0000".equals(ret)){
					interfaceLogBean.setRespFlag("1");
					lclBean.setStatus("1");
					orderGiftBean.setGiftSendStatus("1");
				}else if(ret!=null&&"-1111".equals(ret)){
					
					interfaceLogBean.setRespFlag("0");
					interfaceLogBean.setErrorMsg("异常");
					interfaceLogBean.setErrorCode(ret);
					lclBean.setStatus("2");
					orderGiftBean.setGiftSendStatus("2");
					
//					if (StringUtils.isBlank(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("1");
//					}else if("1".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("2");
//					}else if("2".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendStatus("2");
//						orderGiftBean.setGiftSendError("-1");
//					}
					
				}else {
					interfaceLogBean.setRespFlag("0");
					interfaceLogBean.setErrorMsg("无可使用礼券");
					interfaceLogBean.setErrorCode(ret);
					lclBean.setStatus("2");
					orderGiftBean.setGiftSendStatus("2");
					
//					if (StringUtils.isBlank(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("1");
//					}else if("1".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("2");
//					}else if("2".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendStatus("2");
//						orderGiftBean.setGiftSendError("-1");
//					}
					
				}
				couponCodeService.updateCouponcode(lclBean);
				 interfaceLogService.saveInterfaceLog(interfaceLogBean);
				} catch (Exception e) {
					interfaceLogBean.setRespFlag("0");
					orderGiftBean.setGiftSendStatus("2");
					
//					if (StringUtils.isBlank(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("1");
//					}else if("1".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("2");
//					}else if("2".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendStatus("2");
//						orderGiftBean.setGiftSendError("-1");
//					}
					interfaceLogService.saveInterfaceLog(interfaceLogBean);
				}
				
			} else {// --麦当劳
				try {
				if (MDLUtil.sendCode(orderGiftBean.getMsisdn(), orderGiftBean.getGiftTicketId(),marketSecondBossCode,ztChannel)) {// (phoneNum, applyCode)
					interfaceLogBean.setRespFlag("1");
					orderGiftBean.setGiftSendStatus("1");
				}else{
					interfaceLogBean.setRespFlag("0");
					orderGiftBean.setGiftSendStatus("2");
					
//					if (StringUtils.isBlank(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("1");
//					}else if("1".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("2");
//					}else if("2".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendStatus("2");
//						orderGiftBean.setGiftSendError("-1");
//					}
				}
				interfaceLogService.saveInterfaceLog(interfaceLogBean);
				} catch (Exception e) {
					interfaceLogBean.setRespFlag("0");
					orderGiftBean.setGiftSendStatus("2");
					
//					if (StringUtils.isBlank(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("1");
//					}else if("1".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendError("2");
//					}else if("2".equals(giftErrorMsg)){
//						orderGiftBean.setGiftSendStatus("2");
//						orderGiftBean.setGiftSendError("-1");
//					}
				interfaceLogService.saveInterfaceLog(interfaceLogBean);
				}
			}
			
			rechargeMktBillMapper.updateOrderGift(orderGiftBean);
		}
		System.out.println("========================礼品下发任务结束：" + new Date().toLocaleString());
	}
	
	//@Scheduled(cron = "0 58 18 17 2 ?")//--每天早8:30 0 15 10 17 2 ? 2005
	private void checkAllBill(){
		System.out.println("========================ALL充值对账任务开始：" + new Date().toLocaleString());
		List<OrderPayBean>  OrderPayBeanList=rechargeMktBillMapper.queryOrderPay();
		List<RechargeMktBillBean> rechargeMktBillBeanList=rechargeMktBillMapper.queryRechargeMktAllBill();
		System.out.println(OrderPayBeanList.size()+"========OrderPayBeanList=================");
	System.out.println(rechargeMktBillBeanList.size()+"========rechargeMktBillBeanList=================");
		Map<String,Object> rechargeMktBillBeanMap=new HashMap<String,Object> ();
		String serialOrderId="";
	    String bankState="";
	    String busiState="";
	    String payStatus="0";//--默认未处理
		List<Object[]> serialOrderIdList=new ArrayList<Object[]>();
		List orderIdList=new ArrayList();
		for (RechargeMktBillBean rechargeMktBillBean:rechargeMktBillBeanList) {
			rechargeMktBillBeanMap.put(rechargeMktBillBean.getOrderId(), rechargeMktBillBean);
			
		}
	
		for(OrderPayBean orderPayBean:OrderPayBeanList){
			
			serialOrderId=orderPayBean.getSerialOrderId();
			
			if(rechargeMktBillBeanMap.containsKey(serialOrderId)){
				//System.out.println(serialOrderId+"========serialOrderId=================");
				RechargeMktBillBean tempRechargeMktBillBean=(RechargeMktBillBean) rechargeMktBillBeanMap.get(serialOrderId);
				bankState=tempRechargeMktBillBean.getBankState();
				busiState=tempRechargeMktBillBean.getBusiState();
				if("1".equals(bankState)&&"1".equals(busiState)){payStatus="1";}//--银行处理状态成功 &&业务处理状态成功
				if(!"1".equals(bankState)&&"1".equals(busiState)){payStatus="7";}//--银行处理状态成功&&业务处理状态失败
				if("1".equals(bankState)&&!"1".equals(busiState)){payStatus="8";}//--业务处理状态成功&&银行处理状态失败
				if("9".equals(bankState)&&"9".equals(busiState)){payStatus="9";}//--银行处理状态失败 &&业务处理状态失败
				serialOrderIdList.add(new Object[]{payStatus,tempRechargeMktBillBean.getBusiTime(),serialOrderId});
				orderIdList.add(orderPayBean.getOrderId());
			//System.out.println(serialOrderId+"=====serialOrderId====payStatus=="+payStatus);
			//System.out.println(orderPayBean.getOrderId()+"=====orderPayBean.getOrderId()====payStatus=="+payStatus);
			}
			
		}
		//System.out.println(serialOrderIdList.size()+"=====serialOrderIdList.size()====payStatus==");
		if(serialOrderIdList.size()>0){
			orderPayDao.updateOrderPay(serialOrderIdList);
			 checkAllOrder(orderIdList);
		}
	
	}
	
	/*
	 * JSMSS_MARKET_ORDER--营销方案订单表
	 * JSMSS_ORDER_PAY--充值营销案订单关联的充值表
	 * 相互比对
	 */
	private void checkAllOrder(List orderIdList1){
		List<OrderPayBean>  OrderPayBeanList=rechargeMktBillMapper.queryOrderPayAllSuccess();//
		List<MarketOrderBean> MarketOrderList=marketOrderMapper.queryMarketAllOrder();
		//System.out.println(OrderPayBeanList.size()+"====OrderPayBeanList=====");
		//System.out.println(MarketOrderList.size()+"=====MarketOrderList=======");
		String marketOrderId="";
		String  payStatus="";
		String  currState="";
		Map orderPayBeanMap=new HashMap();
		List<Object[]> orderIdList=new ArrayList<Object[]>();
		for(OrderPayBean orderPayBean:OrderPayBeanList){
			if(orderIdList1.contains(orderPayBean.getOrderId())){
			orderPayBeanMap.put(orderPayBean.getOrderId(), orderPayBean);
			}
		}
		for(MarketOrderBean marketOrderBean:MarketOrderList){
			marketOrderId=marketOrderBean.getMarketOrderId();
			
			if(orderPayBeanMap.containsKey(marketOrderId)){
				OrderPayBean orderPayBean1=(OrderPayBean) orderPayBeanMap.get(marketOrderId);
				payStatus=orderPayBean1.getPayStatus();//0,1,7,8,9
				if("0".equals(payStatus)){
					currState="0";
				}else if("1".equals(payStatus)){
					currState="1";
				}else{
					currState="9";
				}
				orderIdList.add(new Object[]{currState,orderPayBean1.getPayTime(), marketOrderId});
			}
			
		}
		if(orderIdList.size()>0){
			marketOrderDao.updateMarketOrder(orderIdList);
		}
		   System.out.println("ALL充值对账任务已完成:" + new Date().toLocaleString());
	}

	
}
