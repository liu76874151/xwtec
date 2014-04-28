package com.xwtech.uomp.base.service;

import java.util.List;

import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;


/**
 * 项目名称：js_obsh_manage
 * 类名称：ICacheCleanService
 * 类描述：   缓存清除类
 * 创建人：zangjianhua
 * 创建时间：2012-5-23 下午07:11:02
 * 修改人：Administrator
 * 修改时间：2012-5-23 下午07:11:02
 * 修改备注：
 */
public interface ICacheCleanService {

    /**
     * @param @param  serviceList
     * @param @param  resultInfo
     * @param @param  keyList
     * @param @return
     * @param @throws Exception    设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: cleanChacheByList
     * @Description: 根据serviceList  来刷新缓存
     */
    public boolean cleanChacheByList(List<CacheServiceManageBean> serviceList, HandlerResult result, List<String> keyList) throws Exception;

}
