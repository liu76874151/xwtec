package com.xwtech.uomp.base.util.order;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SendTicketResultV2
{
    private boolean isSuccess = false;
    
    private String ret;
    
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
    
    public String toString()
    {
        try
        {
            return "ret:"+ret+",\nmsg:"+URLDecoder.decode(msg,"utf-8")+",\nisSuccess:"+isSuccess;
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e);
        }
        return "ret:"+ret+",\nmsg:"+msg+",\nisSuccess:"+isSuccess; 
    }
}
