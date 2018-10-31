package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.Seesion.Session;
import protocol.packet.*;
import protocol.code.PacketCodec;
import utils.LoginUtil;
import utils.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * 描述:
 * 服务端登录处理
 *
 * @author caorui1
 * @create 2018-10-24 16:44
 */
public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            //创建随机ID值并返回给客户端
            String id = randomUserId();
            loginResponsePacket.setId(id);
            loginResponsePacket.setName(msg.getUserName());
            System.out.println(new Date() + "用户:" + msg.getUserName() + ": 登录成功!");
            SessionUtil.bindSession(new Session(id,msg.getUserName()),ctx.channel());

        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){

    }
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
