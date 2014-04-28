package com.xwtech.uomp.base.dao.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageStaticInfo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午7:57
 * To change this template use File | Settings | File Templates.
 */
public interface PageStaticInfoMapper {
    void savePageStaticInfo(PageStaticInfo object);

    void deletePageStaticInfo(Map<String, String> paramMap);
}
