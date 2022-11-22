package com.example.webpcmarket.projection;

import com.example.webpcmarket.entity.Currency;
import com.example.webpcmarket.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "currency",types = Currency.class)
public interface CurrencyProjection {
    Integer getId();
    String getName();
}
