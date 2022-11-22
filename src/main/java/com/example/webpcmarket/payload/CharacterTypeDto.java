package com.example.webpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CharacterTypeDto {
    @NotNull(message = "Maydon bo'sh bo'lishi mumkin emas")
    private String feature;
    @NotNull(message = "Tanlang")
    private Integer characterId;
}
