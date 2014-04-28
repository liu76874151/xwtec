package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageCompInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageCompInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageCompInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-18
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
@Service("pageCompInfoService")
public class PageCompInfoServiceImpl implements IPageCompInfoService {
    protected static final Logger log = Logger.getLogger(PageCompInfoServiceImpl.class);

    @Autowired
    PageCompInfoMapper pageCompInfoMapper;

    public Page pagingQueryPageCompInfoList(Map<String, String> paramMap) {
        List<PageCompInfo> list = pageCompInfoMapper.pagingQueryPageCompInfoList(paramMap);
        int count = pageCompInfoMapper.countPageCompInfoList(paramMap);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
    }

    public PageCompInfo findOnePageCompInfo(String pkid) {
        return pageCompInfoMapper.findOnePageCompInfo(pkid);
    }

    public void savePageCompInfo(PageCompInfo object) {
        pageCompInfoMapper.savePageCompInfo(object);
    }

    public void updatePageCompInfo(PageCompInfo object) {
        pageCompInfoMapper.updatePageCompInfo(object);
    }

    public void deletePageCompInfo(String pkid) {
        pageCompInfoMapper.deletePageCompInfo(pkid);
    }
    
    public List<PageCompInfo> queryPageCompInfoList(Map<String, String> paramMap){
    	return pageCompInfoMapper.queryPageCompInfoList(paramMap);
    }
}
