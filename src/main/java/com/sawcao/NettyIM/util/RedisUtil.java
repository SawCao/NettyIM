package com.sawcao.NettyIM.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import protocol.packet.MessageResponsePacket;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 描述:
 * redis帮助类
 *
 * @author caorui1
 * @create 2018-11-13 16:43
 */
public class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

    public static List<MessageResponsePacket> mgetMRP(String userName) {
        Set<String> userNameKey = redisTemplate.keys(userName + "*");
        return Arrays.asList(redisTemplate.opsForValue().multiGet(userNameKey).toArray(new MessageResponsePacket[0]));
    }

    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

}
