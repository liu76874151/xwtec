package com.xwtech.uomp.base.service.content.impl;

import com.xwtech.uomp.base.dao.content.ContentDocMapper;
import com.xwtech.uomp.base.dao.content.IContentDocDao;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.content.ContentAttachmentBean;
import com.xwtech.uomp.base.pojo.content.ContentDocBean;
import com.xwtech.uomp.base.service.content.IContentAttachmentService;
import com.xwtech.uomp.base.service.content.IContentDocService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-15
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
@Service("contentDocService")
public class ContentDocServiceImpl implements IContentDocService {

    @Autowired
    ContentDocMapper contentDocMapper;
	@Autowired
	IContentDocDao contentDocDao ;
	@Autowired
	IContentAttachmentService contentAttachmentService;
	@Value("${fileZQUpload.URL}")
	private String targetURL;

    public List<Map<String, Object>> queryContentDocsByChanIds(String chanIds) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (chanIds != null && !chanIds.isEmpty()) {
            String[] chanId_array = chanIds.split(",");
            param.put("chanIds", chanId_array);
        }

        return contentDocMapper.queryContentDocsByChanIds(param);
    }

	public Page queryContentDocs(Map<String, String> map) {
		List<ContentDocBean> list=contentDocMapper.queryContentDocs(map);
		String stateLink;//TODO(DOC STATE)
		for(ContentDocBean contentDocBean:list){
			stateLink="";
			if("0".equals(contentDocBean.getState())){
				stateLink="新稿&nbsp;<a href='javascript:component.updateState("
									+ contentDocBean.getDocId()
									+ ",2);' ><img alt='' src='../../../resource/img/toggle_enabled.gif' height='10'></a>";
				stateLink+="&nbsp;&nbsp;<a href='javascript:component.updateState("
					+ contentDocBean.getDocId()
					+ ",1);' ><img alt='' src='../../../resource/img/toggle_disabled.gif' height='10'></a>";
			}else if("1".equals(contentDocBean.getState())){
				stateLink="发布&nbsp;<a href='javascript:component.updateState("
					+ contentDocBean.getDocId()
					+ ",2);' ><img alt='' src='../../../resource/img/toggle_disabled.gif' height='10'></a>";
			}else if("2".equals(contentDocBean.getState())){
				stateLink="不可用&nbsp;<a href='javascript:component.updateState("
					+ contentDocBean.getDocId()
					+ ",1);' ><img alt='' src='../../../resource/img/toggle_enabled.gif' height='10'></a>";
			}
			contentDocBean.setStateLink(stateLink);
		}
		int count=contentDocMapper.queryContentDocsCount(map);
		Page page = new Page();
		page.setTotalRecord(count);
		page.setRecords(list);
		return page;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List saveContentDoc(ContentDocBean contentDocBean) {
		List<ContentAttachmentBean> list=contentDocBean.getContentAttachmentBeanList();
		String contentDocPkid=contentDocBean.getDocId();
		if(StringUtils.isNotBlank(contentDocBean.getLogo())){
		contentDocBean.setLogo(targetURL+"/"+contentDocBean.getLogo());
		}
			contentDocMapper.saveContentDoc(contentDocBean);
			return  contentAttachmentService.saveContentAttachment(list, contentDocPkid);
			
		
	}

	public ContentDocBean queryOneContentDoc(Map<String, String> map) {
		return contentDocMapper.queryOneContentDoc(map);
	}
 
	@Transactional(propagation=Propagation.REQUIRED)
	public List updateBydocId(ContentDocBean contentDocBean,boolean isUpdateImg) {
	if(StringUtils.isNotBlank(contentDocBean.getLogo())){
		 contentDocBean.setLogo(targetURL+"/"+contentDocBean.getLogo());}
		 List list=new ArrayList();
		 if(isUpdateImg){
		 contentAttachmentService.deleteAttachByDocId(contentDocBean.getDocId());
		 list=contentAttachmentService.saveContentAttachment(contentDocBean.getContentAttachmentBeanList(), contentDocBean.getDocId());
		 }
		 contentDocMapper.updateBydocId(contentDocBean);
		  return list;
	}
	public void updateStateBydocId(Map<String, String> map) {
		 contentDocMapper.updateStateBydocId(map);
	}

	public boolean sort(String[] ContentDocPkids, String ContentDocPkid) {
		return contentDocDao.sort(ContentDocPkids, ContentDocPkid);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteBydocId(String ContentDocPkid) {
		contentDocMapper.deleteBydocId(ContentDocPkid);
		contentAttachmentService.deleteAttachByDocId(ContentDocPkid);
	}
}
