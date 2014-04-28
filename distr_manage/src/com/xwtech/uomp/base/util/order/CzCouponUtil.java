package com.xwtech.uomp.base.util.order;



import java.util.Map;
import java.util.HashMap;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;

import com.xwtech.uomp.base.util.ConfigurationRead;


/**电子优惠券下发接口
 *@ClassName:CzCouponUtil.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-18 time：上午11:24:00
 *@version 1.0
 */
public class CzCouponUtil
{
    private static final Logger logger = Logger.getLogger(CzCouponUtil.class);
    //@Value("${coupon.Httpurl}")
	private static  String httpurl = ConfigurationRead.getInstance().getValue("coupon.Httpurl");
    //private static final String httpurl = "http://127.0.0.1:8080/draw_service/largessCoupon.do";
	//private static final String httpurl = "http://10.32.110.120:9010/draw_service/largessCoupon.do";
	//private static final String httpurl = "http://192.168.66.28:9001/draw_service/largessCoupon.do";
   /**
    * 
    * @param user_mobile  手机号
    * @param channel  渠道
    * @param type  价格
    * @param coupon_code 电子卷编码
    * @return
    */
    public static CzCouponResult create(String user_mobile,  String channel,
            String type,String coupon_code)
    {
        XmlTools xmlTools = new XmlTools();
        xmlTools.addParameter("type", type);
        xmlTools.addParameter("user_mobile", user_mobile);
        xmlTools.addParameter("channel", channel);
        xmlTools.addParameter("coupon_code", coupon_code);
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
            CzCouponResult ino = new CzCouponResult();
            ino.setRet("调用中间键异常");
            ino.setMsg(e.getClass().getName() + ": " + e.getMessage());
            return ino;
        }
        return getCzCouponResult(result);
        
    }
    
    private static CzCouponResult getCzCouponResult(String result)
    {
        CzCouponResult czCoupon = new CzCouponResult();
        if (result == null)
        {
        	czCoupon.setRet("中间键返回报文为NULL");
        	czCoupon.setMsg("返回报文为NULL");
            return czCoupon;
        }
        try
        {
            Document document = DocumentHelper.parseText(result);
            Element root = document.getRootElement();
            String ret = StringUtils.trimToEmpty(root.elementTextTrim("ret"));
            String msg = StringUtils.trimToEmpty(root.elementTextTrim("msg"));
            
            czCoupon.setRet(ret);
            czCoupon.setMsg(msg);
        }
        catch (Exception e)
        {
            logger.error("解析XML失败" + " result:" + result, e);
            czCoupon.setSuccess(false);
            czCoupon.setMsg("解析XML失败");
        }
        return czCoupon;
    }

   
}
