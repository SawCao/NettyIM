package protocol.packet;

import lombok.Data;
import protocol.Command;

import java.util.List;

/**
 * 描述:
 * 创建群聊请求包
 *
 * @author caorui1
 * @create 2018-10-31 16:05
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
