package com.xwtech.uomp.base.service.sysparammge;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.sysparammge.SysParamBean;

public interface ISysParamMgeService {

    public List<SysParamBean> query(SysParamBean spb);

    public List<Map> getSysArr();

    public void addSysParms(SysParamBean spb);

    public void modifySysParms(SysParamBean spb);

    public void delSysParms(SysParamBean spb);

    public SysParamBean getSysParmsById(SysParamBean spb);
}
