package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.sysparammge.SysParamBean;

public interface SysparamMgeMapper {
    public List<SysParamBean> query(SysParamBean spb);

    public List<Map> getSysArr();

    public void addSysParms(SysParamBean spb);

    public void delSysParms(SysParamBean spb);

    public void modifySysParms(SysParamBean spb);

    public SysParamBean getSysParmsById(SysParamBean spb);
}
