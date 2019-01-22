package com.kxl.union.captain.zk.operator;

import com.kxl.union.captain.zk.config.ZookeeperConfig;
import com.kxl.union.captain.zk.holder.ZookeeperHolder;
import org.apache.zookeeper.CreateMode;
@Deprecated
public enum ZookeeperCreator {
    CREATOR;

    /**
     * 创建空节点，默认持久节点
     */
    public void forPath(String path) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create()
                .creatingParentsIfNeeded().forPath(path);
    }

    public void forPath(String path, String pathValue) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .forPath(path, pathValue.getBytes("UTF-8"));
    }
    public void forPersistentPath(String path) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path);
    }

    public void forPersistentPath(String path, String pathValue) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forPersistentSequentialPath(String path) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);
    }

    public void forPersistentSequentialPath(String path, String pathValue) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forEphemeralPath(String path) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    public void forEphemeralPath(String path, String pathValue) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forEphemeralSequentialPath(String path) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
    }

    public void forEphemeralSequentialPath(String path, String pathValue) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(ZookeeperConfig.DEFAULT_CLIENT_KEY).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forPathByClientKey(String path,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create()
                .creatingParentsIfNeeded().forPath(path);
    }

    public void forPathByClientKey(String path, String pathValue,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .forPath(path, pathValue.getBytes("UTF-8"));
    }
    public void forPersistentPathByClientKey(String path,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path);
    }

    public void forPersistentPathByClientKey(String path, String pathValue,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forPersistentSequentialPathByClientKey(String path,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path);
    }

    public void forPersistentSequentialPathByClientKey(String path, String pathValue,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forEphemeralPathByClientKey(String path,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    public void forEphemeralPathByClientKey(String path, String pathValue,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path, pathValue.getBytes("UTF-8"));
    }

    public void forEphemeralSequentialPathByClientKey(String path,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path);
    }

    public void forEphemeralSequentialPathByClientKey(String path, String pathValue,String clientKey) throws Exception {
        ZookeeperHolder.HOLDER.zkClient(clientKey).create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, pathValue.getBytes("UTF-8"));
    }
}
