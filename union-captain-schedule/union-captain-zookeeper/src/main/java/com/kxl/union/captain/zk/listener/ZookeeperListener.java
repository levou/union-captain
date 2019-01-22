package com.kxl.union.captain.zk.listener;

import com.kxl.union.captain.zk.event.ZkChildNodeEventCaller;
import com.kxl.union.captain.zk.event.ZkNodeEventCaller;
import com.kxl.union.captain.zk.exception.ZookeeperException;

public interface ZookeeperListener {

    void addChildNodeListener(String path, ZkChildNodeEventCaller zkChildNodeEventCaller) throws ZookeeperException;

    void addNodeListener(String path, ZkNodeEventCaller zkNodeEventCaller) throws ZookeeperException;

    void addChildNodeListener(String path,String clientKey, ZkChildNodeEventCaller zkChildNodeEventCaller) throws ZookeeperException;

    void addNodeListener(String path,String clientKey, ZkNodeEventCaller zkNodeEventCaller) throws ZookeeperException;

    static ZookeeperListener listener(){
        return new DefaultZookeeperListener();
    }
}
