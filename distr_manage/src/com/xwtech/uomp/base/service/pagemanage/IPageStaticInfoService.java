package com.xwtech.uomp.base.service.pagemanage;

import com.xwtech.uomp.base.pojo.pagemanage.PageStaticInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public interface IPageStaticInfoService {

    void savePageStaticInfoList(List<PageStaticInfo> list);

    void savePageStaticInfo(PageStaticInfo pageStaticInfo);
}
