package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocol.packet.LoginRequestPacket;
import protocol.packet.LoginResponsePacket;
import protocol.packet.MessageResponsePacket;
import protocol.packet.Packet;
import protocol.code.PacketCodec;
import utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * 描述:
 * 客户端登录处理
 *
 * @author caorui1
 * @create 2018-10-24 16:28
 */
public class ClientLoginHandler  extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        System.out.print("\n" + new Date() + "客户端开始登录");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("sawcao");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf buffer = PacketCodec.INSTANCE.encode(channelHandlerContext.alloc(), loginRequestPacket);

        //写数据
        channelHandlerContext.channel().writeAndFlush(buffer);

    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println("\n" + new Date() + ": 客户端登录成功");
            } else {
                System.out.println("\n" + new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }

}
