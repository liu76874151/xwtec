package com.xwtech.uomp.base.pojo;

import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

public class MenuInfoBean {

    private String sysNum;

    private String sysTitle;

    private TreeNode treeNode;

    public String getSysNum() {
        return sysNum;
    }

    public void setSysNum(String sysNum) {
        this.sysNum = sysNum;
    }

    public String getSysTitle() {
        return sysTitle;
    }

    public void setSysTitle(String sysTitle) {
        this.sysTitle = sysTitle;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

}
