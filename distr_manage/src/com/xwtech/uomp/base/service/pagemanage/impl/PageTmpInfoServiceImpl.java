package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageTmpInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageTmpInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageInCompListService;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaBusiListService;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaNonbusiListService;
import com.xwtech.uomp.base.service.pagemanage.IPageTmpInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 下午2:11
 * To change this template use File | Settings | File Templates.
 */
@Service("pageTmpInfoService")
public class PageTmpInfoServiceImpl implements IPageTmpInfoService {
    protected static final Logger log = Logger.getLogger(PageTmpInfoServiceImpl.class);

    @Autowired
    PageTmpInfoMapper pageTmpInfoMapper;

    @Autowired
    IPageInCompListService pageInCompListService;

    @Autowired
    IPageRelaBusiListService pageRelaBusiListService;

    @Autowired
    IPageRelaNonbusiListService pageRelaNonbusiListService;

    public Page pagingQueryPageTmpInfoList(Map<String, String> paramMap) {
        List<PageTmpInfo> list = pageTmpInfoMapper.pagingQueryPageTmpInfoList(paramMap);
        int count = pageTmpInfoMapper.countPageTmpInfoList(paramMap);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
    }

    /**
     * 查询页面模板列表
     *
     * @param paramMap
     * @return
     */
    public List<PageTmpInfo> queryPageTmpInfoListForGen(Map<String, String> paramMap) {
        List<PageTmpInfo> list = pageTmpInfoMapper.queryPageTmpInfoList(paramMap);
        return list;
    }

    /**
     * 查询页面模板
     *
     * @param paramMap
     * @return
     */
    public List<PageTmpInfo> queryPageTmpInfoList(Map<String, String> paramMap) {
        return pageTmpInfoMapper.queryPageTmpInfoList(paramMap);
    }

    /**
     * 查询一个页面模板
     *
     * @param paramMap
     * @return
     */
    public PageTmpInfo queryOnePageTmpInfo(Map<String, String> paramMap) {
        List<PageTmpInfo> list = pageTmpInfoMapper.findPageTmpInfoList(paramMap);
        if (!list.isEmpty()) {
            PageTmpInfo pageTmpInfo = list.get(0);
            //当paramMap中version为非0且可以查询出记录时，设置查询结果的version字段为paramMap中的值
            String version = paramMap.get("version");
            pageTmpInfo.setVersion(version);
            return pageTmpInfo;
        } else {
            return null;
        }
    }

    public PageTmpInfo findOnePageTmpInfo(Map<String, String> paramMap) {
        return pageTmpInfoMapper.findOnePageTmpInfo(paramMap);
    }

    @Transactional
    public void savePageTmpInfo(PageTmpInfo pageTmpInfo, List<PageInCompList> pageInCompList, List<PageRelaBusiList> pageRelaBusiLists, List<PageRelaNonbusiList> pageRelaNonbusiList) {
        //保存页面模板信息
        pageTmpInfoMapper.savePageTmpInfo(pageTmpInfo);

        //保存页面模板关联组件信息
        pageInCompListService.savePageInCompList(pageInCompList);

        //保存页面模板关联业务信息
        pageRelaBusiListService.savePageRelaBusiList(pageRelaBusiLists);

        //保存页面模板关联非业务信息
        pageRelaNonbusiListService.savePageRelaNonbusiList(pageRelaNonbusiList);
    }

    @Transactional
    public void updatePageTmpInfo(PageTmpInfo object, List<PageInCompList> pageInCompList, List<PageRelaBusiList> pageRelaBusiLists, List<PageRelaNonbusiList> pageRelaNonbusiList) {
        Map paramMap = new HashMap();
        paramMap.put("pageTmpNum", object.getPageTmpNum());
        paramMap.put("version", object.getVersion());

        //修改页面模板信息
        pageTmpInfoMapper.updatePageTmpInfo(object);

        //删除页面模板关联组件信息
        pageInCompListService.deletePageInCompList(paramMap);

        //保存页面模板关联组件信息
        pageInCompListService.savePageInCompList(pageInCompList);

        //删除页面模板关联业务信息
        pageRelaBusiListService.deletePageRelaBusiList(paramMap);

        //保存页面模板关联业务信息
        pageRelaBusiListService.savePageRelaBusiList(pageRelaBusiLists);

        //删除页面模板关联非业务信息
        pageRelaNonbusiListService.deletePageRelaNonbusiList(paramMap);

        //保存页面模板关联非业务信息
        pageRelaNonbusiListService.savePageRelaNonbusiList(pageRelaNonbusiList);
    }

    @Transactional
    public void deletePageTmpInfo(Map<String, String> paramMap) {
        //删除页面模板
        pageTmpInfoMapper.deletePageTmpInfo(paramMap);

        //删除页面模板关联组件信息
        pageInCompListService.deletePageInCompList(paramMap);

        //删除页面模板关联业务信息
        pageRelaBusiListService.deletePageRelaBusiList(paramMap);

        //删除页面模板关联非业务信息
        pageRelaNonbusiListService.deletePageRelaNonbusiList(paramMap);
    }
}
