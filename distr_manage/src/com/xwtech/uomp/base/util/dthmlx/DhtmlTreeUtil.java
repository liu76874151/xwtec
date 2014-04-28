package com.xwtech.uomp.base.util.dthmlx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.dhtmlx.CheckedTreeNode;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

public class DhtmlTreeUtil {
    /**
     * 合并树节点列表
     *
     * @param treeNodeList
     * @return modified author fulibin
     *         modified date 2013-04-23
     */
    public static TreeNode mergeTreeNodeList(List<TreeNode> treeNodeList) {
        TreeNode root = TreeNode.newInstance();
        root.setId("0");
        Map<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();
        for (TreeNode node : treeNodeList) {
            nodeMap.put(node.getId(), node);
        }
        for (TreeNode node : treeNodeList) {
            if ("-1".equals(node.getPid())) {
                root.getItem().add(node);
            } else {
                TreeNode parNode = nodeMap.get(node.getPid());
                if (parNode != null) {
                    parNode.getItem().add(node);
                }
            }
        }
        return root;
    }

    /**
     * 合并树节点列表,有子系统时使用
     *
     * @param treeNodeList
     * @return modified author fulibin
     *         modified date 2013-04-23
     */
    public static TreeNode mergeTreeNodeList(List<TreeNode> parTreeList, Map<String, List<TreeNode>> subTreeNodeMap) {
        TreeNode root = new TreeNode();
        root.setId("0");
        root.setItem(parTreeList);
        for (TreeNode node : parTreeList) {
            List<TreeNode> subList = subTreeNodeMap.get(node.getId());
            if (subList != null) {
                Map<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();
                //修改节点的id和pid,加上最上层的id
                for (TreeNode subNode : subList) {
                    subNode.setId(node.getId() + subNode.getId());
                    if (!"-1".equals(subNode.getPid())) {
                        subNode.setPid(node.getId() + subNode.getPid());
                    }
                    nodeMap.put(subNode.getId(), subNode);
                }
                for (TreeNode treeNode : subList) {
                    if ("-1".equals(treeNode.getPid())) {
                        node.getItem().add(treeNode);
                    } else {
                        TreeNode parNode = nodeMap.get(treeNode.getPid());
                        if (parNode != null) {
                            parNode.getItem().add(treeNode);
                        }
                    }
                }
            }
        }
        return root;
    }

    /**
     * 合并子节点
     *
     * @param treeNodeList
     * @param parNode
     */
//    private static void mergeSubTreeNodes(List<TreeNode> treeNodeList, TreeNode parNode)
//    {
//        for (int i = 0; i < treeNodeList.size(); i++)
//        {
//            TreeNode node = (TreeNode) treeNodeList.get(i);
//            if (node.getPid().equals(parNode.getId()))
//            {
//                parNode.getItem().add(node);
//                if (node.getMj().equals("0"))
//                {
//                    mergeSubTreeNodes(treeNodeList, node);
//                }
//            }
//        }
//    }

    /**
     * 合并树节点列表，自定义根节点，该根节点显示
     *
     * @param treeNodeList
     * @param titleNode
     * @return
     */
    public static TreeNode mergeTreeNodeList(List<TreeNode> treeNodeList, TreeNode titleNode) {
        List<TreeNode> mergeLst = new ArrayList<TreeNode>();
        if (treeNodeList != null) {
            Map<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();
            for (TreeNode node : treeNodeList) {
                node.setId(titleNode.getId() + node.getId());
                if (!"-1".equals(node.getPid())) {
                    node.setPid(titleNode.getId() + node.getPid());
                }
                nodeMap.put(node.getId(), node);
            }
            for (TreeNode treeNode : treeNodeList) {
                if ("-1".equals(treeNode.getPid())) {
                    mergeLst.add(treeNode);
                } else {
                    TreeNode parNode = nodeMap.get(treeNode.getPid());
                    if (parNode != null) {
                        parNode.getItem().add(treeNode);
                    }
                }
            }
            titleNode.setItem(mergeLst);
        }

        TreeNode root = TreeNode.newInstance();
        root.setId("0");
        root.getItem().add(titleNode);
        return root;
    }

    public static TreeNode getTreeNode(Map<String, Object> dataMap) {
        TreeNode node = TreeNode.newInstance();
        if (dataMap != null) {
            for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                if ("id".equals(key)) {
                    node.setId((String) dataMap.get(key));
                } else if ("text".equals(key)) {
                    node.setText((String) dataMap.get(key));
                } else if ("pid".equals(key)) {
                    node.setPid((String) dataMap.get(key));
                } else if ("mj".equals(key)) {
                    node.setMj(((BigDecimal) dataMap.get(key)).toString());

                    // 末级需要使用-用户组
                    Map<String, Object> tmpMap = new HashMap<String, Object>();
                    tmpMap.put("name", key);
                    tmpMap.put("content", dataMap.get(key));
                    node.getUserdata().add(tmpMap);
                } else {
                    Map<String, Object> tmpMap = new HashMap<String, Object>();
                    tmpMap.put("name", key);
                    tmpMap.put("content", dataMap.get(key));
                    node.getUserdata().add(tmpMap);
                }
            }
        }
        return node;
    }

    public static CheckedTreeNode getCheckedTreeNode(Map<String, Object> dataMap) {
        CheckedTreeNode node = CheckedTreeNode.newInstance();
        if (dataMap != null) {
            for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                if ("id".equals(key)) {
                    node.setId((String) dataMap.get(key));
                } else if ("text".equals(key)) {
                    node.setText((String) dataMap.get(key));
                } else if ("pid".equals(key)) {
                    node.setPid((String) dataMap.get(key));
                } else if ("mj".equals(key)) {
                    node.setMj(((BigDecimal) dataMap.get(key)).toString());
                } else if ("checked".equals(key)) {
                    node.setChecked((String) dataMap.get(key));
                } else {
                    Map<String, Object> tmpMap = new HashMap<String, Object>();
                    tmpMap.put("name", key);
                    tmpMap.put("content", dataMap.get(key));
                    node.getUserdata().add(tmpMap);
                }
            }
        }
        return node;
    }
}
