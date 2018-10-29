package protocol.Seesion;

import lombok.Data;

/**
 * 描述:
 * 用户表示
 *
 * @author caorui1
 * @create 2018-10-29 16:14
 */
@Data
public class Session {
    private String id;
    private String name;

    @Override
    public String toString() {
        return id + ":" + name;
    }
}
