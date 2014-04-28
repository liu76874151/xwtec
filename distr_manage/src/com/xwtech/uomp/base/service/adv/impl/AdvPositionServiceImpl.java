package com.xwtech.uomp.base.service.adv.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.adv.AdvPositionMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.adv.AdvPositionBean;
import com.xwtech.uomp.base.service.adv.IAdvPositionService;
import com.xwtech.uomp.base.service.demo.impl.StudentServiceImpl;
@Service("advPositionService")
public class AdvPositionServiceImpl implements IAdvPositionService{
    protected static final Logger log = Logger.getLogger(StudentServiceImpl.class);

    @Autowired
    AdvPositionMapper advPositionMapper;
    
	public void saveAdvPosition(AdvPositionBean advPositionBean) {
		advPositionMapper.insertSelective(advPositionBean);
		
	}
	public Page queryAdvPositionList(Map<String, String> paramMap) {
		List<AdvPositionBean> list=advPositionMapper.queryAdvPosition(paramMap);
		for(AdvPositionBean advPositionBean:list){
			String state=advPositionBean.getState();
			String stateLink="";
			if(null!=state&&"0".equals(state)){
				stateLink = "<a href='javascript:component.updateState(1)' title='点击更新为停用'>已启用<img src='../../../resource/img/toggle_disabled.gif'/></a>";
			}else{
				stateLink = "<a href='javascript:component.updateState(0);' title='点击更新为启用'>已停用<img src='../../../resource/img/toggle_enabled.gif'/></a>";
			}
			advPositionBean.setStateLink(stateLink);
		}
		int count=advPositionMapper.queryAdvPositionCount(paramMap);
		Page page = new Page();
	    page.setRecords(list);
	    page.setTotalRecord(count);
		return page;
	}
	public int queryAdvPositionListCount(Map<String, String> param) {
	
		
		return 0;
	}
	public void deleteAdvPosition(String key) {
		advPositionMapper.deleteByPrimaryKey(key);
	}
	public AdvPositionBean findOneAdvPositionBean(String pkid) {
		
		return advPositionMapper.findOneAdvPositionBean(pkid);
	}
	public int updateByPrimaryKeySelective(Map<String, String> paramMap) {
		return advPositionMapper.updateByPrimaryKeySelective(paramMap);
	}

}
