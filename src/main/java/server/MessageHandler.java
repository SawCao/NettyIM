package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.Seesion.Session;
import protocol.code.PacketCodec;
import protocol.packet.MessageRequestPacket;
import protocol.packet.MessageResponsePacket;
import utils.SessionUtil;

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
        //拿到消息发送方的session
        Session session = SessionUtil.getSession(ctx.channel());

        //构造会话信息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromId(session.getId());
        messageResponsePacket.setFromName(session.getName());
        messageResponsePacket.setMessage(msg.getMessage());

        //获取消息接收方的channel
        Channel toChannel = SessionUtil.getChannel(msg.getToId());

        //将消息发送给接收方
        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            toChannel.writeAndFlush(messageResponsePacket);
            System.out.print("向客户端：" + msg.getToId() + "发送了消息");
        } else {
            System.err.println("[" + msg.getToId() + "] 不在线，发送失败!");
        }
    }
}
