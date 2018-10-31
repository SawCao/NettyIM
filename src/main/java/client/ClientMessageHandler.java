package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class ClientMessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    public static final ClientMessageHandler INSTANCE = new ClientMessageHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        //获取来自别的客户端的消息
        System.out.println(new Date() + ": 收到来自："+ msg.getFromName() +"的消息: " + msg.getMessage());
    }

}


