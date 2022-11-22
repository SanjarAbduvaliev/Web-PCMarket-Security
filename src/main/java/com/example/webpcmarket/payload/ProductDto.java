package com.example.webpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotNull(message = "Ismni kiriting")
    private String name;
    @NotNull(message = "Ma'lumot kiriting")
    private String comment;
    @NotNull(message = "Brendini kiriting")
    private String brand;
    @NotNull(message = "Narxini kiriting")
    private Double price;
    private boolean active;
    private Integer attachmentPhotoId;
    @NotNull(message = "Kategoriyani tanlang")
    private Integer categoryId;
    @NotNull(message = "Xususiyatlarini kiriting")
    private  Integer characterId;
    @NotNull(message = "O'chov birligini tanlang")
    private  Integer measurmentId;
    private Integer stars;
}
