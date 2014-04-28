package com.xwtech.uomp.base.service.content;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.content.ContentDocBean;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-15
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public interface IContentDocService {

    List<Map<String,Object>> queryContentDocsByChanIds(String chanIds);
    
    Page queryContentDocs(Map<String, String> map);
    
    List saveContentDoc(ContentDocBean contentDocBean);
    
    ContentDocBean queryOneContentDoc (Map<String, String> map);
    
    List updateBydocId(ContentDocBean contentDocBean,boolean isUpdateImg);
    
    void updateStateBydocId(Map<String, String> map);
    boolean sort(String[] ContentDocPkids, String ContentDocPkid);
    
     void deleteBydocId(String ContentDocPkid);
    	
}
