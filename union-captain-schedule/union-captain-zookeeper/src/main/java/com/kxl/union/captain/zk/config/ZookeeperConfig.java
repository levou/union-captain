package com.kxl.union.captain.zk.config;

import com.netflix.curator.RetryPolicy;
import lombok.Data;

@Data
public class ZookeeperConfig {

    public static final Integer DEFAULT_SESSION_TIMEOUT = 60000;
    public static final Integer DEFAULT_CONNECTION_TIMEOUT = 60000;
    public static final String DEFAULT_CLIENT_KEY = "default.zookeeper.client.key";
    public static final String DEFAULT_NAME_SPACE = "defaultNS";
    private String clientkey;
    private String zookeeperUrl;
    private Integer sessionTimeOut;
    private Integer connectionTimeOut;

    private String nameSpace;
    //重试策略
    private RetryPolicy retryPolicy;

    public static ZookeeperConfig config() {
        return new ZookeeperConfig();
    }

    public ZookeeperConfig url(String connectionUrl) {
        this.zookeeperUrl = connectionUrl;
        return this;
    }
}
