package com.zero.zk.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DataGetter implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper("master:2181", 5000, new DataGetter());
        countDownLatch.await();
        String path = "/app4";
        zooKeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(new String(zooKeeper.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + " " + stat.getMzxid() + " " + stat.getVersion());
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            try {
                // 这边一定要再次注册，否则不会再watch了
                System.out.println("检测到的path = " + watchedEvent.getPath());
                System.out.println("type = " + watchedEvent.getType());
                System.out.println("state = " + watchedEvent.getState());
                System.out.println(new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)));
                System.out.println(stat.getCzxid() + " " + stat.getMzxid() + " " + stat.getVersion());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
