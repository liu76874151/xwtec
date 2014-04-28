package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.GroupInfoBean;
import com.xwtech.uomp.base.pojo.dhtmlx.TreeNode;
import com.xwtech.uomp.base.service.IGroupInfoService;
import com.xwtech.uomp.base.service.IUserInfoService;
import com.xwtech.uomp.base.util.StringUtil;
import com.xwtech.uomp.base.util.dthmlx.DhtmlTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：xw_uomp 类名称：GroupInfoHandler 类描述： 用户组handler 创建人：zangjianhua
 * 创建时间：2013-2-21 上午10:18:51 修改人：Administrator 修改时间：2013-2-21 上午10:18:51 修改备注：
 */

@Component("H_groupinfo")
public class GroupInfoHandler implements IHandler {

    @Autowired
    IGroupInfoService groupInfoService;
    @Autowired
    private IUserInfoService userInfoService;

    public IUserInfoService getUserInfoService() {
        return userInfoService;
    }

    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * @param context
     * @param 设定文件
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryGroupInfo
     * @Description: 查询用户组，拼装tree
     */
    public HandlerResult queryGroupInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            // 当前不分用户类型，故注释
            // UserInfoBean user =
            // (UserInfoBean)SessionUtil.getObjectAttribute(context.getRequest(),
            // RequestConstants.ADMIN_SESSION_KEY);
            // ((UserInfoBean)request.getSession().getAttribute("admin"));
            // String loginName = user.getLoginName();
            // String loginName = "sysadmin";

            // 查询该用户组的级别
            // GroupInfoBean groupInfoBean =
            // groupInfoService.queryGroupJb(loginName);
            // String jb = String.valueOf(groupInfoBean.getJb());
            // 当前不分用户类型，故注释

            List<TreeNode> list = groupInfoService.queryGroupTree();
            TreeNode root = DhtmlTreeUtil.mergeTreeNodeList(list);
            result.setRetObj(root);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    /**
     * @param context
     * @param 设定文件
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryGroupInfo
     * @Description: 查询用户组，拼装tree
     */
    public HandlerResult viewGroupInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            String groupId = context.getRequest().getParameter("groupId");

