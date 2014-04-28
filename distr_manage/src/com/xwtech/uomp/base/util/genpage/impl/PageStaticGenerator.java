package com.xwtech.uomp.base.util.genpage.impl;

import com.xwtech.uomp.base.pojo.content.ContentChannelBean;
import com.xwtech.uomp.base.pojo.pagemanage.PageStaticInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageStaticInfoService;
import com.xwtech.uomp.base.util.genpage.ICommunicator;
import com.xwtech.uomp.base.util.genpage.IPageStaticGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Component("pageStaticGenerator")
public class PageStaticGenerator implements IPageStaticGenerator {

    private static final Logger logger = Logger.getLogger(PageStaticGenerator.class);

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    ICommunicator communicator;

    @Autowired
    IPageStaticInfoService pageStaticInfoService;

    /**
     * 生成所有频道的概览页面
     *
     * @param list
     * @return
     */
    public void generateChannelPages(List<ContentChannelBean> list) {
        try {
            //多线程方式频道概览页面生成
            for (ContentChannelBean contentChannelBean : list) {
                generateOneChannelPage(contentChannelBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成所有内容的细览页面
     *
     * @param list
     * @return
     */
    public void generateDocsPage(List<Map<String, Object>> list) {
        try {
            //多线程方式内容细览页面生成
            for (Map<String, Object> map : list) {
                generateOneDocPage(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成网站的未登陆情况下的首页面
     */
    public void generateIndexPage() {
        try {
            System.out.println("生成首页面开始-------------------------------");
            PageStaticGenerateThread pageStaticGenerateThread1 = new PageStaticGenerateThread(null, null, "index", "index", "未登陆静态化首页", "2");
            taskExecutor.execute(pageStaticGenerateThread1);

            PageStaticGenerateThread pageStaticGenerateThread2 = new PageStaticGenerateThread(null, null, "index", "index", "未登陆静态化首页", "3");
            taskExecutor.execute(pageStaticGenerateThread2);

            System.out.println("生成首页面结束-------------------------------");

        } catch (TaskRejectedException e) {
            System.out.println("线程池已满，睡眠一秒钟。。。。。");
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {

            }
        }
    }

    /**
     * 生成一个频道页面
     *
     * @param contentChannelBean
     */
    private void generateOneChannelPage(ContentChannelBean contentChannelBean) {
        String overviewTmp = contentChannelBean.getOverviewTmp();
        //生成标准版页面
        if (overviewTmp != null && !"".equals(overviewTmp)) {
            String version = "2";
            String pageName = contentChannelBean.getChanName();
            String pageNum = contentChannelBean.getDepository() + "@index";
            String template = overviewTmp;
            String pkid = contentChannelBean.getChanId().toString();
            String fkid = "";
            try {
                PageStaticGenerateThread pageStaticGenerateThread = new PageStaticGenerateThread(pkid, fkid, template, pageNum, pageName, version);
                taskExecutor.execute(pageStaticGenerateThread);
            } catch (TaskRejectedException e) {
                System.out.println("线程池已满，睡眠一秒钟。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    logger.info(e, e);
                }
            }
        }

        //生成触屏版页面
        String overviewTmp2 = contentChannelBean.getOverviewTmp2();
        if (overviewTmp2 != null && !"".equals(overviewTmp2)) {
            String version = "3";
            String pageName = contentChannelBean.getChanName();
            String pageNum = contentChannelBean.getDepository() + "@index";
            String template = overviewTmp2;
            String pkid = contentChannelBean.getChanId().toString();
            String fkid = "";
            try {
                PageStaticGenerateThread pageStaticGenerateThread = new PageStaticGenerateThread(pkid, fkid, template, pageNum, pageName, version);
                taskExecutor.execute(pageStaticGenerateThread);
            } catch (TaskRejectedException e) {
                System.out.println("线程池已满，睡眠一秒钟。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    logger.info(e, e);
                }
            }
        }
    }

    /**
     * 生成一个内容页面
     *
     * @param map
     */
    private void generateOneDocPage(Map<String, Object> map) {
        //生成标准版细览页面
        String detailTmp = map.get("F_DETAIL_TMP") == null ? "" : map.get("F_DETAIL_TMP").toString();
        if (!"".equals(detailTmp)) {
            String version = "2";
            String depository = map.get("F_DEPOSITORY").toString();
            String pkid = map.get("F_DOC_ID").toString();
            String pageNum = depository + "@" + pkid;
            String template = detailTmp;
            String fkid = map.get("F_CHAN_ID").toString();

            try {
                PageStaticGenerateThread pageStaticGenerateThread = new PageStaticGenerateThread(pkid, fkid, template, pageNum, pageNum, version);
                taskExecutor.execute(pageStaticGenerateThread);
            } catch (TaskRejectedException e) {
                System.out.println("线程池已满，睡眠一秒钟。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    logger.info(e, e);
                }
            }
        }

        //生成触屏版细览页面
        String detailTmp2 = map.get("F_DETAIL_TMP2") == null ? "" : map.get("F_DETAIL_TMP2").toString();
        if (!"".equals(detailTmp2)) {
            String version = "3";
            String depository = map.get("F_DEPOSITORY").toString();
            String pkid = map.get("F_DOC_ID").toString();
            String pageNum = depository + "@" + pkid;
            String template = detailTmp2;
            String fkid = map.get("F_CHAN_ID").toString();

            try {
                PageStaticGenerateThread pageStaticGenerateThread = new PageStaticGenerateThread(pkid, fkid, template, pageNum, pageNum, version);
                taskExecutor.execute(pageStaticGenerateThread);
            } catch (TaskRejectedException e) {
                System.out.println("线程池已满，睡眠一秒钟。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    logger.info(e, e);
                }
            }
        }
    }

    /**
     * 多线程进行页面生成
     */
    private class PageStaticGenerateThread implements Runnable {
        String pkid;
        String fkid;
        String template;
        String pageNum;
        String pageName;
        String version;

        public PageStaticGenerateThread(String pkid, String fkid, String template, String pageNum, String pageName, String version) {
            this.pkid = pkid;
            this.fkid = fkid;
            this.template = template;
            this.pageNum = pageNum;
            this.pageName = pageName;
            this.version = version;
        }

        public void run() {
            String desc = "";
            String url = "";
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (template.equals("index")) {//模拟访问动态的首页
                if (version.equals("2")) {
                    url = template + ".shtml?mod=d";
                    desc = "页面【" + pageNum + "标准版】";
                } else if (version.equals("3")) {
                    url = template + ".thtml?mod=d";
                    desc = "页面【" + pageNum + "触屏版】";
                }
            } else {
                if (version.equals("2")) {
                    url = template + ".shtml" + "!" + pkid + "_" + fkid;
                    desc = "页面【" + pageNum + "标准版】";
                } else if (version.equals("3")) {
                    url = template + ".thtml" + "!" + pkid + "_" + fkid;
                    desc = "页面【" + pageNum + "触屏版】";
                }
            }
            System.out.println("---------------------开始一个线程---------------------------" + pageNum);
            Properties prop = new Properties();
            String pageContent = communicator.doWapRequest(url, prop);
            if (pageContent != null && !"".equals(pageContent)) { //发生模拟访问成功时
                desc += "生成成功！";

                PageStaticInfo pageStaticInfo = new PageStaticInfo();
                pageStaticInfo.setPageNum(pageNum);
                pageStaticInfo.setPageName(pageName);
                pageStaticInfo.setVersion(version);
                pageStaticInfo.setPageContent(pageContent);
                pageStaticInfo.setDesc(desc);
                pageStaticInfo.setUpdateTime(now);

                //保存页面至数据库
                pageStaticInfoService.savePageStaticInfo(pageStaticInfo);
            } else {
                System.out.println("模拟访问：" + url + "失败，不进行页面覆盖操作........");
            }

            System.out.println("---------------------线程执行结束-----------------------" + pageNum);
        }
    }
}
