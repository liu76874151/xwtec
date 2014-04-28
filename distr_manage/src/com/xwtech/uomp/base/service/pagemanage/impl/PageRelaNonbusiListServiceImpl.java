package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageRelaNonbusiListMapper;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusi;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusiList;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaNonbusiListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-5
 * Time: 下午3:09
 * To change this template use File | Settings | File Templates.
 */
@Service("pageRelaNonbusiListService")
public class PageRelaNonbusiListServiceImpl implements IPageRelaNonbusiListService {


    protected static final Logger log = Logger.getLogger(PageRelaNonbusiListServiceImpl.class);

    @Autowired
    PageRelaNonbusiListMapper pageRelaNonbusiListMapper;

    public List<PageRelaNonbusi> queryNonbusiUsedList(Map<String, String> paramMap) {
        return pageRelaNonbusiListMapper.queryNonbusiUsedList(paramMap);
    }

    public List<PageRelaNonbusi> queryPageRelaNonbusiList(Map<String, String> paramMap) {
        return pageRelaNonbusiListMapper.queryPageRelaNonbusiList(paramMap);
    }

    public List<PageRelaNonbusi> queryGeneratePageRelaNonbusiList(Map<String, Object> paramMap) {
        return pageRelaNonbusiListMapper.queryGeneratePageRelaNonbusiList(paramMap);
    }

    @Transactional
    public void savePageRelaNonbusiList(List<PageRelaNonbusiList> list) {
        //批量新增记录 todo 修改mapper的批量新增，目前有问题
        for (PageRelaNonbusiList pageRelaNonbusiList : list) {
            pageRelaNonbusiListMapper.savePageRelaNonbusiList(pageRelaNonbusiList);
        }
    }

    public void deletePageRelaNonbusiList(Map<String, String> paramMap) {
        pageRelaNonbusiListMapper.deletePageRelaNonbusiList(paramMap);
    }
}
