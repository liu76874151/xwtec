package com.xwtech.uomp.base.action.handler.brand;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.service.brand.IBrandService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;

/**
 * 品牌查询管理
 * @author xwtec
 */
@Controller("H_brand")
public class BrandHandler implements IHandler {

	@Autowired
	IBrandService brandService;

	/**
	 * 品牌查询
	 * @param context
	 * brandName,brandNum,jb
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryBrandList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);

		try {
			Page page = brandService.queryBrandList(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 品牌查询
	 * brandName,brandNum,jb(可传入以","分开的jb)
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryBrandListEx(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);

		try {
		    String jb = (String) param.get("jb");
		    if (StringUtils.isNotBlank(jb)) {
			param.put("jb", jb.split(","));
		    }
		    List<BrandBean> brandBeans = brandService.queryBrandListEx(param);
		    result.setRetObj(brandBeans);
		    result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
		    result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

	/**
	 * 根据品牌代码查询单一品牌
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult queryOneBrand(HandlerRequestContext context) {

		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String brandNum = RequestUtil.getString(request, "pkid");
		try {
			BrandBean brandBean = brandService.queryOneBrand(brandNum);
			result.setRetObj(brandBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	/**
	 * 用于验证品牌是否存在
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryBrandValidate(HandlerRequestContext context) {

		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		try {
			BrandBean brandBean = brandService.queryBrandValidate(param);
			result.setRetObj(brandBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

	/**
	 * 添加品牌
	 * 
	 * @param context
	 * @return
	 */
	public HandlerResult saveBrand(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		BrandBean brandBean = (BrandBean) BeanUtil.getBeanFromRequest(request,
				BrandBean.class);

		try {
			brandService.saveBrand(brandBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}

		return result;
	}

	/**
	 * 删除品牌
	 * @param context
	 * @return
	 */
	public HandlerResult deleteBrandbyBrandNum(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();

		String brandNum = RequestUtil.getString(request, "pkid");
		try {
			brandService.deleteBrandbyBrandNum(brandNum);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
		}
		return result;
	}
	
	/**
	 * 修改品牌信息
	 * @param context
	 * @return
	 */
	public HandlerResult updateBrand(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		BrandBean brandBean = (BrandBean) BeanUtil.getBeanFromRequest(request,
				BrandBean.class);

		try {
			
			brandService.updateBrand(brandBean);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		
		return result;
	}
}
