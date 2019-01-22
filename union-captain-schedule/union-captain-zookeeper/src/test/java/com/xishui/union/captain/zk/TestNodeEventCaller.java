package com.xishui.union.captain.zk;

import com.xishui.union.captain.zk.event.ZkNodeEventCaller;
import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public class TestNodeEventCaller implements ZkNodeEventCaller {
    @Override
    public void callerData(ChildData childData) throws ZookeeperException {
        System.out.println("Node Event Change Value:" + new String(childData.getData()));
    }

    @Override
    public void callerRemove(String path) throws ZookeeperException {
        System.out.println("Node Remove path:" + path);
    }
}
