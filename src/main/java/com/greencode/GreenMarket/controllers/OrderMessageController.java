package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.config.RabbitConfig;
import com.greencode.GreenMarket.entities.Order;
import com.greencode.GreenMarket.services.OrderService;
import com.greencode.GreenMarket.utils.logging.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderMessageController {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void processOrder(Order order) {
        Log4j.log.info("User: " + order.getUser().getUserName() + " saved the order to the database: " + order.getId() + " for the amount of: " + order.getPrice());
    }
}
