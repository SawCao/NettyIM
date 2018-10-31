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

    private String toId;
    private String message;

    public MessageRequestPacket(){

    }
    public MessageRequestPacket(String toId, String msg) {
        this.toId = toId;
        this.message = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
