package com.xwtech.uomp.base.dao;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-9-16
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
@Component("SQLHelper")
public class SQLHelper {
    public String page(String sql, int... args) {
        int pageNum = args[0];
        int pageSize = args[1];
        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
        String pageSql = "SELECT *\n" +
                "  FROM (SELECT ROWNUM ROW_NUM, A.*\n" +
                "          FROM (" + sql + ") A)\n" +
                " WHERE ROW_NUM >= " + start + "\n" +
                "   AND ROW_NUM <= " + end;
        return pageSql;
    }
}
