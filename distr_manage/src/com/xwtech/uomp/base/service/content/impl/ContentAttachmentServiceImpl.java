package com.xwtech.uomp.base.service.content.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.xwtech.uomp.base.dao.content.ContentAttachmentMapper;
import com.xwtech.uomp.base.pojo.content.ContentAttachmentBean;
import com.xwtech.uomp.base.service.content.IContentAttachmentService;
import com.xwtech.uomp.base.service.sequence.ISequenceService;
@Service("contentAttachmentService")
public class ContentAttachmentServiceImpl implements IContentAttachmentService {
	@Autowired
	ISequenceService sequenceService;
	@Autowired
	ContentAttachmentMapper contentAttachmentMapper;
	@Value("${fileZQUpload.URL}")
	private String targetURL;
	@Transactional(propagation=Propagation.REQUIRED)
	public List saveContentAttachment(  List<ContentAttachmentBean> list , String contentDocPkid){
		List pkidList=new ArrayList();
		String tempPkid="";
		for(ContentAttachmentBean contentAttachmentBean:list){
			tempPkid=sequenceService.getSequence("T_CONTENT_ATTACHMENT_PKID_SEQ");
			pkidList.add(tempPkid);
			contentAttachmentBean.setAttachId(tempPkid);
			contentAttachmentBean.setDocId(contentDocPkid);
			if(StringUtils.isNotBlank(contentAttachmentBean.getAttachDir())){
				contentAttachmentBean.setAttachDir(targetURL+"/"+contentAttachmentBean.getAttachDir());
				
			}
			if(StringUtils.isNotBlank(contentAttachmentBean.getInterfaceDir())){
				contentAttachmentBean.setInterfaceDir(targetURL+"/"+contentAttachmentBean.getInterfaceDir());
				
			}
			contentAttachmentMapper.saveContentAttachment(contentAttachmentBean);
		}
		return pkidList;

	}
	public void deleteAttachByDocId(String docId) {
		contentAttachmentMapper.deleteAttachByDocId(docId);
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAttachByDocId(List<ContentAttachmentBean> list) {
		for(ContentAttachmentBean contentAttachmentBean:list){
			
			if(StringUtils.isNotBlank(contentAttachmentBean.getInterfaceDir())){
				contentAttachmentBean.setInterfaceDir(targetURL+"/"+contentAttachmentBean.getInterfaceDir());
				
			}
			if(StringUtils.isNotBlank(contentAttachmentBean.getAttachDir())){
				contentAttachmentBean.setAttachDir(targetURL+"/"+contentAttachmentBean.getAttachDir());
				
			}
			contentAttachmentMapper.updateAttachByDocId(contentAttachmentBean);
			
		}
		
	}

}
