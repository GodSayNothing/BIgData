package com.zero.zk.usage.lock;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        DistributedLock lock = new DistributedLock("master:2181", "app2");
        lock.lock();
        System.out.println("Do some things With DataBase");
        Long sleepDuration = Long.parseLong(args[0]);
        TimeUnit.SECONDS.sleep(sleepDuration);
        if (lock != null) {
            lock.unlock();
        }
    }
}
