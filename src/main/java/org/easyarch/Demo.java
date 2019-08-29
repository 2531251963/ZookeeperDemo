package org.easyarch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs;
import java.io.IOException;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author Liyihe
 * @Date 2019/08/29 下午4:44
 * @Version 1.0
 */
public class Demo {
    public static ZooKeeper getZk() throws IOException {
        ZooKeeper zk = new ZooKeeper("localhost:2181",600000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                System.out.println("已经触发了" + event.getType() + "事件！");
            }
        });
        return zk;
    }
/**
 * CreateMode类型分为4种
 *
 * 1.PERSISTENT--持久型
 *
 * 2.PERSISTENT_SEQUENTIAL--持久顺序型
 *
 * 3.EPHEMERAL--临时型
 *
 * 4.EPHEMERAL_SEQUENTIAL--临时顺序型
 *
 */
    public static void main(String[] args) throws Exception {
        ZooKeeper zk=getZk();
        // 创建一个目录节点
        zk.create("/testRootPath", "testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        // 创建一个子目录节点
        zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath",false,null)));
        // 取出子目录节点列表
        System.out.println(zk.getChildren("/testRootPath",true));
        // 修改子目录节点数据
        zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(),-1);
        System.out.println("目录节点状态：["+zk.exists("/testRootPath",true)+"]");
        // 创建另外一个子目录节点
        zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo",true,null)));
        // 删除子目录节点
        zk.delete("/testRootPath/testChildPathTwo",-1);
        zk.delete("/testRootPath/testChildPathOne",-1);
        // 删除父目录节点
        zk.delete("/testRootPath",-1);
        // 关闭连接
        zk.close();
    }
}
