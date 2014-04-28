package com.xwtech.uomp.base.util.genpage.impl;

import com.xwtech.uomp.base.pojo.floor.FloorFaBean;
import com.xwtech.uomp.base.pojo.pagemanage.*;
import com.xwtech.uomp.base.service.floor.IFloorFaService;
import com.xwtech.uomp.base.service.pagemanage.IPageNonbusiinfoService;
import com.xwtech.uomp.base.util.genpage.ICommunicator;
import com.xwtech.uomp.base.util.genpage.IPageGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
@Component("pageGenerator")
public class PageGenerator implements IPageGenerator {

    private static final Logger logger = Logger.getLogger(PageGenerator.class);

    @Autowired
    ICommunicator communicator;

    @Autowired
    IFloorFaService floorFaService;

    @Autowired
    IPageNonbusiinfoService pageNonbusiinfoService;

    @Value("${pageGenerate.pageletStandard}")
    String pageletStandard;

    @Value("${pageGenerate.pageletTouch}")
    String pageletTouch;

    @Value("${pageGenerate.frameStandard}")
    String frameStandard;

    @Value("${pageGenerate.frameTouch}")
    String frameTouch;

    //todo 这么写不太好
    final static String channelNumCommon = "0201"; //普版渠道编码
    final static String channelNumStandar = "0202"; //标准版渠道编码
    final static String channelNumTouch = "0203";  //触屏版渠道编码

