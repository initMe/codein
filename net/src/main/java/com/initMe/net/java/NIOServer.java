package com.initMe.net.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2021/12/31 2:36 PM
 **/
public class NIOServer {

    public static void main(String[] args) throws Exception {
        NIOServer server = new NIOServer();
        //server.startBlockServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8002));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer readBuff = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(128);

        while (true) {
            int readyNum = selector.select();
            if (readyNum == 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    System.out.println("isAcceptable");
                    // 创建新的连接，并且把连接注册到selector上，而且，
                    // 声明这个channel只对读操作感兴趣。
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuff.clear();
                    socketChannel.read(readBuff);

                    readBuff.flip();
                    System.out.println("received : " + new String(readBuff.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                }
                if (key.isWritable()) {
                    System.out.println("isWritable");
                    writeBuff.clear();
                    writeBuff.put("echo".getBytes(StandardCharsets.UTF_8));
                    writeBuff.flip();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    while (writeBuff.hasRemaining()) {
                        socketChannel.write(writeBuff);
                    }
                    key.interestOps(SelectionKey.OP_READ);
                }
                if (key.isConnectable()) {
                    System.out.println("isConnectable");
                }

                iterator.remove();
            }
        }
    }

    public void startBlockServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8002));
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = serverSocket.accept();
            if (socket.isConnected()) {
                System.out.println("client connected:" + socket.getInetAddress().getHostAddress());
            }
            if (!socket.isClosed()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                String content;
                while ((content = bufferedReader.readLine()) != null) {
                    System.out.println("receive client message： " + content);
                    printWriter.println("ack!");
                }
            } else {
                System.out.println("client disconnected:" + socket.getInetAddress().getHostAddress());
            }
        }
    }
}
