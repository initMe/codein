package com.initMe.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * @Description:
 * @Author: jiqing
 * @Date: 2022/1/5 10:50 AM
 **/
public class EchoServer {

    public static void main(String[] args) throws Exception {
        ServerBootstrap bs = new ServerBootstrap();
        boolean useEpoll = false;
        EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup eventLoopGroupBoss;
        EventLoopGroup eventLoopGroupSelector;
        if (useEpoll) {
            eventLoopGroupBoss = new EpollEventLoopGroup();
            eventLoopGroupSelector = new EpollEventLoopGroup();
        } else {
            eventLoopGroupBoss = new NioEventLoopGroup();
            eventLoopGroupSelector = new NioEventLoopGroup();
        }
        try {
            bs.group(eventLoopGroupBoss, eventLoopGroupSelector)
                    .channel(useEpoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(echoServerHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bs.bind(8001).sync();
            System.out.println("server started...");
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroupBoss.shutdownGracefully().sync();
            eventLoopGroupSelector.shutdownGracefully().sync();
        }
    }


    @ChannelHandler.Sharable
    public static class EchoServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            System.out.println("receive msg: " + in.toString(CharsetUtil.UTF_8));
            ctx.write(in);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
