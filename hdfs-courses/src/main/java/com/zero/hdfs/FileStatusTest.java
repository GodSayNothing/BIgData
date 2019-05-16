package com.zero.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;
import java.net.URI;


public class FileStatusTest {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://master:9000/"), configuration);
        // 查看一个文件的状态信息
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("hdfs://192.168.64.132:9000/user/hadoop-zero/java_hdfs_writer.txt"));
        System.out.println(fileStatus.getPath());
        System.out.println(fileStatus.getBlockSize());
        System.out.println(fileStatus.getLen()); // 总大小
        System.out.println(fileStatus.getReplication());
        System.out.println(fileStatus.isDirectory());
        System.out.println(fileStatus.isFile());
        System.out.println(fileStatus.getOwner());
        System.out.println(fileStatus.getGroup());
        System.out.println(fileStatus.getPermission());

        // 查看目录下所有文件信息
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("hdfs://192.168.64.132:9000/user/hadoop-zero"));
        for (FileStatus temp : fileStatuses) {
            System.out.println(temp.getPath());
            System.out.println(temp.getBlockSize());
            System.out.println(temp.getLen()); // 总大小
            System.out.println(temp.getReplication());
            System.out.println(temp.isDirectory());
            System.out.println(temp.isFile());
            System.out.println(temp.getOwner());
            System.out.println(temp.getGroup());
            System.out.println(temp.getPermission());
        }
    }
}
