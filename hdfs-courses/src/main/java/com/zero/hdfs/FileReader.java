package com.zero.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class FileReader {
    public static void main(String[] args) throws IOException {
        String dest = "/user/hadoop-zero/java_hdfs_writer.txt";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(configuration);
        FSDataInputStream in = fileSystem.open(new Path(dest));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }
}
