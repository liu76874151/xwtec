package com.xwtech.uomp.base.dao.market;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.market.MarketSecondBindBizBean;

/**
 * 二级营销案绑定业务
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-17 下午08:32:55
 */
public interface MarketSecondBindBizMapper {
    void deleteBySecondPkid(String marketSecondPkid);

    int insert(MarketSecondBindBizBean record);

    int insertSelective(MarketSecondBindBizBean record);

    List<MarketSecondBindBizBean> selectBySecondPkid(Map<String, String> map);

    int updateByPrimaryKeySelective(MarketSecondBindBizBean record);

    int updateByPrimaryKey(MarketSecondBindBizBean record);
    void batchUpdate(List<MarketSecondBindBizBean> list);
}