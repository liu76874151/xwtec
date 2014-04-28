package com.xwtech.uomp.base.action.handler.boss;

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
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.pojo.boss.BossBindBizBean;
import com.xwtech.uomp.base.pojo.boss.BossGiftInfoBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketFirstBean;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.service.boss.IBossBindBizService;
import com.xwtech.uomp.base.service.boss.IBossGiftInfoService;
import com.xwtech.uomp.base.service.boss.IBossMarketFirstService;
import com.xwtech.uomp.base.service.boss.IBossMarketSecondService;
import com.xwtech.uomp.base.service.tools.IToolsService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.JsonHelper;
import com.xwtech.uomp.base.util.RequestUtil;
import com.xwtech.uomp.base.util.SSOUtil;
import com.xwtech.uomp.base.util.StringUtil;

/**
 * 营销案相关BOSS查询
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-30 上午10:31:59
 */
@Controller("H_bossMarket")
public class BossMarketHandler implements IHandler {

	@Autowired
	IBossMarketSecondService bossMarketSecondService ;
	@Autowired
	IBossMarketFirstService bossMarketFirstService;
	@Autowired
	IBossBindBizService bossBindBizService;
	@Autowired
	IBossGiftInfoService bossGiftInfoService;
	@Autowired 
	IToolsService  toolsService;
	/**
	 * 查询boss 二级营销案
	 * @param context
	 * @return
	 */
	public HandlerResult queryBossMarketSecondList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			List<BossMarketSecondBean> list = bossMarketSecondService.queryBossMarketSecondList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询boss一级营销案
	 * @param context
	 * @return
	 */
	public HandlerResult queryBossMarketFirstList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		String city=RequestUtil.getString(request, "city");
		String[] cityids = {};
		if(StringUtils.isBlank(city)){
		String userarea = userInfoBean.getUserAreaCode();
			if(!StringUtil.isNull(userarea)){
				cityids = userarea.split(",");
				for(int i=0;i<cityids.length;i++){
					if(cityids[i]!=null){
						 cityids[i]=(cityids[i].length()>2)?cityids[i].substring(0,2):cityids[i];
					}
					cityids[i]=toolsService.reverseCityCode(cityids[i]);
				}
				}else{
					result.setRetCode(IResultCode.SYS_FAILED);
					return result;
				}
		}else{
			cityids= new String[] {toolsService.reverseCityCode(city)};
			
			}
		
			param.put("cityId", cityids);
		

		try {
			List<BossMarketFirstBean> list = bossMarketFirstService.queryBossMarketFirstList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	/**
	 * 查询boss 绑定业务
	 * @param context
	 * @return
	 */
	public HandlerResult queryBossBindBizList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String cityId=RequestUtil.getString(request, "city");
		if(StringUtils.isNotBlank(cityId)){
			cityId=toolsService.reverseCityCode(cityId);
		}
		if(cityId!=null&&"0".equals(cityId)){
			cityId=null;	
		}
		param.put("cityId", cityId);
		try {
			List<BossBindBizBean> list = bossBindBizService.queryBossBindBizList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 查询boss 禮品
	 * @param context
	 * @return toolsService.reverseCityCode(cityids[i]);
	 */
	public HandlerResult queryBossGiftInfoList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String userarea = userInfoBean.getUserAreaCode();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		userarea=toolsService.reverseCityCode(userarea);
		param.put("cityId", userarea);
		
		try {
			List<BossGiftInfoBean> list = bossGiftInfoService.queryBossGiftInfoList(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	
	/**
	 * 查询BOSS二级营销案,根据SecondPkid筛选
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult queryBossBindBizBySecondPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		
		try {
			List<BossBindBizBean> list = bossBindBizService.queryBossBindBizBySecondPkid(param);
			result.setRetObj(list);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 修改二级营销案时礼品树展现
	 * 创建日期：2014-4-23下午4:36:38
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:HandlerResult
	 */
	public HandlerResult queryGiftInfoByPkid(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		String userarea = userInfoBean.getUserAreaCode();
		Map<String, String> param = BeanUtil.getMapFromRequest(request);
		userarea=toolsService.reverseCityCode(userarea);
		param.put("cityId", userarea);
		try {
			List<BossGiftInfoBean> giftInfoBeans = bossGiftInfoService.selectBySecondPkid(param); 
			result.setRetObj(giftInfoBeans);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询boss编码
	 * @param context
	 * @return
	 */
	public HandlerResult queryBossCodeList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		Map<String, String> paramMap = BeanUtil.getMapFromRequest(request);
		try {
			Page page=new Page();
			page=bossMarketSecondService.queryBossCodeList(paramMap);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	/**
	 * 新增bosscode
	 * @param context
	 * @return
	 */
	public HandlerResult addBossCodeList(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String MarketSecondStr=request.getParameter("marketSecond");
		BossMarketFirstBean bean=(BossMarketFirstBean) BeanUtil.getBeanFromRequest(request, BossMarketFirstBean.class);
		if(StringUtils.isNotBlank(MarketSecondStr)){
		List<BossMarketSecondBean> bossMarketSecondBeanList=(List<BossMarketSecondBean>)JsonHelper.json2List(MarketSecondStr,BossMarketSecondBean.class);
		bean.setBossMarketSecondBean(bossMarketSecondBeanList);
			try {
				bossMarketFirstService.insertBosscode(bean);
				result.setRetCode(IResultCode.SYS_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				result.setRetCode(IResultCode.SYS_FAILED);
			}
		}
		return result;
		}
	public HandlerResult deleteMarketFirstCascade(HandlerRequestContext context) {

		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		String MarketFirstCode=request.getParameter("pkid");
		try {
			bossMarketFirstService.deleteMarketFirstCascade(MarketFirstCode);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	public HandlerResult queryBossMarketFirstPage(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");
		UserInfoBean userInfoBean = SSOUtil.checkSSOState(loginReqBean, request).getUserInfoBean();
		Map<String, Object> param = BeanUtil.getMapFromRequestAdn(request);
		String city=RequestUtil.getString(request, "city");
		if(StringUtils.isNotBlank(city)){
		String [] cityids= new String[] {RequestUtil.getString(request, city)};
		param.put("cityId",cityids);}else{
			param.put("cityId",null);
		}
		try {
			Page page=bossMarketFirstService.queryBossMarketFirstPage(param);
			result.setRetObj(page);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		
		return result;
		}
	
}
