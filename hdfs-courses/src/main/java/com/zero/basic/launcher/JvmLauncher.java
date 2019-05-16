package com.zero.basic.launcher;

import java.util.concurrent.TimeUnit;

public class JvmLauncher {
    public static void main(String[] args) throws InterruptedException {
        String name = System.getProperty("name");
        Long sleepDuration = Long.parseLong(System.getProperty("sleepDuration", "10"));
        System.out.println("sleepDuraiton = " + sleepDuration);
        LauncherParam launcherParam = new LauncherParam();
        launcherParam.setName(name);
        System.out.println(launcherParam);
        TimeUnit.SECONDS.sleep(sleepDuration);
    }
}
