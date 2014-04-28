package com.xwtech.uomp.base.util.order;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;

import com.xwtech.uomp.base.util.ConfigurationRead;




/**麦当劳下发接口
 *@ClassName:MDLUtil.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-18 time：上午11:25:24
 *@version 1.0
 */
public class MDLUtil {
	// @Value("${webURL}")
 	private  static String webURL = ConfigurationRead.getInstance().getValue("webURL");
 	/**
 	 * 
 	 * @param phoneNum
 	 * @param applyCode
 	 * @param re_stage_num 二级营销案pkid
 	 * @return
 	 */
	public static boolean sendCode(String phoneNum, String applyCode,String re_stage_num,String channel)
    {
        boolean flag = true;
       System.out.println(webURL+"===========webURL=============");
       // String webURL = "http://192.168.67.120:9010/draw_service/company/applyBarCode.do";
       // String webURL = "http://10.32.111.28:9001/draw_service/company/applyBarCode.do";
        if (null == applyCode || "".equals(applyCode))
        {
        	return false;
        }
        Map<String, String> parameters = new HashMap<String, String>();
        XmlTools xmlTools = new XmlTools();
        xmlTools.addParameter("stageId", "1059");
        xmlTools.addParameter("applyCode", applyCode); //app002
        xmlTools.addParameter("recordNo", "111");
        xmlTools.addParameter("re_stage_num", re_stage_num);
        
        xmlTools.addParameter("mobile", phoneNum);
        xmlTools.addParameter("brand", "1");
        xmlTools.addParameter("area", "16");
        xmlTools.addParameter("sessionId", "1111");
        xmlTools.addParameter("userIp", "127.0.0.1");
        xmlTools.addParameter("resultType", "xml");
        xmlTools.addParameter("channel", channel);
        xmlTools.addParameter("county", "1614");
        xmlTools.addParameter("password", "aaa");
        xmlTools.addParameter("userId", "2222222222");
        parameters.put("params", xmlTools.packRequest());
        
        BufferedReader outInput = null;
        
        StringBuilder rsp = new StringBuilder();
        try
        {
            outInput = Sendhttp.sendPostRetStream(webURL, parameters);
            if (outInput != null)
            {
            	SAXBuilder builder = new SAXBuilder(false);
              
              Document doc = null;
                try
                {
                    doc = builder.build(outInput);
                }
                catch (JDOMException e)
                {
                    e.printStackTrace();
                }
                
                Element root = doc.getRootElement();
                
                List<Element> oneNodeList = root.getChildren();
                
                for (Element oneNode : oneNodeList)
                {
                    String nodeName = oneNode.getName().trim();
                    
                    if ("resultObject".equals(nodeName))
                    { // 得到二级节点信息 
                        List<Element> twoNodList = oneNode.getChildren();
                        for (Element twoNode : twoNodList)
                        {
                            String nodeName1 = twoNode.getName().trim();
                            String nodeValue1 = twoNode.getTextTrim();
                            
                            rsp.append(nodeName1);
                            rsp.append(":");
                            rsp.append(nodeValue1);
                            rsp.append("\n");
                            
                            if ("flag".equals(nodeName1))
                            {
                                if ("false".equals(nodeValue1))
                                {
                                    flag = false;
                                }
                            }
                        }
                    }
                    else
                    {
                        String nodeValue = oneNode.getTextTrim();
                        rsp.append(nodeName);
                        rsp.append(":");
                        rsp.append(nodeValue);
                        rsp.append("<br />");
                    }
                }
            }
            else
            {
                rsp.append("outInput is null!");
                flag = false;
            }
        }
        catch (Exception e)
        {
            flag = false;
        }
        finally
        {
            if (null != outInput)
            {
                try
                {
                    outInput.close();
                }
                catch (Exception e)
                {
                }
            }
        }
        
        if (!flag)
        {
        	System.out.println("SendMDLCode Fail: " + rsp.toString());
        }
        
        return flag;
    }

}
