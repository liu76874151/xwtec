package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.service.pagemanage.IPageScheduledGenerateService;
import com.xwtech.uomp.base.util.genpage.IPageStaticGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-15
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
@Service("pageScheduledGenerateService")
public class PageScheduledGenerateServiceImpl implements IPageScheduledGenerateService {

    @Autowired
    IPageStaticGenerator pageStaticGenerator;

    /**
     * 按计划页面生成
     */
//   @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void scheduledPageGenerate() {
        Date start = new Date();
        System.out.println("开始执行页面生成计划任务：" + start.toLocaleString());
        indexPageGenerate();
//        busiTypePageGenerate();
//        groupDetectionPageGenerate();
        Date end = new Date();
        System.out.println("页面生成计划任务已完成:" + end.toLocaleString());
    }

    /**
     * 业务分类页面静态化生成，用于满足面包屑的导航需要
     */
    private void busiTypePageGenerate() {

    }

    /**
     * 首页面静态化生成，用于未登陆页面的静态化
     */
    private void indexPageGenerate() {
        pageStaticGenerator.generateIndexPage();
    }

    /**
     * 集团探测页面静态化生成
     */
    private void groupDetectionPageGenerate() {

    }
}
