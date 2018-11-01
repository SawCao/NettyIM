package protocol.packet;

import lombok.Data;
import protocol.Command;
import protocol.Seesion.Session;
import java.util.List;


@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
