package com.xwtech.uomp.base.service.sysparammge.impl;

import com.xwtech.uomp.base.dao.automated.SysparamMgeMapper;
import com.xwtech.uomp.base.pojo.sysparammge.SysParamBean;
import com.xwtech.uomp.base.service.sysparammge.ISysParamMgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysParamMgeService")
public class SysParamMgeServiceImpl implements ISysParamMgeService {

    @Autowired
    SysparamMgeMapper sysparamMgeMapper;

    public List<SysParamBean> query(SysParamBean spb) {
        return sysparamMgeMapper.query(spb);
    }

    public List<Map> getSysArr() {

        return sysparamMgeMapper.getSysArr();
    }

    public void addSysParms(SysParamBean spb) {
        sysparamMgeMapper.addSysParms(spb);

    }

    public void delSysParms(SysParamBean spb) {
        sysparamMgeMapper.delSysParms(spb);

    }

    public void modifySysParms(SysParamBean spb) {
        sysparamMgeMapper.modifySysParms(spb);
    }

    public SysParamBean getSysParmsById(SysParamBean spb) {
        return sysparamMgeMapper.getSysParmsById(spb);

    }

}
