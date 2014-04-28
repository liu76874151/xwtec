package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.admin.FunInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;

/**
 * @author huangfeilong
 * @ClassName: IFunInfoService
 * @Description: 模块服务类
 * @date Mar 6, 2013 8:00:29 PM
 */
public interface IFunInfoService {
    /**
     * @return Map<String,List<TreeNode>> 返回类型
     * @throws
     * @Title: queryFunTree
     * @Description: 查询所有模块信息
     */
    public Map<String, List<TreeNode>> queryFunTree();

    /**
     * @param funId
     * @return FunInfoBean 返回类型
     * @throws
     * @Title: queryFunInfoById
     * @Description: 根据Id查询模块信息
     */
    public FunInfoBean queryFunInfoById(String funId);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: modFunInfo
     * @Description: 更新模块信息
     */
    public void modFunInfo(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: deleteFunInfo
     * @Description: 删除模块信息
     */
    public void deleteFunInfo(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: addFunInfo
     * @Description: 增加模块信息
     */
    public void addFunInfo(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return int 返回类型
     * @throws
     * @Title: queryJbNumBySup
     * @Description: 查询父模块下子模块的个数
     */
    public int queryJbNumBySup(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: updateMjById
     * @Description: 更新父模块末级信息
     */
    public void updateMjById(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: updateMjToOne
     * @Description: 更新父模块末级信息为1
     */
    public void updateMjToOne(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return String 返回类型
     * @throws
     * @Title: querySupJbNum
     * @Description: 查询模块级别
     */
    public String querySupJbNum(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: deleteQxInfo
     * @Description: 删除权限信息
     */
    public void deleteQxInfo(FunInfoBean funInfoBean);

    /**
     * @param funInfoBean
     * @return void 返回类型
     * @throws
     * @Title: deleteQxInfo
     * @Description: 删除MouldUser信息
     */
    public void deleteMouldUser(FunInfoBean funInfoBean);
}
