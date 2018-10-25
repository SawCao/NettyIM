package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.code.PacketCodec;
import protocol.packet.MessageRequestPacket;
import protocol.packet.MessageResponsePacket;
import protocol.packet.Packet;

import java.util.Date;

/**
 * 描述:
 * 与服务端交互的消息处理
 *
 * @author caorui1
 * @create 2018-10-25 17:16
 */
public class ClientMessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + msg.getMessage());
    }

}


