package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageTmpInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 下午2:02
 * To change this template use File | Settings | File Templates.
 */
public interface IPageTmpInfoService {
    Page pagingQueryPageTmpInfoList(Map<String, String> paramMap);

    List<PageTmpInfo> queryPageTmpInfoListForGen(Map<String, String> paramMap);

    List<PageTmpInfo> queryPageTmpInfoList(Map<String, String> paramMap);

    PageTmpInfo queryOnePageTmpInfo(Map<String, String> paramMap);

    PageTmpInfo findOnePageTmpInfo(Map<String, String> paramMap);

    void savePageTmpInfo(PageTmpInfo object, List<PageInCompList> pageInCompList, List<PageRelaBusiList> pageRelaBusiLists, List<PageRelaNonbusiList> pageRelaNonbusiList);

    void updatePageTmpInfo(PageTmpInfo object, List<PageInCompList> pageInCompList, List<PageRelaBusiList> pageRelaBusiLists, List<PageRelaNonbusiList> pageRelaNonbusiList);

    void deletePageTmpInfo(Map<String, String> paramMap);
}
