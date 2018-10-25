package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.code.PacketCodec;
import protocol.packet.MessageRequestPacket;
import protocol.packet.MessageResponsePacket;

import java.util.Date;

/**
 * 描述:
 * 与客户端发送消息的处理
 *
 * @author caorui1
 * @create 2018-10-25 17:58
 */
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 处理消息
        System.out.println(new Date() + ": 收到客户端消息: " + msg.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + msg.getMessage() + "】");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
