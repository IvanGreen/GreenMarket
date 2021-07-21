package com.greencode.GreenMarket.utils;

import com.greencode.GreenMarket.entities.OrderProduct;
import com.greencode.GreenMarket.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<OrderProduct> products;
    private Double totalCost;

    public ShoppingCart() {
        products = new ArrayList<>();
        totalCost = 0.0;
    }

    public void add(Product product){
        OrderProduct orderProduct = findOrderFromProduct(product);
        if (orderProduct == null) {
            orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setProductPrice(product.getPrice());
            orderProduct.setQuantity(0L);
            orderProduct.setId(0L);
            orderProduct.setTotalPrice(0.0);
            products.add(orderProduct);
        }
        orderProduct.setQuantity(orderProduct.getQuantity() + 1);
        recalculate();
    }

    public void setQuantity(Product product, Long quantity){
        OrderProduct orderProduct = findOrderFromProduct(product);
        if (orderProduct == null) {
            return;
        }
        orderProduct.setQuantity(quantity);
        recalculate();
    }

    public void remove(Product product) {
        OrderProduct orderProduct = findOrderFromProduct(product);
        if (orderProduct == null) {
            return;
        }
        if (orderProduct.getQuantity() > 1){
            orderProduct.setQuantity(orderProduct.getQuantity() - 1);
        } else {
            products.remove(orderProduct);
        }
        recalculate();
    }

    private void recalculate(){
        totalCost = 0.0;
        for (OrderProduct o : products) {
            o.setTotalPrice(o.getQuantity() * o.getProduct().getPrice());
            totalCost += o.getTotalPrice();
        }
    }

    private OrderProduct findOrderFromProduct(Product product) {
        return products.stream().filter(o -> o.getProduct().getId().equals(product.getId())).findFirst().orElse(null);
    }
}
