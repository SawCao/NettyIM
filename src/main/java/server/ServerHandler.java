package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import protocol.LoginRequestPacket;
import protocol.LoginResponsePacket;
import protocol.Packet;
import protocol.code.PacketCodec;

import java.util.Date;

/**
 * 描述:
 * 服务端登录处理
 *
 * @author caorui1
 * @create 2018-10-24 16:44
 */
public class ServerHandler {

    public void channelRead(ChannelHandlerContext channelHandlerContext,Object msg){
        ByteBuf request = (ByteBuf)msg;

        Packet packet = PacketCodec.INSTANCE.decode(request);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            // 登录校验
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }

            // 登录响应
            ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(channelHandlerContext.alloc(), loginResponsePacket);
            channelHandlerContext.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
