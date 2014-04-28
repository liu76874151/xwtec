package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusi;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusiList;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-5
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public interface IPageRelaNonbusiListService {
    List<PageRelaNonbusi> queryNonbusiUsedList(Map<String, String> paramMap);

    List<PageRelaNonbusi> queryPageRelaNonbusiList(Map<String, String> paramMap);

    List<PageRelaNonbusi> queryGeneratePageRelaNonbusiList(Map<String, Object> paramMap);

    void savePageRelaNonbusiList(List<PageRelaNonbusiList> list);

    void deletePageRelaNonbusiList(Map<String, String> paramMap);
}
