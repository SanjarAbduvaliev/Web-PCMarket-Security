package com.example.webpcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firsName;

    private String lastName;

    private String phoneNumber;

    @OneToOne
    private Basket basket;

    @OneToMany
    private List<Product> products;
}
