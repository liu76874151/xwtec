package com.xwtech.uomp.base.dao.search;

import com.xwtech.uomp.base.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by alabo on 14-1-3.
 */
@Repository("searchInfoDAO")
public class SearchInfoDAOImpl extends BaseDAO implements ISearchInfoDAO {

    /**
     * 从内容管理中获取业务url
     *
     * @return
     */
    public List<Map<String, Object>> queryBusiUrlList() {
        String sql = "select distinct (t.F_URL) F_URL\n" +
                "  from T_CONTENT_DOC t\n" +
                " where t.f_chan_id in (select t.f_chan_id\n" +
                "                         from T_CONTENT_CHANNEL t\n" +
                "                        where t.f_channel_num = '02'\n" +
                "                          and t.f_chan_num like '08%')\n" +
                "   and t.f_state = 1\n" +
                " order by t.F_URL";
        return queryForList(sql);
    }

    /**
     * 根据业务编码合集获取业务列表
     *
     * @param busiNumsStr
     * @return
     */
    public List<Map<String, Object>> queryBusiList(String busiNumsStr) {
        String sql = "select t.f_busi_num, t.f_busi_name, t.f_jb_num\n" +
                "  from T_BUSI_INFO t\n" +
                " where t.f_busi_num in (" + busiNumsStr + ")\n" +
                "   and t.f_state = 0\n" +
                " order by t.f_jb_num";
        return queryForList(sql);
    }

    /**
     * 构造搜索使用的搜索信息
     *
     * @param jbNum
     * @param url
     * @return
     */
    public List<Map<String, Object>> queryBusiSearchInfoList(String jbNum, String url) {
        String sql = "select a.f_busi_num f_id,\n" +
                "       a.f_busi_name f_name,\n" +
                "       a.f_busi_desc f_desc,\n" +
                "       '" + url + "' f_access_url,\n" +
                "       null f_desc_url,\n" +
                "       a.f_fee,\n" +
                "       a.f_fee_type,\n" +
                "       null f_tags,\n" +
                "       b.f_search_words f_index,\n" +
                "       a.f_state f_disabled,\n" +
                "       null f_related,\n" +
                "       null f_utime,\n" +
                "       0 f_sync,\n" +
                "       null f_img_url,\n" +
                "       null f_online_time,\n" +
                "       null f_offline_time,\n" +
                "       null f_city,\n" +
                "       null f_city_name\n" +
                "  from T_BUSI_INFO a, T_BUSI_BASEINFO b\n" +
                " where a.f_busi_num = b.f_busi_num\n" +
                "   and a.f_jb_num like '" + jbNum + "%'\n" +
                "   and a.f_state = 0\n" +
                " order by f_id";
        return queryForList(sql);
    }

    /**
     * 保存搜索信息入库
     *
     * @param list
     */
    public void saveSearchInfoList(List<Object[]> list) {
        String sql = "insert into T_SEARCH_INFO t\n" +
                "values\n" +
                "  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        update(sql, list);
    }

    /**
     * 清空搜索信息表
     */
    public void truncateSearchInfoList() {
        String sql = "truncate table T_SEARCH_INFO";
        execute(sql);
    }
}
