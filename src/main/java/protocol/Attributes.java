package protocol;

import io.netty.util.AttributeKey;
import protocol.Seesion.Session;

/**
 * 描述:
 * 状态码
 *
 * @author caorui1
 * @create 2018-10-25 10:41
 */
public interface Attributes {
    /**
     * AttributeKey是netty自带类
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
