package com.xwtech.uomp.base.util.order;

public class DangDangResult
{
    private boolean isSuccess = false;
    
   /**
    * 报文返回系统级错误
    * SYS00001:系统级输入参数为空
    * SYS00002:不存在的授权资源编号
    * SYS00003: 接口未知异常 
    * SYS00004:无权访问该接口   
    */
    private String ret;
    /**
     * 错误信息，没有错误时 errorMessage为空
     */
    private String msg;

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public String getRet()
    {
        return ret;
    }

    public void setRet(String ret)
    {
        this.ret = ret;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

}
