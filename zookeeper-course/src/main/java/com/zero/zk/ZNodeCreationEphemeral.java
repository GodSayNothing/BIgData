package com.zero.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZNodeCreationEphemeral implements Watcher {
    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("master:2181", 5000, new ZNodeCreationEphemeral());
        countDownLatch.await();
        // 创建持久节点
        String path1 = zooKeeper.create("/app3/p_2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("succ create node : " + path1);
        // 创建一个持久有顺序的节点
        String path2 = zooKeeper.create("/app3/p_3", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("succ create node : " + path2);
        //
        String path3 = zooKeeper.create("/app3/p_3", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("succ create node : " + path3);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
}
