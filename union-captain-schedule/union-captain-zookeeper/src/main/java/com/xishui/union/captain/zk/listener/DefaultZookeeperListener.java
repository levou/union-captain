package com.xishui.union.captain.zk.listener;

import com.xishui.union.captain.zk.ZookeeperUtils;
import com.xishui.union.captain.zk.config.ZookeeperConfig;
import com.xishui.union.captain.zk.event.ZkChildNodeEventCaller;
import com.xishui.union.captain.zk.event.ZkNodeEventCaller;
import com.xishui.union.captain.zk.exception.ZookeeperException;
import com.xishui.union.captain.zk.holder.ZookeeperHolder;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.recipes.cache.ChildData;
import com.netflix.curator.framework.recipes.cache.NodeCache;
import com.netflix.curator.framework.recipes.cache.PathChildrenCache;

public class DefaultZookeeperListener implements ZookeeperListener {

    @Override
    public void addChildNodeListener(String path, ZkChildNodeEventCaller zkChildNodeEventCaller) throws ZookeeperException {
        addChildNodeListener(path, ZookeeperConfig.DEFAULT_CLIENT_KEY, zkChildNodeEventCaller);
    }

    @Override
    public void addNodeListener(String path, ZkNodeEventCaller zkNodeEventCaller) throws ZookeeperException {
        addNodeListener(path, ZookeeperConfig.DEFAULT_CLIENT_KEY, zkNodeEventCaller);
    }

    @Override
    public void addChildNodeListener(String path, String clientKey, ZkChildNodeEventCaller zkChildNodeEventCaller) throws
            ZookeeperException {
        try {
            CuratorFramework zkClient = ZookeeperHolder.HOLDER.zkClient(clientKey);
            if (!ZookeeperUtils.checkNodeExist(zkClient, path)) {
                throw new NullPointerException("Zk Node Not Exist :" + path);
            }
            final PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient,
                    path, true);
            pathChildrenCache.start();
            pathChildrenCache.getListenable().addListener((client, event) -> {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        zkChildNodeEventCaller.callerAdded(event.getData());
                        break;
                    case CHILD_UPDATED:
                        zkChildNodeEventCaller.callerUpdate(event.getData());
                        break;
                    case CHILD_REMOVED:
                        zkChildNodeEventCaller.callerRemove(path);
                        break;
                    default:
                        break;
                }
            });
        } catch (Exception e) {
            throw new ZookeeperException(e);
        }
    }

    @Override
    public void addNodeListener(String path, String clientKey, ZkNodeEventCaller zkNodeEventCaller) throws ZookeeperException {
        try {
            CuratorFramework zkClient = ZookeeperHolder.HOLDER.zkClient(clientKey);
            if (!ZookeeperUtils.checkNodeExist(zkClient, path)) {
                throw new NullPointerException("Zk Node Not Exist :" + path);
            }
            final NodeCache nodeCache = new NodeCache(zkClient, path);
            nodeCache.start();
            nodeCache.getListenable().addListener(() -> {
                ChildData childData = nodeCache.getCurrentData();
                if (null == childData) {
                    zkNodeEventCaller.callerRemove(path);
                } else {
                    zkNodeEventCaller.callerData(childData);
                }
            });
        } catch (Exception e) {
            throw new ZookeeperException(e);
        }
    }
}
