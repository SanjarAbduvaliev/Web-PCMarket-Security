package com.example.webpcmarket.projection;

import com.example.webpcmarket.entity.Basket;
import com.example.webpcmarket.entity.Customer;
import com.example.webpcmarket.entity.Product;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "custom",types = Customer.class)
public interface CustomerProjection {
    Integer getId();
    String getFirsName();
    String getLastName();
    String getPhoneNumber();
    Basket getBasket();
    List<Product> getProducts();
}
