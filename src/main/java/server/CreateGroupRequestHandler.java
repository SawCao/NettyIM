package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import protocol.packet.CreateGroupRequestPacket;
import protocol.packet.CreateGroupResponsePacket;
import utils.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述:
 * 处理建群请求
 *
 * @author caorui1
 * @create 2018-10-31 16:07
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList  = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        //创建一个channelGroup
        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());

        //筛选出要加入群聊的用户名单
        for(String userId:userIdList){
            Channel channel = SessionUtil.getChannel(userId);
            if(channel != null){
                channels.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getName());
            }
        }

        // 3. 创建群聊创建结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId("GROUP" + UUID.randomUUID().toString().split("-")[0]);
        createGroupResponsePacket.setUserNameList(userNameList);

    }



}
