package protocol.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import protocol.Command;
import protocol.packet.*;
import utils.JSONSerializer;
import utils.Serializer;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
public class PacketCodec {
    //这是一个协议标识
    private static final int MAGIC_NUMBER = 0x12345678;
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
