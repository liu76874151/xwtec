package com.xwtech.uomp.base.dao.gift;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.gift.GiftInfoBean;


/**
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-21 下午02:18:39
 */
public interface GiftInfoMapper {
    void deleteBySecondPkid(String marketSecondPkid);

    int insert(GiftInfoBean record);

    int insertSelective(GiftInfoBean record);

    GiftInfoBean selectByPrimaryKey(Long giftId);

    int updateByPrimaryKeySelective(GiftInfoBean record);

    int updateByPrimaryKey(GiftInfoBean record);
    
    List<GiftInfoBean> selectBySecondPkid(Map<String, String> map);
}