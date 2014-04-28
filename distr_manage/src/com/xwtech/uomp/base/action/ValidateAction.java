package com.xwtech.uomp.base.action;

import com.alibaba.fastjson.JSON;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.pojo.sso.LoginRequestBean;
import com.xwtech.uomp.base.pojo.sso.LoginResponseBean;
import com.xwtech.uomp.base.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/validate.do")
public class ValidateAction {

    @Autowired
    ILoginService loginService;

    @RequestMapping(value = "")
    public void defaultHandle(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");

        try {
            LoginRequestBean loginReqBean = (LoginRequestBean) request.getAttribute("reqParams");

            //构造鉴权响应消息
            LoginResponseBean loginResBean = new LoginResponseBean();

            loginResBean.setUserInfoBean(loginReqBean.getUserInfoBean());
            loginResBean.setRetCode(IResultCode.SYS_SUCCESS);
            loginResBean.setRequestType(loginReqBean.getRequestType());

            String jsonStr = JSON.toJSONString(loginResBean);
            response.getWriter().print(jsonStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
