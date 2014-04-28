package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.CustomMenuBean;

/**
 * @ClassName: SubSystemMapper
 * @Description: 子系统管理
 * @date 2013-2-20 下午12:54:46
 */
public interface CustomMenuMapper {

    /**
     * @return List<CustomMenuBean>
     * @Title: queryCustomMenuByUser
     * @Description: 根据登录的角色来查询所拥有的菜单项
     * @date 2013-2-27 下午5:08:40
     */
    List<Map<String, Object>> queryCustomMenuByUser(String loginUserCode);

    /**
     * @return bean
     * @Title: viewCustomMenu
     * @Description: 查看程序功能的详细信息
     * @date 2013-3-3 下午7:25:55
     */
    List<CustomMenuBean> viewCustomMenu(CustomMenuBean bean);

    /**
     * @return void
     * @Title: deleteCustomMenu
     * @Description: 删除程序功能的详细信息
     * @date 2013-3-5 下午8:40:37
     */
    void deleteCustomMenu(CustomMenuBean bean);

    /**
     * 查询同一级别的记录个数
     *
     * @param jbNum
     * @return
     * @throws BaseException
     */
    int queryEqiLevelCountByJbNum(Map<String, Object> map);

    /**
     * 根据Id更新末级信息
     *
     * @param funcId
     * @return
     * @throws BaseException
     */
    int updateMjById(Map<String, Object> map);

    /**
     * @return void
     * @Title: updateCustomMenu
     * @Description: 修改程序功能的详细信息
     * @date 2013-3-3 下午7:25:55
     */
    void updateCustomMenu(Map<String, Object> map);

    /**
     * 查询第一级级别信息
     *
     * @param map
     * @return
     */
    String queryFirstJbNum(Map<String, String> map);

    /**
     * @return int
     * @throws
     * @Title: insertCustomMenu
     * @Description: 添加自定义菜单
     */
    void insertCustomMenu(CustomMenuBean bean);

    /**
     * @return List<Map<String,Object>>
     * @throws
     * @Title: queryPrivilegeInfo
     * @Description: 根据用户权限查询用户的菜单
     */
    List<Map<String, Object>> queryPrivilegeInfo(Map<String, String> map);

    /**
     * @return 添加子级别更改父级别的末级别
     * @throws
     * @Title: updateMjById
     * @Description: TODO
     */
    void updateMjByIdByJbNum(Map<String, String> map);

    /**
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
     * @Description: 已经拥有的菜单
     */
    List<String> queryHaveCustomMenu(String logname);

    /**
     * 控制台展示自定义菜单
     * 创建日期：2014-1-8下午2:32:13
     * 修改日期：
     * 作者：zhanglu
     * @param:
     * @return:List<Map<String,Object>>
     */
    List<Map<String, Object>> queryCustomMenuOnConsole(String loginUserCode);

}
