package com.xwtech.uomp.base.action.handler.adv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.adv.AdvAreaDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvBrandDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.adv.IAdvinfoService;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
/**
 *@ClassName:AdvInfoHandler.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-13 time：下午04:43:06
 *@version 1.0
 */
@Controller("H_advInfo")
public class AdvInfoHandler implements IHandler {
	@Autowired
	IAdvinfoService advinfoService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileUpload.URL}")
	private String targetURL;
	 public HandlerResult queryAdvInfoListOrderShowXh(HandlerRequestContext context) {
		 	HandlerResult result = HandlerResult.newInstance();
	        HttpServletRequest request = context.getRequest();
	        Map<String, String> param = BeanUtil.getMapFromRequest(request);
	        try {
				Page page=advinfoService.queryAdvInfoListOrderShowXh(param);
				result.setRetObj(page);
				result.setRetCode(IResultCode.SYS_SUCCESS);
	        } catch (Exception e) {
	        	e.printStackTrace();
	            result.setRetCode(IResultCode.SYS_FAILED);
			}
			return result;
	 }
    public HandlerResult queryAdvInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        Map<String, String> param = BeanUtil.getMapFromRequest(request);
        try {
			Page page=advinfoService.queryAdvInfoList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
    /**
     * 查询一条广告信息
     * @param context
     * @return
     */
    public HandlerResult findOneAdvInfoBean(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        Map<String, String> param = BeanUtil.getMapFromRequest(request);
        try {
			AdvInfoBean advInfoBean=advinfoService.findOneAdvInfoBean(param);
			result.setRetObj(advInfoBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
    
    /**
     * 保存广告信息
     * @param context
     * @return
     */
    public HandlerResult saveAdvInfo(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        List<AdvAreaDzBean> advAreaDzBeanList=new ArrayList<AdvAreaDzBean>();
        List<AdvBrandDzBean> advBrandDzBeanList=new ArrayList<AdvBrandDzBean>();
    	List<AdvInfoBean> advInfoBeanList1=new ArrayList<AdvInfoBean>();
        List<AdvInfoBean> advInfoBeanList=new ArrayList<AdvInfoBean>();
        String channelNum = RequestUtil.getString(request, "channelNum");
        String positionNum = RequestUtil.getString(request, "positionNum");
        String advInfo = RequestUtil.getString(request, "advInfo");
        advInfoBeanList= JSONArray.parseArray(advInfo,AdvInfoBean.class);
        for(AdvInfoBean advInfoBean:advInfoBeanList){
        	 String area=advInfoBean.getAdvAreaNum();
        	 String brand=advInfoBean.getAdvBrandNum();
        	 advAreaDzBeanList= JSONArray.parseArray(area,AdvAreaDzBean.class);
        	 advBrandDzBeanList= JSONArray.parseArray(brand,AdvBrandDzBean.class);
        	 advInfoBean.setAdvAreaDzList(advAreaDzBeanList);
             advInfoBean.setAdvBrandDzList(advBrandDzBeanList);
             advInfoBean.setChannelNum(channelNum);
             advInfoBean.setPositionNum(positionNum);
           //advInfoBean.setUseState("1");//--在用状态（1：默认，在用；0：停用）
           //advInfoBean.setAuditState("0");//--审核通过状态（0：默认，未审核；1：审核通过；2：审核不通过）
        }
        String sortNum = RequestUtil.getString(request, "sortNum");
        String advNums[]=sortNum.split("\\.");
        try {
			int flag=advinfoService.saveAdvInfo(advInfoBeanList);
			if(1==flag){
				advinfoService.sort(advNums);	
				String advMenuIcon,advImg,advUri,advImgS;
			
				for(AdvInfoBean advInfoBean1:advInfoBeanList){
					advMenuIcon=advInfoBean1.getAdvMenuIcon();
					advImg=advInfoBean1.getAdvImg();
					advUri=advInfoBean1.getAdvUri();
					advImgS=advInfoBean1.getAdvImgS();
					if(StringUtils.isNotBlank(advMenuIcon)){
						advMenuIcon=advMenuIcon.substring(advMenuIcon.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advMenuIcon=nanoTime+advMenuIcon;
						
					}
					if(StringUtils.isNotBlank(advImg)){
						advImg=advImg.substring(advImg.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advImg=nanoTime+advImg;
						
					}
					if(StringUtils.isNotBlank(advUri)){
						advUri=advUri.substring(advUri.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advUri=nanoTime+advUri;
						
					}
					if(StringUtils.isNotBlank(advImgS)){
						advImgS=advImgS.substring(advImgS.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advImgS=nanoTime+advImgS;
						
					}
					advInfoBean1.setAdvMenuIcon(advMenuIcon);
				advInfoBean1.setAdvImg(advImg);
					advInfoBean1.setAdvUri(advUri);
					advInfoBean1.setAdvImgS(advImgS);
				advInfoBeanList1.add(advInfoBean1);
					
				}
			}
			result.setRetObj(advInfoBeanList1);
			result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
    /**
     * 修改广告信息
     */
    public HandlerResult updateAdvInfo(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        List<AdvAreaDzBean> advAreaDzBeanList=new ArrayList<AdvAreaDzBean>();
        List<AdvInfoBean> advInfoBeanList1=new ArrayList<AdvInfoBean>();
        List<AdvBrandDzBean> advBrandDzBeanList=new ArrayList<AdvBrandDzBean>();
        List<AdvInfoBean> advInfoBeanList=new ArrayList<AdvInfoBean>();
        String advInfo = RequestUtil.getString(request, "advInfo");
        advInfoBeanList= JSONArray.parseArray(advInfo,AdvInfoBean.class);
        for(AdvInfoBean advInfoBean:advInfoBeanList){
        	 String area=advInfoBean.getAdvAreaNum();
        	 String brand=advInfoBean.getAdvBrandNum();
        	 advAreaDzBeanList= JSONArray.parseArray(area,AdvAreaDzBean.class);
        	 advBrandDzBeanList= JSONArray.parseArray(brand,AdvBrandDzBean.class);
        	 advInfoBean.setAdvAreaDzList(advAreaDzBeanList);
             advInfoBean.setAdvBrandDzList(advBrandDzBeanList);
        }
        String sortNum = RequestUtil.getString(request, "sortNum");
        String advNums[]=sortNum.split("\\.");
        try {
			int flag=advinfoService.updateAdvInfo(advInfoBeanList,false);
			if(1==flag){
				advinfoService.sort(advNums);
				String advMenuIcon,advImg,advUri,advImgS;
				
				List<String> deleteFileName = new ArrayList<String>();
				for(AdvInfoBean advInfoBean1:advInfoBeanList){
					AdvInfoBean tempBean=new AdvInfoBean();
					advMenuIcon=advInfoBean1.getAdvMenuIcon();
					advImg=advInfoBean1.getAdvImg();
					advUri=advInfoBean1.getAdvUri();
					advImgS=advInfoBean1.getAdvImgS();
//					System.out.println(advMenuIcon+"==advMenuIcon==");
//					System.out.println(advImg+"==advImg==");
//					System.out.println(advUri+"==advUri==");
//					System.out.println(advImgS+"==advImgS==");
					if(StringUtils.isNotBlank(advMenuIcon)){
						deleteFileName.add(advMenuIcon);
						advMenuIcon=advMenuIcon.substring(advMenuIcon.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advMenuIcon=nanoTime+advMenuIcon;
						
					}
					if(StringUtils.isNotBlank(advImg)){
						deleteFileName.add(advImg);
						advImg=advImg.substring(advImg.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advImg=nanoTime+advImg;
						
					}
					if(StringUtils.isNotBlank(advUri)){
						deleteFileName.add(advUri);
						advUri=advUri.substring(advUri.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advUri=nanoTime+advUri;
						
					}
					if(StringUtils.isNotBlank(advImgS)){
						deleteFileName.add(advImgS);
						advImgS=advImgS.substring(advImgS.indexOf("."));
						long nanoTime = System.nanoTime(); 
						advImgS=nanoTime+advImgS;
						
					}
					
					tempBean.setAdvMenuIcon(advMenuIcon);
					tempBean.setAdvImg(advImg);
					tempBean.setAdvUri(advUri);
					tempBean.setAdvImgS(advImgS);
					tempBean.setAdvNum(advInfoBean1.getAdvNum());
					advInfoBeanList1.add(tempBean);
					
				}
				fileuploadService.filesDelete(request, targetURL,deleteFileName);
				
			}
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setRetObj(advInfoBeanList1);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
}
    public HandlerResult updateAdvInfoUserState(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        AdvInfoBean advInfoBean=(AdvInfoBean) BeanUtil.getBeanFromRequest(request,AdvInfoBean.class);
        try {
        	advinfoService.updateAdvInfoUserState(advInfoBean);
        	result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		} 
    /**
     * 删除广告信息和对应品牌,地市
     * @param context
     * @return
     */
    public HandlerResult deleteAdvInfo(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        String advNum = RequestUtil.getString(request, "advNum");
       
        try {
        	advinfoService.deleteAdvInfo(advNum);
        	result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		} 
    public HandlerResult verifyAdvInfo(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        Map<String, String> param =new HashMap<String, String>();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
     	String cfgUserId=userInfoBean.getUserName();
     	String city=userInfoBean.getUserAreaCode();
     	
     	String advNum = RequestUtil.getString(request, "advNum");
     	String areaNums = RequestUtil.getString(request, "areaNums");
     	String auditState = RequestUtil.getString(request, "auditState");
     	areaNums=","+areaNums+",";
     	if (areaNums.indexOf(","+city+",")>-1){//--审核该地区广告
     		param.put("advNum", advNum);
     		param.put("auditer", cfgUserId);
     		param.put("auditState", auditState);
     		try {
     			advinfoService.verifyAdvInfo(param);
     			result.setRetCode(IResultCode.SYS_SUCCESS);
            } catch (Exception e) {
            	e.printStackTrace();
                result.setRetCode(IResultCode.SYS_FAILED);
			}
     	}else{
     		result.setResMsg("只审核同地区的广告!");
     		result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
     		 result.setRetCode(IResultCode.SYS_FAILED);
     	}
     	
		return result;
		}
    
    public HandlerResult filesuploadReName(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		 List<AdvInfoBean> advInfoBeanList=new ArrayList<AdvInfoBean>();
		advInfoBeanList= JSONArray.parseArray(request.getParameter("retobj"),AdvInfoBean.class);
		String fileName="";
		for(AdvInfoBean advInfoBean:advInfoBeanList){
			fileName+=advInfoBean.getAdvMenuIcon()+",";
			fileName+=advInfoBean.getAdvImg()+",";
			fileName+=advInfoBean.getAdvUri()+",";
			fileName+=advInfoBean.getAdvImgS()+",";
		}
		if(fileName.length()>0){
			fileName=fileName.substring(0,fileName.length()-1);
		}
		
		String[] fileNames=fileName.split("\\,",-1); 
		 try {
			fileuploadService.filesuploadReName(request, targetURL, fileNames);
			advinfoService.updateAdvInfo(advInfoBeanList,true);
				result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setRetCode(IResultCode.SYS_FAILED);
			}
		
		return result;
		}
}