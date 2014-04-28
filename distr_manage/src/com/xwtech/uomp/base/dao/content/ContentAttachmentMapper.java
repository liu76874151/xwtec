package com.xwtech.uomp.base.dao.content;

import java.util.List;

import com.xwtech.uomp.base.pojo.content.ContentAttachmentBean;



public interface ContentAttachmentMapper {


    int saveContentAttachment(ContentAttachmentBean record);
    
    void deleteAttachByDocId(String docId);
    void updateAttachByDocId(ContentAttachmentBean contentAttachmentBean);


}