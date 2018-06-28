package com.wxun.web.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.channel.ChannelHandler.Sharable;

/**
 * @author Zhuwx
 * @since 2018-06-20 20:37
 */
@Sharable
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


	/** 每个信息入站都会调用*/
	@Override
	public void channelRead(ChannelHandlerContext ctx,
							Object msg) {
		ByteBuf in = (ByteBuf) msg;
		//2服务端接受到请求，并吧请求的内容返回
		System.out.println("EchoServer received: " + in.toString(CharsetUtil.UTF_8));
		log.info("服务端接受到请求内容"+in.toString(CharsetUtil.UTF_8));
		//3将所接收的消息返回给发送者。注意，这还没有冲刷数据
		ByteBuf buffer = Unpooled.buffer(18);
		String returnStr= "信仰会制裁你";
		ByteBufUtil.writeUtf8(buffer,returnStr);
		ctx.write(buffer);
	}
	/** 通知处理器最后的 channelRead() 是当前批处理中的最后一条消息时调用*/
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//4冲刷所有待审消息到远程节点。关闭通道后，操作完成
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
				.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,
								Throwable cause) {
		//5打印异常栈
		cause.printStackTrace();
		//6关闭通道
		ctx.close();
	}

}
