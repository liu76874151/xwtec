package com.xwtech.uomp.base.action.handler.business;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.business.BusiShortAddressBean;
import com.xwtech.uomp.base.pojo.business.BusiTagBean;
import com.xwtech.uomp.base.pojo.business.BusinessDeductBean;
import com.xwtech.uomp.base.pojo.business.BusinessExattrBean;
import com.xwtech.uomp.base.pojo.business.BusinessExattrDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessSortDzBean;
import com.xwtech.uomp.base.pojo.business.BusinessTypeBean;
import com.xwtech.uomp.base.pojo.business.RelationBusinessInfoBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.business.IBusiShortAddressService;
import com.xwtech.uomp.base.service.business.IBusinessDeductService;
import com.xwtech.uomp.base.service.business.IBusinessExtraService;
import com.xwtech.uomp.base.service.business.IBusinessTagService;
import com.xwtech.uomp.base.service.business.IBusinessTypeService;
import com.xwtech.uomp.base.service.business.IRelationBusinessInfoService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-5 下午03:15:57
 */
@Controller("H_businessHandler")
public class BusinessHandler implements IHandler {
    protected static final Logger logger = Logger.getLogger(BusinessHandler.class);

    @Autowired
    IBusinessExtraService businessExtraService;
    @Autowired
    IBusinessDeductService businessDeductService;
    @Autowired
    IBusinessTypeService businessTypeService;
    @Autowired
    IRelationBusinessInfoService relationBusinessInfoService;
    @Autowired
    IBusiShortAddressService busiShortAddressService;
    @Autowired
    ISequenceService sequenceService;
    @Autowired
    IBusinessTagService businessTagService;
    
