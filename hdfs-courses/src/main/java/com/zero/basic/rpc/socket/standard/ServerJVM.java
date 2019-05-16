package com.zero.basic.rpc.socket.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerJVM {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5678);
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        while (true) {
            String str = in.readLine();
            System.out.println(str);
            if (str.equals("end")) {
                break;
            } else if ("say hello".equals(str)) {
                out.println("Hello from server");
            } else if ("get server time".equals(str)) {
                String now = dateFormat.format(new Date());
            } else if ("get data".equals(str)) {
                String data = "Some Data";
                out.println(data);
            } else {
                out.println("not support operation");
            }
            out.flush();
        }
        client.close();
        server.close();
    }
}
