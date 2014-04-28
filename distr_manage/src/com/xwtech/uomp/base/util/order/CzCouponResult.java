package com.xwtech.uomp.base.util.order;



/**
 *@ClassName:CzCouponResult.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-19 time：上午09:14:08
 *@version 1.0
 */
public class CzCouponResult
{
	/**
	 * 0000      成功
	 * -1111   异常
	 * 9999      无可使用礼券
	 */
    private String ret;
    private String msg;
    private boolean isSuccess;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
    
}
