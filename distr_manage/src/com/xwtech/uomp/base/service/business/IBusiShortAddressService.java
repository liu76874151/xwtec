package com.xwtech.uomp.base.service.business;

import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.business.BusiShortAddressBean;

public interface IBusiShortAddressService {
Page queryBusiShortAdd(Map<String, String> map);
Page queryBusiShortChannel(Map<String, String> map);
void saveBusiShortAdd(BusiShortAddressBean busiShortAddressBean);
void updateBusiShortAdd(BusiShortAddressBean busiShortAddressBean);
BusiShortAddressBean findOnebusiShortAdd(String shortId);
void updateBusiShortAddState(Map<String, String> map);
void deleteBusiShortAdd(String shortId);
}
