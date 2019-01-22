package com.xishui.union.captain.zk;

import com.xishui.union.captain.zk.event.ZkChildNodeEventCaller;
import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public class TestChildNodeEventCaller implements ZkChildNodeEventCaller {
    @Override
    public void callerAdded(ChildData childData) throws ZookeeperException {
        System.out.println("Child Node added:" + childData.getPath() + " value:" + new String(childData.getData()));
    }

    @Override
    public void callerUpdate(ChildData childData) throws ZookeeperException {
        System.out.println("Child Node update:" + childData.getPath() + " value:" + new String(childData.getData()));
    }

    @Override
    public void callerRemove(String path) throws ZookeeperException {
        System.out.println("Child Node remove:" + path);
    }
}
