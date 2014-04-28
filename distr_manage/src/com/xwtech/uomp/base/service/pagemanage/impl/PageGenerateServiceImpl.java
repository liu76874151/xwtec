package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.pojo.pagemanage.*;
import com.xwtech.uomp.base.service.pagemanage.*;
import com.xwtech.uomp.base.util.genpage.IPageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Service("pageGenerateService")
public class PageGenerateServiceImpl implements IPageGenerateService {

    @Autowired
    IPageTmpInfoService pageTmpInfoService;

    @Autowired
    IPageInCompListService pageInCompListService;

    @Autowired
    IPageRelaBusiListService pageRelaBusiListService;

    @Autowired
    IPageRelaNonbusiListService pageRelaNonbusiListService;

    @Autowired
    IPageInfoService pageInfoService;

    @Autowired
    IPageGenerator pageGenerator;

    /**
     * 根据选中的页面模板生成页面
     *
     * @param paramMap
     */
    public void generatePages(Map<String, String> paramMap) {

        //获取一个页面模板
        PageTmpInfo pageTmpInfo = pageTmpInfoService.queryOnePageTmpInfo(paramMap);

        //获取该页面模板关联的组件列表
        List<PageInComp> pageInCompList = pageInCompListService.queryPageInCompList(paramMap);

        String pageTmpNum = paramMap.get("pageTmpNum");
        String version = paramMap.get("version");
        String busiNums = paramMap.get("busiNums");
        String nonbusiNums = paramMap.get("nonbusiNums");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageTmpNum", pageTmpNum);
        param.put("version", version);
        if (busiNums != null && !busiNums.isEmpty()) {
            String[] busiNum_array = busiNums.split(",");
            param.put("busiNums", busiNum_array);
        }
        if (nonbusiNums != null && !nonbusiNums.isEmpty()) {
            String[] nonbusiNum_array = nonbusiNums.split(",");
            param.put("nonbusiNums", nonbusiNum_array);
        }
        //获取该页面模板关联的业务列表
        List<PageRelaBusi> pageRelaBusiList = pageRelaBusiListService.queryGeneratePageRelaBusiList(param);

        //获取该页面模板关联的非业务列表
        List<PageRelaNonbusi> pageRelaNonbusiList = pageRelaNonbusiListService.queryGeneratePageRelaNonbusiList(param);

        //开始多线程处理页面生成
        PageGenerateThread pgt = new PageGenerateThread(pageTmpInfo, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
        pgt.start();
    }

    public void generateAllPages() {
        //查询所有的页面模板
        List<PageTmpInfo> pageTmpInfoList_all = pageTmpInfoService.queryPageTmpInfoList(null);

        //查询所有的页面模板关联的组件
        List<PageInComp> pageInCompList_all = pageInCompListService.queryPageInCompList(null);

        //查询所有的页面模板关联的业务
        List<PageRelaBusi> pageRelaBusiList_all = pageRelaBusiListService.queryPageRelaBusiList(null);

        //查询所有的页面模板关联的非业务
        List<PageRelaNonbusi> pageRelaNonbusiList_all = pageRelaNonbusiListService.queryPageRelaNonbusiList(null);

        //构造页面模板关联组件的map
        Map<String, List<PageInComp>> pageInCompMap = new HashMap<String, List<PageInComp>>();
        for (PageInComp pageInComp : pageInCompList_all) {
            String pageTmpNum = pageInComp.getPageTmpNum();
            String version = pageInComp.getVersion();
            String key = pageTmpNum + ":" + version;
            if (pageInCompMap.get(key) == null) {
                List<PageInComp> list = new ArrayList<PageInComp>();
                list.add(pageInComp);
                pageInCompMap.put(key, list);
            } else {
                List<PageInComp> list = pageInCompMap.get(key);
                list.add(pageInComp);
                pageInCompMap.put(key, list);
            }
        }

        //构造页面模板关联业务的map
        Map<String, List<PageRelaBusi>> pageRelaBusiMap = new HashMap<String, List<PageRelaBusi>>();
        for (PageRelaBusi pageRelaBusi : pageRelaBusiList_all) {
            String pageTmpNum = pageRelaBusi.getPageTmpNum();
            String version = pageRelaBusi.getVersion();
            String key = pageTmpNum + ":" + version;
            if (pageRelaBusiMap.get(key) == null) {
                List<PageRelaBusi> list = new ArrayList<PageRelaBusi>();
                list.add(pageRelaBusi);
                pageRelaBusiMap.put(key, list);
            } else {
                List<PageRelaBusi> list = pageRelaBusiMap.get(key);
                list.add(pageRelaBusi);
                pageRelaBusiMap.put(key, list);
            }
        }

        //构造页面模板关联非业务的map
        Map<String, List<PageRelaNonbusi>> pageRelaNonbusiMap = new HashMap<String, List<PageRelaNonbusi>>();
        for (PageRelaNonbusi pageRelaNonbusi : pageRelaNonbusiList_all) {
            String pageTmpNum = pageRelaNonbusi.getPageTmpNum();
            String version = pageRelaNonbusi.getVersion();
            String key = pageTmpNum + ":" + version;
            if (pageRelaNonbusiMap.get(key) == null) {
                List<PageRelaNonbusi> list = new ArrayList<PageRelaNonbusi>();
                list.add(pageRelaNonbusi);
                pageRelaNonbusiMap.put(key, list);
            } else {
                List<PageRelaNonbusi> list = pageRelaNonbusiMap.get(key);
                list.add(pageRelaNonbusi);
                pageRelaNonbusiMap.put(key, list);
            }
        }

        for (PageTmpInfo pageTmpInfo : pageTmpInfoList_all) {
            String pageTmpNum = pageTmpInfo.getPageTmpNum();
            String version = pageTmpInfo.getVersion();
            String key = pageTmpNum + ":" + version;
            List<PageInComp> pageInCompList = pageInCompMap.get(key);
            List<PageRelaBusi> pageRelaBusiList = pageRelaBusiMap.get(key);
            List<PageRelaNonbusi> pageRelaNonbusiList = pageRelaNonbusiMap.get(key);
            if ("0".equals(version)) {

                //标准版
                PageTmpInfo pageTmpInfo_bz = new PageTmpInfo();
                pageTmpInfo_bz.setPageTmpNum(pageTmpInfo.getPageTmpNum());
                pageTmpInfo_bz.setPageTmpName(pageTmpInfo.getPageTmpName());
                pageTmpInfo_bz.setVersion("2");
                pageTmpInfo_bz.setTmplatePath(pageTmpInfo.getTmplatePath());
                pageTmpInfo_bz.setIsRelaBusi(pageTmpInfo.getIsRelaBusi());
                pageTmpInfo_bz.setDesc(pageTmpInfo.getDesc());
                pageTmpInfo_bz.setType(pageTmpInfo.getType());

                PageGenerateThread pgt_bz = new PageGenerateThread(pageTmpInfo_bz, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
                pgt_bz.start();

                //触屏版
                PageTmpInfo pageTmpInfo_cp = new PageTmpInfo();
                pageTmpInfo_cp.setPageTmpNum(pageTmpInfo.getPageTmpNum());
                pageTmpInfo_cp.setPageTmpName(pageTmpInfo.getPageTmpName());
                pageTmpInfo_cp.setVersion("3");
                pageTmpInfo_cp.setTmplatePath(pageTmpInfo.getTmplatePath());
                pageTmpInfo_cp.setIsRelaBusi(pageTmpInfo.getIsRelaBusi());
                pageTmpInfo_cp.setDesc(pageTmpInfo.getDesc());
                pageTmpInfo_cp.setType(pageTmpInfo.getType());

                PageGenerateThread pgt_cp = new PageGenerateThread(pageTmpInfo_cp, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
                pgt_cp.start();

            } else {
                PageGenerateThread pgt = new PageGenerateThread(pageTmpInfo, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
                pgt.start();
            }
        }

    }

    /**
     * 多线程进行页面生成
     */
    private class PageGenerateThread extends Thread {
        PageTmpInfo pageTmpInfo;
        List<PageInComp> pageInCompList;
        List<PageRelaBusi> pageRelaBusiList;
        List<PageRelaNonbusi> pageRelaNonbusiList;

        public PageGenerateThread(PageTmpInfo pageTmpInfo, List<PageInComp> pageInCompList, List<PageRelaBusi> pageRelaBusiList, List<PageRelaNonbusi> pageRelaNonbusiList) {
            this.pageTmpInfo = pageTmpInfo;
            this.pageInCompList = pageInCompList;
            this.pageRelaBusiList = pageRelaBusiList;
            this.pageRelaNonbusiList = pageRelaNonbusiList;
        }

        public void run() {
            long id = this.getId();
            System.out.println("---------------------开始一个线程---------------------------:" + id);
            //对应每个关联业务生成一个页面
            List<PageInfo> pageInfoList = pageGenerator.generatePages(pageTmpInfo, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);

            //将生成的页面内容插入T_PAGE_INFO表
            pageInfoService.savePageInfoList(pageInfoList);
            System.out.println("---------------------完成一个线程---------------------------:" + id);
        }
    }
}
