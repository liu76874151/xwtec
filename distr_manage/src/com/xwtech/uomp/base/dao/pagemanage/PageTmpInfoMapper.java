package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageTmpInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 下午2:03
 * To change this template use File | Settings | File Templates.
 */
public interface PageTmpInfoMapper {
    List<PageTmpInfo> pagingQueryPageTmpInfoList(Map<String, String> paramMap);

    int countPageTmpInfoList(Map<String, String> paramMap);

    List<PageTmpInfo> queryPageTmpInfoList(Map<String, String> paramMap);

    List<PageTmpInfo> findPageTmpInfoList(Map<String, String> paramMap);

    PageTmpInfo findOnePageTmpInfo(Map<String, String> paramMap);

    void savePageTmpInfo(PageTmpInfo object);

    void updatePageTmpInfo(PageTmpInfo object);

    void deletePageTmpInfo(Map<String, String> paramMap);
}
