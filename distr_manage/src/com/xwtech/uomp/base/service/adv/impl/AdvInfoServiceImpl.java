package com.xwtech.uomp.base.service.adv.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xwtech.uomp.base.dao.adv.AdvAreaDzMapper;
import com.xwtech.uomp.base.dao.adv.AdvBrandDzMapper;
import com.xwtech.uomp.base.dao.adv.AdvInfoMapper;
import com.xwtech.uomp.base.dao.adv.IAdvInfoDao;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.adv.AdvAreaDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvBrandDzBean;
import com.xwtech.uomp.base.pojo.adv.AdvInfoBean;
import com.xwtech.uomp.base.service.adv.IAdvinfoService;

@Service("advInfoService")
public class AdvInfoServiceImpl implements IAdvinfoService {
	@Autowired
	AdvInfoMapper advInfoMapper;
	@Autowired
	AdvBrandDzMapper advBrandDzMapper;
	@Autowired
	AdvAreaDzMapper advAreaDzMapper;
	@Autowired
    IAdvInfoDao  advInfoDao;
	public Page queryAdvInfoListOrderShowXh(Map<String, String> map) {
		List<AdvInfoBean> list = advInfoMapper.queryAdvInfoListOrderShowXh(map);
		int count = advInfoMapper.queryAdvInfoCount(map);
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	public Page queryAdvInfoList(Map<String, String> map) {
		List<AdvInfoBean> list = advInfoMapper.queryAdvInfoList(map);
		
			
			for (AdvInfoBean advInfoBean : list) {
				StringBuffer advAreaNumBuffer = new StringBuffer();
				StringBuffer advAreaNameBuffer = new StringBuffer();
				StringBuffer AdvBrandNumBuffer = new StringBuffer();
				StringBuffer AdvBrandNameBuffer = new StringBuffer();
				String useState=advInfoBean.getUseState();
				String auditState=advInfoBean.getAuditState();
				String advNum=advInfoBean.getAdvNum();
				String auditStateLink="";
				String useStateLink="";
				if("0".equals(auditState)){
					auditStateLink="待审核&nbsp;<a href='javascript:component.updateAuditState(2,\""+advNum+"\")'><img src='../../resource/img/toggle_disabled.gif'/></a>"+
									"&nbsp;&nbsp;<a href='javascript:component.updateAuditState(1,\""+advNum+"\")'><img src='../../resource/img/toggle_enabled.gif'/></a>";
					
					
				}else if("1".equals(auditState)){
					auditStateLink="审核通过&nbsp;<a href='javascript:component.updateAuditState(2,\""+advNum+"\")'><img src='../../resource/img/toggle_disabled.gif'/></a>";
					}else if("2".equals(auditState)){
					auditStateLink="审核不通过&nbsp;<a href='javascript:component.updateAuditState(1,\""+advNum+"\")'><img src='../../resource/img/toggle_enabled.gif'/></a>";
					
				}
				if("1".equals(useState)){
					useStateLink="启用&nbsp;<a href='javascript:component.updateUserState(0,\""+advNum+"\")'><img src='../../resource/img/toggle_disabled.gif'/></a>";
				}else{
					useStateLink="停用&nbsp;<a href='javascript:component.updateUserState(1,\""+advNum+"\")'><img src='../../resource/img/toggle_enabled.gif'/></a>";
				}
				advInfoBean.setUseStateLink(useStateLink);
				advInfoBean.setAuditStateLink(auditStateLink);
				advInfoBean.setPositionName(advInfoBean.getAdvPositionBean()
						.getPositionName());
				List<AdvAreaDzBean> advAreaDzList = advInfoBean.getAdvAreaDzList();
				List<AdvBrandDzBean> advBrandDzList = advInfoBean
						.getAdvBrandDzList();
				
				for (AdvAreaDzBean AdvAreaDzBean : advAreaDzList) {
					advAreaNumBuffer.append(AdvAreaDzBean.getAdvAreaNum() + ",");
					advAreaNameBuffer.append(AdvAreaDzBean.getAdvAreaName() + ",");
				}
				if(advAreaNumBuffer.toString().length()>0){
				advInfoBean.setAdvAreaNum(advAreaNumBuffer.toString().substring(0,
						advAreaNumBuffer.toString().length() - 1));}
				if(advAreaNameBuffer.toString().length()>0){
				advInfoBean.setAdvAreaName(advAreaNameBuffer.toString().substring(0,
						advAreaNameBuffer.toString().length() - 1));}

				for (AdvBrandDzBean advBrandDzBean : advBrandDzList) {
					AdvBrandNumBuffer.append(advBrandDzBean.getAdvBrandNum() + ",");
					AdvBrandNameBuffer.append(advBrandDzBean.getAdvBrandName() + ",");
				}
				if(AdvBrandNumBuffer.toString().length()>0){
				advInfoBean.setAdvBrandNum(AdvBrandNumBuffer.toString().substring(
						0, AdvBrandNumBuffer.toString().length() - 1));}
				if(AdvBrandNameBuffer.toString().length()>0){
				advInfoBean.setAdvBrandName(AdvBrandNameBuffer.toString().substring(
				0, AdvBrandNameBuffer.toString().length() - 1));}

			}
		
		int count = advInfoMapper.queryAdvInfoCount(map);
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	
	/**
	 * 控制台广告审核
	 * 创建日期：2014-1-14上午10:36:15
	 * 修改日期：
	 * 作者：zhanglu
	 * @param:
	 * @return:Page
	 */
	public Page queryAdvInfoListOnConsole(Map<String, String> map) {
		List<AdvInfoBean> list = advInfoMapper.queryAdvInfoList(map);
		
			
			for (AdvInfoBean advInfoBean : list) {
				StringBuffer advAreaNumBuffer = new StringBuffer();
				StringBuffer advAreaNameBuffer = new StringBuffer();
				StringBuffer AdvBrandNumBuffer = new StringBuffer();
				StringBuffer AdvBrandNameBuffer = new StringBuffer();
				String useState=advInfoBean.getUseState();
				String auditState=advInfoBean.getAuditState();
				String advNum=advInfoBean.getAdvNum();
				String auditStateLink="";
				String useStateLink="";
				if("0".equals(auditState)){
					auditStateLink="<a href='javascript:component.updateAuditState(2,\""+advNum+"\")'><img src='../resource/img/toggle_disabled.gif'/></a>"+
									"<a href='javascript:component.updateAuditState(1,\""+advNum+"\")'><img src='../resource/img/toggle_enabled.gif'/></a>";
					
					
				}else if("1".equals(auditState)){
					auditStateLink="<a href='javascript:component.updateAuditState(2,\""+advNum+"\")'><img src='../resource/img/toggle_disabled.gif'/></a>";
					}else if("2".equals(auditState)){
					auditStateLink="<a href='javascript:component.updateAuditState(1,\""+advNum+"\")'><img src='../resource/img/toggle_enabled.gif'/></a>";
					
				}
				if("1".equals(useState)){
					useStateLink="<a href='javascript:component.updateUserState(0,\""+advNum+"\")'><img src='../resource/img/toggle_disabled.gif'/></a>";
				}else{
					useStateLink="<a href='javascript:component.updateUserState(1,\""+advNum+"\")'><img src='../resource/img/toggle_enabled.gif'/></a>";
				}
				advInfoBean.setUseStateLink(useStateLink);
				advInfoBean.setAuditStateLink(auditStateLink);
				advInfoBean.setPositionName(advInfoBean.getAdvPositionBean()
						.getPositionName());
				List<AdvAreaDzBean> advAreaDzList = advInfoBean.getAdvAreaDzList();
				List<AdvBrandDzBean> advBrandDzList = advInfoBean
						.getAdvBrandDzList();
				
				for (AdvAreaDzBean AdvAreaDzBean : advAreaDzList) {
					advAreaNumBuffer.append(AdvAreaDzBean.getAdvAreaNum() + ",");
					advAreaNameBuffer.append(AdvAreaDzBean.getAdvAreaName() + ",");
				}
				if(advAreaNumBuffer.toString().length()>0){
				advInfoBean.setAdvAreaNum(advAreaNumBuffer.toString().substring(0,
						advAreaNumBuffer.toString().length() - 1));}
				if(advAreaNameBuffer.toString().length()>0){
				advInfoBean.setAdvAreaName(advAreaNameBuffer.toString().substring(0,
						advAreaNameBuffer.toString().length() - 1));}

				for (AdvBrandDzBean advBrandDzBean : advBrandDzList) {
					AdvBrandNumBuffer.append(advBrandDzBean.getAdvBrandNum() + ",");
					AdvBrandNameBuffer.append(advBrandDzBean.getAdvBrandName() + ",");
				}
				if(AdvBrandNumBuffer.toString().length()>0){
				advInfoBean.setAdvBrandNum(AdvBrandNumBuffer.toString().substring(
						0, AdvBrandNumBuffer.toString().length() - 1));}
				if(AdvBrandNameBuffer.toString().length()>0){
				advInfoBean.setAdvBrandName(AdvBrandNameBuffer.toString().substring(
				0, AdvBrandNameBuffer.toString().length() - 1));}
				
				String openUrl = advInfoBean.getAdvName();
				if(openUrl.length() > 10){
					openUrl = openUrl.substring(0,9) + "...";
				}
				openUrl = "<a href='javascript:component.openWinAdv(\""+advInfoBean.getAdvNum()+"\")'>" + openUrl + "</a>";
				advInfoBean.setAdvName(openUrl);
				
			}
		
		int count = advInfoMapper.queryAdvInfoCount(map);
		Page page = new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}

	public AdvInfoBean findOneAdvInfoBean(Map<String, String> map) {
		return advInfoMapper.findOneAdvInfoBean(map);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveAdvInfo(List<AdvInfoBean> advInfoBeanList) {
			for(AdvInfoBean advInfoBean:advInfoBeanList){
				List<AdvBrandDzBean> advBrandDzList=advInfoBean.getAdvBrandDzList();
				List<AdvAreaDzBean> AdvAreaDzBeanList=advInfoBean.getAdvAreaDzList();
					advInfoMapper.saveAdvInfo(advInfoBean);
					saveAdvBrandDz(advBrandDzList);
					saveAdvAreaDz(AdvAreaDzBeanList);
			}
		return 1;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveAdvBrandDz(List<AdvBrandDzBean> list) {
			for (AdvBrandDzBean advBrandDzBean:list) {
				advBrandDzMapper.saveAdvBrandDz(advBrandDzBean);
			}
		return 1;
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveAdvAreaDz(List<AdvAreaDzBean> list) {
			for(AdvAreaDzBean advAreaDzBean:list){
				advAreaDzMapper.saveAdvAreaDz(advAreaDzBean);
			}
	
		return 1;
	}

	public boolean sort(String[] advNums) {
		return advInfoDao.sort(advNums);
	}
	public int deleteAdvAreaDz(String advNum ) {
			advAreaDzMapper.deleteAdvAreaDz(advNum);
		return 0;
	}
	public int deleteAdvBrandDz(String advNum ) {
			advBrandDzMapper.deleteAdvBrandDz(advNum);
		return 0;
	}
	
	/**
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateAdvInfo(List<AdvInfoBean> advInfoBeanList,boolean isUpdateImg) {
		for(AdvInfoBean advInfoBean:advInfoBeanList){
			advInfoMapper.updateAdvInfo(advInfoBean);
			if(!isUpdateImg){
			List<AdvBrandDzBean> advBrandDzList=advInfoBean.getAdvBrandDzList();
			List<AdvAreaDzBean> AdvAreaDzBeanList=advInfoBean.getAdvAreaDzList();
				deleteAdvAreaDz( advInfoBean.getAdvNum() );
				saveAdvAreaDz(AdvAreaDzBeanList);
				deleteAdvBrandDz(advInfoBean.getAdvNum());
				saveAdvBrandDz(advBrandDzList);
				}
				
		}
		return 1;
	}
	public int updateAdvInfoUserState(AdvInfoBean advInfoBean) {
		return advInfoMapper.updateAdvInfoUserState(advInfoBean);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteAdvInfo(String advNum) {
		advInfoMapper.deleteAdvInfo(advNum);
		deleteAdvBrandDz(advNum);
		deleteAdvAreaDz(advNum);
		return 0;
	}
	public int verifyAdvInfo(Map<String, String> map) {
		advInfoMapper.verifyAdvInfo(map);
		return 0;
	}

	

	

}
