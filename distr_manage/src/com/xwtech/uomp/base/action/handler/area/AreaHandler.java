package com.xwtech.uomp.base.action.handler.area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.area.AreaBean;
import com.xwtech.uomp.base.pojo.area.AreaDABean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.area.IAreaService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
@Controller("H_area")
public class AreaHandler implements IHandler {

    @Autowired
    IAreaService areaService;
    /**
     * 查询城市地区信息(13个地市+江苏省)
     * @param context
     * @return
     */
    public HandlerResult queryCityList(HandlerRequestContext context) {
    	 HandlerResult result = HandlerResult.newInstance();
         try {
             Page page = areaService.queryCityList();
             result.setRetObj(page);
             result.setRetCode(IResultCode.SYS_SUCCESS);
         } catch (Exception e) {
        	 e.printStackTrace();
             result.setRetCode(IResultCode.SYS_FAILED);
         }

         return result;
         }
    /**
     * 查询地市信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryAreaList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
    	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean,request).getUserInfoBean();
    	String city = userInfoBean.getUserAreaCode();
        Map<String, String> param = BeanUtil.getMapFromRequest(request);
        if("0".equals(city)){
        	city=null;
        }
        param.put("areaBossCode", city);
        try {
            Page page = areaService.queryAreaList(param);
            result.setRetObj(page);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        return result;
    }
    
    /**
     * 新增地市
     *
     * @param context
     * @return
     */
    public HandlerResult saveArea(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        AreaBean areaBean = (AreaBean) BeanUtil.getBeanFromRequest(request,AreaBean.class);
        Map map=new HashMap();
		map.put("areaJbNum", areaBean.getAreaJbNum());
		int flag=areaService.isExistAreaJbNum(map);
        try {
        	if(flag==0){
        	areaService.saveArea(areaBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
            }else{
            	 result.setRetCode(IResultCode.SYS_FAILED);
                 result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                 result.setResMsg("城市级别编码已经存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }

        return result;
    }
    
    /**
     * 删除地市
     *
     * @param context
     * @return
     */
    public HandlerResult deleteArea(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String id = RequestUtil.getString(request, "pkid");

        try {
        	areaService.deleteArea(id);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }
    
    /**
     * 根据pkid查找地市信息
     *
     * @param context
     * @return
     */
    public HandlerResult findOneArea(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String pkid = RequestUtil.getString(request, "pkid");
        try {
            AreaBean areaBean = areaService.findOneArea(pkid);
            result.setRetObj(areaBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }
    /**
     * 修改地市
     *
     * @param context
     * @return
     */
    public HandlerResult updateArea(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        AreaBean areaBean = (AreaBean) BeanUtil.getBeanFromRequest(request,AreaBean.class);
        try {
        	areaService.updateArea(areaBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }
    
    public HandlerResult queryAreaDAList(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
    	String city=userInfoBean.getUserAreaCode();
    	if("0".equals(city))city="";
    	try {
			Page page=areaService.queryAreaDAList(city);
            result.setRetObj(page);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
		}
        return result;
    	 
    }
    
    public HandlerResult queryAreaCityList(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
    	String city=userInfoBean.getUserAreaCode();
    	if("0".equals(city))city="";
    	try {
			List<AreaDABean> page=areaService.queryAreaCityList(city);
            result.setRetObj(page);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
		}
        return result;
    	 
    }
    
    /**
     * 查询地市信息
     * @param end 限制查询条数
     * @return  
     * @author unique
     */
    public HandlerResult findAreaList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<AreaBean> list = areaService.findAreaList(param);
            result.setRetObj(list);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        return result;
    }
    
}
