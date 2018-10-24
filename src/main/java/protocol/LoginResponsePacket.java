package protocol;

import lombok.Data;

/**
 * 描述:
 * 登录返回信息
 *
 * @author caorui1
 * @create 2018-10-24 16:48
 */
@Data
public class LoginResponsePacket extends Packet implements Command{
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
