package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.CustomMenuMapper;
import com.xwtech.uomp.base.pojo.admin.CustomMenuBean;
import com.xwtech.uomp.base.pojo.dhtmlx.CheckedTreeNode;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.ICustomMenuService;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("customMenuService")
public class CustomMenuServiceImpl implements ICustomMenuService {
    protected static final Logger log = Logger.getLogger(CustomMenuServiceImpl.class);

    @Autowired
    CustomMenuMapper customMenuMapper;

    /**
     * @return List<TreeNode>
     * @Title: queryCustomMenuByUser
     * @Description: 根据登录用户区查询所拥有的菜单项
     * @date 2013-2-27 下午5:01:03
     */
    public List<TreeNode> queryCustomMenuByUser(String loginUserCode) {
        List<TreeNode> treeNodeList = null;
        try {
            List<Map<String, Object>> listResult = customMenuMapper.queryCustomMenuByUser(loginUserCode);
            if (listResult == null || listResult.size() <= 0) {
                return treeNodeList;
            }
            treeNodeList = new ArrayList<TreeNode>();
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = (Map<String, Object>) listResult.get(i);
                TreeNode node = DhtmlTreeUtil.getTreeNode(dataMap);
                treeNodeList.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            treeNodeList = null;
        }
        return treeNodeList;

    }

    /**
     * @return CustomMenuBean
     * @Title: viewCustomMenu
     * @Description: 查看程序功能的详细信息
     * @date 2013-3-3 下午7:25:55
     */
    public List<CustomMenuBean> viewCustomMenu(CustomMenuBean bean) {
        List<CustomMenuBean> list = null;
        try {
            list = customMenuMapper.viewCustomMenu(bean);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            list = null;
        }
        return list;
    }

    /**
     * @return CustomMenuBean
     * @Title: viewCustomMenu
     * @Description: 删除程序功能的详细信息
     * @date 2013-3-3 下午7:25:55
     */
    public int deleteCustomMenu(CustomMenuBean bean) {
        int num = 0;
        try {
            customMenuMapper.deleteCustomMenu(bean);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            num = -1;
        }
        return num;
    }

    public int queryJbNumBySup(Map<String, Object> map2) {
        return customMenuMapper.queryEqiLevelCountByJbNum(map2);

    }

    public void updateMjToOne(String jbNum, String loginUserCode) {
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("jbNumsub", jbNum.substring(0, 2));
        map3.put("loginUserCode", loginUserCode);
        customMenuMapper.updateMjById(map3);
    }

    /**
     * @return void
     * @Title: updateCustomMenu
     * @Description: 修改程序功能的详细信息
     * @date 2013-3-3 下午7:25:55
     */
    public void updateCustomMenu(Map<String, Object> map) {
        customMenuMapper.updateCustomMenu(map);

    }

    /**
     * 查询第一级级别信息
     *
     * @return
     */
    public String queryFirstJbNum(String jbNum, String loginUserCode, String subSysNum) {
        String strResult = "";
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("loginUserCode", loginUserCode);
            map.put("subSysNum", subSysNum);
            strResult = customMenuMapper.queryFirstJbNum(map);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            strResult = "";
        }
        return strResult;
    }

    /**
     * @return int
     * @throws
     * @Title: insertCustomMenu
     * @Description: 添加自定义菜单
     */
    public int insertCustomMenu(CustomMenuBean bean) {
        int result = 0;
        try {
            customMenuMapper.insertCustomMenu(bean);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result = -1;
        }
        return result;
    }

    public List<TreeNode> getPrivilegeTree(String userId, String userType, String loginName) {
        List<TreeNode> treeNodeList = null;
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", userId);
            List<Map<String, Object>> listResult = customMenuMapper.queryPrivilegeInfo(map);
            if (listResult == null || listResult.size() <= 0) {
                return treeNodeList;
            }
            treeNodeList = new ArrayList<TreeNode>();
            List<String> list = customMenuMapper.queryHaveCustomMenu(loginName);
            for (int i = 0; i < listResult.size(); i++) {
                Map<String, Object> dataMap = listResult.get(i);
                boolean flag = true;
                String haveCustom = "HAVE" + "-" + dataMap.get("subSysNum") + "-" + dataMap.get("text") + "-" + dataMap.get("funcId");
                for (int n = 0; n < list.size(); n++) {
                    String getString = list.get(n);
                    if (haveCustom.equals(getString) || haveCustom == getString) {
                        flag = false;
                    }
                }
                if (flag) {
                    CheckedTreeNode node = DhtmlTreeUtil.getCheckedTreeNode(dataMap);
                    treeNodeList.add(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            treeNodeList = null;
        }

        return treeNodeList;
    }

    public int updateMjByIdByJbNum(String jbNum, String loginUserCode) {
        int num = 0;
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("jbNum", jbNum.trim());
            map.put("loginUserCode", loginUserCode.trim());
            customMenuMapper.updateMjByIdByJbNum(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            num = -1;
        }
        return num;
    }

    //从父类级别中查询子类的级别编码
    public String querySubJbNumFromBYSup(CustomMenuBean bean) {
        String subJbnum = "";
        try {
            subJbnum = customMenuMapper.querySubJbNumFromBYSup(bean);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return subJbnum;
    }


    public List<String> queryHaveCustomMenu(String logname) {
        List<String> list = null;
        try {
            list = customMenuMapper.queryHaveCustomMenu(logname);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Map<String, Object>> queryCustomMenuOnConsole(String loginUserCode){
    	List<Map<String, Object>> list = null;
    	try {
			list = customMenuMapper.queryCustomMenuOnConsole(loginUserCode);
		} catch (Exception e) {
			log.error(e.getMessage());
            e.printStackTrace();
		}
    	return list;
    }
}
