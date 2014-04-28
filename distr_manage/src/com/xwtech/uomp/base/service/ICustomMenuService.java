package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.CustomMenuBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

/**
 * @ClassName: ICustomMenuService
 * @Description: 自定义菜单管理
 * @date 2013-2-27 下午3:05:56
 */
public interface ICustomMenuService {

    /**
     * @return List<CustomMenuBean>
     * @Title: queryCustomMenuByUser
     * @Description: 根据登录用户区查询所拥有的菜单项
     * @date 2013-2-27 下午5:01:03
     */
    List<TreeNode> queryCustomMenuByUser(String loginUserCode);

    /**
     * @return list
     * @Title: viewCustomMenu
     * @Description: 查看菜单详细信息
     * @date 2013-3-3 下午7:25:55
     */
    List<CustomMenuBean> viewCustomMenu(CustomMenuBean bean);

    /**
     * @Title: deleteCustomMenu
     * @Description: 删除菜单详细信息  及节点
     * @returnv int
     * @date 2013-3-5 下午8:38:08
     */
    int deleteCustomMenu(CustomMenuBean bean);

    int queryJbNumBySup(Map<String, Object> map2);

    /**
     * @return void
     * @Title: updateMjToOne
     * @Description: 更改子节点
     * @date 2013-3-6 下午1:35:47
     */
    void updateMjToOne(String jbNum, String loginUserCode);

    /**
     * @Title: updateCustomMenu
     * @Description: 编辑菜单详细信息  及节点
     * @returnv int
     * @date 2013-3-5 下午8:38:08
     */
    void updateCustomMenu(Map<String, Object> map);

    /**
     * 查询第一级级别信息
     *
     * @param subSysNum
     * @param loginUserCode
     * @param jbNum
     * @return
     */
    String queryFirstJbNum(String jbNum, String loginUserCode, String subSysNum);

    /**
     * @return int
     * @throws
     * @Title: insertCustomMenu
     * @Description: 添加自定义菜单
     */
    int insertCustomMenu(CustomMenuBean bean);

    /**
     * @param loginName
     * @return List<TreeNode>
     * @throws
     * @Title: getPrivilegeTree
     * @Description: 根据用户类型查询用户权限菜单
     */
    List<TreeNode> getPrivilegeTree(String userId, String userType, String loginName);

    /**
     * @return int
     * @throws
     * @Title: updateMjById
     * @Description: 增加子集更新末级
     */
    int updateMjByIdByJbNum(String jbNum, String loginUserCode);

    /**
     * @param loginUserCode
     * @param subSysNum
     * @return String
     * @throws
     * @Title: querySubJbNumFromSup
     * @Description: //从父类级别中查询子类的级别编码
     */
    String querySubJbNumFromBYSup(CustomMenuBean bean);

    /**
     * @return List
     * @throws
     * @Title: queryHaveCustomMenu
     * @Description: 查询已经存在的菜单信息
     */
    List<String> queryHaveCustomMenu(String string);

    /**
     * 控制台展示自定义菜单
     * 创建日期：2014-1-8下午2:33:15
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:List<Map<String,Object>>
     */
    List<Map<String, Object>> queryCustomMenuOnConsole(String loginUserCode);

}
