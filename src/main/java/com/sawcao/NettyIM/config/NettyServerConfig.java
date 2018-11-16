package com.sawcao.NettyIM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import com.sawcao.NettyIM.server.NettyServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 描述:
 * spring启动netty服务端配置
 *
 * @author caorui1
 * @create 2018-11-05 14:01
 */
@Configuration
public class NettyServerConfig {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public NettyServer initServer(){
        //连接本地的 Redis 服务
        redisTemplate.opsForValue().set("testTemplate1","test1");
        redisTemplate.opsForValue().set("testTemplate2","test2");
        redisTemplate.opsForValue().set("testTemplate3","test3");

        List<String> strings = new ArrayList<>();
        strings.add("testTemplate" + "_*");
        System.out.println("存储成功，" + redisTemplate.opsForValue().get("testTemplate" + "*"));
        Set<String> strings1 = redisTemplate.keys("testTemplate" + "*");

        System.out.println("存储成功，" + redisTemplate.opsForValue().multiGet(strings1));
        return new NettyServer();
    }
}
