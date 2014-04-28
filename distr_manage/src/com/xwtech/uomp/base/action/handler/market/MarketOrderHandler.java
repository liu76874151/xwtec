package com.xwtech.uomp.base.action.handler.market;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.order.ExcelBean;
import com.xwtech.uomp.base.pojo.order.MarketOrderBean;
import com.xwtech.uomp.base.service.order.IMarketOrderSerivce;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.order.CreateExcelTool.CreateExcel;
/**
 *@ClassName:MarketOrderHandler.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-11 time：上午10:42:03
 *@version 1.0
 */
@Controller("H_marketOrder")
public class MarketOrderHandler implements IHandler {
	@Autowired
	IMarketOrderSerivce marketOrderSerivce;
	private static final Log log = LogFactory.getLog(MarketOrderHandler.class);
/**
 * 营销案订单查询
 * @param context
 * @return
 */
	 public HandlerResult queryMarketOrderList(HandlerRequestContext context) {
		 	HandlerResult result = HandlerResult.newInstance();
	        HttpServletRequest request = context.getRequest();
	        Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	        String brandId = RequestUtil.getString(request, "brandId");
	        String channelType = RequestUtil.getString(request, "channelType");
	        String payType = RequestUtil.getString(request, "payType");
	        String PayStatus = RequestUtil.getString(request, "PayStatus");
	        String city = RequestUtil.getString(request, "city");
	        if("0".equals(city)){
	        	//param.put("city", null);	//---0位江苏省 全差?
	        }
	        String[] brandIds=null;
	        String[] channelTypes=null;
	        String[] payTypes=null;
	        String[] PayStatuss=null;
	        if(StringUtils.isNotBlank(channelType)){
	        	channelTypes=channelType.split("\\,");
	        }
	        if(StringUtils.isNotBlank(brandId)){
	               brandIds=brandId.split("\\,");
	        }
	        if(StringUtils.isNotBlank(payType)){
	        	payTypes=payType.split("\\,");
	        }
	        if(StringUtils.isNotBlank(PayStatus)){
	        	PayStatuss=PayStatus.split("\\,");
	        }
	        param.put("brandId", brandIds);
	        param.put("channelType", channelTypes);
	        param.put("payType", payTypes);
	        param.put("PayStatus", PayStatuss);
	        try {
				Page page=marketOrderSerivce.queryMarketOrderList(param);
				result.setRetObj(page);
				result.setRetCode(IResultCode.SYS_SUCCESS);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            result.setRetCode(IResultCode.SYS_FAILED);
			}
			return result;
			}
	 
	 
	 public HandlerResult queryMarketOrderCountList(HandlerRequestContext context) {
		 	HandlerResult result = HandlerResult.newInstance();
	        HttpServletRequest request = context.getRequest();
	        Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	        
	        String channalData = RequestUtil.getString(request, "brandId");
	        String[] channalDatas=null;
	        if(StringUtils.isNotBlank(channalData)){
	        	channalDatas=channalData.split("\\,");
	        }
	        param.put("channalData", channalDatas);
	        try {
	        	Page page=marketOrderSerivce.queryMarketOrderCountList(param);
	        	result.setRetObj(page);
				result.setRetCode(IResultCode.SYS_SUCCESS);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            result.setRetCode(IResultCode.SYS_FAILED);
			}
			return result;
			}
	 
