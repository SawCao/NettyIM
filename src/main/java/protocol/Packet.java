package protocol;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();

}
