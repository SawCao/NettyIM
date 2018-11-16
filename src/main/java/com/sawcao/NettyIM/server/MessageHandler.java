package com.sawcao.NettyIM.server;

import com.sawcao.NettyIM.constant.KafkaConstant;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import protocol.Seesion.Session;
import protocol.packet.MessageRequestPacket;
import protocol.packet.MessageResponsePacket;
import utils.SessionUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:
 * 与客户端发送消息的处理
 *
 * @author caorui1
 * @create 2018-10-25 17:58
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public final Logger logger=LoggerFactory.getLogger(MessageHandler.class);

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private KafkaTemplate<String, Session> kafkaTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        //拿到消息发送方的session
        Session session = SessionUtil.getSession(ctx.channel());
        //将联系人添加至电话簿
        session.addphoneBook(msg.getToId());
        //使用kafka进行发送
        kafkaTemplate.send(KafkaConstant.PHONEBOOK_SAVE_KAFKA,session);
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
            System.err.println("[" + msg.getToId() + "] 不在线，发送存储下来!");
            redisTemplate.opsForValue().set(msg.getToId()+ "_" + new Date().getTime(),messageResponsePacket);
            logger.info("user offline,userId is " + msg.getToId());
        }

    }
}
