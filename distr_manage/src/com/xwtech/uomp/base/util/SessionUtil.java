/*
 * Copyright 2007 XWTECH INC. All Rights reserved
 * XWTECH INC.
 * 创建日期: 2007/12/20
 * 创建人：  
 * 修改履历：
 * <ul>
 * <li>
 * </ul>
 */

package com.xwtech.uomp.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session操作的工具
 */
public class SessionUtil {
    /**
     * 通过名称获取Session里的对象
     *
     * @param session    HttpSession实例
     * @param objectName Session键名称
     * @return Object
     */
    public static Object getObjectAttribute(HttpSession session, String objectName) {
        return session.getAttribute(objectName);
    }

    /**
     * 通过名称获取Session里的对象
     *
     * @param request    HttpServletRequest实例
     * @param objectName Session键名称
     * @return Object
     */
    public static Object getObjectAttribute(HttpServletRequest request, String objectName) {
        return request.getSession().getAttribute(objectName);
    }

    /**
     * 通过名称设置Session里的对象
     *
     * @param session    HttpSession实例
     * @param objectName Session键名称
     * @param object     存入Session的对象
     */
    public static void setObjectAttribute(HttpSession session, String objectName, Object object) {
        session.setAttribute(objectName, object);
    }

    /**
     * 通过名称设置Session里的对象
     *
     * @param request    HttpServletRequest实例
     * @param objectName Session键名称
     * @param object     存入Session的对象
     */
    public static void setObjectAttribute(HttpServletRequest request, String objectName, Object object) {
        request.getSession().setAttribute(objectName, object);
    }

    /**
     * 通过名称删除Session里的对象
     *
     * @param session    HttpSession实例
     * @param objectName Session键名称
     */
    public static void removeObjectAttribute(HttpSession session, String objectName) {
        session.removeAttribute(objectName);
    }

    /**
     * 通过名称删除Session里的对象
     *
     * @param request    HttpServletRequest实例
     * @param objectName Session键名称
     */
    public static void removeObjectAttribute(HttpServletRequest request, String objectName) {
        request.getSession().removeAttribute(objectName);
    }
}
