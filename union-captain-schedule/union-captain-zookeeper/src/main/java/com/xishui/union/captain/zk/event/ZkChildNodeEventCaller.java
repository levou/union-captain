package com.xishui.union.captain.zk.event;

import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public interface ZkChildNodeEventCaller extends ZkEventCaller {

    void callerAdded(ChildData childData) throws ZookeeperException;

    void callerUpdate(ChildData childData) throws ZookeeperException;
    
}
