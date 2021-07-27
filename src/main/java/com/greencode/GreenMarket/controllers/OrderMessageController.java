package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.config.RabbitConfig;
import com.greencode.GreenMarket.entities.Order;
import com.greencode.GreenMarket.utils.logging.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
public class OrderMessageController {

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void processOrder(Order order) {
        Log4j.log.info("User: " + order.getUser().getUserName() + " saved the order to the database: " + order.getId() + " for the amount of: " + order.getPrice());
    }
}
