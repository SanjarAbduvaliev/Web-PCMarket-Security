package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Customer;
import com.example.webpcmarket.projection.CustomerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "customers",excerptProjection = CustomerProjection.class)
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
