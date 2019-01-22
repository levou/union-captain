package com.kxl.union.captain.zk.event;

import com.kxl.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public class ZkNodeEventCallerDelegate implements ZkNodeEventCaller {

    private final ZkNodeEventCaller delegate;

    public ZkNodeEventCallerDelegate(ZkNodeEventCaller delegate) {
        this.delegate = delegate;
    }

    public static ZkNodeEventCaller delegate(ZkNodeEventCaller zkNodeEventCaller) {
        return new ZkNodeEventCallerDelegate(zkNodeEventCaller);
    }

    @Override
    public void callerData(ChildData childData) throws ZookeeperException {
        delegate.callerData(childData);
    }

    @Override
    public void callerRemove(String path) throws ZookeeperException {
        delegate.callerRemove(path);
    }
}
