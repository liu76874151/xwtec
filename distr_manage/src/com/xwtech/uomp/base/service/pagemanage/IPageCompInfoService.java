package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageCompInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-18
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */
public interface IPageCompInfoService {
    Page pagingQueryPageCompInfoList(Map<String, String> paramMap);

    PageCompInfo findOnePageCompInfo(String pkid);

    void savePageCompInfo(PageCompInfo object);

    void updatePageCompInfo(PageCompInfo object);

    void deletePageCompInfo(String pkid);
    
    List<PageCompInfo> queryPageCompInfoList(Map<String, String> paramMap);

}
