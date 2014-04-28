package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageletInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-19
 * Time: 上午10:05
 * To change this template use File | Settings | File Templates.
 */
public interface PageletInfoMapper {
    List<PageletInfo> pagingQueryPageletInfoList(Map<String, String> paramMap);

    int countPageletInfoList(Map<String, String> paramMap);

    List<PageletInfo> findOnePageletInfo(Map<String, String> paramMap);

    void savePageletInfo(PageletInfo object);

    void updatePageletInfo(PageletInfo object);

    void deletePageletInfo(Map<String, String> paramMap);
}
