package com.kxl.union.captain.zk;

import com.kxl.union.captain.zk.config.ZookeeperConfig;
import com.kxl.union.captain.zk.listener.ZookeeperListener;

public class ZkTest {

    public static void main(String... args) {

        ZookeeperBoot.BOOT.boot(ZookeeperConfig.config().url("172.20.16.182:2181"));
        try {
            String path = "/test/node4";
            if (null == ZookeeperBoot.BOOT.client().checkExists().forPath(path)) {
                ZookeeperBoot.BOOT.client().create().creatingParentsIfNeeded().forPath(path,
                        "value".getBytes("UTF-8"));
            }
            byte[] value = ZookeeperBoot.BOOT.client().getData().forPath(path);
            System.out.println("value:" + new String(value));

            ZookeeperListener.listener().addNodeListener(path,new TestNodeEventCaller());
            ZookeeperListener.listener().addChildNodeListener(path,new TestChildNodeEventCaller());

            ZookeeperBoot.BOOT.client().setData().forPath(path, "update".getBytes("UTF-8"));

            if(null == ZookeeperBoot.BOOT.client().checkExists().forPath(path+"/child")) {
                ZookeeperBoot.BOOT.client().create().creatingParentsIfNeeded().forPath(path + "/child", "child".getBytes("UTF-8"));
            }
            Thread.sleep(1000L);
            ZookeeperBoot.BOOT.client().setData().forPath(path + "/child", "childUpdate".getBytes("UTF-8"));

            Thread.sleep(3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