	 public HandlerResult marketOrderToExcel(HandlerRequestContext context) {
		 	HandlerResult result = HandlerResult.newInstance();
	        HttpServletRequest request = context.getRequest();
	        HttpServletResponse response=context.getResponse();
		   
	        Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	        String brandId = RequestUtil.getString(request, "brandId");
	        String channelType = RequestUtil.getString(request, "channelType");
	        String payType = RequestUtil.getString(request, "payType");
	        String PayStatus = RequestUtil.getString(request, "PayStatus");
	        String[] brandIds=null;
	        String[] channelTypes=null;
	        String[] payTypes=null;
	        String[] PayStatuss=null;
	        if(StringUtils.isNotBlank(channelType)){
	        	channelTypes=channelType.split("\\,");
	        }
	        if(StringUtils.isNotBlank(brandId)){
	               brandIds=brandId.split("\\,");
	        }
	        if(StringUtils.isNotBlank(payType)){
	        	payTypes=payType.split("\\,");
	        }
	        if(StringUtils.isNotBlank(PayStatus)){
	        	PayStatuss=PayStatus.split("\\,");
	        }
	        param.put("brandId", brandIds);
	        param.put("channelType", channelTypes);
	        param.put("payType", payTypes);
	        param.put("PayStatus", PayStatuss);
	        try {
				Page page=marketOrderSerivce.queryMarketOrderList(param);
				List<MarketOrderBean> list=(List<MarketOrderBean>)page.getRecords();
				MarketOrderBean marketOrderBean=new MarketOrderBean();
				String fileName = request.getSession().getId();
				log.debug("fileName:" + fileName);
				Vector[] content = new Vector[list.size()];
				Vector con = null;
				Vector[] titleContent = new Vector[1];
				Vector<String> tit = new Vector<String>();
				tit.add("一级营销案名称");
				tit.add("二级营销案名称");
				tit.add("地市名称");
				tit.add("订单编码");
				tit.add("手机号码");
				tit.add("品牌");
				tit.add("充值金额");
				tit.add("订单时间");
				tit.add("所属渠道");
				tit.add("订单状态");
				titleContent[0] = tit;

				for (int i = 0; i < list.size(); i++) {
					con = new Vector();
					marketOrderBean=list.get(i);
					con.add(marketOrderBean.getMarketFirstName());
					con.add(marketOrderBean.getMarketSecondName());
					con.add(marketOrderBean.getCityName());
					con.add(marketOrderBean.getMarketOrderId());
					con.add(marketOrderBean.getTelNum());
					con.add(marketOrderBean.getBrandId());
					con.add(marketOrderBean.getPayVal());
					con.add(marketOrderBean.getCreateTime());
					con.add(marketOrderBean.getChannelType());
					con.add(marketOrderBean.getCurrState());
					content[i] = con;
				}
				ExcelBean excelBean = new ExcelBean();
				excelBean.setTitle("webApacheLog20071212");
				excelBean.setSheetName("webApacheLog20071212");
				excelBean.setContent(content);
				excelBean.setTitleContent(titleContent);
				boolean flag= CreateExcel.expordExcelAndSaveToFile("", fileName, excelBean);
				
				
				  
		  
		        //声明一个file对象  
		        File f=null;  
		        try {  
		            //根据刚刚的文件地址、创建一个file对象  
		            f =  new File(fileName+".xls");  
		  
		            //如果文件不存在  
		            if (!f.exists()) {  
		                response.sendError(404, "File not found!");  
		            }  
		  
		            //创建一个缓冲输入流对象  
		            BufferedInputStream br = new BufferedInputStream(  
		                    new FileInputStream(f));  
		            byte[] buf = new byte[1024];  
		            int len = 0;  
		  
		            response.reset(); // 非常重要  
		            response.setContentType("application/x-msdownload");  
		            response.setCharacterEncoding("UTF-8");
		            response.setHeader("Content-Disposition", "attachment; filename="  
		                    + f.getName());  
		              
		            //创建输出流对象  
		            OutputStream outStream = response.getOutputStream();  
		  
		            //开始输出  
		            while ((len = br.read(buf)) > 0)  
		                outStream.write(buf, 0, len);  
		  
		            //关闭流对象  
		            br.close();  
		            outStream.close();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
		        if (f.exists()) {//下载完毕删除文件  
		            f.delete();  
		        }  
				result.setRetCode(IResultCode.SYS_SUCCESS);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            result.setRetCode(IResultCode.SYS_FAILED);
			}
			return null;
			 }
	 /**
	  * 导出营销案统计
	  * @param context
	  * @return
	  */
	 public HandlerResult marketCountToExcel(HandlerRequestContext context) {
		 	HandlerResult result = HandlerResult.newInstance();
	        HttpServletRequest request = context.getRequest();
	        HttpServletResponse response=context.getResponse();
	        Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	        
	        String channalData = RequestUtil.getString(request, "brandId");
	        String[] channalDatas=null;
	        if(StringUtils.isNotBlank(channalData)){
	        	channalDatas=channalData.split("\\,");
	        }
	        param.put("channalData", channalDatas);
	        try {
	        	Page page=marketOrderSerivce.queryMarketOrderCountList(param);
	        	List list=page.getRecords();
	        	String fileName = request.getSession().getId();
				log.debug("fileName:" + fileName);
				Vector[] content = new Vector[list.size()];
				Vector con = null;
				Vector[] titleContent = new Vector[1];
				Vector<String> tit = new Vector<String>();
				tit.add("一级营销案名称");
				tit.add("二级营销案名称");
				tit.add("地市名称");
				tit.add("开始时间");
				tit.add("结束时间");
				tit.add("总办理数");
				tit.add("成功数");
				tit.add("失败数");
				tit.add("成功金额");
				titleContent[0] = tit;
	        	
	    for (int i = 0; i < list.size(); i++) {
			con = new Vector();
			con.add(((Map) list.get(i)).get("marketFirstName")+"");
		    con.add(((Map) list.get(i)).get("marketSecondName")+"");
			con.add(((Map) list.get(i)).get("city")+"");
			con.add(((Map) list.get(i)).get("beginTime")+"");
			con.add(((Map) list.get(i)).get("endTime")+"");
			con.add(((Map) list.get(i)).get("count")+"");
			con.add(((Map) list.get(i)).get("success")+"");
			con.add(((Map) list.get(i)).get("fail")+"");
			con.add(((Map) list.get(i)).get("val")+"");
			content[i] = con;
		}
	        	 	ExcelBean excelBean = new ExcelBean();
					excelBean.setTitle("webApacheLog20071212");
					excelBean.setSheetName("webApacheLog20071212");
					excelBean.setContent(content);
					excelBean.setTitleContent(titleContent);
					boolean flag= CreateExcel.expordExcelAndSaveToFile("", fileName, excelBean);
					//声明一个file对象  
			        File f=null;  
			        try {  
			            //根据刚刚的文件地址、创建一个file对象  
			            f =  new File(fileName+".xls");  
			  
			            //如果文件不存在  
			            if (!f.exists()) {  
			                response.sendError(404, "File not found!");  
			            }  
			  
			            //创建一个缓冲输入流对象  
			            BufferedInputStream br = new BufferedInputStream(  
			                    new FileInputStream(f));  
			            byte[] buf = new byte[1024];  
			            int len = 0;  
			  
			            response.reset(); // 非常重要  
			            response.setContentType("application/x-msdownload");  
			            response.setCharacterEncoding("UTF-8");
			            response.setHeader("Content-Disposition", "attachment; filename="  
			                    + f.getName());  
			              
			            //创建输出流对象  
			            OutputStream outStream = response.getOutputStream();  
			  
			            //开始输出  
			            while ((len = br.read(buf)) > 0)  
			                outStream.write(buf, 0, len);  
			  
			            //关闭流对象  
			            br.close();  
			            outStream.close();  
			        } catch (FileNotFoundException e) {  
			            e.printStackTrace();  
			        } catch (IOException e) {  
			            e.printStackTrace();  
			        }  
			        if (f.exists()) {//下载完毕删除文件  
			            f.delete();  
			        }  
				result.setRetCode(IResultCode.SYS_SUCCESS);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            result.setRetCode(IResultCode.SYS_FAILED);
			}
		return null; }

}
