package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.SubSystemBean;

/**
 * @ClassName: SubSystemMapper
 * @Description: 子系统管理
 * @date 2013-2-20 下午12:54:46
 */
public interface SubSystemMapper {

    /**
     * @return List<SubSystemBean>
     * @throws
     * @Title: querySubsystemByNum
     * @Description: 根据编码查询子系统
     */
    public List<SubSystemBean> querySubsystemByNum(String sysNum);

    /**
     * @return void
     * @throws
     * @Title: addSubSystem
     * @Description: 添加子系统
     */
    public void addSubSystem(SubSystemBean subSystemBean);

    /**
     * @return void
     * @throws
     * @Title: deleteSubSystem
     * @Description: 删除子系统
     */
    public void deleteSubSystem(String sysNum);

    /**
     * @return List<SubSystemBean>
     * @throws
     * @Title: querySubsystemAll
     * @Description: 查询所有的子系统
     */
    public List<SubSystemBean> querySubsystemAll();

    /**
     * @return List<Map<String,Object>>
     * @throws
     * @Title: querySubSystemList
     * @Description: 查询子系统
     */
    public List<Map<String, Object>> querySubSystemList();

    /**
     * @return void
     * @throws
     * @Title: updateSubSystem
     * @Description: 修改子系统
     */
    public void updateSubSystem(SubSystemBean subSystemBean);

    /**
     * @return int
     * @throws
     * @Title: querySubsystemIshave
     * @Description: 查询子系统是否存在
     */
    public int querySubsystemIshave(String sysNum);
}
