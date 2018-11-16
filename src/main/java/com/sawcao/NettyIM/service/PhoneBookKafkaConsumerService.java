package com.sawcao.NettyIM.service;

import com.sawcao.NettyIM.constant.KafkaConstant;
import com.sawcao.NettyIM.util.RedisUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import protocol.Seesion.Session;

import java.util.Optional;

/**
 * 描述:
 * 联系人相关的kafka消费
 *
 * @author caorui1
 * @create 2018-11-16 16:09
 */
public class PhoneBookKafkaConsumerService {
    @KafkaListener(topics = KafkaConstant.PHONEBOOK_SAVE_KAFKA)
    private void kafkaConsumer(ConsumerRecord<?, ?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            Session session = (Session) kafkaMessage.get();
            //在redis中存储用户联系簿
            RedisUtil.set(session.getId(),session.getPhoneBook());
        }
    }
}
