package utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import protocol.Attributes;



/**
 * 描述:
 * 登录帮助类
 *
 * @author caorui1
 * @create 2018-10-25 10:58
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
