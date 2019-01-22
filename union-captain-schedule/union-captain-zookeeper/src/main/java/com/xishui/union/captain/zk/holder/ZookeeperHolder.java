package com.xishui.union.captain.zk.holder;

import com.netflix.curator.framework.CuratorFramework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ZookeeperHolder {
    HOLDER;

    private final static Map<String, CuratorFramework> ZKS = new ConcurrentHashMap<>();

    public void addZkClient(String clientKey, final CuratorFramework curatorFramework) {
        ZookeeperHolder.ZKS.put(clientKey, curatorFramework);
    }

    public CuratorFramework zkClient(String clientKey) {
        return ZookeeperHolder.ZKS.get(clientKey);
    }
}
