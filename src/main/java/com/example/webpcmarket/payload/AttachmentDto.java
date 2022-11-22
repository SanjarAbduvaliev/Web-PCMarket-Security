package com.example.webpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AttachmentDto {
    @NotNull(message = "Satr to'dirilishi majburiy")
    private String name;
    @NotNull(message = "Satr to'dirilishi majburiy")
    private String contentType;
    @NotNull(message = "Satr to'dirilishi majburiy")
    private Long size;
}
