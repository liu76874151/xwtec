package com.xwtech.uomp.base.util.genpage;

import com.xwtech.uomp.base.pojo.pagemanage.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public interface IPageGenerator {
    List<PageInfo> generatePages(PageTmpInfo pageTmpInfo, List<PageInComp> pageInCompList, List<PageRelaBusi> pageRelaBusiList, List<PageRelaNonbusi> pageRelaNonbusiList);

    PageInfo generateOnePage(PageTmpInfo pageTmpInfo, List<PageInComp> pageInCompList, PageRelaBusi pageRelaBusi, PageRelaNonbusi pageRelaNonbusi);
}
