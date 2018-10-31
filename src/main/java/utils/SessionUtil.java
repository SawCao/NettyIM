package utils;

import io.netty.channel.Channel;
import lombok.Data;
import protocol.Attributes;
import protocol.Seesion.Session;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 * 用户表示操作
 *
 * @author caorui1
 * @create 2018-10-29 16:15
 */
@Data
public class SessionUtil {
    private static final Map<String,Channel> icMap = new ConcurrentHashMap<>();
    private static final Map<String,Session> name2sessionMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session,Channel channel){
        icMap.put(session.getId(),channel);
        name2sessionMap.put(session.getName(),session);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            icMap.remove(getSession(channel).getId());
            name2sessionMap.remove(getSession(channel).getName());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return icMap.get(name2sessionMap.get(userId).getId());
    }

}
