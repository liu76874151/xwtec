package com.xwtech.uomp.base.action.handler.sysparammge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.pojo.sysparammge.SysParamBean;
import com.xwtech.uomp.base.service.sysparammge.ISysParamMgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("H_sysparmmage")
public class SysParamMgeHandler implements IHandler {
    @Autowired
    ISysParamMgeService sysParamMgeService;

    public HandlerResult query(HandlerRequestContext context) {
        String sysnum = context.getRequest().getParameter("sysnum");
        String csname = context.getRequest().getParameter("csname");
        SysParamBean spb = new SysParamBean();
        if (sysnum != null && !sysnum.equals("")) {
            spb.setSubSysNum(sysnum);
        }
        if (csname != null && !csname.equals("")) {
            spb.setCsmc(csname);
        }
        List<SysParamBean> list = sysParamMgeService.query(spb);
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj(JSONArray.toJSONString(list));
        return result;
    }

    public HandlerResult getSysArr(HandlerRequestContext context) {
        List<Map> list = sysParamMgeService.getSysArr();
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj(JSONArray.toJSONString(list));
        return result;
    }

    public HandlerResult addSysParms(HandlerRequestContext context) {
        String sysnum = context.getRequest().getParameter("sysnum");
        String csname = context.getRequest().getParameter("csname");
        String csz = context.getRequest().getParameter("csz");
        String bz = context.getRequest().getParameter("bz");
        SysParamBean spb = new SysParamBean();
        spb.setSubSysNum(sysnum);
        spb.setCsmc(csname);
        spb.setCsz(csz);
        spb.setBz(bz);
        sysParamMgeService.addSysParms(spb);
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj("true");
        return result;
    }

    public HandlerResult delSysParms(HandlerRequestContext context) {
        String sysnum = context.getRequest().getParameter("sysnum");
        String csname = context.getRequest().getParameter("csname");
        SysParamBean spb = new SysParamBean();
        spb.setSubSysNum(sysnum);
        spb.setCsmc(csname);
        sysParamMgeService.delSysParms(spb);
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj("true");
        return result;
    }

    public HandlerResult modifySysParms(HandlerRequestContext context) {
        String sysnum = context.getRequest().getParameter("sysnum");
        String csname = context.getRequest().getParameter("csname");
        String csz = context.getRequest().getParameter("csz");
        String bz = context.getRequest().getParameter("bz");
        SysParamBean spb = new SysParamBean();
        spb.setSubSysNum(sysnum);
        spb.setCsmc(csname);
        spb.setCsz(csz);
        spb.setBz(bz);
        sysParamMgeService.modifySysParms(spb);
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj("true");
        return result;
    }

    public HandlerResult getSysParmsById(HandlerRequestContext context) {
        String sysnum = context.getRequest().getParameter("sysnum");
        String csname = context.getRequest().getParameter("csname");
        String csz = context.getRequest().getParameter("csz");
        String bz = context.getRequest().getParameter("bz");
        SysParamBean spb = new SysParamBean();
        spb.setSubSysNum(sysnum);
        spb.setCsmc(csname);
        spb.setCsz(csz);
        spb.setBz(bz);
        spb = sysParamMgeService.getSysParmsById(spb);
        HandlerResult result = HandlerResult.newInstance();
        result.setRetObj(JSONObject.toJSONString(spb));
        return result;
    }
}
