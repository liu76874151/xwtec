package com.xwtech.uomp.base.service.demo;

import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.demo.StudentBean;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-11
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public interface IStudentService {

    Page queryStudentList(Map<String, String> paramMap);

    int queryStudentListCount(Map<String, String> param);

    StudentBean findOneStudent(String pkid);

    void saveStudent(StudentBean studentBean);

    void updateStudent(StudentBean studentBean);

    void deleteStudent(String id);
}
