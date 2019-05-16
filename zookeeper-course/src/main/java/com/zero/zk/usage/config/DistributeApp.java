package com.zero.zk.usage.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * java -cp zookeeper-course-1.0-SNAPSHOT-jar-with-dependencies.jar com-zero.zk.usage.config.DistributeApp
 */

public class DistributeApp {
    public static void main(String[] args) throws InterruptedException {
        ConfigManager configManager = new ConfigManager(ConfigPublisher.DB_CFG_PATH);
        while (true) {
            TimeUnit.SECONDS.sleep(5);
            Map<String, String> configMap = configManager.getConfigMap();
            System.out.println("Do Some Things With Config ：" + configMap);
        }
    }
}
