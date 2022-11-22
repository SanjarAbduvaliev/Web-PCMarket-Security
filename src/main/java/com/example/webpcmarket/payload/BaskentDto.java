package com.example.webpcmarket.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BaskentDto {
    private List<Integer> productId;

    private Integer baskentId;
}
