package com.greencode.GreenMarket.utils.logging;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.services.ProductsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CartLoggingAspect {

    @Autowired
    private ProductsService productsService;

    @Before("execution(public String com.greencode.GreenMarket.controllers.CartController.addToCart(..))")
    public void loggingBeforeAddToCart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long productId = (Long) args[1];
        Product product = productsService.getOneById(productId);
        Log4j.log.info("The user is trying to add a product: " + product.getTitle() + " with id: " + product.getId() + "  to a cart ");
    }

    @After("execution(public String com.greencode.GreenMarket.controllers.CartController.addToCart(..))")
    public void loggingAfterAddToCart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long productId = (Long) args[1];
        Product product = productsService.getOneById(productId);
        Log4j.log.info("The user added the product: " + product.getTitle() + " with id: " + product.getId() + "  to the cart ");
    }

    @Before("execution(public String com.greencode.GreenMarket.controllers.CartController.deleteOneInCart(..))")
    public void loggingBeforeDeleteOneInCart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long productId = (Long) args[1];
        Product product = productsService.getOneById(productId);
        Log4j.log.info("The user is trying to delete the product: " + product.getTitle() + " with id: " + product.getId() + "  from the cart ");
    }

    @After("execution(public String com.greencode.GreenMarket.controllers.CartController.deleteOneInCart(..))")
    public void loggingAfterDeleteOneInCart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Long productId = (Long) args[1];
        Product product = productsService.getOneById(productId);
        Log4j.log.info("The user has deleted the product: " + product.getTitle() + " with id: " + product.getId() + "  from the cart ");
    }
}
