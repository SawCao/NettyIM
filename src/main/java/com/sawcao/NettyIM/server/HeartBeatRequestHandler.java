package com.sawcao.NettyIM.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.packet.HeartBeatRequestPacket;
import protocol.packet.HeartBeatResponsePacket;
import utils.SessionUtil;

/**
 * 描述:
 * 心跳请求处理
 *
 * @author caorui1
 * @create 2018-11-02 16:48
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        System.out.print("收到心跳包，来自 " + SessionUtil.getSession(ctx.channel()).getName());
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}