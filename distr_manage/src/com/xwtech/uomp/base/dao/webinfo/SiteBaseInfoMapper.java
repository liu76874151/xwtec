package com.xwtech.uomp.base.dao.webinfo;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.webinfo.SiteBaseInfoBean;



public interface SiteBaseInfoMapper {
   int queryWebInfoListCount(Map<String, String> map);

   List<SiteBaseInfoBean> queryWebInfoList(Map<String, String> map);
   
   int updateByPrimaryKey(SiteBaseInfoBean record);
   
   SiteBaseInfoBean selectByPrimaryKey(int channelNum);

    
}