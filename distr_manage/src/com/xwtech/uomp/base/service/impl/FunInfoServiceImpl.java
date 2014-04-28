package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.FunInfoMapper;
import com.xwtech.uomp.base.pojo.admin.FunInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IFunInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author huangfeilong
 * @ClassName: FunInfoServiceImpl
 * @Description: 模块服务实现类
 * @date Mar 6, 2013 8:03:55 PM
 */
@Service("funInfoService")
public class FunInfoServiceImpl implements IFunInfoService {
    protected static final Logger log = Logger.getLogger(FunInfoServiceImpl.class);

    @Autowired
    FunInfoMapper funInfoMapper;

    /*
     * <p>Title: queryFunTree</p>
     * <p>Description: 查询所有模块信息</p>
     * @return
     * @see com.xwtech.uomp.base.service.IFunInfoService#queryFunTree()
     */
    public Map<String, List<TreeNode>> queryFunTree() {
        Map<String, List<TreeNode>> sysTreeNodeMap = null;
        try {
            List<Map<String, Object>> listResult = funInfoMapper.queryFunInfoList();
            if (listResult == null || listResult.size() <= 0) {
                return sysTreeNodeMap;
            }
            sysTreeNodeMap = new HashMap<String, List<TreeNode>>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = TreeNode.newInstance();
                if (dataMap != null) {
                    String subSystemNum = null;
                    for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                        String key = (String) iter.next();
                        if ("subSysNum".equals(key)) {
                            subSystemNum = (String) dataMap.get(key);
                            if (!sysTreeNodeMap.containsKey(subSystemNum)) {
                                sysTreeNodeMap.put(subSystemNum, new ArrayList<TreeNode>());
                            }

                        } else if ("id".equals(key)) {
                            node.setId((String) dataMap.get(key));
                        } else if ("text".equals(key)) {
                            node.setText((String) dataMap.get(key));
                        } else if ("pid".equals(key)) {
                            node.setPid((String) dataMap.get(key));
                        } else if ("mj".equals(key)) {
                            node.setMj(((BigDecimal) dataMap.get(key)).toString());
                        } else {
                            Map<String, Object> tmpMap = new HashMap<String, Object>();
                            tmpMap.put("name", key);
                            tmpMap.put("content", dataMap.get(key));
                            node.getUserdata().add(tmpMap);
                        }
                    }
                    ((List<TreeNode>) sysTreeNodeMap.get(subSystemNum)).add(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            sysTreeNodeMap = null;
        }

        return sysTreeNodeMap;
    }

    /*
     * <p>Title: queryFunInfoById</p>
     * <p>Description: 根据Id查询模块信息</p>
     * @param funId
     * @return
     * @see com.xwtech.uomp.base.service.IFunInfoService#queryFunInfoById(java.lang.String)
     */
    public FunInfoBean queryFunInfoById(String funId) {
        FunInfoBean funInfoBean = null;
        try {
            List<FunInfoBean> funInfo = funInfoMapper.queryFunInfoById(funId);
            if (funInfo != null && funInfo.size() > 0) {
                funInfoBean = funInfo.get(0);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            funInfoBean = null;
        }

        return funInfoBean;
    }

    /*
     * <p>Title: modFunInfo</p>
     * <p>Description: 更新模块信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#modFunInfo(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void modFunInfo(FunInfoBean funInfoBean) {
        funInfoMapper.modFunInfo(funInfoBean);
    }

    /*
     * <p>Title: deleteFunInfo</p>
     * <p>Description: 删除模块信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#deleteFunInfo(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void deleteFunInfo(FunInfoBean funInfoBean) {
        funInfoMapper.deleteFunInfo(funInfoBean);
    }

    /*
     * <p>Title: addFunInfo</p>
     * <p>Description: 增加模块信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#addFunInfo(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void addFunInfo(FunInfoBean funInfoBean) {
        funInfoMapper.addFunInfo(funInfoBean);
    }

    /*
     * <p>Title: queryJbNumBySup</p>
     * <p>Description: 查询父模块下子模块的个数</p>
     * @param funInfoBean
     * @return
     * @see com.xwtech.uomp.base.service.IFunInfoService#queryJbNumBySup(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public int queryJbNumBySup(FunInfoBean funInfoBean) {
        return funInfoMapper.queryJbNumBySup(funInfoBean);
    }

    /*
     * <p>Title: updateMjById</p>
     * <p>Description: 更新父模块末级信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#updateMjById(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void updateMjById(FunInfoBean funInfoBean) {
        funInfoMapper.updateMjById(funInfoBean);
    }

    /*
     * <p>Title: updateMjToOne</p>
     * <p>Description: 更新父模块末级信息为1</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#updateMjToOne(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void updateMjToOne(FunInfoBean funInfoBean) {
        funInfoMapper.updateMjToOne(funInfoBean);
    }

    /*
     * <p>Title: querySupJbNum</p>
     * <p>Description: 查询模块级别</p>
     * @param funInfoBean
     * @return
     * @see com.xwtech.uomp.base.service.IFunInfoService#querySupJbNum(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public String querySupJbNum(FunInfoBean funInfoBean) {
        return funInfoMapper.querySupJbNum(funInfoBean);
    }

    /*
     * <p>Title: deleteQxInfo</p>
     * <p>Description: 删除权限信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#deleteQxInfo(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void deleteQxInfo(FunInfoBean funInfoBean) {
        funInfoMapper.deleteQxInfo(funInfoBean);
    }

    /*
     * <p>Title: deleteMouldUser</p>
     * <p>Description: 删除MouldUser信息</p>
     * @param funInfoBean
     * @see com.xwtech.uomp.base.service.IFunInfoService#deleteMouldUser(com.xwtech.uomp.base.pojo.admin.FunInfoBean)
     */
    public void deleteMouldUser(FunInfoBean funInfoBean) {
        funInfoMapper.deleteMouldUser(funInfoBean);
    }
}
