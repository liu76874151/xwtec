package com.xwtech.uomp.base.dao.automated;

import java.util.Map;

public interface ResultMsgMapper {
    /**
     * 根据子系统编码和返回码查询提示信息
     *
     * @param map
     * @return
     */
    public String queryResultMsgInfo(Map<String, Object> map);

}
