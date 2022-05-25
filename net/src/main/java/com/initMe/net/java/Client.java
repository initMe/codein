package com.initMe.net.java;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2021/12/31 2:50 PM
 **/
public class Client {
    private Socket socket;

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.startBClient();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (Objects.nonNull(client.getSocket())) {
                try {
                    System.out.println("shutdown client");
                    client.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    public void startBClient() throws Exception {
        socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8002), 5000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.print(">>> "); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.println();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }


}
