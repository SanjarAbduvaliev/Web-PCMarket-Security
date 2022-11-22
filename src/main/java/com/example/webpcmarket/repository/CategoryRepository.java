package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByParrentCategory_Name(String parrentCategory_name);

    boolean existsByName(String name);
    Category findByParrentCategory_Id(Integer parrentCategory_id);
}
