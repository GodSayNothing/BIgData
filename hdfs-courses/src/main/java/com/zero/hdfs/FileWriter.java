package com.zero.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class FileWriter {
    public static void main(String[] args) throws IOException{
        String content = "this is an example";
        String dest = "hdfs://192.168.64.132:9000/user/hadoop-zero/java_hdfs_writer.txt";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(dest), configuration);
        FSDataOutputStream out = fileSystem.create(new Path(dest));
        out.write(content.getBytes("UTF-8"));
        out.close();
    }
}
