package com.xwtech.uomp.base.dao.tools;
import org.springframework.stereotype.Repository;
import com.xwtech.uomp.base.dao.BaseDAO;
import com.xwtech.uomp.base.util.StringUtil;

@Repository("toolsDAO")
public class ToolsDAOImpl extends BaseDAO  implements IToolsDAO {

    /**
     * 一般编码城市编码转换成boss城市编码
     * eg:14-->250
     * @param cityCode
     * @return
     */
    public String reverseCityCode(String cityCode) {
        String sql = "select  org_map_id from jsmss_zone_sort  where boss_code=" + cityCode;
        if (!StringUtil.isNull(cityCode)) {
            return queryForInt(sql) + "";
        }
        return "";

    }
    /**
     * boss表中的城市编码转换成用户一般编码
     * eg:250-->14
     * @param cityCode
     * @return
     */
    public String reverseCityBOSSCode(String cityCode) {
        String sql = "select  boss_code from jsmss_zone_sort  where org_map_id=" + cityCode;
        if (!StringUtil.isNull(cityCode)) {
            return queryForInt(sql) + "";
        }
        return "";

    }
}
