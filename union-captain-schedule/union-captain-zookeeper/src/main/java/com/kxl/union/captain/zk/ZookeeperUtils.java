package com.kxl.union.captain.zk;

import com.netflix.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public final class ZookeeperUtils {

    public static boolean checkNodeExist(CuratorFramework client, String path) {
        try {
            Stat stat = client.checkExists().forPath(path);
            return stat != null;
        }catch (Exception e){

            return false;
        }
    }
}
