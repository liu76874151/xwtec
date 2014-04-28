package com.xwtech.uomp.base.action.handler.adv;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.adv.AdvPositionBean;
import com.xwtech.uomp.base.service.adv.IAdvPositionService;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;

/**
 *@ClassName:AdvPositionHander.java
 *@Description：
 *@author: Mars
 *@date： date：2013-11-14 time：下午01:48:41
 *@version 1.0
 */
@Controller("H_advPosition")
public class AdvPositionHander implements IHandler {
	@Autowired
	IAdvPositionService advPositionService;
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	IFileuploadService fileuploadService;
	@Value("${fileUpload.URL}")
	private String targetURL;
	/**
	 * 保存广告位
	 * @param context
	 * @return
	 */
	public HandlerResult saveadvPosition(HandlerRequestContext context) {
		//String pkid=sequenceService.getSequence("T_ADV_POSITION_PKID_SEQ");
		HandlerResult result = HandlerResult.newInstance();
	    HttpServletRequest request = context.getRequest();
		AdvPositionBean advPositionBean = (AdvPositionBean) BeanUtil.getBeanFromRequest(request,
				AdvPositionBean.class);
		//advPositionBean.setPositionNum(pkid);
		try {
			advPositionService.saveAdvPosition(advPositionBean);
			long nanoTime = System.nanoTime(); 
			Map<String, String> fileNameMap = new HashMap<String, String>();
			fileNameMap.put("fileName", Long.toString(nanoTime));
			fileNameMap.put("positionNum", advPositionBean.getPositionNum());
			result.setRetObj(fileNameMap);
			result.setRetCode(IResultCode.SYS_SUCCESS);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
			result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
		}

		return result;
	}

	/**
	 * 查询广告位
	 * @param context
	 * @return
	 */
	public HandlerResult queryAdvPositionList(HandlerRequestContext context){
		HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);
     
        try{
        	Page page=advPositionService.queryAdvPositionList(param);
        	result.setRetObj(page);
        	result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
        	e.printStackTrace();
        	result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
		}
	/**
	 * 删除广告位
	 * @param context
	 * @return
	 */
	public HandlerResult deleteAdvPosition(HandlerRequestContext context){
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();
        String id = RequestUtil.getString(request, "pkid");
        try {
        	advPositionService.deleteAdvPosition(id);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
	}
	/**
	 * 查询一条广告位byPkid
	 * @param context
	 * @return
	 */
	public HandlerResult findOneAdvPositionBean(HandlerRequestContext context){
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String pkid = RequestUtil.getString(request, "pkid");
        try {
        	AdvPositionBean advPositionBean=advPositionService.findOneAdvPositionBean(pkid);
            result.setRetObj(advPositionBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }
        return result;
        }
	
	public HandlerResult updateByPrimaryKeySelective(HandlerRequestContext context){
		 HandlerResult result = HandlerResult.newInstance();
	     HttpServletRequest request = context.getRequest();
	     Map<String, String> param = BeanUtil.getMapFromRequest(request);
	     try {
	    	 advPositionService.updateByPrimaryKeySelective(param);
	    	 long nanoTime = System.nanoTime(); 
				Map<String, String> fileNameMap = new HashMap<String, String>();
				fileNameMap.put("fileName", Long.toString(nanoTime));
				fileNameMap.put("positionNum", request.getParameter("positionNum"));
				result.setRetObj(fileNameMap);
	    	 result.setRetCode(IResultCode.SYS_SUCCESS);
	         result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
		} catch (Exception e) {
			e.printStackTrace();
			 result.setRetCode(IResultCode.SYS_FAILED);
	            result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
		}
		return result;}
	
	public HandlerResult filesUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		 String fileName = RequestUtil.getString(request, "fileName");
		 String positionNum = RequestUtil.getString(request, "hiddenPositionNum");
		try {
			boolean flag = fileuploadService.fileuploadReName(request,targetURL,fileName);
			if(flag){
				Map<String, String> param = new HashMap<String, String>();
				 param.put("positionImage", fileName);
				 param.put("positionNum", positionNum);
				 advPositionService.updateByPrimaryKeySelective(param);
			}
			result.setRetObj(flag);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;}
}