package com.zero.basic;

import java.io.*;

public class JavaSerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "block.txt";
        deSerialize(fileName);
    }

    private static void serialize(String fileName) throws IOException {
        Block block = new Block(78062594L, 39447755L, 56736651L);
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(block);
        objectOutputStream.close();
    }

    private static void deSerialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object o = objectInputStream.readObject();
        Block block = (Block)o;
        System.out.println(block);
    }
}
