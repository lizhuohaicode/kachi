package com.team.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建日期：2018-1-22上午11:53:22
 * author:wuzhiheng
 */
@Component  
@ConfigurationProperties(prefix = "memcached")  
public class SockIOPoolConfig {  
    /** 服务地址 */  
    private String[] servers;         
    /** 权重 */  
    private Integer[] weights;  
    /** 初始化连接数 */  
    private int initConn;  
    /** 最小连接数 */  
    private int minConn;  
    /** 最大连接数 */  
    private int maxConn;  
    /** 睡眠时长 */  
    private long maintSleep;  
  
    private boolean nagle;  
  
    private int socketTO;  
  
    public String[] getServers() {  
        return servers;  
    }  
  
    public void setServers(String[] servers) {  
        this.servers = servers;  
    }  
  
    public Integer[] getWeights() {  
        return weights;  
    }  
  
    public void setWeights(Integer[] weights) {  
        this.weights = weights;  
    }  
  
    public int getInitConn() {  
        return initConn;  
    }  
  
    public void setInitConn(int initConn) {  
        this.initConn = initConn;  
    }  
  
    public int getMinConn() {  
        return minConn;  
    }  
  
    public void setMinConn(int minConn) {  
        this.minConn = minConn;  
    }  
  
    public int getMaxConn() {  
        return maxConn;  
    }  
  
    public void setMaxConn(int maxConn) {  
        this.maxConn = maxConn;  
    }  
  
    public long getMaintSleep() {  
        return maintSleep;  
    }  
  
    public void setMaintSleep(long maintSleep) {  
        this.maintSleep = maintSleep;  
    }  
  
    public boolean isNagle() {  
        return nagle;  
    }  
  
    public void setNagle(boolean nagle) {  
        this.nagle = nagle;  
    }  
  
    public int getSocketTO() {  
        return socketTO;  
    }  
  
    public void setSocketTO(int socketTO) {  
        this.socketTO = socketTO;  
    }  
} 
