package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

public class GroupInfoBean extends OrderInfoBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 717464608572605704L;

    public GroupInfoBean() {
    }

    public GroupInfoBean(String jbNum, String parentId, String groupName, int jb) {
        this.jbNum = jbNum;
        // this.parentId = parentId;
        this.groupName = groupName;
        this.jb = jb;

    }

    public static final GroupInfoBean node0 = new GroupInfoBean("0", "-1", "", 0);

    /**
     * 用户组编码
     */
    private String groupId;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组所属地市
     */
    private String groupArea;

    /**
     * 备注
     */
    private String bz;

    /**
     * 用户组类型
     */
    private String groupType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupArea() {
        return groupArea;
    }

    public void setGroupArea(String groupArea) {
        this.groupArea = groupArea;
    }

}
