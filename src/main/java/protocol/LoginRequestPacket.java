package protocol;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
@Data
public class LoginRequestPacket extends Packet implements Command{
    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand(){
        return LOGIN_REQUEST;
    }
}

