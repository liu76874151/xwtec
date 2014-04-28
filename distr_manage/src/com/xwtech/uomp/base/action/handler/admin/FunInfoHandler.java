package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.FunInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IFunInfoService;
import com.xwtech.uomp.base.service.ISubSystemService;
import com.xwtech.uomp.base.util.StringUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangfeilong
 * @ClassName: FunInfoHandler
 * @Description: 模块处理类
 * @date Mar 6, 2013 3:10:17 PM
 */
@Component("H_funinfo")
public class FunInfoHandler implements IHandler {

    @Autowired
    IFunInfoService funInfoService;

    @Autowired
    ISubSystemService subSystemService;

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryFunInfoList
     * @Description: 查询所有模块信息，用于模块树的展示
     */
    public HandlerResult queryFunInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        Map<String, List<TreeNode>> treeNodeMap = null;

        // 查询子系统信息
        List<TreeNode> subSystem = subSystemService.querySubSystemTree();

        treeNodeMap = funInfoService.queryFunTree();

        if (treeNodeMap == null) {
            result.setRetCode(IResultCode.SYS_FAILED);
        } else {
            result.setRetCode(IResultCode.SYS_SUCCESS);
        }

        TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(subSystem, treeNodeMap);

        result.setRetObj(root);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryFunInfo
     * @Description: 根据模块id查询模块信息
     */
    public HandlerResult queryFunInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();

        String funId = context.getRequest().getParameter("funId");
        FunInfoBean funInfo = funInfoService.queryFunInfoById(funId);

        if (funInfo != null) {
            // 对数据进行转换
            funInfo.setBz(StringUtil.replaceUbb(StringUtil.replaceBr(funInfo.getBz())));
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } else {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        map.put("funInfo", funInfo);
        result.setRetObj(map);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: addFunInfo
     * @Description: 添加模块信息
     */
    public HandlerResult addFunInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        String funcId = context.getRequest().getParameter("funcId").trim();
        String funcName = context.getRequest().getParameter("funcName").trim();
        String funcURI = context.getRequest().getParameter("funcURI").trim();
        String bz = context.getRequest().getParameter("bz").trim();
        String subSysName = context.getRequest().getParameter("subSysName").trim();
        String jbNum = context.getRequest().getParameter("jbNum").trim();

        // 获取模块类型
        String funcType = context.getRequest().getParameter("funcType");

        if ("-1".equals(jbNum)) {
            jbNum = null;
        }

        // 父类模块
        FunInfoBean funInfo = new FunInfoBean("", "", "", "", subSysName, jbNum, funcType);

        // 父模块下子模块个数
        int count = funInfoService.queryJbNumBySup(funInfo);

        //级别编码
        String newJbNum = null;

        int jb = 1;
        if (null == jbNum) {
            if (count < 10) {
                newJbNum = "0" + (count + 1);
            } else {
                newJbNum = "" + (count + 1);
            }
        } else {
            newJbNum = funInfoService.querySupJbNum(funInfo);
        }

        if (!(null == jbNum)) {
            // 获取模块级别
            jb = newJbNum.length() / 2;
        }

        // 新添加的子类模块
        FunInfoBean funInfoBean = new FunInfoBean(subSysName, funcId, funcName, funcType, funcURI, bz, newJbNum, 1, jb);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);

        try {
            funInfoService.addFunInfo(funInfoBean);

            // 增加子级时变更父级的末级信息
            if (count == 0) {
                funInfoService.updateMjById(funInfo);
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }

        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: modFunInfo
     * @Description: 修改模块信息
     */
    public HandlerResult modFunInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        String funcId = context.getRequest().getParameter("funcId").trim();
        String funcName = context.getRequest().getParameter("funcName").trim();
        String funcURI = context.getRequest().getParameter("funcURI").trim();
        String bz = context.getRequest().getParameter("bz").trim();
        String subSysName = context.getRequest().getParameter("subSysName").trim();
        String jbNum = context.getRequest().getParameter("jbNum").trim();

        // 获取模块类型
        String funcType = context.getRequest().getParameter("funcType");

        FunInfoBean funInfoBean = new FunInfoBean(funcId, funcURI, funcName, bz, subSysName, jbNum, funcType);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);

        try {
            funInfoService.modFunInfo(funInfoBean);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: deleteFunInfo
     * @Description: 删除模块信息
     */
    public HandlerResult deleteFunInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        String subSysName = context.getRequest().getParameter("subSysName");
        String jbNum = context.getRequest().getParameter("jbNum");
        String funcId = context.getRequest().getParameter("funcId");
        String supJbNum = jbNum.substring(0, (jbNum.length() - 2));

        // 子类模块
        FunInfoBean funInfoBean = new FunInfoBean(funcId, "", "", "", subSysName, jbNum, "");

        //查询要删除模块下是否还有子模块
        int funCount = funInfoService.queryJbNumBySup(funInfoBean);

        if (funCount > 0) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.FUN_HAS_NEXT);
            return result;
        }

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);

        try {
            funInfoService.deleteFunInfo(funInfoBean);
            funInfoService.deleteQxInfo(funInfoBean);
            funInfoService.deleteMouldUser(funInfoBean);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        //父类模块
        FunInfoBean funInfo = new FunInfoBean("", "", "", "", subSysName, supJbNum, "");

        //父模块下子模块个数
        int count = funInfoService.queryJbNumBySup(funInfo);

        //如果该模块下已没有子模块，则将末级信息修改为1
        if (count == 0) {
            funInfoService.updateMjToOne(funInfo);
        }

        return result;
    }
}
