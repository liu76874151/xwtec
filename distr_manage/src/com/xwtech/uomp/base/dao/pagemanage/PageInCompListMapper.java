package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageInComp;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-23
 * Time: 上午10:39
 * To change this template use File | Settings | File Templates.
 */
public interface PageInCompListMapper {
    List<PageInComp> queryPageCompUsedList(Map<String, String> paramMap);

    List<PageInComp> queryPageInCompList(Map<String, String> paramMap);

    void savePageInCompList(PageInCompList object);

    void deletePageInCompList(Map<String, String> paramMap);
}
