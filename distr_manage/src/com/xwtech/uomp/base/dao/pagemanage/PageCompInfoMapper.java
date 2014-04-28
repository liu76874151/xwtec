package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageCompInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-18
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public interface PageCompInfoMapper {
    List<PageCompInfo> pagingQueryPageCompInfoList(Map<String, String> paramMap);

    int countPageCompInfoList(Map<String, String> paramMap);

    PageCompInfo findOnePageCompInfo(String pkid);

    void savePageCompInfo(PageCompInfo object);

    void updatePageCompInfo(PageCompInfo object);

    void deletePageCompInfo(String pkid);
    
    List<PageCompInfo> queryPageCompInfoList(Map<String, String> paramMap);
}
