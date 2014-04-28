package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageNonbusiinfoMapper;
import com.xwtech.uomp.base.pojo.pagemanage.PageNonbusiinfo;
import com.xwtech.uomp.base.service.pagemanage.IPageNonbusiinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-6
 * Time: 下午4:36
 * To change this template use File | Settings | File Templates.
 */
@Service("pageNonbusiinfoService")
public class PageNonbusiinfoServiceImpl implements IPageNonbusiinfoService {

    @Autowired
    PageNonbusiinfoMapper pageNonbusiinfoMapper;

    public PageNonbusiinfo findOnePageNonbusiinfo(String nonbusiNum) {
        return pageNonbusiinfoMapper.findOnePageNonbusiinfo(nonbusiNum);
    }
}
