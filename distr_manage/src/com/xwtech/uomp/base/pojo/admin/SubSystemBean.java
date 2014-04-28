package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

/**
 * @ClassName: SubSystemBean
 * @Description: 子系统管理Bean
 * @date 2013-2-20 下午12:54:34
 */
@SuppressWarnings("serial")
public class SubSystemBean implements Serializable {

    /* 子系统编码 */
    private String sysNum;

    /* 子系统名称 */
    private String sysName;

    /* 子系统标题 */
    private String sysTitle;

    /* 子系统URI地址 */
    private String sysUri;

    /* 序号 */
    private int xh;

    /* 备注 */
    private String bz;

    public String getSysNum() {
        return sysNum;
    }

    public void setSysNum(String sysNum) {
        this.sysNum = sysNum;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysTitle() {
        return sysTitle;
    }

    public void setSysTitle(String sysTitle) {
        this.sysTitle = sysTitle;
    }

    public String getSysUri() {
        return sysUri;
    }

    public void setSysUri(String sysUri) {
        this.sysUri = sysUri;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

}
