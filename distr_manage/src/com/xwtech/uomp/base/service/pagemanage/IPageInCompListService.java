package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageInComp;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-23
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public interface IPageInCompListService {
    List<PageInComp> queryPageCompUsedList(Map<String, String> paramMap);

    List<PageInComp> queryPageInCompList(Map<String, String> paramMap);

    void savePageInCompList(List<PageInCompList> list);

    void deletePageInCompList(Map<String, String> paramMap);
}
