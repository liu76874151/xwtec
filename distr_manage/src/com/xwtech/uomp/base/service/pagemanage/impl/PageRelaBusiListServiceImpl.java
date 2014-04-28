package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageRelaBusiListMapper;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusi;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusiList;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaBusiListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-24
 * Time: 下午4:47
 * To change this template use File | Settings | File Templates.
 */
@Service("pageRelaBusiListService")
public class PageRelaBusiListServiceImpl implements IPageRelaBusiListService {

    protected static final Logger log = Logger.getLogger(PageRelaBusiListServiceImpl.class);

    @Autowired
    PageRelaBusiListMapper pageRelaBusiListMapper;

    public List<PageRelaBusi> queryBusiUsedList(Map<String, String> paramMap) {
        return pageRelaBusiListMapper.queryBusiUsedList(paramMap);
    }

    public List<PageRelaBusi> queryPageRelaBusiList(Map<String, String> paramMap) {
        return pageRelaBusiListMapper.queryPageRelaBusiList(paramMap);
    }

    public List<PageRelaBusi> queryGeneratePageRelaBusiList(Map<String, Object> paramMap) {
        return pageRelaBusiListMapper.queryGeneratePageRelaBusiList(paramMap);
    }

    @Transactional
    public void savePageRelaBusiList(List<PageRelaBusiList> list) {
        //批量新增记录 todo 修改mapper的批量新增，目前有问题
        for (PageRelaBusiList object : list) {
            pageRelaBusiListMapper.savePageRelaBusiList(object);
        }
    }

    public void deletePageRelaBusiList(Map<String, String> paramMap) {
        pageRelaBusiListMapper.deletePageRelaBusiList(paramMap);
    }
}
