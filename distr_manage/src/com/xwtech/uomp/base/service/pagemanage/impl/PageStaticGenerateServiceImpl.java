package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.pojo.content.ContentChannelBean;
import com.xwtech.uomp.base.service.content.IContentChannelService;
import com.xwtech.uomp.base.service.content.IContentDocService;
import com.xwtech.uomp.base.service.pagemanage.IPageStaticGenerateService;
import com.xwtech.uomp.base.util.genpage.IPageStaticGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@Service("pageStaticGenerateService")
public class PageStaticGenerateServiceImpl implements IPageStaticGenerateService {

    private static final Logger logger = Logger.getLogger(PageStaticGenerateServiceImpl.class);

    @Autowired
    IPageStaticGenerator pageStaticGenerator;

    @Autowired
    IContentChannelService contentChannelService;

    @Autowired
    IContentDocService contentDocService;

    /**
     * 根据频道ID，生成频道包括其下所有子频道的页面，以及其下的文章页面
     *
     * @param chanId
     */
    public void generateStaticPages(String chanId) {
        //获得频道和频道下的所有子频道
        List<ContentChannelBean> channelList = contentChannelService.queryAllSubChannelList(chanId);

        List<ContentChannelBean> channelBeanList = new ArrayList<ContentChannelBean>();
        String chanIds = "-1";
        for (ContentChannelBean contentChannelBean : channelList) {  //过滤未设置概览模板的频道
            String overviewTmp = contentChannelBean.getOverviewTmp() == null ? "" : contentChannelBean.getOverviewTmp();
            if (!"".equals(overviewTmp)) {
                chanIds += "," + contentChannelBean.getChanId();
                channelBeanList.add(contentChannelBean);
            }
        }

        //获得所有频道的可发布内容
        List<Map<String, Object>> docList = contentDocService.queryContentDocsByChanIds(chanIds);
        //生成频道概览页面
        pageStaticGenerator.generateChannelPages(channelList);
        //生成内容页面
        pageStaticGenerator.generateDocsPage(docList);

    }

    /**
     * 根据渠道编号，生成渠道下所有频道的页面
     *
     * @param channelNum
     */
    public void generateAllStaticPages(String channelNum) {
        List<ContentChannelBean> channelList = contentChannelService.queryAllChannelList(channelNum);

        List<ContentChannelBean> channelBeanList = new ArrayList<ContentChannelBean>();
        String chanIds = "-1";
        for (ContentChannelBean contentChannelBean : channelList) {  //过滤未设置概览模板的频道
            String overviewTmp = contentChannelBean.getOverviewTmp() == null ? "" : contentChannelBean.getOverviewTmp();
            String overviewTmp2 = contentChannelBean.getOverviewTmp2() == null ? "" : contentChannelBean.getOverviewTmp2();
            if (!"".equals(overviewTmp) || !"".equals(overviewTmp2)) {
                chanIds += "," + contentChannelBean.getChanId();
                channelBeanList.add(contentChannelBean);
            }
        }

        //获得所有频道的可发布内容
        List<Map<String, Object>> docList = contentDocService.queryContentDocsByChanIds(chanIds);

        //生成频道概览页面
        pageStaticGenerator.generateChannelPages(channelBeanList);

        //生成内容页面
        pageStaticGenerator.generateDocsPage(docList);
    }
}
