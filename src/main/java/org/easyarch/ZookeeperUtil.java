package org.easyarch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @ClassName ZookeeperUtil
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/08/24 下午3:58
 * @Version 1.0
 */
public class ZookeeperUtil {
    private  static String zkconfig = "localhost:2181";
    public static final String SERVICECENTRE= "/myservicecentre";
    private static ZooKeeper zk;
    static {
        try {
            zk = new ZooKeeper(zkconfig,6000,null);
            if (zk.exists(SERVICECENTRE,null)==null){
                String res=zk.create(SERVICECENTRE,null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ZooKeeper getZkClient(){
        return zk;
    }

}
