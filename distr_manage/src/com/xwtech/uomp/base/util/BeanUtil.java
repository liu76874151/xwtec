package com.xwtech.uomp.base.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-12
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtil {

    /**
     * 根据bean从request中反射构造pojo
     *
     * @param request
     * @param beanClass
     * @return
     */
    public static Object getBeanFromRequest(HttpServletRequest request, Class beanClass) {
        try {
            Object object = beanClass.newInstance();
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                Class fieldType = field.getType();
                if (fieldType.isInstance(new String())) {
                    String fieldValue = RequestUtil.getString(request, fieldName);
                    Method method = beanClass.getMethod(methodName, String.class);
                    method.invoke(object, fieldValue);
                } else if (fieldType.isInstance(new Integer(1))) {
                    Integer fieldValue = RequestUtil.getInteger(request, fieldName);
                    if (fieldType != null) {
                        Method method = beanClass.getMethod(methodName, Integer.class);
                        method.invoke(object, fieldValue);
                    }
                } else if (fieldType.isInstance(new Long(1))) {
                    Long fieldValue = RequestUtil.getLong(request, fieldName);
                    if (fieldType != null) {
                        Method method = beanClass.getMethod(methodName, Long.class);
                        method.invoke(object, fieldValue);
                    }
                } else if (fieldType.isInstance(new Date())) {
                    Date fieldValue = RequestUtil.getDate(request, fieldName);
                    if (fieldType != null) {
                        Method method = beanClass.getMethod(methodName, Date.class);
                        method.invoke(object, fieldValue);
                    }
                } else if (fieldType.isInstance(new Timestamp(System.currentTimeMillis()))) {
                    Timestamp fieldValue = RequestUtil.getTimestamp(request, fieldName);
                    if (fieldType != null) {
                        Method method = beanClass.getMethod(methodName, Timestamp.class);
                        method.invoke(object, fieldValue);
                    }
                }
            }

            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从request构造查询map
     *
     * @param request
     * @return
     */
    public static Map<String, String> getMapFromRequest(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Map paramtMap = request.getParameterMap();
        Set set = paramtMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = RequestUtil.getString(request, key);
            if (!value.equals("")) {  //当参数值为空，则不传递此参数
                map.put(key, value);
            }
        }
        //默认加载分页参数
        String start = RequestUtil.getString(request, "start", "0");
        String end = RequestUtil.getString(request, "end", "0");
        map.put("start", start);
        map.put("end", end);

        return map;
    }

    /**
     * 从request构造查询map
     * 用于参数值中包含List或者Array的情况
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getMapFromRequestAdn(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map paramtMap = request.getParameterMap();
        Set set = paramtMap.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = RequestUtil.getString(request, key);
            if (!value.equals("")) {  //当参数值为空，则不传递此参数
                map.put(key, value);
            }
        }
        //默认加载分页参数
        String start = RequestUtil.getString(request, "start", "0");
        String end = RequestUtil.getString(request, "end", "0");
        map.put("start", start);
        map.put("end", end);

        return map;
    }

    /**
     * 根据bean结构，从request反射构造bean的list
     *
     * @param request
     * @param beanClass
     * @return
     */
    public static List getBeanListFromRequest(HttpServletRequest request, Class beanClass) {
        try {
            List beanList = new ArrayList();
            String className = beanClass.getName();
            String beanName = className.substring(className.lastIndexOf(".") + 1, className.length());
            beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1, beanName.length());
            Field[] fields = beanClass.getDeclaredFields();
            int flag = 0;
            for (int i = 0; i < 300; i++) {
                Object object = beanClass.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                    Class fieldType = field.getType();
                    String param = beanName + "[" + i + "][" + fieldName + "]";
                    if (fieldType.isInstance(new String())) {
                        String fieldValue = RequestUtil.getString(request, param);
                        if (fieldValue != null && !"".equals(fieldValue)) {
                            Method method = beanClass.getMethod(methodName, String.class);
                            method.invoke(object, fieldValue);
                        } else {
                            flag++;
                        }
                    } else if (fieldType.isInstance(new Integer(1))) {
                        Integer fieldValue = RequestUtil.getInteger(request, param);
                        if (fieldValue != null) {
                            Method method = beanClass.getMethod(methodName, Integer.class);
                            method.invoke(object, fieldValue);
                        } else {
                            flag++;
                        }
                    } else if (fieldType.toString().equals("int")) {
                        Integer fieldValue = RequestUtil.getInteger(request, param);
                        if (fieldValue != null) {
                            Method method = beanClass.getMethod(methodName, int.class);
                            method.invoke(object, fieldValue.intValue());
                        } else {
                            flag++;
                        }
                    } else if (fieldType.isInstance(new Long(1))) {
                        Long fieldValue = RequestUtil.getLong(request, param);
                        if (fieldValue != null) {
                            Method method = beanClass.getMethod(methodName, Long.class);
                            method.invoke(object, fieldValue);
                        } else {
                            flag++;
                        }
                    } else if (fieldType.toString().equals("long")) {
                        Long fieldValue = RequestUtil.getLong(request, param);
                        if (fieldValue != null) {
                            Method method = beanClass.getMethod(methodName, long.class);
                            method.invoke(object, fieldValue.longValue());
                        } else {
                            flag++;
                        }
                    } else if (fieldType.isInstance(new Date())) {
                        Date fieldValue = RequestUtil.getDate(request, param);
                        if (fieldValue != null) {
                            Method method = beanClass.getMethod(methodName, Date.class);
                            method.invoke(object, fieldValue);
                        } else {
                            flag++;
                        }
                    }
                }
                if (flag >= fields.length) {
                    break;
                } else {
                    beanList.add(object);
                    flag = 0;
                }
            }

            return beanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据bean结构，从request反射构造map的list
     *
     * @param request
     * @param beanClass
     * @return
     */
    public static List<Map<String, String>> getMapListFromRequest(HttpServletRequest request, Class beanClass) {
        try {
            List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

            String className = beanClass.getName();
            String beanName = className.substring(className.lastIndexOf(".") + 1, className.length());
            beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1, beanName.length());
            Field[] fields = beanClass.getDeclaredFields();
            int flag = 0;
            for (int i = 0; i < 100; i++) {
                Map<String, String> map = new HashMap<String, String>();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Class fieldType = field.getType();
                    if (fieldType.isInstance(new String())) {
                        String param = beanName + "[" + i + "][" + fieldName + "]";
                        String fieldValue = RequestUtil.getString(request, param);
                        if (!fieldValue.equals("")) {
                            map.put(fieldName, fieldValue);
                        } else {
                            flag++;
                        }
                    }
                }
                if (flag >= fields.length) {
                    break;
                } else {
                    mapList.add(map);
                    flag = 0;
                }
            }

            return mapList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
    }
}
