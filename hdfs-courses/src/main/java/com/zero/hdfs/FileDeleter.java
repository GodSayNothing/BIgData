package com.zero.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;
import java.net.URI;

public class FileDeleter {
    public static void main(String[] args) throws IOException {
        String dest = "hdfs://master:9000/user/hadoop-zero/a.txt";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(dest), configuration);
        fileSystem.delete(new Path(dest), false); //删除文件要置为false
        fileSystem.delete(new Path("hdfs://master:9000/user/hadoop-zero/temp"),true); //删除目录要置为true
    }
}
