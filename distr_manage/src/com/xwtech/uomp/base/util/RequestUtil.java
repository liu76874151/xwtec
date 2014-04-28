package com.xwtech.uomp.base.util;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-12
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class RequestUtil {

    /**
     * 将request.getParameter("$")作字符串处理
     *
     * @param request HttpServletRequest
     * @param name    String 待转换参数
     * @return String 如果request.getParameter("$")为空则返回空字符串
     */
    public static String getString(HttpServletRequest request, String name, String defaultValue) {

        if (name == null || name.equals("")) {
            return "";
        }
        String param = request.getParameter(name);
        if (param == null)
            return defaultValue;

        try {
            if (request.getMethod().toLowerCase().equals("get")) {
                String encode = "utf-8";
                if (encode != null && encode.length() > 0) {
                    param = new String(request.getParameter(name).trim().getBytes(encode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return param.trim();
    }

    /**
     * 将request.getParameter("$")作字符串处理
     *
     * @param request HttpServletRequest
     * @param name    String 待转换参数
     * @return String 如果request.getParameter("$")为空则返回空字符串
     */
    public static String getString(HttpServletRequest request, String name) {

        if (name == null || name.equals("")) {
            return null;
        }
        String param = request.getParameter(name);
        if (param == null)
            return null;

        try {
            if (request.getMethod().toLowerCase().equals("get")) {
                String encode = "utf-8";
                if (encode != null && encode.length() > 0) {
                    param = new String(request.getParameter(name).trim().getBytes(encode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return param.trim();
    }

    public static Integer getInteger(HttpServletRequest request, String name) {
        Integer l = null;
        if (request == null) {
            return l;
        }
        name = getValue(name);
        if ("".equals(name)) {
            return l;
        }
        try {
            String s = request.getParameter(name);
            return String2Integer(s);
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }
    }


    public static Long getLong(HttpServletRequest request, String name) {
        Long l = null;
        if (request == null) {
            return l;
        }
        name = getValue(name);
        if ("".equals(name)) {
            return l;
        }
        try {
            String s = request.getParameter(name);
            return String2Long(s);
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }
    }


    public static Date getDate(HttpServletRequest request, String name) {
        Date l = null;
        if (request == null) {
            return l;
        }
        name = getValue(name);
        if ("".equals(name)) {
            return l;
        }
        try {
            String s = request.getParameter(name);
            return String2Date(s);
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }
    }
    
    public static Timestamp getTimestamp(HttpServletRequest request, String name) {
    	Timestamp l = null;
        if (request == null) {
            return l;
        }
        name = getValue(name);
        if ("".equals(name)) {
            return l;
        }
        try {
            String s = request.getParameter(name);
            return String2Timestamp(s);
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }
    }

    /**
     * 对request.getParameterValues()的处理
     *
     * @param request HttpServletRequest
     * @param name    String
     * @return String[]
     */
    public static String[] getStringValues(HttpServletRequest request, String name) {
        String[] temp = request.getParameterValues(name);

        if (name == null || temp == null || temp.length == 0) {
            return null;
        }
        String[] s = new String[temp.length];
        try {
            if (request.getMethod().toLowerCase().equals("get")) {
//                String encode = PlatForm.getParamValue("http.request.get.encode");
                String encode = "utf-8";
                if (encode != null && encode.length() > 0) {
                    for (int i = 0; i < temp.length; i++) {
                        String param = new String(temp[i].getBytes(encode));
                        s[i] = param;
                    }
                } else {
                    s = temp;
                }
            } else {
                s = temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return s;
    }

    private static String getValue(String s) {
        try {
            if (s == null || "".equals(s) || "null".equals(s.toLowerCase())) {
                return "";
            }
            return s.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Integer String2Integer(String s) {
        try {
            if ("".equals(getValue(s))) {
                return null;
            }
            return Integer.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Long String2Long(String s) {
        try {
            if ("".equals(getValue(s))) {
                return null;
            }
            return Long.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Date String2Date(String s) {
        try {
            if ("".equals(getValue(s))) {
                return null;
            }
            Date date = new Date(s);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Timestamp String2Timestamp(String s) {
        try {
            if ("".equals(getValue(s))) {
                return null;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(s);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
