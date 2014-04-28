package com.xwtech.uomp.base.action.handler.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.constants.SystemConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.business.BusiTagDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessAreaDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessBaseInfoBean;
import com.xwtech.uomp.base.pojo.business.BusinessBrandDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessEffectWayBean;
import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.business.IBusinessBaseInfoService;
import com.xwtech.uomp.base.service.business.IBusinessInfoService;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;

@Controller("H_businessInfoHandler")
public class BusinessInfoHandler implements IHandler {

    @Autowired
    IBusinessInfoService businessInfoService;
    @Autowired
    IBusinessBaseInfoService businessBaseInfoService;
    @Autowired
    private ICacheServiceFactory cacheServiceFactory;
    
    public HandlerResult queryBusiInfoList(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    Page page = businessInfoService.queryBusinessInfoList(param);
	    result.setRetObj(page);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 业务编码查询业务信息
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult queryBusiBaseInfoBynum(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String busiNum = RequestUtil.getString(request, "pkid");

	try {
	    BusinessBaseInfoBean businessBaseInfoBean = businessBaseInfoService.queryBusiInfoBynum(busiNum);
	    result.setRetObj(businessBaseInfoBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 业务编码查询业务个性信息
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult queryBusiInfoBynum(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    BusinessInfoBean businessInfoBean = businessInfoService.queryBusiInfoBynum(param);
	    result.setRetObj(businessInfoBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryBusiBaseInfo
     * @Description: 查询业务信息，用于业务树的展示
     */
    public HandlerResult queryBusiBaseInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	List<TreeNode> treeNodes = null;
        TreeNode root = null;

	try {
	    treeNodes = businessBaseInfoService.queryBusiBaseInfoTree();
	    root = DhtmlTreeUtil.mergeTreeNodeList(treeNodes);
	    result.setRetObj(root);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
	}
	return result;
    }
    
    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryBusiBaseInfoExt
     * @Description: 查询业务信息，用于业务树的展示(扩展方法)
     */
    public HandlerResult queryBusiBaseInfoExt(HandlerRequestContext context) {

	HandlerResult result = HandlerResult.newInstance();
        Map<String, List<TreeNode>> treeNodeMap = null;
        String sysNum = WEBApp.SYS_NUM;
        String key = "KEY_QUERY_BUSINESS_INFO_" + sysNum + "_TREE";
        ICacheService cacheService = cacheServiceFactory.getCacheService("ecm_code");
        TreeNode root = null;
        
        Object obj = cacheService.get(key);
        if (obj != null) {
            root = (TreeNode) obj;
        } else {
            List<TreeNode> topBusiNod = businessBaseInfoService.queryTopBusiTree();
            treeNodeMap = businessBaseInfoService.queryBusiBaseInfoTreeExt();
            
            if (treeNodeMap == null) {
                result.setRetCode(IResultCode.SYS_FAILED);
            } else {
                result.setRetCode(IResultCode.SYS_SUCCESS);
            }
            root = DhtmlTreeUtil.mergeTreeNodeList(topBusiNod, treeNodeMap);
            cacheService.add(key, root, SystemConstants.MEMCACHED_DEFAULT_EXPIRE);
	}
        result.setRetObj(root);
        return result;
    }
    
    /**
     * 业务新增
     * @param context
     * @return  
     * @author zhangel
     */
    @SuppressWarnings("unchecked")
    public HandlerResult saveBusinessInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	
	String sysNum = WEBApp.SYS_NUM;
        String key = "KEY_QUERY_BUSINESS_INFO_" + sysNum + "_TREE";
        ICacheService cacheService = null;
        
	BusinessBaseInfoBean businessBaseInfoBean = (BusinessBaseInfoBean) BeanUtil.getBeanFromRequest(request, BusinessBaseInfoBean.class);
	String effectList = RequestUtil.getString(request, "effectList");
	String neffectList = RequestUtil.getString(request, "neffectList");
	String areaList = RequestUtil.getString(request, "areaList");
	String brandList = RequestUtil.getString(request, "brandList");
	String busiExtraList = RequestUtil.getString(request, "busiExtraList");
	String busiInfoList = RequestUtil.getString(request, "busiInfoList");
	String busiTagJson = RequestUtil.getString(request, "busiTagJson");
	
	List<BusinessEffectWayBean> businessEffectWayBeans = JsonHelper.json2List(effectList, BusinessEffectWayBean.class);
	List<BusinessEffectWayBean> nbusinessEffectWayBeans = JsonHelper.json2List(neffectList, BusinessEffectWayBean.class);
	List<BusinessAreaDzBean> businessAreaDzBeans = JsonHelper.json2List(areaList, BusinessAreaDzBean.class);
	List<BusinessBrandDzBean> businessBrandDzBeans = JsonHelper.json2List(brandList, BusinessBrandDzBean.class);
	List<BusinessExattrDzBean> businessExattrDzBeans = JsonHelper.json2List(busiExtraList, BusinessExattrDzBean.class);
	List<BusiTagDzBean> busiTagDzBeans = JsonHelper.json2List(busiTagJson, BusiTagDzBean.class);
	List<BusinessInfoBean> businessInfoBeans = JsonHelper.json2List(busiInfoList, BusinessInfoBean.class);
	
	
        try {
            businessBaseInfoBean.setBusinessEffectWayBeans(businessEffectWayBeans);
            businessBaseInfoBean.setBusinessNEffectWayBeans(nbusinessEffectWayBeans);
            businessBaseInfoBean.setBusinessAreaDzBeans(businessAreaDzBeans);
            businessBaseInfoBean.setBusinessBrandDzBeans(businessBrandDzBeans);
            businessBaseInfoBean.setBusinessExattrDzBeans(businessExattrDzBeans);
            businessBaseInfoBean.setBusiTagDzBeans(busiTagDzBeans);
            businessBaseInfoBean.setBusinessInfoBeans(businessInfoBeans);
            
            businessBaseInfoService.saveBusinessInfo(businessBaseInfoBean);
            
            cacheService = cacheServiceFactory.getCacheService("ecm_code");
            cacheService.delete(key);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 业务信息修改
     * @param context
     * @return  
     * @author zhangel
     */
    @SuppressWarnings("unchecked")
    public HandlerResult updateBusinessInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
        
	BusinessBaseInfoBean businessBaseInfoBean = (BusinessBaseInfoBean) BeanUtil.getBeanFromRequest(request, BusinessBaseInfoBean.class);
	String effectList = RequestUtil.getString(request, "effectList");
	String neffectList = RequestUtil.getString(request, "neffectList");
	String areaList = RequestUtil.getString(request, "areaList");
	String brandList = RequestUtil.getString(request, "brandList");
	String busiExtraList = RequestUtil.getString(request, "busiExtraList");
	String busiInfoList = RequestUtil.getString(request, "busiInfoList");
	String busiTagJson = RequestUtil.getString(request, "busiTagJson");
	
	List<BusinessEffectWayBean> businessEffectWayBeans = JsonHelper.json2List(effectList, BusinessEffectWayBean.class);
	List<BusinessEffectWayBean> nbusinessEffectWayBeans = JsonHelper.json2List(neffectList, BusinessEffectWayBean.class);
	List<BusinessAreaDzBean> businessAreaDzBeans = JsonHelper.json2List(areaList, BusinessAreaDzBean.class);
	List<BusinessBrandDzBean> businessBrandDzBeans = JsonHelper.json2List(brandList, BusinessBrandDzBean.class);
	List<BusinessExattrDzBean> businessExattrDzBeans = JsonHelper.json2List(busiExtraList, BusinessExattrDzBean.class);
	List<BusiTagDzBean> busiTagDzBeans = JsonHelper.json2List(busiTagJson, BusiTagDzBean.class);
	List<BusinessInfoBean> businessInfoBeans = JsonHelper.json2List(busiInfoList, BusinessInfoBean.class);
	
        try {
            businessBaseInfoBean.setBusinessEffectWayBeans(businessEffectWayBeans);
    	    businessBaseInfoBean.setBusinessNEffectWayBeans(nbusinessEffectWayBeans);
     	    businessBaseInfoBean.setBusinessAreaDzBeans(businessAreaDzBeans);
    	    businessBaseInfoBean.setBusinessBrandDzBeans(businessBrandDzBeans);
    	    businessBaseInfoBean.setBusinessExattrDzBeans(businessExattrDzBeans);
    	    businessBaseInfoBean.setBusiTagDzBeans(busiTagDzBeans);
    	    businessBaseInfoBean.setBusinessInfoBeans(businessInfoBeans);
    	    businessBaseInfoBean.setState(2);//待审核
    	    
            businessBaseInfoService.updateBusinessInfo(businessBaseInfoBean);
		
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 删除业务
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult deleteBusinessBaseInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String busiNum = RequestUtil.getString(request, "funcId");
	
	try {
            businessBaseInfoService.deleteBusinessBaseInfo(busiNum);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
	
    }
    
    /**
     * 业务审核用查询
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult selectBusiInfoListForVerify(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	try {
	    String userarea = userInfoBean.getUserAreaCode();
	    Integer isProUser = 0;
	    if ("0".equals(userarea)) {
		isProUser = 1;
		param.put("isProUser", isProUser.toString());
	    }
	    
	    if (StringUtils.isEmpty(userarea)) {
		result.setRetCode(IResultCode.SYS_FAILED);
		return result;
	    }
	    
	    String state = (String) param.get("state");
	    if (StringUtils.isEmpty(state)) {
		state = "0,2,3,4";
	    }
	    String[] arr = state.split(",");
	    param.put("state", arr);
	    
	    String brandNum = (String) param.get("brandNum");
	    if (StringUtils.isEmpty(brandNum)) {
		brandNum = null;
		param.put("brandNum", brandNum);
	    }else{
		String[] barr = brandNum.split(",");
		param.put("brandNum", barr);
	    }
	    String channelNum = (String) param.get("channelNum");
	    if (StringUtils.isEmpty(channelNum)) {
		channelNum = null;
		param.put("channelNum", channelNum);
	    }else{
		String[] barr = channelNum.split(",");
		param.put("channelNum", barr);
	    }
	    
	    String city = (String) param.get("city");
	    if (StringUtils.isEmpty(city)) {
		if ("0".equals(userarea)) {//省级用户
		    param.put("city", null);
		}else {
		    param.put("city", userarea);
		}
	    }else {
		param.put("city", city);
	    }
	    
	    Page page = businessInfoService.selectBusiInfoListForVerify(param,userInfoBean);
	    result.setRetObj(page);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 业务审核
     * @param context
     * @return  
     * @author zhangel
     * 业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
     */
    public HandlerResult verifyBusinessInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	

	try {
	    param.put("userInfoBean", userInfoBean);
	    businessInfoService.verifyBusinessInfo(param);
	    
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 业务测试用查询
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult selectBusiInfoListForTest(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	try {
	    String userarea = userInfoBean.getUserAreaCode();
	    
	    if (StringUtils.isEmpty(userarea)) {
		result.setRetCode(IResultCode.SYS_FAILED);
		return result;
	    }
	    
	    String state = (String) param.get("state");
	    if (StringUtils.isEmpty(state)) {
		state = "0,4,5";
	    }
	    String[] arr = state.split(",");
	    param.put("state", arr);
	    
	    String brandNum = (String) param.get("brandNum");
	    if (StringUtils.isEmpty(brandNum)) {
		brandNum = null;
		param.put("brandNum", brandNum);
	    }else{
		String[] barr = brandNum.split(",");
		param.put("brandNum", barr);
	    }
	    String channelNum = (String) param.get("channelNum");
	    if (StringUtils.isEmpty(channelNum)) {
		channelNum = null;
		param.put("channelNum", channelNum);
	    }else{
		String[] barr = channelNum.split(",");
		param.put("channelNum", barr);
	    }
	    
	    String city = (String) param.get("city");
	    if (StringUtils.isEmpty(city)) {
		if ("0".equals(userarea)) {//省级用户
		    param.put("city", null);
		}else {
		    param.put("city", userarea);
		}
	    }else {
		param.put("city", city);
	    }
	    
	    Page page = businessInfoService.selectBusiInfoListForTest(param,userInfoBean);
	    result.setRetObj(page);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 业务测试
     * @param context
     * @return  
     * @author zhangel
     * 业务状态（0-正常；1-已删除；2-未审核; 3-审核未通过;4,待测试,5,测试不通过）
     */
    public HandlerResult testBusinessInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
	

	try {
	    if (userInfoBean==null) {
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		return result;
	    }
	    
	    param.put("userInfoBean", userInfoBean);
	    businessInfoService.testBusinessInfo(param);
	    
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 业务排序用查询
     * @param context
     * @return  
     * @author unique
     */
    public HandlerResult queryForSort(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);

	try {
	    String state  = RequestUtil.getString(request, "state");
	    if (StringUtils.isNotBlank(state)) {
		param.put("state", state.split(","));
	    }
	    
	    String jbNum = businessInfoService.queryJbNum(param);
	    if (StringUtils.isBlank(jbNum)) {
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setResMsg("未查询到相关业务数据");
		return result;
	    }
	    int jb =  jbNum.length() / 3;
	    if (jb==1) {
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setResMsg("一级业务不参加排序");
		return result;
	    }
	    param.put("jb", jb);
	    param.put("jbNum", jbNum.substring(0,(jb-1)*3)==""?null:jbNum.substring(0,(jb-1)*3));
	    List<BusinessInfoBean> list = businessInfoService.queryForSort(param);
	    result.setRetObj(list);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 业务排序
     * @param context
     * @return  
     * @author unique
     */
    public HandlerResult sortBusinessInfo(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String sortJson = RequestUtil.getString(request, "sortJson");
	try {
	    List<BusinessInfoBean> list = JsonHelper.json2List(sortJson, BusinessInfoBean.class);
	    businessInfoService.sortBusinessInfo(list);
	    
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
}
