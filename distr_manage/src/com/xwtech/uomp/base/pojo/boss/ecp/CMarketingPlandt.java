package com.xwtech.uomp.base.pojo.boss.ecp;

import java.io.Serializable;

/**
 * 
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-22 下午03:15:12
 */
public class CMarketingPlandt implements Serializable {

    private static final long serialVersionUID = -5822276258224610599L;
    /*
     * 营销案dt/用户营销案信息dt
     */
    public String marketingplan_deduct_type = "";// 1 long
    public String marketingplan_deduct_value = "";// 1 long
    public String marketingplan_change_time = "";// 1 string 修改时间
    public String marketingplan_remark = "";// 1 string 营销方案详细描述
    public String marketingplan_target_user_type = "";// 1 long
						      // 目标用户定义：1－品牌、2－大客户等级、3－指定目标用户群中的一种；默认是品牌
    // public String marketingplan_new_business_amount ="";// 1 string
    // 新业务充值金额：默认是0，大于0时，则受理时充至新业务帐本
    // public String marketingplan_discount_amount ="";// 1 string
    // 优惠充值金额，默认为0，大于0时，在申请营销方案时，系统自动充入用户的优惠帐本
    public String marketingplan_cash_amount = "";// 1 string
						 // 现金充值金额，默认为0，大于0时，在申请营销方案时，系统自动充入用户的现金帐本
    public String marketingplan_end_date = "";// 1 string 方案结束时间，空表示一直有效
    public String marketingplan_start_date = "";// 1 string 方案起用时间
    public String marketingplan_agreement_period = "";// 1 long
						      // 协议时长：按月为单位，0表示没有有效期，申请后立即就失效
    public String marketingplan_name = "";// 1 string 方案名称（不超过16个汉字）
    public String marketingplan_home_counties = "";// 1 string 县市代码：用“,”隔开的县市代码
						   // 如："1101,1103"表示“苏州市区”、“昆山”可用，其他县市不可用；为空，表示还没有下发到县市使用
    public String marketingplan_home_city = "";// 1 long
    public String marketingplan_type_id = "";// 1 long 方案类型代码
    public String marketingplan_plan_id = "";// 1 string
					     // 营销方案编号：地市代码＋1＋9位系统序列号（其中1表示临时集团营销方案）
    public String marketingplan_permit_brand_ids = "";

    public String usermarketingbaseinfo_create_time = "";// 营销方案办理时间
    public String usermarketingbaseinfo_end_date = "";// 1 string 结束日期
    public String usermarketingbaseinfo_inure_date = "";// 1 string 生效日期
    public String usermarketingbaseinfo_plan_id = "";

    public String creditrelease_credit_amount = "";// 信用金
    public String bossmms_special_account_amount = "";// 专有账户

    public String marketingplantype_name = "";// 一级名称
}
