package com.xishui.union.captain.zk.event;

import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.netflix.curator.framework.recipes.cache.ChildData;

public class ZkChildNodeEventCallerDelegate implements ZkChildNodeEventCaller {

    private final ZkChildNodeEventCaller delegate;

    public static ZkChildNodeEventCallerDelegate delegate(ZkChildNodeEventCaller zkChildNodeEventCaller) {
        return new ZkChildNodeEventCallerDelegate(zkChildNodeEventCaller);
    }

    public ZkChildNodeEventCallerDelegate(ZkChildNodeEventCaller delegate) {
        this.delegate = delegate;
    }

    @Override
    public void callerAdded(ChildData childData) throws ZookeeperException {
        delegate.callerAdded(childData);
    }

    @Override
    public void callerUpdate(ChildData childData) throws ZookeeperException {
        delegate.callerUpdate(childData);
    }

    @Override
    public void callerRemove(String path) throws ZookeeperException {
        delegate.callerRemove(path);
    }
}
