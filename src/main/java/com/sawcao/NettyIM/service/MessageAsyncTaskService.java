package com.sawcao.NettyIM.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * 发送消息时用户不在线的重试机制
 *
 * @author caorui1
 * @create 2018-11-13 10:59
 */
@Service
public class MessageAsyncTaskService {
    @Async
    public void sendMessage(){

    }
}
