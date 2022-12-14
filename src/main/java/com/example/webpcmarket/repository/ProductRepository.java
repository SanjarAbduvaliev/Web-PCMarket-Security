package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory_Id(Integer category_id);
}
