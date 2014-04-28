package com.xwtech.uomp.base.pojo.dhtmlx;

import java.util.ArrayList;
import java.util.Map;

public class CheckedTreeNode extends TreeNode {
    /**
     * 是否选中 true,false
     */
    private String checked;

    /**
     * 创建一个实例
     *
     * @return
     */
    public static CheckedTreeNode newInstance() {
        CheckedTreeNode treeNode = new CheckedTreeNode();
        treeNode.setUserdata(new ArrayList<Map<String, Object>>());
        treeNode.setItem(new ArrayList<TreeNode>());
        return treeNode;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
