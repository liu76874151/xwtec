package com.xwtech.uomp.base.action.handler.schedule;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.service.schedule.ISynchMarketService;
import com.xwtech.uomp.base.util.RequestUtil;

/**
 * 营销案数据同步手动触发入口
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-24 下午01:49:52
 */

@Controller("H_scheduleHandler")
public class ScheduleHandler implements IHandler{

    @Autowired
    ISynchMarketService synchMarketService;
    
    /**
     * 江苏省营销案数据
     * @param context
     * @return  
     * @author unique
     */
    public HandlerResult synchBossMarket(HandlerRequestContext context) {
	synchMarketService.synchMarketPlanInfo();
	return null;
    }
    
    /**
     * 某一地市营销案数据
     * @param context
     * @return  
     * @author unique
     */
    public HandlerResult synchMarketPlanInfoByCity(HandlerRequestContext context){
	
	HttpServletRequest request = context.getRequest();
	String cityId = RequestUtil.getString(request, "cityId");
	if (StringUtils.isNotBlank(cityId)) {
	    synchMarketService.synchMarketPlanInfoByCity(cityId);
	}
	return null;
    }
}
