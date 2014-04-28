package com.xwtech.uomp.base.dao.search;

import java.util.List;
import java.util.Map;

/**
 * Created by alabo on 14-1-3.
 */
public interface ISearchInfoDAO {

    List<Map<String, Object>> queryBusiUrlList();

    List<Map<String, Object>> queryBusiList(String busiNumsStr);

    List<Map<String, Object>> queryBusiSearchInfoList(String jbNum, String busiNumUrl);

    void saveSearchInfoList(List<Object[]> list);

    void truncateSearchInfoList();
}
