package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.SubSystemBean;
import com.xwtech.uomp.base.service.ISubSystemService;
import com.xwtech.uomp.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SubSystemHandler
 * @Description: 子系统管理
 * @date 2013-2-21 下午4:55:54
 */
@Component("H_subSystem")
public class SubSystemHandler implements IHandler {
    @Autowired
    ISubSystemService subSystemService;

    /**
     * @param HandlerRequestContext
     * @return HandlerResult
     * @Title: querySubSystemByNum
     * @Description: 根据子系统掩码来查询子系统
     */
    public HandlerResult querySubSystemByNum(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String sysNum = context.getRequest().getParameter("sysNum").trim();
            List<SubSystemBean> subSystemList = null;
            if (sysNum == null || sysNum.equals("")) {
                subSystemList = (List<SubSystemBean>) subSystemService.querySubSystemAll();
            } else {
                subSystemList = subSystemService.querySubSystemByNum(sysNum);
            }
            map.put("subSystemList", subSystemList);
            result.setRetObj(map);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);

        }
        return result;
    }

    /**
     * @param context
     * @return HandlerResult
     * @Title: querySubSystemAll
     * @Description: 查询所有子系统
     */
    public HandlerResult querySubSystemAll(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<SubSystemBean> subSystemList = subSystemService.querySubSystemAll();
            map.put("subSystemList", subSystemList);
            result.setRetObj(map);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    /**
     * @param HandlerRequestContext context
     * @return void
     * @Title: addSubSystem
     * @Description: 保存子系统
     */
    public HandlerResult saveSubSystem(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            String sysNum = context.getRequest().getParameter("sysNum");
            String sysName = context.getRequest().getParameter("sysName");
            String sysTitle = context.getRequest().getParameter("sysTitle");
            String sysUri = context.getRequest().getParameter("sysUri");
            String xh = context.getRequest().getParameter("xh");
            String bz = context.getRequest().getParameter("bz");

            SubSystemBean subSystemBean = new SubSystemBean();
            subSystemBean.setSysNum(sysNum.trim());
            subSystemBean.setSysName(sysName.trim());
            subSystemBean.setSysTitle(sysTitle.trim());
            subSystemBean.setSysUri(sysUri.trim());
            subSystemBean.setXh(Integer.parseInt(xh.trim()));
            subSystemBean.setBz(bz);

            if (StringUtil.isNull(sysNum) || StringUtil.isNull(sysName) || StringUtil.isNull(sysTitle) || StringUtil.isNull(sysUri) || StringUtil.isNull(xh)) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CREATE_INFO_ERROR);
            }
            int ishave = subSystemService.querySubsystemIshave(sysNum.trim());
            if (ishave > 0) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.SYSNUM_HAS_EXIT);
            } else {
                int intResult = subSystemService.addSubSystem(subSystemBean);
                if (intResult == 0) {
                    result.setRetCode(IResultCode.SYS_SUCCESS);
                    result.setSysCode(SystemCodeConstants.CREATE_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.CREATE_INFO_ERROR);
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.CREATE_INFO_ERROR);
        }
        return result;


    }

    /**
     * @param HandlerRequestContext
     * @return HandlerResult
     * @Title: delSubSystem
     * @Description: 根据子系统掩码来删除子系统
     */
    public HandlerResult delSubSystem(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            String sysNum = context.getRequest().getParameter("sysNum");
            if (sysNum == null || sysNum.equals("")) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
            } else {
                int intResult = subSystemService.deleteSubSystem(sysNum);
                if (intResult == 0) {
                    result.setRetCode(IResultCode.SYS_SUCCESS);
                    result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }
        return result;
    }

    /**
     * @param HandlerRequestContext
     * @return void
     * @Title: updateSubSystem
     * @Description: 根据子系统掩码来修改参数
     */
    public HandlerResult updateSubSystem(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            String sysNum = context.getRequest().getParameter("sysNum");
            String sysName = context.getRequest().getParameter("sysName");
            String sysTitle = context.getRequest().getParameter("sysTitle");
            String sysUri = context.getRequest().getParameter("sysUri");
            String xh = context.getRequest().getParameter("xh");
            String bz = context.getRequest().getParameter("bz");

            SubSystemBean subSystemBean = new SubSystemBean();
            subSystemBean.setSysNum(sysNum);
            subSystemBean.setSysName(sysName);
            subSystemBean.setSysTitle(sysTitle);
            subSystemBean.setSysUri(sysUri);
            subSystemBean.setXh(Integer.parseInt(xh));
            subSystemBean.setBz(bz);

            if (sysNum == null || sysNum.equals("")) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.EDIT_INFO_ERROR);
            } else {
                int intResult = subSystemService.updateSubSystem(subSystemBean);
                if (intResult == 0) {
                    result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.EDIT_INFO_ERROR);
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_ERROR);
        }
        return result;
    }

}
