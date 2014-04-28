package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.FunInfoBean;

public interface FunInfoMapper {
    public List<Map<String, Object>> queryFunInfoList();

    public List<FunInfoBean> queryFunInfoById(String funId);

    public void modFunInfo(FunInfoBean funInfoBean);

    public void deleteFunInfo(FunInfoBean funInfoBean);

    public void addFunInfo(FunInfoBean funInfoBean);

    public int queryJbNumBySup(FunInfoBean funInfoBean);

    public void updateMjById(FunInfoBean funInfoBean);

    public void updateMjToOne(FunInfoBean funInfoBean);

    public String querySupJbNum(FunInfoBean funInfoBean);

    public void deleteQxInfo(FunInfoBean funInfoBean);

    public void deleteMouldUser(FunInfoBean funInfoBean);
}