    public HandlerResult queryBusinessExtra(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    Page page = businessExtraService.queryByName(param);
	    result.setRetObj(page);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    public HandlerResult addBusiExtra(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessExattrBean businessExattrBean = (BusinessExattrBean) BeanUtil.getBeanFromRequest(
		request, BusinessExattrBean.class);

	try {
	    businessExtraService.addBusiExtra(businessExattrBean);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setResMsg("添加失败,请检查属性标识是否重复!");
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
	}
	return result;
    }

    public HandlerResult queryBusinessExtraByKey(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String attrKey = request.getParameter("attrKey");

	try {
	    BusinessExattrBean businessExattrBean = businessExtraService
		    .queryBusinessExtraByKey(attrKey);
	    result.setRetObj(businessExattrBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 修改扩展属性
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult updateBusiExtra(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessExattrBean businessExattrBean = (BusinessExattrBean) BeanUtil.getBeanFromRequest(
		request, BusinessExattrBean.class);

	try {
	    businessExtraService.updateBusinessExattrBean(businessExattrBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }

    /**
     * 删除扩展属性
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult deleteBusiExtra(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String attrKey = RequestUtil.getString(request, "attrKey");

	try {
	    List<BusinessExattrDzBean> businessExattrDzBeans = businessExtraService.deleteBusiExtraByAttrKey(attrKey);
	    result.setRetObj(businessExattrDzBeans);
	    if (businessExattrDzBeans==null || businessExattrDzBeans.size()==0) {
		result.setRetCode(IResultCode.SYS_SUCCESS);
		result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	    }else {
		result.setResMsg("有业务关联到将要删除的属性,需要先取消关联");
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	    }
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
    }

    /**
     * 扣费方式查询
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult queryBusinessDeduct(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    Page page = businessDeductService.queryBusinessDeductList(param);
	    result.setRetObj(page);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 扣费方式添加
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult addBusiDeduct(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessDeductBean businessDeductBean = (BusinessDeductBean) BeanUtil.getBeanFromRequest(
		request, BusinessDeductBean.class);
	try {
	    businessDeductService.addBusiDeduct(businessDeductBean);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setResMsg("添加失败,扣费方式编码是否重复!");
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
	}
	return result;
    }

    /**
     * 查询单个扣费方式
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult queryBusiDeductBykey(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String deductNum = RequestUtil.getString(request, "deductNum");

	try {
	    BusinessDeductBean businessDeductBean = businessDeductService
		    .queryBusiDeductBykey(deductNum);
	    result.setRetObj(businessDeductBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 修改扣费方式
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult updateBusiDeduct(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessDeductBean businessDeductBean = (BusinessDeductBean) BeanUtil.getBeanFromRequest(
		request, BusinessDeductBean.class);

	try {
	    businessDeductService.updateByPrimaryKey(businessDeductBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }

    /**
     * 删除扣费方式
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult deleteBusiDeduct(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String deductNum = RequestUtil.getString(request, "deductNum");

	try {
	    businessDeductService.deleteByPrimaryKey(deductNum);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
    }

    /**
     * 查询业务分类	
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult queryParentBusiType(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	try {
	    String channelNum = (String) param.get("channelNum");
	    if (StringUtils.isEmpty(channelNum)) {
		channelNum = null;
		param.put("channelNum", channelNum);
	    }else{
		String[] barr = channelNum.split(",");
		param.put("channelNum", barr);
	    }
	    
	    List<BusinessTypeBean> businessTypeBeans = businessTypeService
		    .queryParentBusiType(param);
	    result.setRetObj(businessTypeBeans);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 查询单一业务类型
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult queryBusiTypeBean(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessTypeBean bean = (BusinessTypeBean) BeanUtil.getBeanFromRequest(request, BusinessTypeBean.class);
	try {
	    BusinessTypeBean businessTypeBean = businessTypeService.queryBusiTypeBykey(bean);
	    result.setRetObj(businessTypeBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }

    /**
     * 添加业务类型
     * 
     * @param context
     * @return
     * @author zhangel
     */
    public HandlerResult addBusiType(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessTypeBean businessTypeBean = (BusinessTypeBean) BeanUtil.getBeanFromRequest(request,
		BusinessTypeBean.class);

	try {
	    BusinessTypeBean bean = businessTypeService.insertSelective(businessTypeBean);
	    result.setRetObj(bean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
	}
	return result;
    }

    public HandlerResult queryBusiTypeCombo(HandlerRequestContext context) {
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	HttpServletResponse response = context.getResponse();
	response.setContentType("text/plain;charset=UTF-8");
	PrintWriter writer = null;
	try {
	    String channelNum = (String) param.get("channelNum");
	    if (StringUtils.isEmpty(channelNum)) {
		channelNum = null;
		param.put("channelNum", channelNum);
	    }else{
		String[] barr = channelNum.split(",");
		param.put("channelNum", barr);
	    }
	    
	    String mask = new String(request.getParameter("mask").getBytes("ISO-8859-1"), "utf-8");
	    param.put("busiTypeName", mask);
	    List<BusinessTypeBean> businessTypeBeans = businessTypeService
		    .queryParentBusiType(param);

	    StringBuffer stringBuffer = new StringBuffer();
	    stringBuffer.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
	    stringBuffer.append("<complete>");
	    for (BusinessTypeBean businessTypeBean : businessTypeBeans) {
		String busiTypeNum = businessTypeBean.getBusiTypeNum();
		String busiTypeName = businessTypeBean.getBusiTypeName();
		stringBuffer.append("<option value=\"" + busiTypeNum + "\">" + busiTypeName
			+ "</option>");
	    }
	    stringBuffer.append("</complete>");
	    writer = response.getWriter();
	    writer.print(stringBuffer.toString());
	    writer.flush();
	} catch (Exception e) {
	    logger.error(e, e);
	    throw new RuntimeException("queryBusiTypeCombo!", e);
	}finally{
	    IOUtils.closeQuietly(writer);
	}
	return null;
    }

    public HandlerResult queryBusiType(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	String jb = request.getParameter("jb");
	try {
	    if (StringUtils.isBlank(jb)) {
		param.put("jb", null);
	    }else {
		param.put("jb", jb.split(","));
	    }
	    List<BusinessTypeBean> list = businessTypeService.queryBusiType(param);
	    result.setRetObj(list);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 修改业务分类
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult modifyBusiType(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusinessTypeBean businessTypeBean = (BusinessTypeBean) BeanUtil.getBeanFromRequest(request,
		BusinessTypeBean.class);

	try {
	    businessTypeService.updateByBusiTypeNum(businessTypeBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 删除业务分类
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult deleteBusiType(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String jbNums = RequestUtil.getString(request, "jbNums");
	
	String[] jbNumArray = StringUtils.split(jbNums,',');
	
	try {
	    List<BusinessSortDzBean> businessSortDzBeans = businessTypeService.deleteBusiType(jbNumArray);
	    result.setRetObj(businessSortDzBeans);
	    if (businessSortDzBeans==null || businessSortDzBeans.size()==0) {
		result.setRetCode(IResultCode.SYS_SUCCESS);
		result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	    }else {
		result.setResMsg("有业务关联到将要删除的分类,需要先取消关联");
		result.setRetCode(IResultCode.SYS_FAILED);
		result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	    }
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
    }
    
    public HandlerResult queryRelationInfos(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);
	try {
	    RelationBusinessInfoBean bean = relationBusinessInfoService.queryRelationInfos(param);
	    result.setRetObj(bean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 关联业务信息查询
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult queryRelaBusiDetail(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
		Page page = relationBusinessInfoService.queryRelaBusiDetail(param);
		result.setRetObj(page);
		result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    e.printStackTrace();
		result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 查询段地址
     * @param context
     * @return
     */
    public HandlerResult queryBusiShortAdd(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	Map<String, String> param = BeanUtil.getMapFromRequest(request);
    	try {
			Page page=busiShortAddressService.queryBusiShortAdd(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
			 result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
		}
    /**
     * 查询短渠道
     * @param context
     * @return
     */
    public HandlerResult queryBusiShortChannel(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	Map<String, String> param = BeanUtil.getMapFromRequest(request);
    	try {
			Page page=busiShortAddressService.queryBusiShortChannel(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
			 result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
		}
		return result;
		}
    /**
     * 保存一条段地址
     * @param context
     * @return
     */
    public HandlerResult saveBusiShortAdd(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	String id=sequenceService.getSequence("BUSI_SHORT_ADDRESS_PKID_SEQ");
    	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
     	String cfgUser=userInfoBean.getUserName();
    	BusiShortAddressBean busiShortAddBean=(BusiShortAddressBean)BeanUtil.getBeanFromRequest(request, BusiShortAddressBean.class);
    	String startTimeStr=RequestUtil.getString(request, "startTimeStr");
    	String endTimeStr=RequestUtil.getString(request, "endTimeStr");
    	busiShortAddBean.setShortId(id);
    	busiShortAddBean.setCreater(cfgUser);
    	Date startTime=new Date();
    	Date endTime=new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		try {
    			if(StringUtils.isNotBlank(startTimeStr)){
    				 startTime=(Date)sdf.parse(startTimeStr);
    				 busiShortAddBean.setStartTime(startTime);
    			}
    			if(StringUtils.isNotBlank(endTimeStr)){
    				endTime=(Date)sdf.parse(endTimeStr);
//    				 Calendar   calendar   =   new   GregorianCalendar(); 
//    				 calendar.setTime(endTime); 
//    			     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
//    			     endTime=calendar.getTime();
    				busiShortAddBean.setEndTime(endTime);
    			}
				busiShortAddressService.saveBusiShortAdd(busiShortAddBean);
				result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				 result.setRetCode(IResultCode.SYS_FAILED);
			
    	}
    	
		return result; 
		}
    /**
     * 查询一条段地址
     * @param context
     * @return
     */
    public HandlerResult findOnebusiShortAdd(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	String shortId=RequestUtil.getString(request, "shortId");
    	try {
    		BusiShortAddressBean busiShortAddBean=busiShortAddressService.findOnebusiShortAdd(shortId);
    		result.setRetObj(busiShortAddBean);
    		result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
		
		}
		return result;
		}  
    /**
     * 更新段地址
     * @param context
     * @return
     */
    public HandlerResult updateBusiShortAdd(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
	UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
     	String cfgUser=userInfoBean.getUserName();
    	BusiShortAddressBean busiShortAddBean=(BusiShortAddressBean)BeanUtil.getBeanFromRequest(request, BusiShortAddressBean.class);
    	String startTimeStr=RequestUtil.getString(request, "startTimeStr");
    	String endTimeStr=RequestUtil.getString(request, "endTimeStr");
    	busiShortAddBean.setUpdater(cfgUser);
    	Date startTime=new Date();
    	Date endTime=new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {

			if(StringUtils.isNotBlank(startTimeStr)){
				 startTime=(Date)sdf.parse(startTimeStr);
				 busiShortAddBean.setStartTime(startTime);
			}
			if(StringUtils.isNotBlank(endTimeStr)){
				endTime=(Date)sdf.parse(endTimeStr);
				busiShortAddBean.setEndTime(endTime);
			}
			
			busiShortAddressService.updateBusiShortAdd(busiShortAddBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		} 
    /**
     * 更新段地址的状态
     * @param context
     * @return
     */
    public HandlerResult updateBusiShortAddState(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	Map<String, String> param = BeanUtil.getMapFromRequest(request);
    	try {
    		busiShortAddressService.updateBusiShortAddState(param);
    		result.setRetCode(IResultCode.SYS_SUCCESS);
    		
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
			
		}
		return result;}
    /**
     * 删除一条段地址
     * @param context
     * @return
     */
    public HandlerResult deleteBusiShortAdd(HandlerRequestContext context) {
    	HandlerResult result = HandlerResult.newInstance();
    	HttpServletRequest request = context.getRequest();
    	String shortId=RequestUtil.getString(request, "shortId");
    	try {
    		busiShortAddressService.deleteBusiShortAdd(shortId);
result.setRetCode(IResultCode.SYS_SUCCESS);
    		
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
			
		}
		return result;}
    
    /**
     * 业务标签查询
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult queryBusinessTag(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    List<BusiTagBean> list = businessTagService.queryBusiTagList(param);
	    result.setRetObj(list);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * @param context
     * query by id,name,state
     * @return  
     * @author zhangel
     */
    public HandlerResult selectBusiTagBean(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, String> param = BeanUtil.getMapFromRequest(request);

	try {
	    BusiTagBean busiTagBean = businessTagService.selectBusiTagBean(param);
	    result.setRetObj(busiTagBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 业务标签添加
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult addBusiTag(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusiTagBean busiTagBean = (BusiTagBean) BeanUtil.getBeanFromRequest(request, BusiTagBean.class);
	try {
	    businessTagService.insertSelective(busiTagBean);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 删除标签
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult deleteBusinessTag(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
	try {
	    String tagId = (String) param.get("tagId");
	    if (StringUtils.isBlank(tagId)) {
		param.put("tagId", null);
	    }else {
		String[] array = tagId.split(",");
		param.put("tagId", array);
	    }
	    businessTagService.deleteBusinessTag(param);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
    }
    
    /**
     * 根据主键查询标签
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult queryOneBusinessTag(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	String tagId = RequestUtil.getString(request, "tagId");

	try {
	    BusiTagBean busiTagBean = businessTagService.selectByPrimaryKey(Long.parseLong(tagId));
	    result.setRetObj(busiTagBean);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	}
	return result;
    }
    
    /**
     * 修改标签
     * @param context
     * @return  
     * @author zhangel
     */
    public HandlerResult updateBusinessTag(HandlerRequestContext context) {
	HandlerResult result = HandlerResult.newInstance();
	HttpServletRequest request = context.getRequest();
	BusiTagBean busiTagBean = (BusiTagBean) BeanUtil.getBeanFromRequest(request, BusiTagBean.class);
	try {
	    
	    businessTagService.updateByPrimaryKeySelective(busiTagBean);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
	    result.setRetCode(IResultCode.SYS_SUCCESS);
	} catch (Exception e) {
	    result.setRetCode(IResultCode.SYS_FAILED);
	    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
	}
	return result;
    }
}
