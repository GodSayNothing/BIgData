package com.zero.zk.usage.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConfigClient implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private String nodePath;
    public ConfigClient() {

    }

    public void init(String nodePath, ConfigManager configManager) {
        this.nodePath = nodePath;
        try {
            zooKeeper = new ZooKeeper("master:2181", 5000, new ConfigClient());
            countDownLatch.await();
            String config = new String(zooKeeper.getData(this.nodePath, new ConfigWatcher(configManager, zooKeeper), null));
            configManager.setConfigMap(Utils.parseConfig(config));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }
}
