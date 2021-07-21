package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.config.RabbitConfig;
import com.greencode.GreenMarket.entities.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class OrderMessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendLogCreateOrder(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, order);
    }
}
