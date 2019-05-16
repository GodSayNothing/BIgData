package com.zero.hdfs.webhdfs;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;

public class HDFSHttpTest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 以http方式访问HDFS
        HttpGet httpget = new HttpGet("http://192.168.64.132:50070/webhdfs/v1/user/hadoop-zero/a.txt?op=OPEN");
        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity httpEntity = response.getEntity();
        String strResult = EntityUtils.toString(httpEntity);
        System.out.println(strResult);
    }
}
