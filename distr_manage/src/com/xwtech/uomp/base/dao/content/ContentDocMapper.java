package com.xwtech.uomp.base.dao.content;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.content.ContentDocBean;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-15
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public interface ContentDocMapper {
    List<Map<String, Object>> queryContentDocsByChanIds(Map<String, Object> map);
    
    int queryContentDocsCount(Map<String, String> map);
    
    List<ContentDocBean> queryContentDocs(Map<String, String> map);
    
    int saveContentDoc(ContentDocBean contentDocBean);
    
    ContentDocBean queryOneContentDoc (Map<String, String> map);
    
    int updateBydocId(ContentDocBean contentDocBean);
    
    void updateStateBydocId(Map<String, String> map);
    
    void deleteBydocId(String id);
}
