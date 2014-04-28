package com.xwtech.uomp.base.pojo.demo;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-11
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
public class StudentBean extends OrderInfoBean implements Serializable {

    private String stuName;
    private String stuAge;
    private String stuSex;
    private String stuNative;
    private String stuTel;
    private String stuAddr;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuNative() {
        return stuNative;
    }

    public void setStuNative(String stuNative) {
        this.stuNative = stuNative;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public String getStuAddr() {
        return stuAddr;
    }

    public void setStuAddr(String stuAddr) {
        this.stuAddr = stuAddr;
    }
}
