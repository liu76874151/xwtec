package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.GroupInfoBean;

public interface GroupInfoMapper {

    public List<GroupInfoBean> queryGroupInfoByName(String userName);

    public List<Map<String, Object>> queryGroupInfo();

    /**
     * @param groupId
     * @return List<GroupInfoBean> 返回类型
     * @throws
     * @Title: queryGroupInfoById
     * @Description: 查看用户组详细信息
     */
    public List<GroupInfoBean> queryGroupInfoById(String groupId);

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
     * 根据areaNum查询区市信息
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
    public void insertGroupInfo(GroupInfoBean groupInfo);

    /**
     * 根据Id更新末级信息
     *
     * @param groupId
     * @return
     */
    public void updateMjById(String supGroupId);

    /**
     * 删除用户组信息
     *
     * @param groupId
     * @return
     */
    public void deleteGroupInfo(String groupId);

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
    public void updateMjByJbNum(String substring);

    /**
     * 根据用户组ID删除权限
     *
     * @param groupId
     * @return
     */
    public void deleteQxByGroupId(String groupId);

    /**
     * 更新用户组信息
     *
     * @param groupInfo
     * @return
     */
    public void updateGroupInfo(GroupInfoBean groupInfo);

    /**
     * 查询该用户组的级别
     */
    public List<GroupInfoBean> queryGroupJb(String loginName);

}
