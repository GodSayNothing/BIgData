package com.zero.hdfs;


import org.apache.hadoop.conf.Configuration;
import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;

public class FileMaker {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://master:9000/"), configuration);
        fileSystem.mkdirs(new Path("hdfs://master:9000/user/hadoop-zero/java")); // 默认 755
        // FsAction.ALL, FsAction.EXECUTE, FsAction.NONE
        // 属主          属组              Other
        fileSystem.mkdirs(new Path("hdfs://master:9000/user/hadoop-zero/scala"), new FsPermission(FsAction.ALL, FsAction.EXECUTE, FsAction.NONE));
    }
}
