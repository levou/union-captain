package com.kxl.union.captain.zk.event;

import com.kxl.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public interface ZkNodeEventCaller extends ZkEventCaller {

    void callerData(ChildData childData) throws ZookeeperException;
}
