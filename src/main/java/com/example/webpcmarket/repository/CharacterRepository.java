package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Characters;
import com.example.webpcmarket.projection.CharacterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "character",excerptProjection = CharacterProjection.class)
public interface CharacterRepository extends JpaRepository<Characters,Integer> {
    boolean existsByName(String name);
}
