package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
public interface PageInfoMapper {

    List<PageInfo> pagingQueryPageInfoList(Map<String, String> paramMap);

    int countPageInfoList(Map<String, String> paramMap);

    PageInfo findOnePageInfo(Map<String, String> paramMap);

    void savePageInfo(PageInfo object);

    void updatePageInfo(PageInfo object);

    void deletePageInfo(Map<String, String> paramMap);

    List<PageInfo> queryPageInfoList(Map<String, String> paramMap);


}
