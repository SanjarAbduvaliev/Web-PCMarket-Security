package com.example.webpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class AmountDto {
    @NotNull(message = "Ism Familiya kiritilishi shart")
    private String fullName;
    @NotNull(message = "Telefon raqamingizmi kiring")
    private String phoneNumber;
    private List<Integer> productId;
}
