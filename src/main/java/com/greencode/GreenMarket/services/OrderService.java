package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.entities.Order;
import com.greencode.GreenMarket.entities.OrderProduct;
import com.greencode.GreenMarket.entities.User;
import com.greencode.GreenMarket.repositorties.OrderRepository;
import com.greencode.GreenMarket.utils.ShoppingCart;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Transactional
    public Order makeOrder(ShoppingCart cart, User user) {
        Order order = new Order();
        order.setId(0L);
        order.setUser(user);
        order.setOrderStatus(orderStatusService.getStatusById(1L));
        order.setPrice(cart.getTotalCost());
        order.setOrderProducts(new ArrayList<>(cart.getProducts()));
        for (OrderProduct o : cart.getProducts()) {
            o.setOrder(order);
        }
        return order;
    }

    public List<Order> getAllOrders(){ return  (List<Order>) orderRepository.findAll(); }

    public Order findById(Long id) { return orderRepository.findById(id).get(); }

    public void deleteById(Long id) { orderRepository.deleteById(id); }

    public Order saveOrder(Order order) {
        Order orderOut = orderRepository.save(order);
        orderOut.setConfirmed(true);
        return orderOut;
    }

    public Order changeOrderStatus(Order order, Long statusId) {
        order.setOrderStatus(orderStatusService.getStatusById(statusId));
        return saveOrder(order);
    }
}
