package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.entities.DeliveryAddress;
import com.greencode.GreenMarket.entities.Order;
import com.greencode.GreenMarket.entities.User;
import com.greencode.GreenMarket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMessageSender orderMessageSender;

    @GetMapping("/fill")
    public String orderFill(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()),user);
        List<DeliveryAddress> deliveryAddress = deliveryAddressService.getUserAddresses(user.getId());
        model.addAttribute("order",order);
        model.addAttribute("deliveryAddresses",deliveryAddress);
        return "order-filler";
    }

    @PostMapping("/confirm")
    public String orderConfirm(Model model,
                               HttpServletRequest httpServletRequest,
                               @ModelAttribute(name = "order") Order orderFromFrontend,
                               Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order order = orderService.makeOrder(shoppingCartService.getCurrentCart(httpServletRequest.getSession()), user);
        order.setDeliveryAddress(orderFromFrontend.getDeliveryAddress());
        order.setPhoneNumber(orderFromFrontend.getPhoneNumber());
        order.setDeliveryDate(LocalDateTime.now().plusDays(2));
        order.setDeliveryPrice(300.00);
        order = orderService.saveOrder(order);
        orderMessageSender.sendLogCreateOrder(order);
        model.addAttribute("order", order);
        return "order-filler";
    }

    @GetMapping("/result/{id}")
    public String orderConfirm(Model model, @PathVariable(name = "id") Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = userService.findByUserName(principal.getName());
        Order confirmedOrder = orderService.findById(id);
        if (!user.getId().equals(confirmedOrder.getUser().getId())) {
            return "redirect:/";
        }
        model.addAttribute("order", confirmedOrder);
        return "order-result";
    }


}
