package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

public class CityInfoBean extends OrderInfoBean implements Serializable {

    /*
     * 地市ID
     */
    private String areaNum;

    /*
     * 地市名称
     */
    private String areaName;

    /*
     * 地市备注
     */

    private String bz;

    private String f_boss_code;

    /*
     * 是否被选中 0:未选中 1:选中
     */


    private int isSelected;

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getF_boss_code() {
        return f_boss_code;
    }

    public void setF_boss_code(String f_boss_code) {
        this.f_boss_code = f_boss_code;
    }

}
