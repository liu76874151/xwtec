package com.xwtech.uomp.base.service.pagemanage;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public interface IPageGenerateService {

    void generatePages(Map<String, String> paramMap);

    void generateAllPages();
}
