package com.example.webpcmarket.projection;

import com.example.webpcmarket.entity.Characters;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Characters.class,name = "character")
public interface CharacterProjection {
}
