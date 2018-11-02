package protocol.code;

import client.GroupMessageResponseHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import protocol.Command;
import protocol.packet.*;
import server.GroupMessageRequestHandler;
import utils.JSONSerializer;
import utils.Serializer;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
public class PacketCodec {
    //这是一个协议标识
    public static final int MAGIC_NUMBER = 0x12345678;
    //实现单例调用
    public static final PacketCodec INSTANCE = new PacketCodec();

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command){
        if(command == Command.LOGIN_REQUEST)
            return LoginRequestPacket.class;
        if(command == Command.LOGIN_RESPONSE)
            return LoginResponsePacket.class;
        if(command == Command.MESSAGE_REQUEST)
            return MessageRequestPacket.class;
        if(command == Command.MESSAGE_RESPONSE)
            return MessageResponsePacket.class;
        if(command == Command.CREATE_GROUP_REQUEST)
            return CreateGroupRequestPacket.class;
        if(command == Command.CREATE_GROUP_RESPONSE)
            return CreateGroupResponsePacket.class;
        if(command == Command.LIST_GROUP_MEMBERS_REQUEST)
            return ListGroupMembersRequestPacket.class;
        if(command == Command.LIST_GROUP_MEMBERS_RESPONSE)
            return ListGroupMembersResponsePacket.class;
        if (command == Command.JOIN_GROUP_REQUEST)
            return JoinGroupRequestPacket.class;
        if (command == Command.JOIN_GROUP_RESPONSE)
            return JoinGroupResponsePacket.class;
        if (command == Command.QUIT_GROUP_REQUEST)
            return QuitGroupRequestPacket.class;
        if (command == Command.QUIT_GROUP_RESPONSE)
            return QuitGroupResponsePacket.class;
        if (command == Command.GROUP_MESSAGE_REQUEST)
            return GroupMessageRequestPacket.class;
        if (command == Command.GROUP_MESSAGE_RESPONSE)
            return GroupMessageResponsePacket.class;
        if(command == Command.HEARTBEAT_REQUEST)
            return HeartBeatRequestPacket.class;
        if(command == Command.HEARTBEAT_RESPONSE)
            return HeartBeatResponsePacket.class;
        return null;
    }

   /**
    * @author caorui
    * @return utils.Serializer
    * @description:  目前只有json序列化一种方式
    */
    private Serializer getSerializer(byte serializeAlgorithm){
        return new JSONSerializer();
    }
}
