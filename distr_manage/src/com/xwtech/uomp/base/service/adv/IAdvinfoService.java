package com.xwtech.uomp.base.service.adv;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.adv.AdvAreaDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvBrandDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvInfoBean;

public interface IAdvinfoService {
Page queryAdvInfoList(Map<String, String> map);
Page queryAdvInfoListOrderShowXh(Map<String, String> map);
AdvInfoBean findOneAdvInfoBean(Map<String, String> map);
int saveAdvInfo(List<AdvInfoBean> advInfoBeanList );
int updateAdvInfo(List<AdvInfoBean> advInfoBeanList,boolean isUpdateImg );
int updateAdvInfoUserState(AdvInfoBean advInfoBean);
int saveAdvBrandDz(List<AdvBrandDzBean> list );
int saveAdvAreaDz(List<AdvAreaDzBean> list );
int deleteAdvBrandDz(String advNum );
int deleteAdvAreaDz(String advNum  );
int deleteAdvInfo(String advNum);
int verifyAdvInfo(Map<String, String> map);

boolean sort(String[] advNums) ;

Page queryAdvInfoListOnConsole(Map<String, String> map);

}
