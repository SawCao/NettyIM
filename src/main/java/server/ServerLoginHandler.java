package server;

import com.sawcao.NettyIM.util.RedisUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;
import org.springframework.util.Assert;
import protocol.Seesion.Session;
import protocol.packet.*;
import protocol.code.PacketCodec;
import utils.LoginUtil;
import utils.SessionUtil;

import java.io.Serializable;
import java.util.*;

/**
 * 描述:
 * 服务端登录处理
 *
 * @author caorui1
 * @create 2018-10-24 16:44
 */
public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            String id = msg.getUserId();
            if(id == null)
            //创建随机ID值并返回给客户端
                id = randomUserId();
            loginResponsePacket.setId(id);
            loginResponsePacket.setName(msg.getUserName());
            System.out.println(new Date() + "用户:" + msg.getUserName() + ": 登录成功!");
            SessionUtil.bindSession(new Session(id,msg.getUserName()),ctx.channel());
            //获取因为用户不在线而未发送的单人消息
            List<MessageResponsePacket> messageResponsePackets = RedisUtil.mgetMRP(id);
            //将存储下来的离线消息发送给用户
            messageResponsePackets.forEach(e->ctx.channel().writeAndFlush(e));

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
