package com.zero.local;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class LocalFileReader {
    public static void main(String[] args) throws IOException {
        String dest = "E:\\PersonalCode\\IDEA-BigData\\hdfs-courses\\src\\main\\java\\com\\zero\\local\\java_local_writer.txt";
        FileInputStream fileInputStream = new FileInputStream(dest);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        fileInputStream.close();
    }
}