            GroupInfoBean groupInfo = groupInfoService.viewGroupInfo(groupId);
            CityInfoBean bean = userInfoService.queryByGrouName(groupId);
            if (groupInfo != null) {
                // 对数据进行转换
                groupInfo.setBz(StringUtil.replaceUbb(StringUtil.replaceBr(groupInfo.getBz())));
                map.put("groupInfo", groupInfo);
                map.put("cityBean", bean);
                result.setRetObj(map);
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.VIEW_INFO_ERR0R);
        }
        return result;
    }

    /**
     * @param context
     * @param 设定文件
     * @return HandlerResult 返回类型
     * @throws
     * @Title: createGroupInfo
     * @Description: 创建用户组
     */
    public HandlerResult createGroupInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            String groupId = context.getRequest().getParameter("groupId");

            String groupType = null;
            List<CityInfoBean> listCityInfo = null;

            String jbNum = "";
            if ("ROOT".equals(groupId)) {
                // 一级编码
                jbNum = groupInfoService.queryFirstJbNum();
                listCityInfo = groupInfoService.queryAreaInfo();
            } else {
                GroupInfoBean groupInfo1 = groupInfoService.viewGroupInfo(groupId);
                groupType = groupInfo1.getGroupType();
                String areaNum = context.getRequest().getParameter("areaNum");
                if (!"".equals(areaNum) && null != areaNum) {
                    listCityInfo = groupInfoService.queryAreaInfoByNum(areaNum);
                }
                if (groupInfo1 != null) {
                    String supJbNum = groupInfo1.getJbNum();
                    jbNum = groupInfoService.querySubJbNumFromSup(supJbNum);
                }
            }
            GroupInfoBean groupInfo = new GroupInfoBean();
            groupInfo.setJbNum(jbNum);
            groupInfo.setJb(jbNum.length() / 2);
            groupInfo.setGroupType(groupType);

            map.put("groupInfo", groupInfo);
            map.put("supGroupId", groupId);
            map.put("listCityInfo", listCityInfo);
            result.setRetObj(map);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.CREATE_INFO_ERROR);
        }

        return result;
    }

    /**
     * 新增用户组信息
     *
     * @return
     */
    public HandlerResult saveGroupInfo(HandlerRequestContext context) {

        HandlerResult result = HandlerResult.newInstance();
        try {
            GroupInfoBean groupInfo = getRequestValue(context);
            // 判断groupInfo是否有效
            if (("".equals(groupInfo.getGroupId())) || ("".equals(groupInfo.getJbNum()))) {
                // 提示必须填写子段为空
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {
                // 更新数据
                int intResult = groupInfoService.insertGroupInfo(groupInfo);
                if (intResult == 0) {

                    String supGroupId = context.getRequest().getParameter("supGroupId");
                    if (!"ROOT".equals(supGroupId)) {
                        // 增加子级时变更父级的末级信息
                        intResult = groupInfoService.updateMjById(supGroupId);
                    }
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_ERROR);
        }
        return result;
    }

    /**
     * 删除用户组信息
     *
     * @return
     */
    public HandlerResult deleteGroupInfo(HandlerRequestContext context) {

        HandlerResult result = HandlerResult.newInstance();
        try {
            String groupId = context.getRequest().getParameter("groupId");

            int intResult = userInfoService.sumByGroupId(groupId);
            if (intResult >= 1) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_EXIT_MEMBER);
            } else if (intResult == -1) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_EXIST_ERROR);
            } else {
                GroupInfoBean groupInfo = groupInfoService.viewGroupInfo(groupId);
                String jbNum = groupInfo.getJbNum();

                intResult = groupInfoService.deleteGroupInfo(groupId);
                if (intResult == 0) {

                    intResult = groupInfoService.queryEqiLevelCountByJbNum(jbNum);

                    if (intResult < 1) {
                        // 删除子级信息时变更父级末级信息
                        intResult = groupInfoService.updateMjByJbNum(jbNum.substring(0, jbNum.length() - 2));
                    }

                    intResult = groupInfoService.deleteQxByGroupId(groupId);

                    // 显示提示信息
                    result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
                } else if (intResult != 0) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
                }
            }
        } catch (Exception e) {
            // 错误处理
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_ERROR);
        }
        return result;
    }

    /**
     * 编辑用户组信息
     *
     * @return
     * @throws Exception
     */
    public HandlerResult editGroupInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();
        // List <CityInfoBean> listCityInfo = new ArrayList <CityInfoBean> ();
        try {
            // 获取参数信息
            String groupId = context.getRequest().getParameter("groupId");
            GroupInfoBean groupInfo = groupInfoService.viewGroupInfo(groupId);
            String areaNum = context.getRequest().getParameter("areaNum");

            if (!"".equals(areaNum) && areaNum != null) {
                CityInfoBean bean = userInfoService.queryByGrouName(groupInfo.getGroupId());
                // listCityInfo = groupInfoService.queryAreaInfoByNum(areaNum);
                map.put("cityInfoBean", bean);
            }
            if (groupInfo != null) {
                map.put("groupInfo", groupInfo);
            } else {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
            }
            result.setRetObj(map);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_ERROR);
        }
        return result;
    }

    /**
     * 更新用户组信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public HandlerResult updateGroupInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            GroupInfoBean groupInfo = getRequestValue(context);

            // 判断数据是否有效
            if ("".equals(groupInfo.getGroupId())) {
                // 提示必须填写子段为空
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {

                // 更新数据
                int intResult = groupInfoService.updateGroupInfo(groupInfo);
                if (intResult == 0) {
                    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_ERROR);
        }
        return result;
    }

    /**
     * @param @param  context
     * @param @return
     * @param @throws Exception 设定文件
     * @return GroupInfoBean 返回类型
     * @throws
     * @Title: getRequestValue
     * @Description: 封装用户组 对象
     */
    private GroupInfoBean getRequestValue(HandlerRequestContext context) throws Exception {
        String groupId = context.getRequest().getParameter("groupId");
        String groupName = context.getRequest().getParameter("groupName");
        String groupArea = context.getRequest().getParameter("groupArea");
        String jbNum = context.getRequest().getParameter("jbNum");
        int jb = Integer.parseInt(context.getRequest().getParameter("jb"));
        String bz = context.getRequest().getParameter("bz");
        String groupType = context.getRequest().getParameter("groupType");

        GroupInfoBean groupInfo = new GroupInfoBean();
        groupInfo.setGroupId(groupId);
        groupInfo.setGroupName(groupName);
        groupInfo.setGroupArea(groupArea);
        groupInfo.setJbNum(jbNum);
        groupInfo.setJb(jb);
        groupInfo.setMj(1);
        groupInfo.setBz(bz);
        groupInfo.setGroupType(groupType);

        return groupInfo;
    }
}
