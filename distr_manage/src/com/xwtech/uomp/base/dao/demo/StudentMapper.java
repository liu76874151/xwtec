package com.xwtech.uomp.base.dao.demo;

import com.xwtech.uomp.base.pojo.demo.StudentBean;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-11
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public interface StudentMapper {

    List<StudentBean> queryStudentList(Map<String, String> map);

    int queryStudentListCount(Map<String, String> map);

    StudentBean findOneStudent(String pkid);

    void saveStudent(StudentBean studentBean);

    void updateStudent(StudentBean studentBean);

    void deleteStudent(String pkid);
}
