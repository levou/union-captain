package com.xishui.union.captain.zk.event;

import com.xishui.union.captain.zk.exception.ZookeeperException;

public interface ZkEventCaller {

    void callerRemove(String path) throws ZookeeperException;
}
