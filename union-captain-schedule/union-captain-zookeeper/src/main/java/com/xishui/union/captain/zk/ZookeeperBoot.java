package com.xishui.union.captain.zk;

import com.xishui.union.captain.zk.config.ZookeeperConfig;
import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.xishui.union.captain.zk.holder.ZookeeperHolder;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.state.ConnectionState;
import com.netflix.curator.framework.state.ConnectionStateListener;
import com.netflix.curator.retry.ExponentialBackoffRetry;

public enum ZookeeperBoot {
    BOOT;

    //启动一个zk connect
    public void boot(ZookeeperConfig zookeeperConfig) throws ZookeeperException {
        if (null == zookeeperConfig) {
            throw new ZookeeperException("ZookeeperConfig is null.");
        }
        if (null == zookeeperConfig.getZookeeperUrl()) {
            throw new ZookeeperException("ZookeeperConfig's URL is null.");
        }
        if (null == zookeeperConfig.getConnectionTimeOut()) {
            zookeeperConfig.setConnectionTimeOut(ZookeeperConfig.DEFAULT_CONNECTION_TIMEOUT);
        }
        if (null == zookeeperConfig.getSessionTimeOut()) {
            zookeeperConfig.setSessionTimeOut(ZookeeperConfig.DEFAULT_SESSION_TIMEOUT);
        }
        if (null == zookeeperConfig.getRetryPolicy()) {
            zookeeperConfig.setRetryPolicy(new ExponentialBackoffRetry(2000, Integer.MAX_VALUE));
        }
        if (null == zookeeperConfig.getClientkey()) {
            zookeeperConfig.setClientkey(ZookeeperConfig.DEFAULT_CLIENT_KEY);
        }
        if (null == zookeeperConfig.getNameSpace()) {
            zookeeperConfig.setNameSpace(ZookeeperConfig.DEFAULT_NAME_SPACE);
        }
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperConfig.getZookeeperUrl())
                .sessionTimeoutMs(zookeeperConfig.getSessionTimeOut())
                .connectionTimeoutMs(zookeeperConfig.getConnectionTimeOut())
                .retryPolicy(zookeeperConfig.getRetryPolicy())
                .namespace(zookeeperConfig.getNameSpace())
                .build();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                //todo
                System.out.println(connectionState.name());
            }
        });
        client.start();
        ZookeeperHolder.HOLDER.addZkClient(zookeeperConfig.getClientkey(), client);
    }

    public CuratorFramework client() {
        return ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY);
    }

    public CuratorFramework client(String clientKey) {
        return ZookeeperHolder.HOLDER.zkClient(clientKey);
    }
}
