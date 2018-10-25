package protocol.packet;

import lombok.Data;
import protocol.Command;

/**
 * 描述:
 * 收发消息包
 *
 * @author caorui1
 * @create 2018-10-25 10:33
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
