package protocol.packet;

import lombok.Data;
import protocol.Command;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand(){
        return Command.LOGIN_REQUEST;
    }
}

