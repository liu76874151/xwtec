package com.xwtech.uomp.base.dao.market;

import com.xwtech.uomp.base.pojo.market.ReMsRelationBean;



public interface ReMsRelationMapper {

    int saveReMsRelationBean(ReMsRelationBean record);
    void deleteReMsRelation(String marketFirstPkid);
}