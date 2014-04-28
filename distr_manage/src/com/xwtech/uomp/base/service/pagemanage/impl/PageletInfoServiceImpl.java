package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageletInfoMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageletInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageletInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-19
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
@Service("pageletInfoService")
public class PageletInfoServiceImpl implements IPageletInfoService {

    @Autowired
    PageletInfoMapper pageletInfoMapper;

    public Page pagingQueryPageletInfoList(Map<String, String> paramMap) {
        try {
            List<PageletInfo> list = pageletInfoMapper.pagingQueryPageletInfoList(paramMap);
            int count = pageletInfoMapper.countPageletInfoList(paramMap);

            Page page = new Page();
            page.setRecords(list);
            page.setTotalRecord(count);

            return page;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PageletInfo findOnePageletInfo(Map<String, String> paramMap) {
        List<PageletInfo> list = pageletInfoMapper.findOnePageletInfo(paramMap);
        return list.get(0);
    }

    public void savePageletInfo(PageletInfo object) {
        pageletInfoMapper.savePageletInfo(object);
    }

    public void updatePageletInfo(PageletInfo object) {
        pageletInfoMapper.updatePageletInfo(object);
    }

    public void deletePageletInfo(Map<String, String> paramMap) {
        pageletInfoMapper.deletePageletInfo(paramMap);
    }
}
