package com.xwtech.uomp.base.service.search;

import com.xwtech.uomp.base.dao.search.ISearchInfoDAO;
import com.xwtech.uomp.base.service.ISearchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by alabo on 14-1-3.
 */
@Service("searchInfoService")
public class SearchInfoServiceImpl implements ISearchInfoService {

    @Autowired
    ISearchInfoDAO searchInfoDAO;


    /**
     * 每24小时构建一次搜索使用信息列表
     */
   // @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    @Transactional
    public void initSearchInfo() {
        System.out.println("开始构建搜索使用信息列表.....................");
        //查询和其它一些业务的编码
        String BUSI_NUMS = "'ZDCX','QDCX','ZHYEJYXQ','TCSYQK','LLZQ','TCJYWCX_MWYW','TCJYWCX_CPTC','TCJYWCX_FJGN','TCJYWCX_YXYW','TCJYWCX_YYYW','MZDHCX_MZCX','MZDH_MZDHXYW','GSDCX','CDHCX','GRZLGL_PUKMCX','SEXCHM','KHFW_MMBJ','SJYXDSXFCX','WLANRDCX','JCMFGBCSCX','BLLSCX','CZJF_CZJFJL','MZDHCX_MZCX','DZGDXYCX','YHHD','WSCZYL'";
        //获取业务办理下的所有业务和页面url
        List<Map<String, Object>> list = searchInfoDAO.queryBusiUrlList();
        for (Map<String, Object> map : list) {
            String url = map.get("F_URL") == null ? "" : map.get("F_URL").toString();
            if (!"".equals(url) && url.contains(".")) {
                String[] url_array = url.split("\\.");
                String busiNum = url_array[0];
                BUSI_NUMS += ",'" + busiNum + "'";
            }
        }

        //获取所有业务列表
        List<Map<String, Object>> busiList = searchInfoDAO.queryBusiList(BUSI_NUMS);
        Map<String, Map<String, Object>> searchmap = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> map : busiList) {
            String jbNum = map.get("F_JB_NUM") == null ? "" : map.get("F_JB_NUM").toString();
            String busiNumUrl = map.get("F_BUSI_NUM") == null ? "" : map.get("F_BUSI_NUM").toString() + ".html";
            //构造搜索信息列表
            List<Map<String, Object>> searchInfoList = searchInfoDAO.queryBusiSearchInfoList(jbNum, busiNumUrl);
            for (Map<String, Object> searchInfoMap : searchInfoList) {
                String id = searchInfoMap.get("F_ID") == null ? "" : searchInfoMap.get("F_ID").toString();
                if (!"".equals(id)) {
                    searchmap.put(id, searchInfoMap);
                }
            }
        }

        Set set = searchmap.keySet();
        Iterator iterator = set.iterator();
        List<Object[]> paramList = new ArrayList<Object[]>();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Map<String, Object> map = searchmap.get(key);

            String F_ID = map.get("F_ID") == null ? "" : map.get("F_ID").toString();
            String F_NAME = map.get("F_NAME") == null ? "" : map.get("F_NAME").toString();
            String F_DESC = map.get("F_DESC") == null ? "" : map.get("F_DESC").toString();
            String F_ACCESS_URL = map.get("F_ACCESS_URL") == null ? "" : map.get("F_ACCESS_URL").toString();
            String F_DESC_URL = map.get("F_DESC_URL") == null ? "" : map.get("F_DESC_URL").toString();
            String F_FEE = map.get("F_FEE") == null ? "" : map.get("F_FEE").toString();
            String F_FEE_TYPE = map.get("F_FEE_TYPE") == null ? "" : map.get("F_FEE_TYPE").toString();
            String F_TAGS = map.get("F_TAGS") == null ? "" : map.get("F_TAGS").toString();
            String F_INDEX = map.get("F_INDEX") == null ? "" : map.get("F_INDEX").toString();
            String F_DISABLED = map.get("F_DISABLED") == null ? "" : map.get("F_DISABLED").toString();
            String F_RELATED = map.get("F_RELATED") == null ? "" : map.get("F_RELATED").toString();
            String F_UTIME = map.get("F_UTIME") == null ? "" : map.get("F_UTIME").toString();
            String F_SYNC = map.get("F_SYNC") == null ? "" : map.get("F_SYNC").toString();
            String F_IMG_URL = map.get("F_IMG_URL") == null ? "" : map.get("F_IMG_URL").toString();
            String F_ONLINE_TIME = map.get("F_ONLINE_TIME") == null ? "" : map.get("F_ONLINE_TIME").toString();
            String F_OFFLINE_TIME = map.get("F_OFFLINE_TIME") == null ? "" : map.get("F_OFFLINE_TIME").toString();
            String F_CITY = map.get("F_CITY") == null ? "" : map.get("F_CITY").toString();
            String F_CITY_NAME = map.get("F_CITY_NAME") == null ? "" : map.get("F_CITY_NAME").toString();

            Object[] objects = new Object[18];
            objects[0] = F_ID;
            objects[1] = F_NAME;
            objects[2] = F_DESC;
            objects[3] = F_ACCESS_URL;
            objects[4] = F_DESC_URL;
            objects[5] = F_FEE;
            objects[6] = F_FEE_TYPE;
            objects[7] = F_TAGS;
            objects[8] = F_INDEX;
            objects[9] = F_DISABLED;
            objects[10] = F_RELATED;
            objects[11] = F_UTIME;
            objects[12] = F_SYNC;
            objects[13] = F_IMG_URL;
            objects[14] = F_ONLINE_TIME;
            objects[15] = F_OFFLINE_TIME;
            objects[16] = F_CITY;
            objects[17] = F_CITY_NAME;

            paramList.add(objects);
        }
        searchInfoDAO.truncateSearchInfoList();
        searchInfoDAO.saveSearchInfoList(paramList);
        System.out.println("结束构建搜索使用信息列表，共插入：" + paramList.size() + "条记录.....................");
    }
}
