package com.xwtech.uomp.base.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-21 上午09:40:49
 */
public final class JsonHelper {   
  
    // 将JSON转换成POJO,其中beanClass为POJO的Class   
    @SuppressWarnings("unchecked")
    public static Object json2Object(String json, Class beanClass) {
	if (!StringUtils.isBlank(json)) {
	    return JSONObject.parseObject(json, beanClass);
	}else{
	    return null;
	}
    }
    
    //将json转换成成List
    @SuppressWarnings("unchecked")
    public static List json2List(String json, Class beanClass) {
	if (!StringUtils.isBlank(json)) {
	    return JSONObject.parseArray(json, beanClass);
	}else{
	    return null;
	}
    }
}