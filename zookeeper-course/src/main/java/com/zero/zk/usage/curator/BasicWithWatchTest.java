package com.zero.zk.usage.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class BasicWithWatchTest {
    private static CuratorFramework client = CuratorFrameworkFactory.newClient("master:2181", new ExponentialBackoffRetry(1000, 3));
    public static void main(String[] args) throws Exception {
        client.start();
        client.getData().usingWatcher(new DataGetterWatch()).forPath("/test");
        client.getChildren().usingWatcher(new ChildrenGetterWatch()).forPath("/test");
        client.checkExists().usingWatcher(new ExistWatch()).forPath("/test");
    }
    public static class DataGetterWatch implements Watcher{
        @Override
        public void process(WatchedEvent watchedEvent) {
            try {
                System.out.println(client.getChildren().usingWatcher(new DataGetterWatch()).forPath(watchedEvent.getPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ChildrenGetterWatch implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            try {
                System.out.println(client.getChildren().usingWatcher(new DataGetterWatch()).forPath(watchedEvent.getPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ExistWatch implements Watcher {
        @Override
        public void process(WatchedEvent watchedEvent) {
            try {
                if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")Created");
                } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")Deleted");
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("Node(" + watchedEvent.getPath() + ")DataChanged");
                }
                client.checkExists().usingWatcher(new ExistWatch()).forPath(watchedEvent.getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
