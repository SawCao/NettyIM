package protocol.packet;

import lombok.Data;
import protocol.Command;

/**
 * 描述:
 * 登录返回信息
 *
 * @author caorui1
 * @create 2018-10-24 16:48
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    private String id;

    private String name;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
