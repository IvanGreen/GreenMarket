package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.services.ProductsService;
import com.greencode.GreenMarket.services.ShoppingCartService;
import com.greencode.GreenMarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/show")
    public String cartPage(Model model, HttpSession httpSession) {
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart",cart);
        return "cart-page";
    }

    @GetMapping("/add")
    public String addToCart(Model model, @RequestParam(value = "id") Long id, HttpServletRequest httpServletRequest) {
        shoppingCartService.addToCart(httpServletRequest.getSession(),id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/delete")
    public String deleteOneInCart(Model model,
                                  @RequestParam(value = "id") Long id,
                                  HttpSession httpSession) {
        shoppingCartService.removeFromCart(httpSession,id);
        ShoppingCart cart = shoppingCartService.getCurrentCart(httpSession);
        model.addAttribute("cart",cart);
        return "cart-page";
    }

}
