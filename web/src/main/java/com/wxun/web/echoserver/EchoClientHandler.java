package com.wxun.web.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.channel.ChannelHandler.Sharable;

/**
 * @author Zhuwx
 * @since 2018-06-21 09:26
 */
@Sharable
@Slf4j
public class EchoClientHandler extends
		SimpleChannelInboundHandler<ByteBuf> {
	//2.当被通知该 channel 是活动的时候就发送信息
	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		ctx.writeAndFlush(Unpooled.copiedBuffer("信仰圣光吧!",
				CharsetUtil.UTF_8));
	}
	//3.记录接收到的消息
	@Override
	public void channelRead0(ChannelHandlerContext ctx,
							 ByteBuf in) {
		System.out.println("EchoClient received: " + in.toString(CharsetUtil.UTF_8));    //3
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,
								Throwable cause) {                    //4
		cause.printStackTrace();
		ctx.close();
	}
}
