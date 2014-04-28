package com.xwtech.uomp.base.dao;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("baseDAO")
public class BaseDAO {

    @Autowired
    JdbcOperations jdbcTemplate;

    @Autowired
    protected LobHandler lobHandler;

    @Autowired
    SQLHelper SQLHelper;

    public JdbcOperations getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 执行sql
     *
     * @param sql
     */
    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }

    /**
     * sql查询，返回Map List
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * sql查询，返回bean List
     *
     * @param sql
     * @param classzz
     * @param <T>
     * @return
     */
    public <T> List<?> queryForList(String sql, Class<T> classzz) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return covertBeanList(list, classzz);
    }

    /**
     * 带参数sql查询，返回Map List
     *
     * @param sql
     * @param args
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    /**
     * 带参数sql查询，返回bean List
     *
     * @param sql
     * @param args
     * @param classzz
     * @param <T>
     * @return
     */
    public <T> List<?> queryForList(String sql, Object args[], final Class<T> classzz) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return covertBeanList(list, classzz);
    }


    /**
     * 分页sql查询,list中是Map<String,Object>
     *
     * @param sql
     * @param pagingArgs
     * @return
     */
    public Map<String, Object> queryForPageList(String sql, int... pagingArgs) {
        long count = count(sql);
        String pageSql = SQLHelper.page(sql, pagingArgs[0], pagingArgs[1]);
        List<Map<String, Object>> list = queryForList(pageSql);

        Map<String, Object> page = new HashMap<String, Object>();
        page.put("list", list);
        page.put("count", count);
        return page;
    }

    /**
     * 分页sql查询,list中是bean
     *
     * @param sql
     * @param pagingArgs
     * @return
     */
    public Map<String, Object> queryForPageList(String sql, final Class classzz, int... pagingArgs) {
        long count = count(sql);
        String pageSql = SQLHelper.page(sql, pagingArgs[0], pagingArgs[1]);
        List list = queryForList(pageSql, classzz);

        Map<String, Object> page = new HashMap<String, Object>();
        page.put("list", list);
        page.put("count", count);
        return page;
    }

    /**
     * 带参数分页sql查询,list中是Map<String,Object>
     *
     * @param sql
     * @param args
     * @param pagingArgs
     * @return
     */
    public Map<String, Object> queryForPageList(String sql, Object[] args, int... pagingArgs) {
        long count = count(sql, args);
        String pageSql = SQLHelper.page(sql, pagingArgs[0], pagingArgs[1]);
        List<Map<String, Object>> list = queryForList(pageSql, args);

        Map<String, Object> page = new HashMap<String, Object>();
        page.put("list", list);
        page.put("count", count);
        return page;
    }

    /**
     * 带参数分页sql查询,list中是bean
     *
     * @param sql
     * @param args
     * @param classzz
     * @param pagingArgs
     * @return
     */
    public Map<String, Object> queryForPageList(String sql, Object[] args, Class classzz, int... pagingArgs) {
        long count = count(sql, args);
        String pageSql = SQLHelper.page(sql, pagingArgs[0], pagingArgs[1]);
        List list = queryForList(pageSql, args, classzz);

        Map<String, Object> page = new HashMap<String, Object>();
        page.put("list", list);
        page.put("count", count);
        return page;
    }


    public long count(String sql, Object[] args) {
        return jdbcTemplate.queryForLong(sql, args);
    }

    public long count(String sql) {
        return jdbcTemplate.queryForLong(sql);
    }

    /**
     * 查找一个，返回map
     *
     * @param sql
     * @return
     */
    public Map<String, Object> findOne(String sql) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (list.size() == 0) {
            return new JSONObject();
        }
        return list.get(0);
    }

    /**
     * 查找一个,返回bean
     *
     * @param sql
     * @param classzz
     * @param <T>
     * @return
     */
    public <T> T findOne(String sql, Class<T> classzz) {
        List list = queryForList(sql, classzz);
        if (list == null || list.size() == 0) {
            return null;
        }
        return (T) list.get(0);
    }

    /**
     * 批量新增
     *
     * @param sql
     * @param values
     */
    public void insert(String sql, List<Object[]> values) {
        jdbcTemplate.batchUpdate(sql, values);
    }

    /**
     * 批量修改
     *
     * @param sql
     * @param values
     */
    public void update(String sql, List<Object[]> values) {
        jdbcTemplate.batchUpdate(sql, values);
    }

    /**
     * 批量删除
     *
     * @param sql
     * @param values
     */
    public void delete(String sql, List<Object[]> values) {
        jdbcTemplate.update(sql, values);
    }

    /**
     * Clob字段更新
     *
     * @param sql
     * @param args
     * @return intResult
     * @throws DataAccessException
     */
    public void updateForClob(String sql, Object[] args) throws DataAccessException {
        String className = "";
        int[] argTypes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            className = args[i].getClass().getName();
            if (className.indexOf("String") > 0) {
                argTypes[i] = Types.VARCHAR;
            } else if (className.indexOf("Long") > 0
                    || className.indexOf("Integer") > 0
                    || className.indexOf("Double") > 0
                    || className.indexOf("Float") > 0) {
                argTypes[i] = Types.NUMERIC;
            } else if (className.indexOf("SqlLobValue") > 0) {
                argTypes[i] = Types.CLOB;
            } else {
                argTypes[i] = Types.VARCHAR;
            }
        }
        jdbcTemplate.update(sql, args, argTypes);
    }

    /**
     * 带Clob字段的查询
     *
     * @param sql
     * @param args
     * @return
     */
    @SuppressWarnings("unchecked")
    public List queryForClob(String sql, Object[] args) {
        return jdbcTemplate.query(sql, args, new RowMapper<Object>() {
            public Object mapRow(ResultSet resultset, int i) throws SQLException {
                Map<String, String> retMap = new HashMap<String, String>();

                ResultSetMetaData metaData = resultset.getMetaData();
                for (int num = 1; num <= metaData.getColumnCount(); num++) {
                    if (metaData.getColumnType(num) == Types.CLOB) {
                        retMap.put(metaData.getColumnName(num).toUpperCase(), lobHandler.getClobAsString(resultset, num));
                    } else {
                        retMap.put(metaData.getColumnName(num).toUpperCase(), resultset.getString(num));
                    }
                }
                return retMap;
            }
        });
    }

    /**
     * 带Clob字段的查询
     *
     * @param sql
     * @return
     */
    @SuppressWarnings("unchecked")
    public List queryForClob(String sql) {
        return this.queryForClob(sql, null);
    }


    /**
     * 根据bean的字段名匹配出数据库中的字段名
     *
     * @param propertyName
     * @return
     */
    private String getFieldName(String propertyName) {
        String fieldName = "f_";
        char[] chars = propertyName.toCharArray();
        for (char ch : chars) {
            if (ch >= 'A' && ch <= 'Z') {
                fieldName += "_" + ch;
            } else {
                fieldName += ch;
            }
        }
        return fieldName.toLowerCase();
    }

    /**
     * 将List<Map<String, Object>>转换成BeanList
     *
     * @param list
     * @param classzz
     * @param <T>
     * @return
     */
    private <T> List<?> covertBeanList(List<Map<String, Object>> list, Class<T> classzz) {
        List<T> beanList = new ArrayList<T>();
        try {
            for (Map<String, Object> map : list) {
                BeanInfo beanInfo = Introspector.getBeanInfo(classzz);
                T obj = classzz.newInstance();
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor descriptor : propertyDescriptors) {
                    String propertyName = descriptor.getName();
                    String fieldName = getFieldName(propertyName);
                    if (map.get(fieldName) != null) {
                        // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                        Object propertyValue = map.get(fieldName);
                        Class propertyType = descriptor.getPropertyType();
                        if (propertyValue.getClass() != propertyType) {
                            if (propertyType.toString().equals("int")) {
                                propertyValue = Integer.parseInt(propertyValue.toString());
                            } else if (propertyType.toString().equals("long")) {
                                propertyValue = Long.parseLong(propertyValue.toString());
                            } else if (propertyType.toString().contains("java.lang.Integer")) {
                                propertyValue = new Integer(propertyValue.toString());
                            } else if (propertyType.toString().contains("java.lang.Long")) {
                                propertyValue = new Long(propertyValue.toString());
                            }
                        }
                        Object[] args = new Object[1];
                        args[0] = propertyValue;
                        descriptor.getWriteMethod().invoke(obj, args);
                    }
                }
                beanList.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanList;
    }

    /**
     * @param sql
     * @return
     */
    public long queryForLong(String sql) {
        return jdbcTemplate.queryForLong(sql);
    }

    public int queryForInt(String sql) {
        return jdbcTemplate.queryForInt(sql);
    }


}