    /**
     * 生成一个页面模板关联的业务集合(多个业务)和非业务集合（多个非业务）对应的页面
     *
     * @param pageTmpInfo
     * @param pageInCompList
     * @param pageRelaBusiList
     * @param pageRelaNonbusiList
     * @return
     */
    public List<PageInfo> generatePages(PageTmpInfo pageTmpInfo, List<PageInComp> pageInCompList, List<PageRelaBusi> pageRelaBusiList, List<PageRelaNonbusi> pageRelaNonbusiList) {
        try {
            List<PageInfo> list = new ArrayList<PageInfo>();

            if (pageRelaBusiList.isEmpty() && pageRelaNonbusiList.isEmpty()) { //没有设置关联业务和关联非业务
                PageInfo pageInfo = generateOnePage(pageTmpInfo, pageInCompList, null, null);
                if (pageInfo != null) {
                    list.add(pageInfo);
                }
            } else {
                //页面模板关联业务
                for (PageRelaBusi pageRelaBusi : pageRelaBusiList) {
                    PageInfo pageInfo = generateOnePage(pageTmpInfo, pageInCompList, pageRelaBusi, null);
                    if (pageInfo != null)
                        list.add(pageInfo);
                }

                //页面模板关联非业务
                for (PageRelaNonbusi pageRelaNonbusi : pageRelaNonbusiList) {
                    PageInfo pageInfo = generateOnePage(pageTmpInfo, pageInCompList, null, pageRelaNonbusi);
                    if (pageInfo != null)
                        list.add(pageInfo);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 生成一个页面模板关联的单个业务或非业务的页面
     *
     * @param pageTmpInfo
     * @param pageInCompList
     * @param pageRelaBusi
     */
    public PageInfo generateOnePage(PageTmpInfo pageTmpInfo, List<PageInComp> pageInCompList, PageRelaBusi pageRelaBusi, PageRelaNonbusi pageRelaNonbusi) {
        Properties prop = new Properties();
        if (pageRelaBusi != null) {
            prop.setProperty("busiNum", pageRelaBusi.getBusiNum());
        } else if (pageRelaNonbusi != null) {
            prop.setProperty("nonbusiNum", pageRelaNonbusi.getNonbusiNum());
        }
        String version = pageTmpInfo.getVersion();
        String tmplatePath = pageTmpInfo.getTmplatePath();

        //获得页面模板内容
        String templateStr = getTemplateContent(tmplatePath, prop, version);
        if (templateStr == null) { //当没有获取到页面模板内容时，直接跳出，返回null,不做任何处理
            return null;
        }

        List<PageInComp> staticCompList = new ArrayList<PageInComp>();
        List<PageInComp> dynamicCompList = new ArrayList<PageInComp>();
        List<PageInComp> floorCompList = new ArrayList<PageInComp>();
        PageInComp confirmComp = new PageInComp();
        PageInComp resultComp = new PageInComp();
        for (PageInComp pageInComp : pageInCompList) {
            String compType = pageInComp.getCompType();
            if (compType.equals("1")) {   //静态组件
                staticCompList.add(pageInComp);
            } else if (compType.equals("2")) { //动态组件
                dynamicCompList.add(pageInComp);
            } else if (compType.equals("3")) { //货架组件
                floorCompList.add(pageInComp);
            } else if (compType.equals("4")) { //二次确认组件
                confirmComp = pageInComp;
            } else if (compType.equals("5")) {  //结果组件
                resultComp = pageInComp;
            }
        }

        //生成静态组件
        for (PageInComp pageInComp : staticCompList) {
            String compNum = pageInComp.getCompNum();
            String staticCompStr = getStaticCompString(pageInComp.getCompUrl(), prop, version);
            templateStr = templateStr.replaceAll("T\\{" + compNum + "\\}", staticCompStr);
        }

        //生成动态组件
        String dynamicComp = "";
        if (!dynamicCompList.isEmpty()) {
            String[] result = genDynamicCompString(dynamicCompList, templateStr, version);
            templateStr = result[0];
            dynamicComp = result[1];
        }

        //生成楼层组件
        String floorComp = "";
        if (!floorCompList.isEmpty()) {
            String[] result = getFloorCompString(templateStr, version);
            templateStr = result[0];
            floorComp = result[1];
        }
        // 生成业务组件
        String[] busi_result = genBusiCompString(templateStr, version);
        templateStr = busi_result[0];
        String busiComp = busi_result[1];

        // 生成非业务组件
        String[] nonbusi_result = genNonbusiCompString(templateStr, version);
        templateStr = nonbusi_result[0];
        String nonbusiComp = nonbusi_result[1];

        //去掉模板中没用到的组件 TODO:暂时注掉，看以后是否用的
        //templateStr = templateStr.replaceAll("T\\{.*\\}", "");
        //页面文本信息
        StringBuffer pageContent = new StringBuffer();
        pageContent.append(templateStr);
        pageContent.append(busiComp + nonbusiComp + dynamicComp + floorComp);

        return constructPageInfo(pageTmpInfo, pageRelaBusi, pageRelaNonbusi, confirmComp, resultComp, pageContent);
    }


    /**
     * 获取静态组件内容
     *
     * @param path
     * @param params
     * @param version
     * @return
     * @throws Exception
     */
    private String getStaticCompString(String path, Properties params, String version) {
        try {
            if (path != null) {
                String staticCompPath = "";
                // 标准版
                if ("2".equals(version)) {
                    staticCompPath = pageletStandard;
                }
                //触屏版
                else if ("3".equals(version)) {
                    staticCompPath = pageletTouch;
                }
                path = (staticCompPath == null ? "" : staticCompPath) + "/" + path;
                String str = communicator.doLocalRequest(path, params);
                str = str.replaceAll("\\$", "\\\\\\$");//做特殊字符转换，解决$这个特殊字符
                return str;
            } else {
                return "找不到静态组件路径: \"" + path + "\"!";
            }
        } catch (Exception e) {
            logger.error(e, e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成动态组件自定义标签字符串
     *
     * @param dynamicCompList
     * @param templateStr
     * @param version
     * @return
     */
    private String[] genDynamicCompString(List<PageInComp> dynamicCompList, String templateStr, String version) {
        String[] result = new String[2];
        StringBuffer componentPipeBuff = new StringBuffer();


        if ("1".equals(version) || "2".equals(version)) {    // 普版和标准版
            for (PageInComp pageInComp : dynamicCompList) {
                componentPipeBuff.append("<mobile:component name=\"" + pageInComp.getCompNum() + "\" />");
                templateStr = templateStr.replaceAll("T\\{" + pageInComp.getCompNum() + "\\}", "\\$\\{result." + pageInComp.getCompNum() + "\\}");
            }
        } else if ("3".equals(version)) {   // 触屏版
            for (PageInComp pageInComp : dynamicCompList) {
                componentPipeBuff.append("<mobile:component name=\"" + pageInComp.getCompNum() + "\" containerId=\"" + pageInComp.getContainerId() + "\" />");
                templateStr = templateStr.replaceAll("T\\{" + pageInComp.getCompNum() + "\\}", "");
            }
        }

        result[0] = templateStr;
        result[1] = componentPipeBuff.toString();
        return result;
    }


    /**
     * 生成楼层组件自定义标签字符串
     *
     * @param templateStr
     * @param version
     * @return
     */
    private String[] getFloorCompString(String templateStr, String version) {
        String[] result = new String[2];
        StringBuffer componentPipeBuff = new StringBuffer();

        String channelNum = "";
        if (version.equals("1")) {
            channelNum = channelNumCommon;
        } else if (version.equals("2")) {
            channelNum = channelNumStandar;
        } else if (version.equals("3")) {
            channelNum = channelNumTouch;
        }
        //查询楼层信息
        List<FloorFaBean> list = floorFaService.queryFloorFaList(channelNum);


        if ("1".equals(version) || "2".equals(version)) {    // 普版和标准版
            for (FloorFaBean floorFaBean : list) {
                String floorComp = floorFaBean.getFloorComp();
                Long floorId = floorFaBean.getFloorId();
                componentPipeBuff.append("<mobile:component name=\"" + floorComp + "\" floorId=\"" + floorId + "\" />");
                templateStr = templateStr.replaceAll("T\\{" + floorComp + "\\}", "\\$\\{result." + floorComp + "\\}");
            }
        } else if ("3".equals(version)) {   // 触屏版
            for (FloorFaBean floorFaBean : list) {
                String floorComp = floorFaBean.getFloorComp();
                Long floorId = floorFaBean.getFloorId();

                componentPipeBuff.append("<mobile:component name=\"" + floorComp + "\" containerId=\"" + floorComp + "\" floorId=\"" + floorId + "\" />");
                templateStr = templateStr.replaceAll("T\\{" + floorComp + "\\}", "");
            }
        }

        result[0] = templateStr;
        result[1] = componentPipeBuff.toString();
        return result;
    }

    /**
     * 生成业务组件自定义标签字符串
     *
     * @param templateStr
     * @param version
     * @return
     */
    private String[] genBusiCompString(String templateStr, String version) {
        String[] result = new String[2];
        StringBuffer componentPipeBuff = new StringBuffer();

        // 标准版
        if ("2".equals(version)) {
            // 生成业务组件
            Pattern pattern = Pattern.compile("T_CORE\\{.*\\}");
            Matcher matcher = pattern.matcher(templateStr);
            while (matcher.find()) {
                String matches = matcher.group();
                String busiComp = matches.substring(7, matches.length() - 1);
                componentPipeBuff.append("<mobile:component name=\"");
                componentPipeBuff.append(busiComp);
                componentPipeBuff.append("\" isBusi=\"1\" />");
                templateStr = templateStr.replaceAll("T_CORE\\{" + busiComp + "\\}", "\\$\\{result." + busiComp + "\\}");
            }
        }
        // 触屏版
        else if ("3".equals(version)) {
            // 生成业务组件
            Pattern pattern = Pattern.compile("T_CORE\\{.*\\}");
            Matcher matcher = pattern.matcher(templateStr);
            while (matcher.find()) {
                String matches = matcher.group();
                String busiComp = matches.substring(7, matches.length() - 1);
                componentPipeBuff.append("<mobile:component name=\"");
                componentPipeBuff.append(busiComp);
                componentPipeBuff.append("\" containerId=\"busiArea\" isBusi=\"1\" />");
            }
            templateStr = templateStr.replaceAll("T_CORE\\{.*\\}", "");
        }

        result[0] = templateStr;
        result[1] = componentPipeBuff.toString();
        return result;
    }

    /**
     * 生成非业务组件自定义标签字符串
     *
     * @param templateStr
     * @param version
     * @return
     */
    private String[] genNonbusiCompString(String templateStr, String version) {
        String[] result = new String[2];
        StringBuffer componentPipeBuff = new StringBuffer();

        // 普版和标准版
        if ("1".equals(version) || "2".equals(version)) {
            // 生成业务组件
            Pattern pattern = Pattern.compile("T_NONCORE\\{.*\\}");
            Matcher matcher = pattern.matcher(templateStr);
            while (matcher.find()) {
                String matches = matcher.group();
                String nonbusiComp = matches.substring(10, matches.length() - 1);
                //获得对应的非业务信息对象
                PageNonbusiinfo pageNonbusiinfo = pageNonbusiinfoService.findOnePageNonbusiinfo(nonbusiComp);
                if (pageNonbusiinfo != null && !pageNonbusiinfo.getNonbusiNum().equals("")) {
                    String type = pageNonbusiinfo.getType();
                    componentPipeBuff.append("<mobile:component name=\"");
                    componentPipeBuff.append(nonbusiComp);
                    if (type.equals("0")) { //非业务
                        componentPipeBuff.append("\" />");
                    } else if (type.equals("1")) { //业务
                        componentPipeBuff.append("\" isBusi=\"1\" />");
                    }
                    templateStr = templateStr.replaceAll("T_NONCORE\\{" + nonbusiComp + "\\}", "\\$\\{result." + nonbusiComp + "\\}");
                }
            }
        }
        // 触屏版
        else if ("3".equals(version)) {
            // 生成业务组件
            Pattern pattern = Pattern.compile("T_NONCORE\\{.*\\}");
            Matcher matcher = pattern.matcher(templateStr);
            while (matcher.find()) {
                String matches = matcher.group();
                String nonbusiComp = matches.substring(7, matches.length() - 1);
                //获得对应的非业务信息对象
                PageNonbusiinfo pageNonbusiinfo = pageNonbusiinfoService.findOnePageNonbusiinfo(nonbusiComp);
                if (pageNonbusiinfo != null && !pageNonbusiinfo.getNonbusiNum().equals("")) {
                    String type = pageNonbusiinfo.getType();
                    componentPipeBuff.append("<mobile:component name=\"");
                    componentPipeBuff.append(nonbusiComp);
                    if (type.equals("0")) { //非业务
                        componentPipeBuff.append("\" containerId=\"nonbusiArea\" />");
                    } else if (type.equals("1")) { //业务
                        componentPipeBuff.append("\" containerId=\"nonbusiArea\" isBusi=\"1\" />");
                    }
                }
            }
            templateStr = templateStr.replaceAll("T_NONCORE\\{.*\\}", "");
        }

        result[0] = templateStr;
        result[1] = componentPipeBuff.toString();
        return result;
    }

    /**
     * 获取模板内容
     *
     * @param templatePath
     * @param params
     * @param version
     * @return
     */

    private String getTemplateContent(String templatePath, Properties params, String version) {
        try {
            if (templatePath != null) {
                String path = "";
                if ("2".equals(version)) {     // 标准版
                    path = frameStandard;
                } else if ("3".equals(version)) {   //触屏版
                    path = frameTouch;
                }
                templatePath = (path == null ? "" : path) + "/" + templatePath;
                String str = this.communicator.doLocalRequest(templatePath, params);
                return str;
            } else {
                System.out.println("找不到模板路径: \"" + templatePath + "\"!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常:" + templatePath);
            return null;
        }
    }

    /**
     * 构造生成的页面信息
     *
     * @param pageTmpInfo
     * @param pageRelaBusi
     * @param pageRelaNonbusi
     * @param confirmComp
     * @param resultComp
     * @param pageContent
     * @return
     */
    private PageInfo constructPageInfo(PageTmpInfo pageTmpInfo, PageRelaBusi pageRelaBusi, PageRelaNonbusi pageRelaNonbusi, PageInComp confirmComp, PageInComp resultComp, StringBuffer pageContent) {
        String version = pageTmpInfo.getVersion();
        PageInfo pageInfo = new PageInfo();
        if (pageRelaBusi != null) {
            pageInfo.setPageNum(pageRelaBusi.getBusiNum());
            pageInfo.setPageName(pageRelaBusi.getBusiName());
        } else if (pageRelaNonbusi != null) {
            pageInfo.setPageNum(pageRelaNonbusi.getNonbusiNum());
            pageInfo.setPageName(pageRelaNonbusi.getNonbusiName());
        } else {
            pageInfo.setPageNum(pageTmpInfo.getPageTmpNum());
            pageInfo.setPageName(pageTmpInfo.getPageTmpName());
        }
        pageInfo.setVersion(version);
        pageInfo.setPageContent(pageContent.toString());

        if (confirmComp != null && confirmComp.getCompNum() != null) {
            pageInfo.setConfirmComp(confirmComp.getCompNum());
        } else {
            pageInfo.setConfirmComp("");
        }

        if (resultComp != null && resultComp.getCompNum() != null) {
            pageInfo.setResultComp(resultComp.getCompNum());
        } else {
            pageInfo.setResultComp("");
        }

        String version_name = "";
        if ("1".equals(version)) {
            version_name = "普版";
        } else if ("2".equals(version)) {
            version_name = "标准版";
        } else if ("3".equals(version)) {
            version_name = "触屏版";
        }
        pageInfo.setType(pageTmpInfo.getType());
        String desc = "页面【" + pageInfo.getPageNum() + version_name + "】生成成功！";
        pageInfo.setDesc(desc);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        pageInfo.setUpdateTime(now);

        return pageInfo;
    }
}
