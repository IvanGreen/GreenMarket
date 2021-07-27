package com.greencode.GreenMarket.JUnit;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.utils.ShoppingCart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

    ShoppingCart shoppingCart;
    Product firstProduct;
    Product secondProduct;

    @Before
    public void initCart(){
        shoppingCart = new ShoppingCart();

        firstProduct = new Product();
        firstProduct.setId(1L);
        firstProduct.setPrice(100);
        firstProduct.setTitle("First Product");

        secondProduct = new Product();
        secondProduct.setId(2L);
        secondProduct.setPrice(200);
        secondProduct.setTitle("Second Product");
    }

    @Test
    public void addCartTitleTest(){
        shoppingCart.add(firstProduct);
        Assert.assertEquals(firstProduct.getTitle(),shoppingCart.getProducts().get(Math.toIntExact(firstProduct.getId()) - 1).getProduct().getTitle());
    }

    @Test
    public void addCartRecalculatePriceWithEqualsProductsTest(){
        shoppingCart.add(firstProduct);
        shoppingCart.add(firstProduct);
        Double costTwoItems = firstProduct.getPrice() * 2;
        Assert.assertEquals(costTwoItems,shoppingCart.getTotalCost());
    }

    @Test
    public void addCartRecalculateWithDifferentProductsTest(){
        shoppingCart.add(firstProduct);
        shoppingCart.add(secondProduct);
        Double costTwoItems = firstProduct.getPrice() + secondProduct.getPrice();
        Assert.assertEquals(costTwoItems,shoppingCart.getTotalCost());
    }

    @Test
    public void removeCartTest(){
        shoppingCart.add(firstProduct);
        shoppingCart.add(secondProduct);
        int cartSize = shoppingCart.getProducts().size();
        shoppingCart.remove(firstProduct);
        Assert.assertEquals(cartSize - 1,shoppingCart.getProducts().size());
    }
}
