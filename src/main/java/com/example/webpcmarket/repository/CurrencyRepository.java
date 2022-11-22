package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Currency;
import com.example.webpcmarket.projection.CurrencyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "currency",excerptProjection = CurrencyProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
