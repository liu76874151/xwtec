package com.xwtech.uomp.base.util.order;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;

import com.xwtech.uomp.base.util.ConfigurationRead;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**当当券下发接口
 *@ClassName:DangDangUtil.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-18 time：上午11:24:42
 *@version 1.0
 */
public class DangDangUtil {
	 private static final Logger logger = Logger.getLogger(DangDangUtil.class);
	// @Value("${Create_httpurl}")
		private static  String Create_httpurl=ConfigurationRead.getInstance().getValue("Create_httpurl");
	  //  private static final String Create_httpurl = "http://192.168.67.120:9010/draw_service/dangdangMt.do";
	   // private static final String Create_httpurl = "http://10.32.111.28:9001/draw_service/dangdangMt.do";
	    //private static final String Create_httpurl = " http://10.32.110.120:9010/draw_service/dangdangMt.do";
	   // private static final String Cancle_httpurl = " http://10.32.110.120:9010/draw_service/dangdangQx.do";
	   // private static final String Query_httpurl = " http://10.32.110.120:9010/draw_service/dangdangCx.do";
	    private static Map<String, String> activeNo = new ConcurrentHashMap<String, String>();
	    
	    static{
	        activeNo.put("60", "2001");
	        activeNo.put("120", "2002");
	    }
	    
	    public static DangDangCreateResult sendDangDang(String msisdn, String actName, String mount, String mPlanId, String orderId,String stage_num,String channel)
	    {
	        //if(Util.isNullValidate(orderId))
	        	 if(StringUtils.isBlank(orderId))
	        {
	            orderId = getOrder_Id();
	        }
	        DecimalFormat df = new DecimalFormat("#######");
	        DecimalFormat pointDf = new DecimalFormat("########.00");
	        DangDangCreateResult result = create(orderId, 
	                msisdn,
	                activeNo.get(mount), //活动号  60元2001  120元2002df.format(Double.parseDouble(mount))
	                actName, //活动名称
	                channel, //渠道  4：网厅    5：掌厅     3：短厅
	                pointDf.format(Double.parseDouble(mount)), //充值金额 小数后2位  60.00   120.00
	                "1",//获取礼券类型： 1：新发  2：补发
	                stage_num);  //二级营销案pkid
	        //记发送日志
	        return result;
	    }
	    public static DangDangCreateResult create(String order_id, String user_mobile,
	            String act_id, String act_name, String channel, String amount,
	            String type,String stage_num)
	    {
	        XmlTools xmlTools = new XmlTools();
	        xmlTools.addParameter("order_id", order_id);
	        xmlTools.addParameter("stage_num", stage_num);
	        xmlTools.addParameter("user_mobile", user_mobile);
	        xmlTools.addParameter("act_id", act_id);
	        xmlTools.addParameter("act_name", act_name);
	        xmlTools.addParameter("channel", channel);
	        xmlTools.addParameter("amount", amount);
	        xmlTools.addParameter("type", type);
	        Map<String, String> paramMap = new HashMap<String, String>();
	        
	        paramMap.put("params", xmlTools.packRequest());
	        String result = null;
	        try
	        {
	            result = Sendhttp.sendPost(Create_httpurl, paramMap);
	            System.out.println(result);
	        }
	        catch (Exception e)
	        {
	            logger.error("调用中间键失败", e);
	            DangDangCreateResult ino = new DangDangCreateResult();
	            ino.setRet("调用中间键异常");
	            ino.setMsg(e.getClass().getName() + ": " + e.getMessage());
	            return ino;
	        }
	        System.out.println(result+"============result========DangDangCreateResult=====");
	        return getCreateResult(result);
	        
	    }
	    
	    private static DangDangCreateResult getCreateResult(String result)
	    {
	        DangDangCreateResult ddc = new DangDangCreateResult();
	        if (result == null)
	        {
	            ddc.setRet("中间键返回报文为NULL");
	            ddc.setMsg("返回报文为NULL");
	            return ddc;
	        }
	        
	        try
	        {
	            Document document = DocumentHelper.parseText(result);
	            Element root = document.getRootElement();
	            String ret = StringUtils.trimToEmpty(root.elementTextTrim("ret"));
	            String msg = StringUtils.trimToEmpty(root.elementTextTrim("msg"));
	            if ("0".equals(ret))
	            {
	                ddc.setSuccess(true);
	                String coupon_id = StringUtils.trimToEmpty(root.elementTextTrim("coupon_id"));//trimToEmpty
	                String password = StringUtils.trimToEmpty(root.elementTextTrim("password"));
	                String order_id = StringUtils.trimToEmpty(root.elementTextTrim("order_id"));
	                String request_id = StringUtils.trimToEmpty(root.elementTextTrim("request_id"));
	                String error_code = StringUtils.trimToEmpty(root.elementTextTrim("error_code"));
	                String error_message = StringUtils.trimToEmpty(root.elementTextTrim("error_message"));
	                ddc.setCoupon_id(coupon_id);
	                ddc.setPassword(password);
	                ddc.setOrder_id(order_id);
	                ddc.setRequest_id(request_id);
	                ddc.setError_code(error_code);
	                ddc.setError_msg(error_message);
	            }
	            else
	            {
	                ddc.setSuccess(false);
	            }
	            ddc.setRet(ret);
	            ddc.setMsg(msg);
	        }
	        catch (Exception e)
	        {
	            logger.error("解析XML失败" + " result:" + result, e);
	            ddc.setSuccess(false);
	            ddc.setMsg("解析XML失败");
	        }
	        return ddc;
	    }
	    public static String getOrder_Id()
	    {
	        String[] strs = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	        StringBuffer order_id = new StringBuffer("");
	        int index = 0;
	        for(int i = 0 ; i < 3 ; i++ )
	        {
	            index = (int)(Math.random()*26);
	            order_id.append(strs[index]);
	        }
	        order_id.append("_");
	        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        order_id.append(sd.format(new Date()));
	        return order_id.toString();
	    }
	    
}
