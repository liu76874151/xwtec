package com.xwtech.uomp.base.util.order;

/**
 *@ClassName:DangDangCreateResult.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-19 time：上午09:14:25
 *@version 1.0
 */
public class DangDangCreateResult extends DangDangResult
{
    /**
     *  操作状态码
     *  0： 成功
     *  1：操作失败
     *  2：参数错误（流水号为空）
     *  3：参数错误（订单号为空）
     *  4：参数错误（活动号为空）
     *  5：参数错误（活动号没有对应的礼券批次号）
     *  6：参数错误（手机号码为空）
     *  7：礼券已经发完，无礼券发放
     *  8：参数格式错误
     *  9：参数错误（客户标识为空）
     *  10：参数错误（活动名称为空）
     *  11：参数错误（活动渠道来源为空）
     *  12：参数错误（充值金额为空）
     *  13：参数错误（张数为空）
     *  14：参数错误（发放类型为空）
     *  15：礼券已过期  
     *  16：此订单号已发送过此活动的礼券
     */
    private String error_code;
    /**
     *  操作成功时返回空
     *  否则根据操作状态码返回相应错误
     */
    private String error_msg;
    
    /**
     * 请求流水号，格式：
     *  yyyyMMddHHmmssSSS
     */
    private String request_id;
    /**
     *  订单号，格式:字母_时间戳
     *  XXX_ yyyyMMddHHmmssSSS
     */
    private String order_id;
    private String user_mobile;
    /**
     * 活动号
     */
    private String act_id;
    /**
     * 礼券序列号
     */
    private String coupon_id;
    /**
     * 礼券密码
     */
    private String password;
    
    
    public String getError_code()
    {
        return error_code;
    }
    public void setError_code(String error_code)
    {
        this.error_code = error_code;
    }
    public String getError_msg()
    {
        return error_msg;
    }
    public void setError_msg(String error_msg)
    {
        this.error_msg = error_msg;
    }
    public String getRequest_id()
    {
        return request_id;
    }
    public void setRequest_id(String request_id)
    {
        this.request_id = request_id;
    }
    public String getOrder_id()
    {
        return order_id;
    }
    public void setOrder_id(String order_id)
    {
        this.order_id = order_id;
    }
    public String getUser_mobile()
    {
        return user_mobile;
    }
    public void setUser_mobile(String user_mobile)
    {
        this.user_mobile = user_mobile;
    }
    public String getAct_id()
    {
        return act_id;
    }
    public void setAct_id(String act_id)
    {
        this.act_id = act_id;
    }
    public String getCoupon_id()
    {
        return coupon_id;
    }
    public void setCoupon_id(String coupon_id)
    {
        this.coupon_id = coupon_id;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
	@Override
	public String toString() {
		return "DangDangCreateResult [act_id=" + act_id + ", coupon_id="
				+ coupon_id + ", error_code=" + error_code + ", error_msg="
				+ error_msg + ", order_id=" + order_id + ", password="
				+ password + ", request_id=" + request_id + ", user_mobile="
				+ user_mobile + "]";
	}
    
    
}
