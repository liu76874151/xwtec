package com.xwtech.uomp.base.service.demo.impl;

import com.xwtech.uomp.base.dao.demo.StudentMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.demo.StudentBean;
import com.xwtech.uomp.base.service.demo.IStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-11
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
@Service("studentService")
public class StudentServiceImpl implements IStudentService {

    protected static final Logger log = Logger.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentMapper studentMapper;

    public Page queryStudentList(Map<String, String> param) {
        List<StudentBean> list = studentMapper.queryStudentList(param);
        int count = studentMapper.queryStudentListCount(param);

        Page page = new Page();
        page.setRecords(list);
        page.setTotalRecord(count);

        return page;
    }

    public int queryStudentListCount(Map<String, String> param) {
        return studentMapper.queryStudentListCount(param);
    }

    public StudentBean findOneStudent(String pkid) {
        return studentMapper.findOneStudent(pkid);
    }

    public void saveStudent(StudentBean studentBean) {
        studentMapper.saveStudent(studentBean);
    }

    public void updateStudent(StudentBean studentBean) {
        studentMapper.updateStudent(studentBean);
    }

    public void deleteStudent(String pkid) {
        studentMapper.deleteStudent(pkid);
    }
}
