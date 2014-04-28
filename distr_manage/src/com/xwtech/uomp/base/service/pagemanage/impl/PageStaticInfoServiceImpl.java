package com.xwtech.uomp.base.service.pagemanage.impl;

import com.xwtech.uomp.base.dao.pagemanage.PageStaticInfoMapper;
import com.xwtech.uomp.base.pojo.pagemanage.PageStaticInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageStaticInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
@Service("pageStaticInfoService")
public class PageStaticInfoServiceImpl implements IPageStaticInfoService {

    @Autowired
    PageStaticInfoMapper pageStaticInfoMapper;

    @Transactional
    public void savePageStaticInfoList(List<PageStaticInfo> list) {
        for (PageStaticInfo pageStaticInfo : list) {
            String pageNum = pageStaticInfo.getPageNum();
            String version = pageStaticInfo.getVersion();

            //构造参数
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("pageNum", pageNum);
            paramMap.put("version", version);
            //删除已有的页面信息
            pageStaticInfoMapper.deletePageStaticInfo(paramMap);
            //新增新的页面信息
            pageStaticInfoMapper.savePageStaticInfo(pageStaticInfo);
        }
    }

    @Transactional
    public void savePageStaticInfo(PageStaticInfo pageStaticInfo) {
        String pageNum = pageStaticInfo.getPageNum();
        String version = pageStaticInfo.getVersion();

        //构造参数
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("pageNum", pageNum);
        paramMap.put("version", version);
        //删除已有的页面信息
        pageStaticInfoMapper.deletePageStaticInfo(paramMap);
        //新增新的页面信息
        pageStaticInfoMapper.savePageStaticInfo(pageStaticInfo);
    }
}
