package com.zero.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZNodeSetData implements Watcher {
    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("master:2181", 5000, new ZNodeSetData());
        countDownLatch.await();
        zooKeeper.setData("/app3", "data3".getBytes(), 0);
        // zooKeeper.setData("/app3", "data3".getBytes(), -1);
        // version:表示待更新节点的版本号, 不考虑版本时 version 置为 -1
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
}
