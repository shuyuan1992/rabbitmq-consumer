/**
 * @Copyright (c) 2018/8/27, Lianjia Group All Rights Reserved.
 */
package com.wilson.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * hello consumer
 * @author wilson wei
 * @version 1.0
 * @since 2018/8/27 16:26
 */
@Component
@Slf4j
public class HelloConsumer {

    @RabbitListener(queues = "hello-queue")
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            log.info("msg: {}", message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
