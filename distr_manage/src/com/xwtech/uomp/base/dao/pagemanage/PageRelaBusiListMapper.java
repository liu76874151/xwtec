package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusi;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusiList;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-24
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public interface PageRelaBusiListMapper {
    List<PageRelaBusi> queryBusiUsedList(Map<String, String> paramMap);

    List<PageRelaBusi> queryPageRelaBusiList(Map<String, String> paramMap);

    List<PageRelaBusi> queryGeneratePageRelaBusiList(Map<String, Object> paramMap);

    void savePageRelaBusiList(PageRelaBusiList object);

    void deletePageRelaBusiList(Map<String, String> paramMap);
}
