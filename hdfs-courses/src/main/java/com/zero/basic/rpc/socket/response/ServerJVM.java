package com.zero.basic.rpc.socket.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerJVM {
    public static void main(String[] args) throws IOException {
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        System.out.println("启动server socket. 将一直等待连接的到来");
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0,len, "UTF-8"));
        }
        String msg = sb.toString();
        System.out.println(msg);
        inputStream.close();
        socket.close();
        server.close();
    }
}
