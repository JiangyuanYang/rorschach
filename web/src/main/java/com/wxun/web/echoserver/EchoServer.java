package com.wxun.web.echoserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


/**
 * 一个服务器 handler：这个组件实现了服务器的业务逻辑，决定了连接创建后和接收到信息后该如何处理
 * Bootstrapping： 这个是配置服务器的启动代码。最少需要设置服务器绑定的端口，用来监听连接请求。
 *
 * @author Zhuwx
 * @since 2018-06-20 20:36
 */
public class EchoServer {
	private final int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println(
					"Usage: " + EchoServer.class.getSimpleName() +
							" <port>");
			return;
		}
		//获取端口
		int port = Integer.parseInt(args[0]);
		//启动
		new EchoServer(port).start();
	}

	/**
	 * 监听和接收进来的连接请求
	 * 配置 Channel 来通知一个关于入站消息的 EchoServerHandler 实例
	 *
	 * @throws Exception
	 */
	public void start() throws Exception {
		//3.创建 EventLoopGroup
		NioEventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			//4.创建 ServerBootstrap
			b.group(group)
					//5.指定使用 NIO 的传输 Channel
					.channel(NioServerSocketChannel.class)
					//6.设置 socket 地址使用所选的端口
					.localAddress(new InetSocketAddress(port))
					//7.添加 EchoServerHandler 到 Channel 的 ChannelPipeline
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(
									new EchoServerHandler());
						}
					});
			//8.绑定的服务器;sync 等待服务器关闭
			ChannelFuture f = b.bind().sync();
			System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
			//9.关闭 channel 和 块，直到它被关闭
			f.channel().closeFuture().sync();
		} finally {
			//10.关闭 EventLoopGroup，释放所有资源。
			group.shutdownGracefully().sync();
		}
	}

}
