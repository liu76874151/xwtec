package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.SubSystemMapper;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.ISubSystemService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SubSystemServiceImpl
 * @Description: 子系统管理
 * @date 2013-2-20 下午12:54:08
 */
@Service("subSystemService")
public class SubSystemServiceImpl implements ISubSystemService {
    protected static final Logger log = Logger.getLogger(SubSystemServiceImpl.class);

    @Autowired
    SubSystemMapper subSystemMapper;

    /**
     * @param String
     * @return List<SubSystemBean>
     * @description 根据子掩码查询子系统
     */
    public List<SubSystemBean> querySubSystemByNum(String sysNum) {
        return subSystemMapper.querySubsystemByNum(sysNum);
    }

    /**
     * @param SubSystemBean
     * @return
     * @description 添加子系统
     */
    public int addSubSystem(SubSystemBean subSystemBean) {
        int intResult = 0;
        try {
            subSystemMapper.addSubSystem(subSystemBean);
        } catch (Exception be) {
            log.error(be.getMessage());
            be.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * @param String
     * @return
     * @description 删除子系统
     */
    public int deleteSubSystem(String sysNum) {
        int intResult = 0;
        try {
            subSystemMapper.deleteSubSystem(sysNum);
        } catch (Exception be) {
            log.error(be.getMessage());
            be.printStackTrace();
            intResult = -1;
        }
        return intResult;

    }

    /**
     * @param
     * @return
     * @description 查询所有子系统
     */
    public List<SubSystemBean> querySubSystemAll() {
        return subSystemMapper.querySubsystemAll();
    }

    /**
     * 查询所有子系统信息
     *
     * @return
     * @throws BaseException
     */
    public List<TreeNode> querySubSystemTree() {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = subSystemMapper.querySubSystemList();
            if (listResult == null || listResult.size() <= 0) {
                return treeNodeList;
            }
            treeNodeList = new ArrayList<TreeNode>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = DhtmlTreeUtil.getTreeNode(dataMap);
                treeNodeList.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }

    /**
     * @param SubSystemBean subSystemBean
     * @return
     * @description 根据sysNum修改子系统
     */
    public int updateSubSystem(SubSystemBean subSystemBean) {
        int intResult = 0;
        try {
            subSystemMapper.updateSubSystem(subSystemBean);
        } catch (Exception be) {
            log.error(be.getMessage());
            be.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    public int querySubsystemIshave(String sysNum) {

        return subSystemMapper.querySubsystemIshave(sysNum);

    }


}
