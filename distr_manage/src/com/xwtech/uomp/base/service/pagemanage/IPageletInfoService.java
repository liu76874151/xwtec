package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageletInfo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-19
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public interface IPageletInfoService {
    Page pagingQueryPageletInfoList(Map<String, String> paramMap);

    PageletInfo findOnePageletInfo(Map<String, String> paramMap);

    void savePageletInfo(PageletInfo object);

    void updatePageletInfo(PageletInfo object);

    void deletePageletInfo(Map<String, String> paramMap);
}
