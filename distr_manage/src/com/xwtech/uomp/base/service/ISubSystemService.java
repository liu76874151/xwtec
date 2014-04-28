package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

/**
 * @ClassName: ISubSystemService
 * @Description: 子系统管理
 * @date 2013-2-20 下午12:51:02
 */
public interface ISubSystemService {

    /**
     * @return List<SubSystemBean>
     * @throws
     * @Title: querySubSystemByNum
     * @Description: 根据子系统编码查询子系统集合
     */
    List<SubSystemBean> querySubSystemByNum(String sysNum);

    /**
     * @return int
     * @throws
     * @Title: addSubSystem
     * @Description: 添加子系统
     */
    int addSubSystem(SubSystemBean subSystemBean);

    /**
     * @return int
     * @throws
     * @Title: deleteSubSystem
     * @Description: TODO
     */
    int deleteSubSystem(String sysNum);

    /**
     * @return List<SubSystemBean>
     * @throws
     * @Title: querySubSystemAll
     * @Description: 查询所有的子系统
     */
    List<SubSystemBean> querySubSystemAll();

    /**
     * @return List<TreeNode>
     * @throws
     * @Title: querySubSystemTree
     * @Description: 查询子系统树
     */
    public List<TreeNode> querySubSystemTree();

    /**
     * @return int
     * @throws
     * @Title: updateSubSystem
     * @Description: 修改子系统
     */
    int updateSubSystem(SubSystemBean subSystemBean);

    /**
     * @return int
     * @throws
     * @Title: querySubsystemIshave
     * @Description: 查询子系统编码是否存在
     */
    int querySubsystemIshave(String sysNum);
}
