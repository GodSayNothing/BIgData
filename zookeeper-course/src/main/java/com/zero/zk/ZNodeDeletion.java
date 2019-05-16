package com.zero.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZNodeDeletion implements Watcher {
    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("master:2181", 5000, new ZNodeDeletion());
        countDownLatch.await();
        // delete只能删除一个节点，有子节点则不能
        zooKeeper.delete("/app3/p_10000000000", -1);
        zooKeeper.delete("/app3/p_10000000001", -1);
        zooKeeper.delete("/app3", -1);
        // version -1 意思是任何版本，不在乎版本
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
}
