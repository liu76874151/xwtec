package com.xwtech.uomp.base.action.handler.demo;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.demo.StudentBean;
import com.xwtech.uomp.base.service.demo.IStudentService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-11
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */

@Component("H_student")
public class StudentHandler implements IHandler {

    @Autowired
    IStudentService studentService;

    /**
     * 查询学生信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryStudentList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            Page page = studentService.queryStudentList(param);
            result.setRetObj(page);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        return result;
    }

    /**
     * 根据主键查找一名学生信息
     *
     * @param context
     * @return
     */
    public HandlerResult findOneStudent(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String pkid = RequestUtil.getString(request, "pkid");
        try {
            StudentBean studentBean = studentService.findOneStudent(pkid);
            result.setRetObj(studentBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }

    /**
     * 新增学生
     *
     * @param context
     * @return
     */
    public HandlerResult saveStudent(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        StudentBean studentBean = (StudentBean) BeanUtil.getBeanFromRequest(request, StudentBean.class);
        try {
            studentService.saveStudent(studentBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 修改学生
     *
     * @param context
     * @return
     */
    public HandlerResult updateStudent(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        StudentBean studentBean = (StudentBean) BeanUtil.getBeanFromRequest(request, StudentBean.class);

        try {
            studentService.updateStudent(studentBean);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 删除学生
     *
     * @param context
     * @return
     */
    public HandlerResult deleteStudent(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String id = RequestUtil.getString(request, "pkid");

        try {
            studentService.deleteStudent(id);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }
}
