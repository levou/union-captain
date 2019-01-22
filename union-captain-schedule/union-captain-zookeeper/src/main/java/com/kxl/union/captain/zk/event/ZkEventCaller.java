package com.kxl.union.captain.zk.event;

import com.kxl.union.captain.zk.exception.ZookeeperException;

public interface ZkEventCaller {

    void callerRemove(String path) throws ZookeeperException;
}
