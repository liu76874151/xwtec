package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageInCompListMapper;
import com.xwtech.uomp.base.pojo.pagemanage.PageInComp;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;
import com.xwtech.uomp.base.service.pagemanage.IPageInCompListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-23
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
@Service("pageInCompListService")
public class PageInCompListServiceImpl implements IPageInCompListService {
    protected static final Logger log = Logger.getLogger(PageInCompListServiceImpl.class);

    @Autowired
    PageInCompListMapper pageInCompListMapper;

    public List<PageInComp> queryPageCompUsedList(Map<String, String> paramMap) {
        return pageInCompListMapper.queryPageCompUsedList(paramMap);
    }

    public List<PageInComp> queryPageInCompList(Map<String, String> paramMap) {
        return pageInCompListMapper.queryPageInCompList(paramMap);
    }

    @Transactional
    public void savePageInCompList(List<PageInCompList> list) {
        //批量新增记录 todo 修改mapper的批量新增，目前有问题
        for (PageInCompList object : list) {
            pageInCompListMapper.savePageInCompList(object);
        }
    }

    public void deletePageInCompList(Map<String, String> paramMap) {
        pageInCompListMapper.deletePageInCompList(paramMap);
    }
}
