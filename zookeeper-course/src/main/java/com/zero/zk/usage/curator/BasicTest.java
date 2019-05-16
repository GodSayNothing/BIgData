package com.zero.zk.usage.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class BasicTest {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("master:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        // 创建一个节点
        client.create().forPath("/zk-test");
        // 创建一个临时节点
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/zt-test");
        // 创建一个节点，如果此节点父节点不存在则优先创建父节点
        client.create().creatingParentsIfNeeded().forPath("/curator/test");

        // 修改节点内容
        client.setData().forPath("/curator/test", "data".getBytes());
        // 指定版本修改节点的内容
        client.setData().withVersion(1).forPath("/curator/test", "data".getBytes());
        // 删除一个节点以及子节点
        client.delete().deletingChildrenIfNeeded().forPath("/curator/test");
        // 指定版本删除节点的内容
        client.delete().withVersion(1).forPath("/test");
        // 保证一定能成功删除一个节点
        client.delete().guaranteed().forPath("/test");
    }
}
