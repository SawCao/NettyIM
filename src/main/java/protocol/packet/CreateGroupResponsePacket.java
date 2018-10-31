package protocol.packet;

import lombok.Data;
import protocol.Command;

import java.util.List;

/**
 * 描述:
 * 群聊建立的反馈包
 *
 * @author caorui1
 * @create 2018-10-31 16:16
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return Command.CREATE_GROUP_RESPONSE;
    }
}
