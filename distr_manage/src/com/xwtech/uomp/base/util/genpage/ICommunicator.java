package com.xwtech.uomp.base.util.genpage;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-25
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public interface ICommunicator {
    String doLocalRequest(String url, Properties params);

    String doWapRequest(String url, Properties params);
}
