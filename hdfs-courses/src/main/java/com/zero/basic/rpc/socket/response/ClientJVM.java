package com.zero.basic.rpc.socket.response;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientJVM {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55533;
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        String message = "say hello";
        byte[] sendBytes = message.getBytes("UTF-8");
        outputStream.write(sendBytes);
        outputStream.close();
        socket.close();
    }
}
