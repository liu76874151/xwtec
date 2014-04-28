package com.xwtech.uomp.base.util.order;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;



/**电影票下发接口
 *@ClassName:MoveUtil.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-18 time：上午11:25:49
 *@version 1.0
 */
public class MoveUtil {
	  public static String mvTicket(String mobile,String cId,String tId, int cont, String actName, String typeId, String mPlanId, String orderId,String stageId,String channel) throws Exception
	    {
	       // if(Util.isNullValidate(orderId))
		  if(StringUtils.isBlank(orderId))
	        {
	            // 取得订单号
	            orderId = SendTicketUtilV2.getOrderId();
	        }
	        //电影院
	        String cinemaId = cId;
	        //电影票ID
	        String ticketId = tId;
	        if(StringUtils.isBlank(actName))
	        {
	            actName = "%E6%8E%8C%E8%90%A5%E7%94%B5%E5%AD%90%E6%B8%A0%E9%81%93%E5%85%85%E5%80%BC%E4%BC%98%E6%83%A0"; //短厅电子渠道更优惠活动
	        }
	        else
	        {
	            try
	            {
	                actName = URLEncoder.encode(actName, "utf-8");
	            }
	            catch (UnsupportedEncodingException e)
	            {
	                actName = "%E6%8E%8C%E8%90%A5%E7%94%B5%E5%AD%90%E6%B8%A0%E9%81%93%E5%85%85%E5%80%BC%E4%BC%98%E6%83%A0"; //短厅电子渠道更优惠活动
	                e.printStackTrace();
	            }
	        }
	        
	        SendTicketResultV2 sendTicketResultV2 = SendTicketUtilV2.sendTicket(mobile,
	                cinemaId,
	                ticketId,
	                orderId,
	                String.valueOf(cont), //1张
	                stageId, //二级营销案pkid
	                channel,//3:短厅 4:网厅 5:掌厅
	                actName); //活动名 活动名称（中文需用uft-8转码）URLEncoder.encode(ss, "utf-8")
	        
	        return sendTicketResultV2.isSuccess() ? "1" : orderId;
	    }
}
