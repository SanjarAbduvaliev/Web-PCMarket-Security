package com.example.webpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull(message = "Ism kiritish majburiy")
    private String name;
    @NotNull(message = "Sharh kiriting")
    private String comment;
    private boolean active;
    @NotNull(message = "Categoriya haqida ma'lumot kiriting")
    private String discription;
    @NotNull(message = "Categoriyani tanlang")
    private Integer categoryId;

    public CategoryDto(String name, String comment, boolean active, String discription) {
        this.name = name;
        this.comment = comment;
        this.active = active;
        this.discription = discription;
    }
}
