package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {

    @Autowired
    private ProductsService productsService;

    public ShoppingCart getCurrentCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart",cart);
        }
        return cart;
    }

    public void resetCart(HttpSession session) { session.removeAttribute("cart"); }

    public void addToCart(HttpSession session, Long productId) {
        Product product = productsService.getOneById(productId);
        addToCart(session,product);
    }

    public void addToCart(HttpSession session, Product product) {
        ShoppingCart cart = getCurrentCart(session);
        cart.add(product);
    }

    public void removeFromCart(HttpSession session, Long productId) {
        Product product = productsService.getOneById(productId);
        removeFromCart(session,product);
    }

    public void removeFromCart(HttpSession session,Product product) {
        ShoppingCart cart = getCurrentCart(session);
        cart.remove(product);
    }

    public void setProductCount(HttpSession session, Long productId, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        Product product = productsService.getOneById(productId);
        cart.setQuantity(product,quantity);
    }

    public void setProductCount(HttpSession session, Product product, Long quantity) {
        ShoppingCart cart = getCurrentCart(session);
        cart.setQuantity(product,quantity);
    }

    public double getTotalCost(HttpSession session) {
        return getCurrentCart(session).getTotalCost();
    }
}
