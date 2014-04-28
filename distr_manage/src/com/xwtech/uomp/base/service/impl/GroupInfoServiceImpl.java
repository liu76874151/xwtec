package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.GroupInfoMapper;
import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.GroupInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IGroupInfoService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("groupInfoService")
public class GroupInfoServiceImpl implements IGroupInfoService {
    protected static final Logger log = Logger.getLogger(GroupInfoServiceImpl.class);

    @Autowired
    GroupInfoMapper groupInfoMapper;

    public List<TreeNode> queryGroupTree() {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = groupInfoMapper.queryGroupInfo();
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
            log.info(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }

    public List<GroupInfoBean> queryGroupInfo(String userName, String userType) {
        return null;
    }

    public GroupInfoBean viewGroupInfo(String groupId) {
        GroupInfoBean bean = null;
        try {
            List<GroupInfoBean> groupInfo = groupInfoMapper.queryGroupInfoById(groupId);
            if (groupInfo != null && groupInfo.size() > 0) {
                bean = groupInfo.get(0);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            bean = null;
        }
        return bean;
    }

    /**
     * 查询第一级级别信息
     *
     * @return
     */
    public String queryFirstJbNum() {
        String strResult = "";
        try {
            strResult = groupInfoMapper.queryFirstJbNum();
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            strResult = "";
        }
        return strResult;
    }

    /**
     * 查询所有区市信息
     *
     * @return
     */
    public List<CityInfoBean> queryAreaInfo() {
        List<CityInfoBean> cityInfoList = null;
        try {
            cityInfoList = groupInfoMapper.queryAreaInfo();
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return cityInfoList;
    }

    /**
     * 根据areaNum查询区市信息
     *
     * @return
     */
    public List<CityInfoBean> queryAreaInfoByNum(String areaNum) {
        List<CityInfoBean> cityInfoList = null;
        try {
            cityInfoList = groupInfoMapper.queryAreaInfo();//.queryAreaInfoByNum(areaNum);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return cityInfoList;
    }

    /**
     * 根据子级别编码查父级别编码
     *
     * @param subJbNum
     * @return
     */
    public String querySubJbNumFromSup(String supJbNum) {
        String strResult = "";
        try {
            strResult = groupInfoMapper.querySubJbNumFromSup(supJbNum);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            strResult = "";
        }
        return strResult;
    }

    /**
     * 保存用户组信息
     *
     * @param groupInfo
     * @return
     */
    public int insertGroupInfo(GroupInfoBean groupInfo) {
        int intResult = 0;
        try {
            groupInfoMapper.insertGroupInfo(groupInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 根据Id更新末级信息
     *
     * @param groupId
     * @return
     */
    public int updateMjById(String supGroupId) {
        int intResult = 0;
        try {
            groupInfoMapper.updateMjById(supGroupId);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 删除用户组信息
     *
     * @param groupId
     * @return
     */
    public int deleteGroupInfo(String groupId) {
        int intResult = 0;
        try {
            groupInfoMapper.deleteGroupInfo(groupId);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 查询同一级别的记录个数
     *
     * @param jbNum
     * @return
     */
    public int queryEqiLevelCountByJbNum(String jbNum) {
        int intResult = 0;
        try {
            intResult = groupInfoMapper.queryEqiLevelCountByJbNum(jbNum);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = 0;
        }
        return intResult;
    }

    /**
     * 根据级别编号更新末级信息
     *
     * @param jbNum
     * @return
     */
    public int updateMjByJbNum(String substring) {
        int intResult = 0;
        try {
            groupInfoMapper.updateMjByJbNum(substring);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 根据用户组ID删除权限
     *
     * @param groupId
     * @return
     */
    public int deleteQxByGroupId(String groupId) {
        int intResult = 0;
        try {
            groupInfoMapper.deleteQxByGroupId(groupId);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 更新用户组信息
     *
     * @param groupInfo
     * @return
     */
    public int updateGroupInfo(GroupInfoBean groupInfo) {
        int intResult = 0;
        try {
            groupInfoMapper.updateGroupInfo(groupInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            intResult = -1;
        }
        return intResult;
    }

    /**
     * 查询该用户组的级别
     */
    public GroupInfoBean queryGroupJb(String loginName) {
        GroupInfoBean bean = null;
        try {
            List<GroupInfoBean> groupInfo = groupInfoMapper.queryGroupJb(loginName);
            if (groupInfo != null && groupInfo.size() > 0) {
                bean = groupInfo.get(0);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            bean = null;
        }
        return bean;
    }
}
