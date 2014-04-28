package com.xwtech.uomp.base.service.content;

import java.util.List;

import com.xwtech.uomp.base.pojo.content.ContentAttachmentBean;

public interface IContentAttachmentService {
	 List saveContentAttachment(  List<ContentAttachmentBean> list , String contentDocPkid);
	  void deleteAttachByDocId(String docId);
	  void updateAttachByDocId(  List<ContentAttachmentBean> list  );
}
