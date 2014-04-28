package com.xwtech.uomp.base.dao.business;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.business.BusiShortAddressBean;
import com.xwtech.uomp.base.pojo.business.BusiShortChannelBean;




public interface BusiShortAddressMapper {
	List<BusiShortAddressBean> queryBusiShortAdd(Map<String, String> map);
	List<BusiShortChannelBean> queryBusiShortChannel(Map<String, String> map);
	int queryBusiShortAddcount(Map<String, String> map);
	void saveBusiShortAdd(BusiShortAddressBean busiShortAddressBean);
	void updateBusiShortAdd(BusiShortAddressBean busiShortAddressBean);
	void updateBusiShortAddState(Map<String, String> map);
	void deleteBusiShortAdd(String shortId);
	BusiShortAddressBean findOnebusiShortAdd(String shortId);
//    int deleteByPrimaryKey(Long fShortId);
//List<AdvInfoBean> queryAdvInfoList(Map<String, String> map);
//    int insert(BusiShortAddress record);
//
//    int insertSelective(BusiShortAddress record);
//
//    BusiShortAddress selectByPrimaryKey(Long fShortId);
//
//    int updateByPrimaryKeySelective(BusiShortAddress record);
//
//    int updateByPrimaryKey(BusiShortAddress record);
}