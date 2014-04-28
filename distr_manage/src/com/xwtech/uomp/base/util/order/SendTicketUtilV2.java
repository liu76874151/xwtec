package com.xwtech.uomp.base.util.order;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xwtech.uomp.base.util.ConfigurationRead;





/**
 *@ClassName:SendTicketUtilV2.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-19 time：上午09:14:44
 *@version 1.0
 */
public class SendTicketUtilV2 {
	   private static final Logger logger = Logger.getLogger(SendTicketUtilV2.class);

	    //private static final String httpurl = "http://10.32.111.28:9001/draw_service/mvmtV2.do";
	    private static  String httpurl = ConfigurationRead.getInstance().getValue("Ticket_url");
	    //private static final String resendHttpurl = "http://10.32.111.28:9001/draw_service/reSend.do";
	    
	    private static int seq = 0;
	    public static SendTicketResultV2 sendTicket(String mobile, String cinemaId,
	            String ticketId, String orderId, String ticketNum, String stageId,
	            String channel, String actName)
	    {
	        XmlTools xmlTools = new XmlTools();
	        xmlTools.addParameter("cinema_id", cinemaId);
	        xmlTools.addParameter("ticket_id", ticketId);
	        xmlTools.addParameter("amount_per_mobile", ticketNum);
	        xmlTools.addParameter("order_id", orderId);
	        xmlTools.addParameter("user_mobie", mobile);
	        xmlTools.addParameter("channel", channel);
	        xmlTools.addParameter("stageId", stageId);
	        xmlTools.addParameter("actName", actName);
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        paramMap.put("params", xmlTools.packRequest());
	        String result = null;
	        try
	        {
	            result = Sendhttp.moveSendPost(httpurl, paramMap);
	        }
	        catch (Exception e)
	        {
	            logger.error("调用中间键失败", e);
	            SendTicketResultV2 ino = new SendTicketResultV2();
	            ino.setRet("调用中间键异常");
	            ino.setMsg(e.getClass().getName() + ": " + e.getMessage());
	            System.out.println("---------调用中间键失败------"+e);
	            return ino;
	        }
	        try
	        {
	            logger.debug(URLDecoder.decode(result, "UTF-8"));
	        }
	        catch (UnsupportedEncodingException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println(result+"==========result================");
	        return getResult(result);
	        
	    }
	    private static SendTicketResultV2 getResult(String result)
	    {
	        SendTicketResultV2 sendTicketResultV2 = new SendTicketResultV2();
	        if (result == null)
	        {
	            sendTicketResultV2.setRet("中间键返回报文为NULL");
	            sendTicketResultV2.setMsg("返回报文为NULL");
	            return sendTicketResultV2;
	        }
	        
	        try
	        {
	            Document document = DocumentHelper.parseText(result);
	            Element root = document.getRootElement();
	            String ret = StringUtils.trimToEmpty(root.elementTextTrim("ret"));
	            String msg = StringUtils.trimToEmpty(root.elementTextTrim("msg"));
	            if ("0000".equals(ret) || "000".equals(ret))
	            {
	                sendTicketResultV2.setSuccess(true);
	            }
	            else
	            {
	                sendTicketResultV2.setSuccess(false);
	            }
	            sendTicketResultV2.setRet(ret);
	            sendTicketResultV2.setMsg(msg);
	        }
	        catch (Exception e)
	        {
	            logger.error("解析XML失败" + " result:" + result, e);
	            sendTicketResultV2.setSuccess(false);
	            sendTicketResultV2.setMsg("解析XML失败");
	            System.out.println("解析XML失败" + " result:" + result);
	            System.out.println("##################"+e);
	        }
	        return sendTicketResultV2;
	        
	    }
	    
	    public static String getTodayChar17()
	    {
	        String dateString = DateFormatUtils.format(new Date(),
	                "yyyyMMddHHmmssS");
	        int length = dateString.length();
	        for (int i = 0; i < 17 - length; i++)
	        {
	            dateString = dateString + "0";
	        }
	        return dateString;
	    }
	/**
     * 通过当前逻辑服务器ID、时间戳和序列得到唯一键
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static String getUniqueKey()
    {
        String timeStr = getTodayChar17();
        String serverId = "1"; //当前逻辑服务ID
        return serverId + timeStr + getNextSeq();
    }
    
    public static String getOrderId()
    {
        return "WAP_MV" + getUniqueKey();
    }
    /**
     * 取得0-99循环的序列
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static synchronized int getNextSeq()
    {
        if (seq == 99)
        {
            seq = 0;
        }
        else
        {
            seq++;
        }
        return seq;
    }
}
