package com.xwtech.uomp.base.service.pagemanage;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public interface IPageStaticGenerateService {

    void generateStaticPages(String chanId);

    void generateAllStaticPages(String channelNum);
}
