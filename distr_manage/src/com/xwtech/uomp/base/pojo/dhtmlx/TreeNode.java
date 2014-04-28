package com.xwtech.uomp.base.pojo.dhtmlx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode implements Serializable{
    private static final long serialVersionUID = 7181904477002570357L;
    //节点ID
    private String id;
    //节点文本
    private String text;
    //父节点
    private String pid;
    //是否末级
    private String mj;
    //自定义的值
    private List<Map<String, Object>> userdata;
    //子节点
    private List<TreeNode> item;

    /**
     * 创建一个实例
     *
     * @return
     */
    public static TreeNode newInstance() {
        TreeNode treeNode = new TreeNode();
        treeNode.setUserdata(new ArrayList<Map<String, Object>>());
        treeNode.setItem(new ArrayList<TreeNode>());
        return treeNode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Map<String, Object>> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<Map<String, Object>> userdata) {
        this.userdata = userdata;
    }

    public List<TreeNode> getItem() {
        return item;
    }

    public void setItem(List<TreeNode> item) {
        this.item = item;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }
}
