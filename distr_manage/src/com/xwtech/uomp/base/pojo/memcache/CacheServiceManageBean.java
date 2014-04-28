package com.xwtech.uomp.base.pojo.memcache;

import java.io.Serializable;

/**
 * 项目名称：js_obsh_manage
 * 类名称：CacheServiceManageBean
 * 类描述：   缓存服务管理 bean
 * 创建人：zangjianhua
 * 创建时间：2012-5-22 下午08:24:12
 * 修改人：Administrator
 * 修改时间：2012-5-22 下午08:24:12
 * 修改备注：
 */
public class CacheServiceManageBean implements Serializable {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = -2066673173504343727L;

    /**
     * 缓存编码
     */
    private String num;

    /**
     * 缓存类型编码
     */
    private String typeNum;
    /**
     * 服务器列表
     */
    private String servers;
    /**
     * 服务器的权重
     */
    private String weights;
    /**
     * 初始连接数
     */
    private String initConn;
    /**
     * 最小连接数
     */
    private String minConn;
    /**
     * 最大连接数
     */
    private String maxConn;
    /**
     * 最大处理时间
     */
    private String maxIdle;
    /**
     * 主线程睡眠时间
     */
    private String mainSleep;
    /**
     * TCP参数nagle
     */
    private String nagle;
    /**
     * TCP参数socketTo
     */
    private String socketTo;
    /**
     * TCP参数socketConnectTo
     */
    private String socketConnectTo;

    /**
     * 该值存在t_memcached_service_da 表中
     * 参数类型（只标记区域和品牌） 空或者0：无参数。1：区域。2：品牌。3：区域+品牌
     */
    private String paraFlag;
    private String serverType;
    private String redHost;
    private String redPort;
    private String redPass;
    private String redDefaultdb;
    private String redTimeout;
    private String redMaxactive;
    private String redMaxidle;
    private String redMaxwait;
    private String redTestonborrow;


    public String getParaFlag() {
        return paraFlag;
    }

    public void setParaFlag(String paraFlag) {
        this.paraFlag = paraFlag;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getInitConn() {
        return initConn;
    }

    public void setInitConn(String initConn) {
        this.initConn = initConn;
    }

    public String getMinConn() {
        return minConn;
    }

    public void setMinConn(String minConn) {
        this.minConn = minConn;
    }

    public String getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(String maxConn) {
        this.maxConn = maxConn;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMainSleep() {
        return mainSleep;
    }

    public void setMainSleep(String mainSleep) {
        this.mainSleep = mainSleep;
    }

    public String getNagle() {
        return nagle;
    }

    public void setNagle(String nagle) {
        this.nagle = nagle;
    }

    public String getSocketTo() {
        return socketTo;
    }

    public void setSocketTo(String socketTo) {
        this.socketTo = socketTo;
    }

    public String getSocketConnectTo() {
        return socketConnectTo;
    }

    public void setSocketConnectTo(String socketConnectTo) {
        this.socketConnectTo = socketConnectTo;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getRedHost() {
        return redHost;
    }

    public void setRedHost(String redHost) {
        this.redHost = redHost;
    }

    public String getRedPort() {
        return redPort;
    }

    public void setRedPort(String redPort) {
        this.redPort = redPort;
    }

    public String getRedPass() {
        return redPass;
    }

    public void setRedPass(String redPass) {
        this.redPass = redPass;
    }

    public String getRedDefaultdb() {
        return redDefaultdb;
    }

    public void setRedDefaultdb(String redDefaultdb) {
        this.redDefaultdb = redDefaultdb;
    }

    public String getRedTimeout() {
        return redTimeout;
    }

    public void setRedTimeout(String redTimeout) {
        this.redTimeout = redTimeout;
    }

    public String getRedMaxactive() {
        return redMaxactive;
    }

    public void setRedMaxactive(String redMaxactive) {
        this.redMaxactive = redMaxactive;
    }

    public String getRedMaxidle() {
        return redMaxidle;
    }

    public void setRedMaxidle(String redMaxidle) {
        this.redMaxidle = redMaxidle;
    }

    public String getRedMaxwait() {
        return redMaxwait;
    }

    public void setRedMaxwait(String redMaxwait) {
        this.redMaxwait = redMaxwait;
    }

    public String getRedTestonborrow() {
        return redTestonborrow;
    }

    public void setRedTestonborrow(String redTestonborrow) {
        this.redTestonborrow = redTestonborrow;
    }
}
