package com.xwtech.uomp.base.util.genpage;

import com.xwtech.uomp.base.pojo.content.ContentChannelBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */
public interface IPageStaticGenerator {

    void generateChannelPages(List<ContentChannelBean> list);

    void generateDocsPage(List<Map<String, Object>> list);

    void generateIndexPage();
}
