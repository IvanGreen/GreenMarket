package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.entities.OrderStatus;
import com.greencode.GreenMarket.repositorties.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatus getStatusById(Long id) {
        return orderStatusRepository.findById(id).orElse(null);
    }
}
