package protocol.packet;

import lombok.Data;
import protocol.Command;

/**
 * 描述:
 * 消息接收包
 *
 * @author caorui1
 * @create 2018-10-25 10:35
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    //消息发送方的id
    private String fromId;

    //消息发送方的name
    private String fromName;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
