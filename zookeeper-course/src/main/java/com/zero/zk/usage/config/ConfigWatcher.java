package com.zero.zk.usage.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ConfigWatcher implements Watcher {
    private ConfigManager configManager;
    private ZooKeeper zooKeeper;
    public ConfigWatcher(ConfigManager configManager, ZooKeeper zooKeeper) {
        this.configManager = configManager;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            String config = new String(zooKeeper.getData(watchedEvent.getPath(), new ConfigWatcher(configManager, zooKeeper),null));
            configManager.setConfigMap(Utils.parseConfig(config));
            System.out.println("修改后的配置: " + configManager.getConfigMap());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
