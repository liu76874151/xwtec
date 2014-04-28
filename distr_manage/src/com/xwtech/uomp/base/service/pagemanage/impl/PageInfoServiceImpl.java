package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageInfoService;
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
 * Time: 上午9:35
 * To change this template use File | Settings | File Templates.
 */

@Service("pageInfoService")
public class PageInfoServiceImpl implements IPageInfoService {

    protected static final Logger log = Logger.getLogger(PageInfoServiceImpl.class);

    @Autowired
    PageInfoMapper pageInfoMapper;

    public Page pagingQueryPageInfoList(Map<String, String> paramMap) {
        List<PageInfo> list = pageInfoMapper.pagingQueryPageInfoList(paramMap);
        int count = pageInfoMapper.countPageInfoList(paramMap);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
    }

    public PageInfo findOnePageInfo(Map<String, String> paramMap) {
        return pageInfoMapper.findOnePageInfo(paramMap);
    }

    public void savePageInfo(PageInfo object) {
        pageInfoMapper.savePageInfo(object);
    }

    @Transactional
    public void savePageInfoList(List<PageInfo> pageInfoList) {
        for (PageInfo pageInfo : pageInfoList) {
            String pageNum = pageInfo.getPageNum();
            String version = pageInfo.getVersion();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("pageNum", pageNum);
            paramMap.put("version", version);

            //删除已有的生成页面
            pageInfoMapper.deletePageInfo(paramMap);
            //保存新的生成页面
            pageInfoMapper.savePageInfo(pageInfo);
        }
    }

    public void updatePageInfo(PageInfo object) {
        pageInfoMapper.updatePageInfo(object);
    }

    public void deletePageInfo(Map<String, String> paramMap) {
        pageInfoMapper.deletePageInfo(paramMap);
    }

    /**
     * 根据条件查询页面列表
     *
     * @param paramMap
     * @return
     */
    public List<PageInfo> queryPageInfoList(Map<String, String> paramMap) {
        return pageInfoMapper.queryPageInfoList(paramMap);
    }
}
