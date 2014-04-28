package com.xwtech.uomp.base.action.handler.admin;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.admin.CityInfoBean;
import com.xwtech.uomp.base.pojo.admin.GroupInfoBean;
import com.xwtech.uomp.base.pojo.admin.UserInfoBean;
import com.xwtech.uomp.base.service.IGroupInfoService;
import com.xwtech.uomp.base.service.IUserInfoService;
import com.xwtech.uomp.base.util.Md5Util;
import com.xwtech.uomp.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：xw_uomp 类名称：UserInfoHandler 类描述： 后台管理-用户管理 创建人：zangjianhua
 * 创建时间：2013-2-25 上午10:52:38 修改人：Administrator 修改时间：2013-2-25 上午10:52:38 修改备注：
 */
@Component("H_userInfo")
public class UserInfoHandler implements IHandler {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    IGroupInfoService groupInfoService;

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryUserInfo
     * @Description: 查询用户列表
     */
    public HandlerResult queryUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> mapage = new HashMap<String, Object>();

            String userGroup = context.getRequest().getParameter("userGroup");
            String userName = context.getRequest().getParameter("userName");
            String start = context.getRequest().getParameter("start");
            String end = context.getRequest().getParameter("end");
            if (userGroup != null && !userGroup.equals("")) {
                mapage.put("userGroup", userGroup);
            }
            if (userName != null && !userName.equals("")) {
                mapage.put("userName", userName);
            }
            if (end != null && !end.equals("")) {
                mapage.put("end", end);
            }
            if (start != null && !start.equals("")) {
                mapage.put("start", start);
            }
            List<UserInfoBean> userInfoList = userInfoService.queryUserInfoForPage(mapage);
            int count = userInfoService.queryUserInfoForCount(mapage);//获取分页总数
            Page pageList = new Page();
            pageList.setRecords(userInfoList);
            pageList.setTotalRecord(count);
            if (StringUtil.isNull(start) || StringUtil.isNull(end)) {
                map.put("userInfoList", userInfoList);
                map.put("userGroup", userGroup);
                map.put("userName", userName);
                result.setRetObj(map);
                result.setRetCode(IResultCode.SYS_SUCCESS);
                result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
            } else {
                map.put("pageList", pageList);
                map.put("userGroup", userGroup);
                map.put("userName", userName);
                result.setRetObj(map);
                result.setRetCode(IResultCode.SYS_SUCCESS);
                result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
            }
            // List<UserInfoBean> userInfoList = userInfoService.queryUserInfo(userGroup, userName);

        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_ERROR);
        }
        return result;
    }

    /**
     * @param @param  context
     * @param @return 设定文件
     * @return HandlerResult 返回类型
     * @throws
     * @Title: viewUserInfo
     * @Description: 查看用户详情信息
     */
    public HandlerResult viewUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();

            String loginName = context.getRequest().getParameter("loginName");

            UserInfoBean userInfo = userInfoService.viewUserInfo(loginName);
            CityInfoBean cityInfoBean = userInfoService.queryByGrouName(userInfo.getUserGroup());
            if (userInfo != null) {
                // 对数据进行转换
                userInfo.setBz(StringUtil.replaceUbb(StringUtil.replaceBr(userInfo.getBz())));
                map.put("userInfo", userInfo);
                map.put("cityInfoBean", cityInfoBean);
                result.setRetObj(map);
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.VIEW_INFO_ERR0R);
        }
        return result;
    }

    /**
     * @param @param  context
     * @param @return 设定文件
     * @return HandlerResult 返回类型
     * @throws
     * @Title: createUserInfo
     * @Description: 添加管理员信息
     */
    public HandlerResult createUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<CityInfoBean> listCityInfo = null;
            String groupId = context.getRequest().getParameter("groupId");
            String areaNum = context.getRequest().getParameter("areaNum");
            if (!"".equals(areaNum) && null != areaNum) {
                listCityInfo = groupInfoService.queryAreaInfoByNum(areaNum);
            }
            // CityInfoBean bean = userInfoService.queryByGrouName(groupId);
            map.put("userGroup", groupId);
            // map.put("cityInfoBean", bean);
            map.put("listCityInfo", listCityInfo);
            result.setRetObj(map);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.CREATE_INFO_ERROR);
        }
        return result;
    }

    /**
     * 新增管理员
     *
     * @param request
     * @param response
     * @return
     */
    public HandlerResult saveUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            UserInfoBean userInfo = getRequestValue(context);
            if ("".equals(userInfo.getLoginName())) {
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {
                // 判断登录名是否存在了
                int intResult = userInfoService.findUserInfo(userInfo.getLoginName());
                if (intResult >= 1) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.CHECK_FIELD_EXIST);
                } else if (intResult == -1) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.CHECK_EXIST_ERROR);
                } else {
                    // 更新数据
                    intResult = userInfoService.insertUserInfo(userInfo);
                    if (intResult == 0) {
                        result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                    } else {
                        result.setRetCode(IResultCode.SYS_FAILED);
                        result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                    }
                }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_ERROR);
        }
        return result;
    }

    /**
     * 删除管理员
     *
     * @param request
     * @param response
     * @return
     */
    public HandlerResult deleteUserInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            String loginName = context.getRequest().getParameter("loginName");
            // String loginPwd = context.getRequest().getParameter("loginPwd");
            int intResult = userInfoService.deleteUserInfo(loginName);
            if (intResult == 0) {
                // 删除用户的同时删除相应的权限信息
                intResult = userInfoService.deleteQxByUser(loginName);
                // 显示提示信息
                result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
            }
            if (intResult == 0) {
                result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
            } else if (intResult < 0) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
            } else {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.DELETE_INFO_ERROR);
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_ERROR);
        }
        return result;
    }

    /**
     * 编辑管理员
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public HandlerResult editUserInfo(HandlerRequestContext context) {

        Map<String, Object> map = new HashMap<String, Object>();
        HandlerResult result = HandlerResult.newInstance();
        try {
            // 获取参数信息
            String loginName = context.getRequest().getParameter("loginName");
            UserInfoBean userInfo = userInfoService.viewUserInfo(loginName);
            CityInfoBean bean = userInfoService.queryByGrouName(userInfo.getUserGroup());
            // 根据user查找到用户组的状态
            GroupInfoBean groupInfo = groupInfoService.viewGroupInfo(userInfo.getUserGroup());
            map.put("groupInfo", groupInfo);
            if (userInfo != null && null != bean) {
                map.put("userInfo", userInfo);
                map.put("cityInfoBean", bean);
            } else {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
            }
            result.setRetObj(map);
        } catch (Exception e) {
        	e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_ERROR);
        }
        return result;
    }

    /**
     * 更新管理员信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public HandlerResult updateUserInfo(HandlerRequestContext context) {

        HandlerResult result = HandlerResult.newInstance();

        // 获取特殊参数信息
        // String groupType = context.getRequest().getParameter("groupType");
        try {
            UserInfoBean userInfo = getRequestValue(context);

            // 判断数据是否有效
            if ("".equals(userInfo.getLoginName())) {
                // 提示必须填写子段为空
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {

                // 判断登录名是否存在了
                // int intResult =
                // userInfoService.findUserInfo(userInfo.getLoginName());
                // if(intResult>=1){
                // resultInfo.setPromptInfo(ResultConstants.CHECK_FIELD_EXIST);
                // } else if(intResult==-1) {
                // resultInfo.setPromptInfo(ResultConstants.CHECK_EXIST_ERROR);
                // } else {
                // 更新数据
                int intResult = userInfoService.updateUserInfo(userInfo);
                if (intResult == 0) {
                    result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
                } else {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
                }
                // }
            }
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_ERROR);
        }
        return result;
    }

    // 从页面获取数据
    private UserInfoBean getRequestValue(HandlerRequestContext context) {
        String loginName = context.getRequest().getParameter("loginName");
        String loginPwd = context.getRequest().getParameter("loginPwd");
        String userName = context.getRequest().getParameter("userName");
        String userGroup = context.getRequest().getParameter("userGroup");
        String userState = context.getRequest().getParameter("userState");
        String phone = context.getRequest().getParameter("phone");
        String bz = context.getRequest().getParameter("bz");
        String pwd = context.getRequest().getParameter("pwd");
        String changePwd = context.getRequest().getParameter("changePwd");
        String userArea = context.getRequest().getParameter("userArea");
        String userType = context.getRequest().getParameter("userType");
        String userAreaCode = context.getRequest().getParameter("userAreaCode");
        if("".equals(userAreaCode)){
        	userAreaCode = "0";
        }
        String channelNum = context.getRequest().getParameter("channelNum");

        UserInfoBean bean = new UserInfoBean();
        bean.setLoginName(loginName);
        bean.setLoginPwd(Md5Util.toMD5(loginPwd));
        bean.setUserName(userName);
        bean.setUserGroup(userGroup);
        bean.setUserState(Integer.parseInt(userState));
        bean.setPhone(phone);
        bean.setBz(bz);
        bean.setUserArea(userArea);
        bean.setUserType(userType);
        bean.setUserAreaCode(userAreaCode);
        bean.setChannelNum(channelNum);

        // 判断是否修改了密码
        if (null != changePwd && !"".equals(changePwd)) {
            bean.setLoginPwd(Md5Util.toMD5(loginPwd));
        } else {
            bean.setLoginPwd(pwd);
        }
        return bean;
    }

}
