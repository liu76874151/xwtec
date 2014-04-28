package com.xwtech.uomp.base.service;

import java.util.List;

import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.GroupInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

public interface IGroupInfoService {

    /**
     * 查询所有用户组信息
     *
     * @return
     */
    public List<TreeNode> queryGroupTree();

    /**
     * 根据用户类型查询所有用户组信息
     *
     * @return
     */
    public List<GroupInfoBean> queryGroupInfo(String userName, String userType);

    /**
     * 查看用户组详细信息
     *
     * @param groupId
     * @return
     */
    public GroupInfoBean viewGroupInfo(String groupId);

    /**
     * 查询第一级级别信息
     *
     * @return
     */
    public String queryFirstJbNum();

    /**
     * 查询所有区市信息
     *
     * @return
     */
    public List<CityInfoBean> queryAreaInfo();

    /**
     * 根据areaNum查询所有区市信息
     *
     * @return
     */
    public List<CityInfoBean> queryAreaInfoByNum(String areaNum);

    /**
     * 根据子级别编码查父级别编码
     *
     * @param subJbNum
     * @return
     */
    public String querySubJbNumFromSup(String supJbNum);

    /**
     * 保存用户组信息
     *
     * @param groupInfo
     * @return
     */
    public int insertGroupInfo(GroupInfoBean groupInfo);

    /**
     * 根据Id更新末级信息
     *
     * @param groupId
     * @return
     */
    public int updateMjById(String supGroupId);

    /**
     * 删除用户组信息
     *
     * @param groupId
     * @return
     */
    public int deleteGroupInfo(String groupId);

    /**
     * 查询同一级别的记录个数
     *
     * @param jbNum
     * @return
     */
    public int queryEqiLevelCountByJbNum(String jbNum);

    /**
     * 根据级别编号更新末级信息
     *
     * @param jbNum
     * @return
     */
    public int updateMjByJbNum(String substring);

    /**
     * 根据用户组ID删除权限
     *
     * @param groupId
     * @return
     */
    public int deleteQxByGroupId(String groupId);

    /**
     * 更新用户组信息
     *
     * @param groupInfo
     * @return
     */
    public int updateGroupInfo(GroupInfoBean groupInfo);

    /**
     * 查询该用户组的级别
     */
    public GroupInfoBean queryGroupJb(String loginName);

}
