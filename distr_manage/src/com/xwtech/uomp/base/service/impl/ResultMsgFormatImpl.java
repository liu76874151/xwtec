package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.WEBApp;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.constants.SystemConstants;
import com.xwtech.uomp.base.dao.automated.ResultMsgMapper;
import com.xwtech.uomp.base.service.IResultMsgFormat;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("resultMsgFormat")
public class ResultMsgFormatImpl implements IResultMsgFormat {
    protected static final Logger logger = Logger.getLogger(ResultMsgFormatImpl.class);

    @Autowired
    ResultMsgMapper resultMsgMapper;

    @Autowired
    private ICacheServiceFactory cacheServiceFactory;

    public void formatResultMsg(HandlerResult result) {
        String resultMsg = "";
        try {
            if (result != null) {
                if (StringUtils.isBlank(result.getSysCode())) {
                    resultMsg = "系统忙，请稍候再试！";
                } else {
                	
                    resultMsg = queryResultMsgInfo(result.getSysCode());
                }
            }
            if(StringUtils.isNotBlank(result.getResMsg())){
            	resultMsg=result.getResMsg();
            }
            if (StringUtils.isBlank(resultMsg)) {
                resultMsg = "系统忙，请稍候再试！";
            }
        } catch (Exception ex) {
            resultMsg = "系统忙，请稍候再试！";
            logger.error(ex, ex);
        }
        result.setResMsg(resultMsg);
    }

    private String queryResultMsgInfo(String systemCode) {
        String resultMsg = "";
        String sysNum = WEBApp.SYS_NUM;
        String key = "KEY_QUERY_RESULTMSG_INFO_" + sysNum + "_" + systemCode;

        ICacheService cacheService = cacheServiceFactory.getCacheService("ecm_code");

        Object obj = cacheService.get(key);
        if (obj != null) {
            resultMsg = (String) obj;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("subSysNum", sysNum);
            map.put("sysCode", systemCode);
            resultMsg = resultMsgMapper.queryResultMsgInfo(map);
            cacheService.add(key, resultMsg, SystemConstants.MEMCACHED_DEFAULT_EXPIRE);
        }
        return resultMsg;
    }
}
