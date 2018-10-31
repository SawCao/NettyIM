package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.Seesion.Session;
import protocol.packet.LoginRequestPacket;
import protocol.packet.LoginResponsePacket;
import protocol.packet.MessageResponsePacket;
import protocol.packet.Packet;
import protocol.code.PacketCodec;
import utils.LoginUtil;
import utils.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * 描述:
 * 客户端登录处理
 *
 * @author caorui1
 * @create 2018-10-24 16:28
 */
@ChannelHandler.Sharable
public class ClientLoginHandler  extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final ClientLoginHandler INSTANCE = new ClientLoginHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
            if (loginResponsePacket.isSuccess()) {
                SessionUtil.bindSession(new Session(loginResponsePacket.getId(),loginResponsePacket.getName()),ctx.channel());
                System.out.println("\n" + new Date() + ": 客户端登录成功");
            } else {
                System.out.println("\n" + new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
    }

}
