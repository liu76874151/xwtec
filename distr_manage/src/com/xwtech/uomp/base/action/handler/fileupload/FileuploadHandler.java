package com.xwtech.uomp.base.action.handler.fileupload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.service.fileupload.IFileuploadService;

/**
 * 文件上传
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-29 下午06:45:26
 */
@Controller("H_fileuploadHandler")
public class FileuploadHandler implements IHandler {

	@Autowired
	IFileuploadService fileuploadService;
	
	@Value("${fileUpload.URL}")
	private String targetURL;
	
	/**
	 * 单个文件上传
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult fileUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();

		try {
			boolean flag = fileuploadService.fileupload(request,targetURL);
			result.setRetObj(flag);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}
	
	/**
	 * 多个文件上传
	 * @param context
	 * @return  
	 * @author zhangel
	 */
	public HandlerResult filesUpload(HandlerRequestContext context) {
		HandlerResult result = HandlerResult.newInstance();
		HttpServletRequest request = context.getRequest();
		try {
			boolean flag = fileuploadService.filesupload(request,targetURL);
			result.setRetObj(flag);
			result.setRetCode(IResultCode.SYS_SUCCESS);
		} catch (Exception e) {
			result.setRetCode(IResultCode.SYS_FAILED);
		}
		return result;
	}

}
